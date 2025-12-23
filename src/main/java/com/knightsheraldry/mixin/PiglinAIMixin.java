package com.knightsheraldry.mixin;

import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public class PiglinAIMixin {
    @Inject(method = "isWearingGold", at = @At("HEAD"), cancellable = true)
    private static void isWearingGold(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
        if (AccessoriesCapability.getOptionally(livingEntity).isPresent()) {
            var capability = AccessoriesCapability.get(livingEntity);

            for (SlotEntryReference equipped : capability.getAllEquipped()) {
                ItemStack equippedStack = equipped.stack();
                if (equippedStack.isEmpty()) continue;

                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(equippedStack.getItem());
                if (itemId.getPath().startsWith("golden_")) {
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
    }
}
