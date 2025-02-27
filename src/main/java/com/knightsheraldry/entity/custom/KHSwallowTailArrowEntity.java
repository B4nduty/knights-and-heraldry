package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.ModEntities;
import banduty.stoneycore.entity.custom.SCArrowEntity;
import banduty.stoneycore.util.playerdata.IEntityDataSaver;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.itemdata.KHTags;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.Random;

public class KHSwallowTailArrowEntity extends SCArrowEntity {
    private final Random random = new Random();
    private final ItemStack swallowTailArrowStack;
    private PlayerEntity stuckPlayer;

    public KHSwallowTailArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.SC_ARROW, shooter, world);
        this.swallowTailArrowStack = new ItemStack(ModItems.SWALLOWTAIL_ARROW);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.swallowTailArrowStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        LivingEntity target = (LivingEntity) entityHitResult.getEntity();

        if (target instanceof PlayerEntity player && stuckPlayer == null) {
            if (player.isCreative()) return;

            if (shouldDeflect(player)) return;

            stuckPlayer = player;
            updateSwallowTailArrowCount(player);
        }

        hitKHEntity(target, swallowTailArrowStack, getDamageAmount());
        super.onEntityHit(entityHitResult);
    }

    private boolean shouldDeflect(PlayerEntity player) {
        float deflectProbability = calculateDeflectProbability(player);
        return random.nextFloat() < deflectProbability;
    }

    private void updateSwallowTailArrowCount(PlayerEntity player) {
        NbtCompound nbt = ((IEntityDataSaver) player).stoneycore$getPersistentData();
        int currentCount = nbt.getInt("swallowtail_arrow_count");
        nbt.putInt("swallowtail_arrow_count", currentCount + 1);
    }

    private float calculateDeflectProbability(PlayerEntity player) {
        final float[] deflectChance = {0f};

        TrinketsApi.getTrinketComponent(player).ifPresent(trinkets ->
                trinkets.getAllEquipped().forEach(pair -> {
                    if (pair.getRight().isIn(KHTags.DEFLECTIVE_ARMOR.getTag())) {
                        deflectChance[0] += 0.25f;
                    }
                })
        );

        for (ItemStack armor : player.getArmorItems()) {
            if (armor.isIn(KHTags.DEFLECTIVE_ARMOR.getTag())) {
                deflectChance[0] += 0.25f;
            }
        }

        return deflectChance[0];
    }
}