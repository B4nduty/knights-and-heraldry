package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"), argsOnly = true
    )
    public BakedModel use1st3dPersonModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (renderMode == ModelTransformationMode.GUI) {
            return value;
        }

        boolean isBlocking = false;
        if (MinecraftClient.getInstance().player != null) {
            isBlocking = MinecraftClient.getInstance().player.isBlocking();
        }

        String customModelDataSuffix = stack.getOrCreateNbt().getInt("CustomModelData") == 1 ? "_bludgeoning" : "";
        String blockingSuffix = isBlocking ? "_blocking" : "";
        String modelPath = "";

        if (stack.isOf(ModItems.DAGGER)) {
            modelPath += "dagger_3d";
        } else if (stack.isOf(ModItems.STILETTO)) {
            modelPath += "stiletto_3d";
        } else if (stack.isOf(ModItems.RAPIER)) {
            modelPath += "rapier_3d";
        } else if (stack.isOf(ModItems.SWORD)) {
            modelPath += "sword_3d" + customModelDataSuffix + blockingSuffix;
        } else if (stack.isOf(ModItems.V_SWORD)) {
            modelPath += "v_sword_3d" + customModelDataSuffix + blockingSuffix;
        } else if (stack.isOf(ModItems.ARMING_SWORD)) {
            modelPath += "arming_sword_3d" + customModelDataSuffix + blockingSuffix;
        } else if (stack.isOf(ModItems.AXE)) {
            modelPath += "axe_3d" + blockingSuffix;
        } else if (stack.isOf(ModItems.BROAD_AXE)) {
            modelPath += "broad_axe_3d" + blockingSuffix;
        } else if (stack.isOf(ModItems.CROOKED_AXE)) {
            modelPath += "crooked_axe_3d" + blockingSuffix;
        } else if (stack.isOf(ModItems.STRAIGHT_CROOKED_AXE)) {
            modelPath += "straight_crooked_axe_3d" + blockingSuffix;
        } else if (stack.isOf(ModItems.WARSWORD)) {
            modelPath += "warsword_3d" + customModelDataSuffix + blockingSuffix;
        } else if (stack.isOf(ModItems.WARSWORD_CLAYMORE)) {
            modelPath += "warsword_claymore_3d" + customModelDataSuffix + blockingSuffix;
        } else if (stack.isOf(ModItems.WARSWORD_FLAMBERGE)) {
            modelPath += "warsword_flamberge_3d" + customModelDataSuffix + blockingSuffix;
        } else if (stack.isOf(ModItems.WARSWORD_ZWEIHANDER)) {
            modelPath += "warsword_zweihander_3d" + customModelDataSuffix + blockingSuffix;
        } else {
            return value;
        }

        return ((ItemRendererAccessor) this).knightsAndHereldry$getModels().getModelManager().getModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, modelPath, "inventory"));
    }
}