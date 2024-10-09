package com.knightsheraldry.entity.custom;

import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHBodkinArrowEntity extends KHArrowEntity {
    private final ItemStack bodkinArrowStack = new ItemStack(ModItems.BODKIN_ARROW);

    public KHBodkinArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world);
    }

    public KHBodkinArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.KH_ARROW, shooter, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.bodkinArrowStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            this.applyDamage(target, getShooter());
        }
        super.onEntityHit(entityHitResult);
    }

    @Override
    public void applyDamage(LivingEntity target, LivingEntity attacker) {
        float damageDealt = new KHDamageCalculator().getKHDamage(target, getDamageAmount() - 4, getDamageType());
        float armorToughness = (float)target.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS) - 5;
        float armor = (float)target.getArmor() - 10;
        damageDealt = DamageUtil.getDamageLeft(damageDealt, armor >= 0 ? armor : 0, armorToughness >= 0 ? armorToughness : 0);
        target.damage(this.getDamageSources().generic(), damageDealt);
    }
}