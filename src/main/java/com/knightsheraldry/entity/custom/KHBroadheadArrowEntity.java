package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class KHBroadheadArrowEntity extends SCArrowEntity {
    public KHBroadheadArrowEntity(LivingEntity shooter, Level level) {
        super(ModEntities.BROADHEAD_ARROW.get(), shooter, level);
    }

    public KHBroadheadArrowEntity(EntityType<KHBroadheadArrowEntity> khBroadheadArrowEntityEntityType, Level level) {
        super(khBroadheadArrowEntityEntityType, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.BROADHEAD_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            scHitEntity(target, new ItemStack(ModItems.BROADHEAD_ARROW.get()), getBaseDamage());
        }
    }
}