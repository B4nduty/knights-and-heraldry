package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import banduty.stoneycore.util.data.keys.NBTDataHelper;
import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import banduty.stoneycore.util.data.playerdata.PDKeys;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class KHSwallowTailArrowEntity extends SCArrowEntity {
    private Player stuckPlayer;

    public KHSwallowTailArrowEntity(LivingEntity shooter, Level level) {
        super(ModEntities.SWALLOWTAIL_ARROW.get(), shooter, level);
    }

    public KHSwallowTailArrowEntity(EntityType<KHSwallowTailArrowEntity> khSwallowTailArrowEntityEntityType, Level level) {
        super(khSwallowTailArrowEntityEntityType, level);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(ModItems.SWALLOWTAIL_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        LivingEntity target = (LivingEntity) entityHitResult.getEntity();

        if (target instanceof Player player && stuckPlayer == null) {
            if (player.isCreative()) return;

            stuckPlayer = player;
            updateSwallowTailArrowCount(player);
        }

        scHitEntity(target, new ItemStack(ModItems.SWALLOWTAIL_ARROW.get()), getBaseDamage());
    }

    private void updateSwallowTailArrowCount(Player player) {
        int currentCount = NBTDataHelper.get((IEntityDataSaver) player, PDKeys.SWALLOWTAIL_ARROW_COUNT, 0);
        NBTDataHelper.set((IEntityDataSaver) player, PDKeys.SWALLOWTAIL_ARROW_COUNT, currentCount + 1);
    }
}