package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.StaminaData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
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
    public NbtCompound bsroleplay$getPersistentData() {
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
        itemsList.add(ModItems.SMITHING_HAMMER);
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (itemsList.contains(player.getActiveItem().getItem())) {
            if (!player.getWorld().isClient) {
                player.incrementStat(Stats.USED.getOrCreateStat(player.getActiveItem().getItem()));
            }

            if (amount >= 3.0F) {
                int i = 1 + MathHelper.floor(amount);
                Hand hand = player.getActiveHand();
                player.getActiveItem().damage(i, player, (p) -> p.sendToolBreakStatus(hand));
                if (player.getActiveItem().isEmpty()) {
                    if (hand == Hand.MAIN_HAND) {
                        player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    } else {
                        player.equipStack(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                    }

                    player.clearActiveItem();
                    player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + player.getWorld().random.nextFloat() * 0.4F);
                }
            }
            ci.cancel();
        }

        int stamina = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getInt("stamina_int");
        int staminaCost = 20;

        if (KnightsHeraldry.CONFIG.common.getBlocking && stamina >= staminaCost) {
            StaminaData.removeStamina((IEntityDataSaver) player, staminaCost);
            player.sendMessage(Text.literal("Shield Stamina: " + stamina));
        } else if (KnightsHeraldry.CONFIG.common.getBlocking) {
            StaminaData.removeStamina((IEntityDataSaver) player, stamina);
        }
    }
}