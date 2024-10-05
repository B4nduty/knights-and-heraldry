package com.knightsheraldry.entity.custom;

import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.armor.KHArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.KHTags;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.Random;

public class KHSwallowTailArrowEntity extends KHArrowEntity {
    private final Random random = new Random();
    private final ItemStack swallowTailArrowStack = new ItemStack(ModItems.SWALLOWTAIL_ARROW);
    private PlayerEntity stuckPlayer;

    public KHSwallowTailArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world);
    }

    public KHSwallowTailArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.KH_ARROW, shooter, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.swallowTailArrowStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target && stuckPlayer == null) {
            if (target instanceof PlayerEntity player) {
                if (player.isCreative()) return;
                float deflectProbability = calculateDeflectProbability(player);
                if (random.nextFloat() < deflectProbability) return;
                stuckPlayer = player;
                NbtCompound nbt = ((IEntityDataSaver) player).knightsheraldry$getPersistentData();
                int currentSwallowTailArrowCount = nbt.getInt("swallowtail_arrow_count");
                currentSwallowTailArrowCount = currentSwallowTailArrowCount + 1;
                nbt.putInt("swallowtail_arrow_count", currentSwallowTailArrowCount);
            }
            applyDamage(target);
        }
        super.onEntityHit(entityHitResult);
    }

    private float calculateDeflectProbability(PlayerEntity player) {
        final float[] deflectChance = {0f};

        TrinketsApi.getTrinketComponent(player).ifPresent(trinkets -> trinkets.getAllEquipped().forEach(pair -> {
            ItemStack trinket = pair.getRight();
            if (trinket.getItem() instanceof KHTrinketsItem && trinket.isIn(KHTags.Armors.KH_DEFLECTIVE_ARMOR)) {
                deflectChance[0] += 0.25f;
            }
        }));

        for (ItemStack armor : player.getArmorItems()) {
            if (armor.getItem() instanceof KHArmorItem && armor.isIn(KHTags.Armors.KH_DEFLECTIVE_ARMOR)) {
                deflectChance[0] += 0.25f;
            }
        }

        return deflectChance[0];
    }
}