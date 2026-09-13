package banduty.knightsheraldry.ai;

import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.stoneycore.combat.mechanics.AttackSpeedHelper;
import banduty.stoneycore.combat.weapon.SCRangeWeaponUtil;
import com.google.common.collect.ImmutableMap;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

/**
 * Per difficulty (world gamemode, not effective difficulty):
 * - Easy: freezes while charging, 8-block range, retreats 5, never melees.
 * - Normal: slow-drifts while charging, 10-block range, retreats 6, melees under 4 blocks.
 * - Hard: same as Normal but 12-block range; if still pressured at 4-12 blocks
 *   post-shot it retreats to 16 instead of 6; after its first shot, if <=2
 *   other piglins are nearby it commits to melee for the rest of the fight.
 *
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
    private static final int RETREAT_TIMEOUT_TICKS = 60;
    private static final double ALLY_SEARCH_RADIUS = 16.0;
    private static final int LONE_WOLF_ALLY_THRESHOLD = 2;

    private static final float MAX_INACCURACY_DEGREES = 12.0F;
    private static final float MIN_INACCURACY_DEGREES = 2.0F;
    private static final float MAX_EFFECTIVE_DIFFICULTY = 6.75F;

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
        state = State.CHARGING;
        chargeTicks = 0;
        retreatTicks = 0;
        charged = false;
        firedFirstShot = false;
        meleeCommitted = false;
        ItemStack weapon = piglin.getMainHandItem();
        if (isFirearm(weapon)) {
            SCRangeWeaponUtil.setWeaponState(weapon, new SCRangeWeaponUtil.WeaponState(true, false, false));
        }
    }

    @Override
    protected void tick(ServerLevel level, Piglin piglin, long gameTime) {
        ItemStack weapon = piglin.getMainHandItem();
        if (!isFirearm(weapon)) return;

        LivingEntity target = piglin.getBrain()
                .getMemory(MemoryModuleType.ATTACK_TARGET)
                .orElse(null);
        FirearmDifficultyProfile profile = FirearmDifficultyProfile.fromLevel(level);

        if (target == null) {
            piglin.getNavigation().stop();
            tickIdleReload(weapon);
            firedFirstShot = false;
            meleeCommitted = false;
            return;
        }

        double distance = piglin.distanceTo(target);

        boolean naturalMeleeRange = profile.meleeSwitchRange > 0 && distance <= profile.meleeSwitchRange;
        if (naturalMeleeRange || meleeCommitted) {
            state = State.CHARGING;
            chargeTicks = 0;
            charged = false;
            SCRangeWeaponUtil.setWeaponState(weapon, SCRangeWeaponUtil.WeaponState.idle());
            return;
        }

        piglin.getLookControl().setLookAt(target, 30.0F, 30.0F);

        switch (state) {
            case CHARGING -> tickCharging(level, piglin, target, weapon, profile, distance);
            case RETREATING -> tickRetreat(piglin, target, profile, distance);
        }
    }

    private void tickIdleReload(ItemStack weapon) {
        chargeTicks++;
        if (chargeTicks >= IDLE_RELOAD_CYCLE_TICKS) {
            chargeTicks = 0;
            SCRangeWeaponUtil.setWeaponState(weapon, new SCRangeWeaponUtil.WeaponState(true, false, false));
        }
    }

    private void tickCharging(ServerLevel level, Piglin piglin, LivingEntity target, ItemStack weapon,
                              FirearmDifficultyProfile profile, double distance) {
        if (distance > profile.maxShootRange) {
            moveToDistanceFromTarget(piglin, target, profile.maxShootRange - 1.0, APPROACH_SPEED);
            return;
        }

        if (profile.moveWhileRecharging) {
            double idealDistance = profile.maxShootRange * 0.75;
            if (Math.abs(distance - idealDistance) > 1.5) {
                moveToDistanceFromTarget(piglin, target, idealDistance, SLOW_RECHARGE_SPEED);
            } else {
                piglin.getNavigation().stop();
            }
        } else {
            piglin.getNavigation().stop();
        }

        if (!charged) {
            chargeTicks++;
            int reloadTime = Math.max(MIN_RELOAD_TICKS, AttackSpeedHelper.getReloadSpeedModified(piglin, weapon));
            if (chargeTicks < reloadTime) return;

            charged = true;
            SCRangeWeaponUtil.setWeaponState(weapon, SCRangeWeaponUtil.WeaponState.charged());
        }

        if (!piglin.hasLineOfSight(target)) {
            return;
        }

        fire(level, piglin, weapon, target);
        charged = false;
        chargeTicks = 0;
        retreatTicks = 0;
        state = State.RETREATING;

        if (!firedFirstShot) {
            firedFirstShot = true;
            if (profile == FirearmDifficultyProfile.HARD && countNearbyPiglins(level, piglin) <= LONE_WOLF_ALLY_THRESHOLD) {
                meleeCommitted = true;
            }
        }
    }

    private static int countNearbyPiglins(ServerLevel level, Piglin piglin) {
        return level.getEntitiesOfClass(
                Piglin.class,
                piglin.getBoundingBox().inflate(ALLY_SEARCH_RADIUS),
                other -> other != piglin && other.isAlive()
        ).size();
    }

    private void tickRetreat(Piglin piglin, LivingEntity target, FirearmDifficultyProfile profile, double distance) {
        double retreatGoal = profile.retreatDistance;
        if (profile.extendedRetreatOnPressure && distance >= profile.meleeSwitchRange && distance <= profile.maxShootRange) {
            retreatGoal = HARD_PRESSURE_RETREAT_DISTANCE;
        }

        retreatTicks++;
        if (distance >= retreatGoal || retreatTicks > RETREAT_TIMEOUT_TICKS) {
            piglin.getNavigation().stop();
            state = State.CHARGING;
            chargeTicks = 0;
            charged = false;
            ItemStack weapon = piglin.getMainHandItem();
            if (isFirearm(weapon)) {
                SCRangeWeaponUtil.setWeaponState(weapon, new SCRangeWeaponUtil.WeaponState(true, false, false));
            }
            return;
        }

        moveToDistanceFromTarget(piglin, target, retreatGoal, RETREAT_SPEED);
    }

    private void moveToDistanceFromTarget(Piglin piglin, LivingEntity target, double desiredDistance, double speed) {
        Vec3 away = piglin.position().subtract(target.position());
        if (away.lengthSqr() < 1.0E-4) {
            away = new Vec3(piglin.getRandom().nextDouble() - 0.5, 0.0, piglin.getRandom().nextDouble() - 0.5);
        }
        away = away.normalize();
        Vec3 destination = target.position().add(away.scale(desiredDistance));
        piglin.getNavigation().moveTo(destination.x, destination.y, destination.z, speed);
    }

    private void fire(ServerLevel level, Piglin piglin, ItemStack weapon, LivingEntity target) {
        piglin.lookAt(EntityAnchorArgument.Anchor.EYES, target.position());

        float inaccuracy = getInaccuracyDegrees(level, piglin);
        piglin.setYRot(piglin.getYRot() + (piglin.getRandom().nextFloat() - 0.5F) * inaccuracy);
        piglin.setXRot(Mth.clamp(piglin.getXRot() + (piglin.getRandom().nextFloat() - 0.5F) * inaccuracy, -90.0F, 90.0F));

        piglin.swing(InteractionHand.MAIN_HAND);
        SCRangeWeaponUtil.shootBullet(level, weapon, piglin);
        SCRangeWeaponUtil.setWeaponState(weapon, new SCRangeWeaponUtil.WeaponState(false, false, true));

        if (Boolean.TRUE.equals(weapon.get(KHDataComponents.PIGLIN.get()))) {
            weapon.setDamageValue(Math.max(0, weapon.getDamageValue() - 1));
        }
    }

    private float getInaccuracyDegrees(ServerLevel level, Piglin piglin) {
        float effectiveDifficulty = level.getCurrentDifficultyAt(piglin.blockPosition()).getEffectiveDifficulty();
        float t = Mth.clamp(effectiveDifficulty / MAX_EFFECTIVE_DIFFICULTY, 0.0F, 1.0F);
        return Mth.lerp(t, MAX_INACCURACY_DEGREES, MIN_INACCURACY_DEGREES);
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
        FirearmDifficultyProfile profile = FirearmDifficultyProfile.fromLevel(mob.level());
        if (profile.meleeSwitchRange <= 0) return false;

        LivingEntity target = mob.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
        if (target == null) return false;

        return mob.distanceTo(target) <= profile.meleeSwitchRange;
    }
}