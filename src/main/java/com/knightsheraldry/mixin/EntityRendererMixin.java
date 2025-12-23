package com.knightsheraldry.mixin;

import com.knightsheraldry.items.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity> {
    @Inject(method = "renderNameTag", at = @At("HEAD"), cancellable = true)
    private void stoneycore$onRenderLabelIfPresent(T entity, Component component, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, CallbackInfo ci) {
        if (entity instanceof Player player) {
            if (AccessoriesCapability.getOptionally(player).isPresent()) {
                for (SlotEntryReference equipped : AccessoriesCapability.get(player).getAllEquipped()) {
                    ItemStack itemStack = equipped.stack();
                    if (itemStack.getItem() == ModItems.HOOD.get() || itemStack.getItem() == ModItems.TORN_HOOD.get() ||
                            itemStack.getItem() == ModItems.HELMET_HOOD.get() || itemStack.getItem() == ModItems.HELMET_TORN_HOOD.get()) ci.cancel();
                }
            }
        }
    }
}