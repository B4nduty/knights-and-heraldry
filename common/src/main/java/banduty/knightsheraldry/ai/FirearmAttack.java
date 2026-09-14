package banduty.knightsheraldry.ai;

import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.stoneycore.combat.mechanics.AttackSpeedHelper;
import banduty.stoneycore.combat.weapon.SCRangeWeaponUtil;
import com.google.common.collect.ImmutableMap;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/**
 * Per difficulty (world gamemode, not effective difficulty):
 * <p>
 * - Easy: freezes while charging, 8-block range, retreats 5, never melees.
 * <p>
 * - Normal: slow-drifts while charging, 12-block range, retreats 6, melees under 3 blocks.
 * <p>
 * - Hard: 20-block range; if still pressured between melee range and max range
 *   post-shot it retreats to 16 instead of 6; after its first shot, if &lt;=2
 *   other piglins are nearby it commits to melee for the rest of the fight.
 * <p>
 * Accuracy is separate: it scales with regional/effective difficulty
 * (getInaccuracyDegrees), not the gamemode.
 */
public class FirearmAttack extends Behavior<Piglin> {

    private enum State { CHARGING, RETREATING }

    private static final int MIN_RELOAD_TICKS = 20;
    private static final int IDLE_RELOAD_CYCLE_TICKS = MIN_RELOAD_TICKS * 3;
    private static final int MAX_RUN_TICKS = 999999;

    private static final double HARD_PRESSURE_RETREAT_DISTANCE = 16.0;
    private static final double APPROACH_SPEED = 1.0;
    private static final double SLOW_RECHARGE_SPEED = 0.4;
    private static final double RETREAT_SPEED = 1.0;
    private static final double RECHARGE_IDEAL_RANGE_FACTOR = 0.75;
    private static final double RECHARGE_DRIFT_TOLERANCE = 1.5;
    private static final int RETREAT_TIMEOUT_TICKS = 60;
    private static final double ALLY_SEARCH_RADIUS = 16.0;
    private static final int LONE_WOLF_ALLY_THRESHOLD = 2;

    private static final float MAX_INACCURACY_DEGREES = 12.0F;
    private static final float MIN_INACCURACY_DEGREES = 2.0F;
    private static final float MAX_EFFECTIVE_DIFFICULTY = 6.75F;

    private static final SCRangeWeaponUtil.WeaponState RELOADING =
            new SCRangeWeaponUtil.WeaponState(true, false, false);
    private static final SCRangeWeaponUtil.WeaponState FIRED =
            new SCRangeWeaponUtil.WeaponState(false, false, true);

    private State state = State.CHARGING;
    private int chargeTicks;
    private int retreatTicks;
    private boolean charged;
    private boolean firedFirstShot;
    private boolean meleeCommitted;

    public FirearmAttack() {
        super(ImmutableMap.of(), MAX_RUN_TICKS);
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, Piglin piglin) {
        return isHoldingFirearm(piglin);
    }

    @Override
    protected boolean canStillUse(ServerLevel level, Piglin piglin, long gameTime) {
        return isHoldingFirearm(piglin);
    }

    @Override
    protected void start(ServerLevel level, Piglin piglin, long gameTime) {
        retreatTicks = 0;
        firedFirstShot = false;
        setMeleeCommitted(piglin, false);
        beginCharging(piglin.getMainHandItem());
    }

    @Override
    protected void tick(ServerLevel level, Piglin piglin, long gameTime) {
        ItemStack weapon = piglin.getMainHandItem();
        if (!isFirearm(weapon)) return;

        LivingEntity target = piglin.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
        if (target == null) {
            piglin.getNavigation().stop();
            tickIdleReload(weapon);
            firedFirstShot = false;
            setMeleeCommitted(piglin, false);
            return;
        }

        FirearmDifficultyProfile profile = FirearmDifficultyProfile.fromLevel(level);
        double distance = piglin.distanceTo(target);

        if (meleeCommitted || (profile.meleeSwitchRange > 0 && distance <= profile.meleeSwitchRange)) {
            // Hand off to melee
            resetChargeState();
            SCRangeWeaponUtil.setWeaponState(weapon, SCRangeWeaponUtil.WeaponState.idle());
            return;
        }

        piglin.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (state == State.CHARGING) {
            tickCharging(level, piglin, target, weapon, profile, distance);
        } else {
            tickRetreat(piglin, target, weapon, profile, distance);
        }
    }

    private void tickIdleReload(ItemStack weapon) {
        if (++chargeTicks >= IDLE_RELOAD_CYCLE_TICKS) {
            chargeTicks = 0;
            SCRangeWeaponUtil.setWeaponState(weapon, RELOADING);
        }
    }

    private void tickCharging(ServerLevel level, Piglin piglin, LivingEntity target, ItemStack weapon,
                              FirearmDifficultyProfile profile, double distance) {
        if (distance > profile.maxShootRange) {
            moveToDistanceFromTarget(piglin, target, profile.maxShootRange - 1.0, APPROACH_SPEED);
            return;
        }

        double idealDistance = profile.maxShootRange * RECHARGE_IDEAL_RANGE_FACTOR;
        if (profile.moveWhileRecharging && Math.abs(distance - idealDistance) > RECHARGE_DRIFT_TOLERANCE) {
            moveToDistanceFromTarget(piglin, target, idealDistance, SLOW_RECHARGE_SPEED);
        } else {
            piglin.getNavigation().stop();
        }

        if (!charged) {
            int reloadTime = Math.max(MIN_RELOAD_TICKS, AttackSpeedHelper.getReloadSpeedModified(piglin, weapon));
            if (++chargeTicks < reloadTime) return;
            charged = true;
            SCRangeWeaponUtil.setWeaponState(weapon, SCRangeWeaponUtil.WeaponState.charged());
        }

        if (!piglin.hasLineOfSight(target)) return;

        fire(level, piglin, weapon, target);
        resetChargeState();
        retreatTicks = 0;
        state = State.RETREATING;

        if (!firedFirstShot) {
            firedFirstShot = true;
            boolean commit = profile.extendedRetreatOnPressure && isLoneWolf(level, piglin);
            meleeCommitted = commit;
            setMeleeCommitted(piglin, commit);
        }
    }

    private void tickRetreat(Piglin piglin, LivingEntity target, ItemStack weapon,
                             FirearmDifficultyProfile profile, double distance) {
        boolean pressured = profile.extendedRetreatOnPressure
                && distance >= profile.meleeSwitchRange
                && distance <= profile.maxShootRange;
        double retreatGoal = pressured ? HARD_PRESSURE_RETREAT_DISTANCE : profile.retreatDistance;

        if (distance >= retreatGoal || ++retreatTicks > RETREAT_TIMEOUT_TICKS) {
            piglin.getNavigation().stop();
            beginCharging(weapon);
            return;
        }

        moveToDistanceFromTarget(piglin, target, retreatGoal, RETREAT_SPEED);
    }

    private void beginCharging(ItemStack weapon) {
        resetChargeState();
        if (isFirearm(weapon)) {
            SCRangeWeaponUtil.setWeaponState(weapon, RELOADING);
        }
    }

    private void resetChargeState() {
        state = State.CHARGING;
        chargeTicks = 0;
        charged = false;
    }

    private static boolean isLoneWolf(ServerLevel level, Piglin piglin) {
        AABB box = piglin.getBoundingBox().inflate(ALLY_SEARCH_RADIUS);
        int allies = 0;
        for (Piglin ignored : level.getEntitiesOfClass(Piglin.class, box, o -> o != piglin && o.isAlive())) {
            if (++allies > LONE_WOLF_ALLY_THRESHOLD) return false;
        }
        return true;
    }

    private static void moveToDistanceFromTarget(Piglin piglin, LivingEntity target,
                                                 double desiredDistance, double speed) {
        Vec3 targetPos = target.position();
        Vec3 away = piglin.position().subtract(targetPos);
        if (away.lengthSqr() < 1.0E-4) {
            RandomSource random = piglin.getRandom();
            away = new Vec3(random.nextDouble() - 0.5, 0.0, random.nextDouble() - 0.5);
        }
        Vec3 destination = targetPos.add(away.normalize().scale(desiredDistance));
        piglin.getNavigation().moveTo(destination.x, destination.y, destination.z, speed);
    }

    private static void fire(ServerLevel level, Piglin piglin, ItemStack weapon, LivingEntity target) {
        piglin.lookAt(EntityAnchorArgument.Anchor.EYES, target.position());

        RandomSource random = piglin.getRandom();
        float inaccuracy = getInaccuracyDegrees(level, piglin);
        piglin.setYRot(piglin.getYRot() + (random.nextFloat() - 0.5F) * inaccuracy);
        piglin.setXRot(Mth.clamp(piglin.getXRot() + (random.nextFloat() - 0.5F) * inaccuracy, -90.0F, 90.0F));

        piglin.swing(InteractionHand.MAIN_HAND);
        SCRangeWeaponUtil.shootBullet(level, weapon, piglin);
        SCRangeWeaponUtil.setWeaponState(weapon, FIRED);

        if (Boolean.TRUE.equals(weapon.get(KHDataComponents.PIGLIN.get()))) {
            weapon.setDamageValue(Math.max(0, weapon.getDamageValue() - 1));
        }
    }

    private static float getInaccuracyDegrees(ServerLevel level, Piglin piglin) {
        float effective = level.getCurrentDifficultyAt(piglin.blockPosition()).getEffectiveDifficulty();
        return Mth.lerp(Mth.clamp(effective / MAX_EFFECTIVE_DIFFICULTY, 0.0F, 1.0F),
                MAX_INACCURACY_DEGREES, MIN_INACCURACY_DEGREES);
    }

    @Override
    protected void stop(ServerLevel level, Piglin piglin, long gameTime) {
        ItemStack weapon = piglin.getMainHandItem();
        if (isFirearm(weapon)) {
            SCRangeWeaponUtil.setWeaponState(weapon, SCRangeWeaponUtil.WeaponState.idle());
        }
    }

    public static boolean isFirearm(ItemStack stack) {
        return stack.is(KHItems.ARQUEBUS.get()) || stack.is(KHItems.HANDGONNE.get());
    }

    public static boolean isHoldingFirearm(LivingEntity entity) {
        return isFirearm(entity.getMainHandItem());
    }

    public static boolean isInMeleeRange(Mob mob) {
        if (!isHoldingFirearm(mob)) return false;

        double meleeRange = FirearmDifficultyProfile.fromLevel(mob.level()).meleeSwitchRange;
        if (meleeRange <= 0) return false;

        LivingEntity target = mob.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
        return target != null && mob.distanceToSqr(target) <= meleeRange * meleeRange;
    }

    public static boolean isMeleeCommitted(LivingEntity entity) {
        return entity instanceof FirearmCommitmentHolder holder && holder.knightsheraldry$isMeleeCommitted();
    }

    private static void setMeleeCommitted(Piglin piglin, boolean committed) {
        if (piglin instanceof FirearmCommitmentHolder holder) {
            holder.knightsheraldry$setMeleeCommitted(committed);
        }
    }
}