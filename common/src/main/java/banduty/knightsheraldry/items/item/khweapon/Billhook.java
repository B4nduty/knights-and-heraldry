package banduty.knightsheraldry.items.item.khweapon;

import banduty.knightsheraldry.items.ModToolMaterials;
import banduty.stoneycore.combat.melee.CombatSelect;
import banduty.stoneycore.combat.melee.SCDamageType;
import banduty.stoneycore.util.weaponutil.SCWeaponUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class Billhook extends SwordItem {
    public Billhook(float attackSpeed, Properties properties) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);

        if (attacker instanceof Player player) {
            SCDamageType damageType = SCWeaponUtil.calculateDamageType(stack, CombatSelect.getComboCount(player));
            double maxDistance = SCWeaponUtil.getMaxDistance(this);
            AABB detectionBox = new AABB(player.getOnPos()).inflate(maxDistance);
            Vec3 playerPos = player.position();
            player.level().getEntitiesOfClass(LivingEntity.class, detectionBox, entity ->
                            entity != player && entity == target && player.getOnPos().closerThan(entity.getOnPos(), maxDistance + 1))
                    .forEach(entity -> {
                        boolean critical = false;
                        double distance = playerPos.distanceTo(target.position());
                        double damage = SCDamageType.calculateSCDamage(player, SCWeaponUtil.calculateDamage(this, distance,
                                damageType), damageType);
                        double maxDamage = SCWeaponUtil.getMaxDamage(damageType, this);

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