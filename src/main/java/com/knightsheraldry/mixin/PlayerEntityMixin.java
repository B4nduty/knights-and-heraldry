package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.StaminaData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IEntityDataSaver {
    @Unique
    private NbtCompound persistentData;

    @Override
    public NbtCompound knightsheraldry$getPersistentData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfo ci) {
        if(persistentData != null) {
            nbt.put("bsroleplay.data", persistentData);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("bsroleplay.data", 10)) {
            persistentData = nbt.getCompound("bsroleplay.data");
        }
    }

    @Inject(method = "damageShield", at = @At("HEAD"), cancellable = true)
    private void knightsHeraldry$onDamageShield(float amount, CallbackInfo ci) {
        List<Item> itemsList = new ArrayList<>();
        itemsList.add(ModItems.WARSWORD);
        itemsList.add(ModItems.WARSWORD_CLAYMORE);
        itemsList.add(ModItems.WARSWORD_FLAMBERGE);
        itemsList.add(ModItems.WARSWORD_ZWEIHANDER);
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (itemsList.contains(player.getActiveItem().getItem())) {
            if (!player.getWorld().isClient) {
                player.incrementStat(Stats.USED.getOrCreateStat(player.getActiveItem().getItem()));
            }
            ci.cancel();
        }

        int stamina = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getInt("stamina_int");
        int staminaCost = 20;

        if (KnightsHeraldry.CONFIG.common.getBlocking && stamina >= staminaCost) {
            StaminaData.removeStamina((IEntityDataSaver) player, staminaCost);
        } else if (KnightsHeraldry.CONFIG.common.getBlocking) {
            StaminaData.removeStamina((IEntityDataSaver) player, stamina);
        }
    }
}