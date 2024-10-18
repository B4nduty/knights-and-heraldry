package com.knightsheraldry.entity.custom;

import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.armor.KHArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.KHTags;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.Random;

public class KHSwallowTailArrowEntity extends KHArrowEntity {
    private final Random random = new Random();
    private final ItemStack swallowTailArrowStack;
    private PlayerEntity stuckPlayer;

    public KHSwallowTailArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.KH_ARROW, shooter, world);
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

        applyDamage(target, (LivingEntity) getOwner());
        super.onEntityHit(entityHitResult);
    }

    private boolean shouldDeflect(PlayerEntity player) {
        float deflectProbability = calculateDeflectProbability(player);
        return random.nextFloat() < deflectProbability;
    }

    private void updateSwallowTailArrowCount(PlayerEntity player) {
        NbtCompound nbt = ((IEntityDataSaver) player).knightsheraldry$getPersistentData();
        int currentCount = nbt.getInt("swallowtail_arrow_count");
        nbt.putInt("swallowtail_arrow_count", currentCount + 1);
    }

    private float calculateDeflectProbability(PlayerEntity player) {
        final float[] deflectChance = {0f};

        TrinketsApi.getTrinketComponent(player).ifPresent(trinkets ->
                trinkets.getAllEquipped().forEach(pair -> {
                    if (pair.getRight().getItem() instanceof KHTrinketsItem trinket
                            && pair.getRight().isIn(KHTags.Armors.KH_DEFLECTIVE_ARMOR)) {
                        deflectChance[0] += 0.25f;
                    }
                })
        );

        for (ItemStack armor : player.getArmorItems()) {
            if (armor.getItem() instanceof KHArmorItem
                    && armor.isIn(KHTags.Armors.KH_DEFLECTIVE_ARMOR)) {
                deflectChance[0] += 0.25f;
            }
        }

        return deflectChance[0];
    }
}