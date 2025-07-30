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
    private ItemStack broadheadArrowStack;

    public KHBroadheadArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.BROADHEAD_ARROW.get(), shooter, world);
        this.broadheadArrowStack = new ItemStack(ModItems.BROADHEAD_ARROW.get());
    }

    public KHBroadheadArrowEntity(EntityType<KHBroadheadArrowEntity> khBroadheadArrowEntityEntityType, World world) {
        super(khBroadheadArrowEntityEntityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.broadheadArrowStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            scHitEntity(target, broadheadArrowStack, getDamageAmount());
        }
        super.onEntityHit(entityHitResult);
    }
}