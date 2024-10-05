package com.knightsheraldry.mixin;

import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public class HungerManagerMixin {

    @Shadow
    private float exhaustion;
    @Unique
    private PlayerEntity player;

    @Inject(method = "update", at = @At("HEAD"))
    public void update(PlayerEntity player, CallbackInfo ci) {
        this.player = player;
    }

    @Inject(method = "addExhaustion", at = @At("HEAD"), cancellable = true)
    public void onAddExhaustion(float exhaustion, CallbackInfo ci) {
        double hungerAddition = 1.0d;

        if (player == null) return;
        if (TrinketsApi.getTrinketComponent(player).isPresent()) {
            for (Pair<SlotReference, ItemStack> equipped : TrinketsApi.getTrinketComponent(player).get().getEquipped(stack -> stack.getItem() instanceof KHTrinketsItem)) {
                ItemStack trinket = equipped.getRight();
                KHTrinketsItem khTrinketsItem = (KHTrinketsItem) trinket.getItem();
                hungerAddition += khTrinketsItem.getHungerDrainAddition();
            }
        }

        float modifiedExhaustion = (float) Math.min(this.exhaustion + exhaustion * hungerAddition, 40.0F);
        player.getHungerManager().setExhaustion(modifiedExhaustion);

        ci.cancel();
    }
}