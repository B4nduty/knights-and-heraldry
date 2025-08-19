package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHBroadheadArrowEntity extends SCArrowEntity {
    public KHBroadheadArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.BROADHEAD_ARROW.get(), shooter, world);
    }

    public KHBroadheadArrowEntity(EntityType<KHBroadheadArrowEntity> khBroadheadArrowEntityEntityType, World world) {
        super(khBroadheadArrowEntityEntityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.BROADHEAD_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            scHitEntity(target, new ItemStack(ModItems.BROADHEAD_ARROW.get()), getDamageAmount());
        }
    }
}