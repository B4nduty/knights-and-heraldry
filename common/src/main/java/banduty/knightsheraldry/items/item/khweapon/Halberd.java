package banduty.knightsheraldry.items.item.khweapon;

import banduty.knightsheraldry.items.ModToolMaterials;
import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.util.SCDamageCalculator;
import banduty.stoneycore.util.weaponutil.SCWeaponUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class Halberd extends SwordItem {
    public Halberd(float attackSpeed, Properties properties) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);

        if (attacker instanceof Player player) {
            SCDamageCalculator.DamageType damageType = SCWeaponUtil.calculateDamageType(stack, Services.PLATFORM.getComboCount(player));

            if (damageType.equals(SCDamageCalculator.DamageType.BLUDGEONING)) {
                target.addEffect(new MobEffectInstance(Services.PLATFORM.getPinEffect(), 2 * 20));
            }

            double maxDistance = SCWeaponUtil.getMaxDistance(this);
            AABB detectionBox = new AABB(player.getOnPos()).inflate(maxDistance);
            Vec3 playerPos = player.position();
            player.level().getEntitiesOfClass(LivingEntity.class, detectionBox, entity ->
                            entity != player && entity == target && player.position().closerThan(entity.position(), maxDistance + 1))
                    .forEach(entity -> {
                        boolean critical = false;
                        double distance = playerPos.distanceTo(target.position());
                        double damage = SCDamageCalculator.getSCDamage(player, SCWeaponUtil.calculateDamage(this, distance, damageType), damageType);
                        double maxDamage = SCWeaponUtil.getDamageValues(damageType, this);

                        if (damage >= maxDamage) {
                            critical = true;
                        }

                        if (target.hasControllingPassenger()) {
                            Entity passenger = target.getControllingPassenger();
                            if (passenger != null && critical) {
                                passenger.stopRiding();
                            }
                        }
                    });
            return true;
        }

        return false;
    }
}