package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import banduty.stoneycore.util.data.keys.NBTDataHelper;
import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import banduty.stoneycore.util.data.playerdata.PDKeys;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHSwallowTailArrowEntity extends SCArrowEntity {
    private PlayerEntity stuckPlayer;

    public KHSwallowTailArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.SWALLOWTAIL_ARROW.get(), shooter, world);
    }

    public KHSwallowTailArrowEntity(EntityType<KHSwallowTailArrowEntity> khSwallowTailArrowEntityEntityType, World world) {
        super(khSwallowTailArrowEntityEntityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.SWALLOWTAIL_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        LivingEntity target = (LivingEntity) entityHitResult.getEntity();

        if (target instanceof PlayerEntity player && stuckPlayer == null) {
            if (player.isCreative()) return;

            stuckPlayer = player;
            updateSwallowTailArrowCount(player);
        }

        scHitEntity(target, new ItemStack(ModItems.SWALLOWTAIL_ARROW.get()), getDamage());
    }

    private void updateSwallowTailArrowCount(PlayerEntity player) {
        int currentCount = NBTDataHelper.get((IEntityDataSaver) player, PDKeys.SWALLOWTAIL_ARROW_COUNT, 0);
        NBTDataHelper.set((IEntityDataSaver) player, PDKeys.SWALLOWTAIL_ARROW_COUNT, currentCount + 1);
    }
}