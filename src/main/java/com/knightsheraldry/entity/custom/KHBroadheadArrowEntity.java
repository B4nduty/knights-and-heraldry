package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.ModEntities;
import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHBroadheadArrowEntity extends SCArrowEntity {
    private final ItemStack broadheadArrowStack;

    public KHBroadheadArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.SC_ARROW, shooter, world);
        this.broadheadArrowStack = new ItemStack(ModItems.BROADHEAD_ARROW);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.broadheadArrowStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            hitKHEntity(target, broadheadArrowStack, getDamageAmount());
        }
        super.onEntityHit(entityHitResult);
    }
}