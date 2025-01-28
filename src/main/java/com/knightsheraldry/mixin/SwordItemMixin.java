package com.knightsheraldry.mixin;

import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.KHTags;
import com.knightsheraldry.util.weaponutil.KHWeaponUtil;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SwordItem.class)
public class SwordItemMixin {
    @Inject(method = "postHit", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$postHit(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof KHWeapon khWeapon && attacker instanceof PlayerEntity playerEntity) {
            Vec3d playerPos = playerEntity.getPos();
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(KHWeaponUtil.getMaxDistance(khWeapon));
            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                            entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), KHWeaponUtil.getMaxDistance(khWeapon) + 1))
                    .forEach(entity -> {
                        double distance = playerPos.distanceTo(target.getPos());
                        KHDamageCalculator.DamageType damageType = KHWeaponUtil.calculateDamageType(stack, khWeapon, ((PlayerAttackProperties) playerEntity).getComboCount());
                        float damage = KHDamageCalculator.getKHDamage(target, KHWeaponUtil.calculateDamage(khWeapon, distance, damageType.getIndex() - 4, damageType.getIndex()), damageType);

                        if (stack.isIn(KHTags.WEAPONS_DAMAGE_BEHIND.getTag()))
                            damage = KHWeaponUtil.adjustDamageForBackstab(target, playerPos, damage);

                        KHDamageCalculator.applyDamage(target, playerEntity, stack, damage);
                    });
            cir.setReturnValue(true);
        }
    }
}
