package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHBodkinArrowEntity extends SCArrowEntity {

    public KHBodkinArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.BODKING_ARROW.get(), shooter, world);
    }

    public KHBodkinArrowEntity(EntityType<KHBodkinArrowEntity> scArrowEntityEntityType, World world) {
        super(scArrowEntityEntityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.BODKIN_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        if (this.getWorld().isClient()) return;
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            applyDamage(target);
        }
    }

    public void applyDamage(LivingEntity target) {
        float damageDealt = SCDamageCalculator.getSCDamage(target, getDamageAmount() - 3, getDamageType());

        float armor = Math.max(0, target.getArmor() - 10);
        float armorToughness = Math.max(0, (float) target.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS) - 5);

        damageDealt = DamageUtil.getDamageLeft(damageDealt, armor, armorToughness);

        if (this.getOwner() instanceof PlayerEntity player) target.damage(target.getWorld().getDamageSources().playerAttack(player), damageDealt);
        else if (this.getOwner() instanceof LivingEntity livingEntity) target.damage(target.getWorld().getDamageSources().mobAttack(livingEntity), damageDealt);
    }
}