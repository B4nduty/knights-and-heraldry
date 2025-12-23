package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class KHBodkinArrowEntity extends SCArrowEntity {

    public KHBodkinArrowEntity(LivingEntity shooter, Level level) {
        super(ModEntities.BODKING_ARROW.get(), shooter, level);
    }

    public KHBodkinArrowEntity(EntityType<KHBodkinArrowEntity> scArrowEntityEntityType, Level level) {
        super(scArrowEntityEntityType, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.BODKIN_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        if (this.level().isClientSide()) return;
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            applyDamage(target);
        }
    }

    public void applyDamage(LivingEntity target) {
        double damageDealt = getBaseDamage() * getDeltaMovement().length();

        damageDealt = SCDamageCalculator.getSCDamage(target, damageDealt - 3, getDamageType());

        double armor = Math.max(0, target.getArmorValue() - 10);
        double armorToughness = Math.max(0, target.getAttributeValue(Attributes.ARMOR_TOUGHNESS) - 5);

        damageDealt = CombatRules.getDamageAfterAbsorb((float) damageDealt, (float) armor, (float) armorToughness);

        if (this.getOwner() instanceof Player player) target.hurt(target.level().damageSources().playerAttack(player), (float) damageDealt);
        else if (this.getOwner() instanceof LivingEntity livingEntity) target.hurt(target.level().damageSources().mobAttack(livingEntity), (float) damageDealt);
    }
}