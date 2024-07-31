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
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"),
            argsOnly = true
    )
    public BakedModel use1st3dPersonModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode,
                                          boolean leftHanded, MatrixStack matrices,
                                          VertexConsumerProvider vertexConsumers, int light, int overlay) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientWorld world = client.world;
        Entity entity = client.cameraEntity;

        if (renderMode == ModelTransformationMode.GUI) {
            return value;
        }

        String modelPath = determineModelPath(stack);

        if (modelPath.isEmpty()) {
            return value;
        }

        BakedModel model3d = ((ItemRendererAccessor) this)
                .knightsAndHereldry$getModels()
                .getModelManager()
                .getModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, modelPath, "inventory"));

        if (entity instanceof LivingEntity livingEntity) {
            BakedModel overriddenModel = model3d.getOverrides().apply(model3d, stack, world, livingEntity, 0);
            return overriddenModel != null ? overriddenModel : model3d;
        }

        return value;
    }

    @Unique
    private String determineModelPath(ItemStack stack) {
        if (stack.isOf(ModItems.DAGGER)) return "dagger_3d";
        if (stack.isOf(ModItems.STILETTO)) return "stiletto_3d";
        if (stack.isOf(ModItems.RAPIER)) return "rapier_3d";
        if (stack.isOf(ModItems.SWORD)) return "sword_3d";
        if (stack.isOf(ModItems.V_SWORD)) return "v_sword_3d";
        if (stack.isOf(ModItems.ARMING_SWORD)) return "arming_sword_3d";
        if (stack.isOf(ModItems.AXE)) return "axe_3d";
        if (stack.isOf(ModItems.BROAD_AXE)) return "broad_axe_3d";
        if (stack.isOf(ModItems.CROOKED_AXE)) return "crooked_axe_3d";
        if (stack.isOf(ModItems.STRAIGHT_CROOKED_AXE)) return "straight_crooked_axe_3d";
        if (stack.isOf(ModItems.WARSWORD)) return "warsword_3d";
        if (stack.isOf(ModItems.WARSWORD_CLAYMORE)) return "warsword_claymore_3d";
        if (stack.isOf(ModItems.WARSWORD_FLAMBERGE)) return "warsword_flamberge_3d";
        if (stack.isOf(ModItems.WARSWORD_ZWEIHANDER)) return "warsword_zweihander_3d";
        return "";
    }
}