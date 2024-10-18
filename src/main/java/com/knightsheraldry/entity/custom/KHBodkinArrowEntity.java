package com.knightsheraldry.entity.custom;

import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHBodkinArrowEntity extends KHArrowEntity {
    private final ItemStack bodkinArrowStack;

    public KHBodkinArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.KH_ARROW, shooter, world);
        this.bodkinArrowStack = new ItemStack(ModItems.BODKIN_ARROW);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.bodkinArrowStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            applyDamage(target, (LivingEntity) getOwner());
        }
        super.onEntityHit(entityHitResult);
    }

    @Override
    public void applyDamage(LivingEntity target, LivingEntity attacker) {
        float damageDealt = new KHDamageCalculator().getKHDamage(target, getDamageAmount() - 4, getDamageType());

        float armor = Math.max(0, target.getArmor() - 10);
        float armorToughness = Math.max(0, (float) target.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS) - 5);

        damageDealt = DamageUtil.getDamageLeft(damageDealt, armor, armorToughness);

        target.damage(getDamageSources().generic(), damageDealt);
    }
}