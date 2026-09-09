package banduty.knightsheraldry.combat.range.goal;

import banduty.stoneycore.combat.range.RangedWeaponHandlers;
import banduty.stoneycore.combat.weapon.SCRangeWeaponUtil;
import banduty.stoneycore.definitions.WeaponDefinitionsStorage;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

/**
 * Generic ranged-attack Goal for any {@link Mob} holding a registered "musket-type" weapon
 * (i.e. an item whose {@link WeaponDefinitionsStorage} data has a {@code ranged()} entry whose
 * id is registered in {@link RangedWeaponHandlers}, e.g. "musket").
 * <p>
 * This does NOT go through {@code IRangedWeaponHandler.shoot/reload}, since that path is built
 * around a right-click-held {@link net.minecraft.world.entity.player.Player} (ammo checks,
 * per-player recharge scheduling via MechanicsUtil). Mobs don't have an inventory to draw ammo
 * from, so this Goal owns its own tiny reload/charge/shoot state machine and only reuses the
 * shared, stack-based {@link SCRangeWeaponUtil.WeaponState}.
 */
public class SCFirearmAttackGoal<T extends Mob> extends Goal {

    private static final UniformInt PATHFINDING_DELAY_RANGE = UniformInt.of(20, 40);
    private static final int SHOT_ANIMATION_TICKS = 10;
    private static final int REQUIRED_SEE_TICKS = 5;

    private final T mob;
    private final double speedModifier;
    private final float attackRadiusSqr;

    private int seeTime;
    private int updatePathDelay;
    private int reloadTicksLeft;
    private int shotAnimTicksLeft;

    public SCFirearmAttackGoal(T mob, double speedModifier, float attackRadius) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.attackRadiusSqr = attackRadius * attackRadius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    private ItemStack getWeapon() {
        return this.mob.getMainHandItem();
    }

    private boolean isHoldingRegisteredFirearm() {
        ItemStack weapon = getWeapon();
        if (weapon.isEmpty()) return false;
        var data = WeaponDefinitionsStorage.getData(weapon);
        if (data == null || data.ranged() == null) return false;
        return RangedWeaponHandlers.get(data.ranged().id()).isPresent();
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        return target != null && target.isAlive() && isHoldingRegisteredFirearm();
    }

    @Override
    public boolean canContinueToUse() {
        return (canUse() || !this.mob.getNavigation().isDone());
    }

    @Override
    public void start() {
        super.start();
        this.mob.setAggressive(true);
        this.seeTime = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.seeTime = 0;
        this.mob.getNavigation().stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target == null || !isHoldingRegisteredFirearm()) return;

        Level level = this.mob.level();
        ItemStack weapon = getWeapon();

        boolean seesTarget = this.mob.getSensing().hasLineOfSight(target);
        if (seesTarget) this.seeTime++;
        else this.seeTime = 0;

        double distSqr = this.mob.distanceToSqr(target);
        boolean inRange = distSqr <= this.attackRadiusSqr;

        // movement / facing
        this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
        if (--this.updatePathDelay <= 0) {
            this.updatePathDelay = PATHFINDING_DELAY_RANGE.sample(this.mob.getRandom());
            if (!inRange) {
                this.mob.getNavigation().moveTo(target, this.speedModifier);
            } else {
                this.mob.getNavigation().stop();
            }
        }

        var state = SCRangeWeaponUtil.getWeaponState(weapon);

        if (state.isShooting()) {
            if (--this.shotAnimTicksLeft <= 0) {
                beginReload(weapon);
            }
            return;
        }

        if (state.isReloading()) {
            if (--this.reloadTicksLeft <= 0) {
                SCRangeWeaponUtil.setWeaponState(weapon, new SCRangeWeaponUtil.WeaponState(false, true, false));
            }
            return;
        }

        if (state.isCharged()) {
            if (inRange && this.seeTime >= REQUIRED_SEE_TICKS) {
                fire(level, weapon);
            }
            return;
        }

        // idle/unloaded state -> start the first reload
        beginReload(weapon);
    }

    private void beginReload(ItemStack weapon) {
        int rechargeTime = WeaponDefinitionsStorage.getData(weapon).ranged().rechargeTime();
        this.reloadTicksLeft = Math.max(1, rechargeTime);
        SCRangeWeaponUtil.setWeaponState(weapon, new SCRangeWeaponUtil.WeaponState(true, false, false));
    }

    private void fire(Level level, ItemStack weapon) {
        SCRangeWeaponUtil.shootBullet(level, weapon, this.mob);
        SCRangeWeaponUtil.setWeaponState(weapon, new SCRangeWeaponUtil.WeaponState(false, false, true));
        this.shotAnimTicksLeft = SHOT_ANIMATION_TICKS;
    }
}