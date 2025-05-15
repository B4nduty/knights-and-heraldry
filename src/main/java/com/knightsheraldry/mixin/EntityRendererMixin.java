package com.knightsheraldry.mixin;

import com.knightsheraldry.items.ModItems;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity> {
    @Inject(method = "renderLabelIfPresent", at = @At("HEAD"), cancellable = true)
    private void stoneycore$onRenderLabelIfPresent(T entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (entity instanceof PlayerEntity player) {
            if (AccessoriesCapability.getOptionally(player).isPresent()) {
                for (SlotEntryReference equipped : AccessoriesCapability.get(player).getAllEquipped()) {
                    ItemStack itemStack = equipped.stack();
                    if (itemStack.getItem() == ModItems.HOOD || itemStack.getItem() == ModItems.TORN_HOOD ||
                            itemStack.getItem() == ModItems.HELMET_HOOD || itemStack.getItem() == ModItems.HELMET_TORN_HOOD) ci.cancel();
                }
            }
        }
    }
}