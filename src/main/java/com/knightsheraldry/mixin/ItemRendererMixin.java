package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.ModTags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V",
            at = @At("HEAD"),
            cancellable = true)
    public void onRenderItem(LivingEntity entity, ItemStack item, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci) {
        if (item.isEmpty() || !item.isIn(ModTags.Items.KH_WEAPONS)) {
            return;
        }

        if (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.FIXED || renderMode == ModelTransformationMode.GROUND) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        BakedModelManager modelManager = client.getItemRenderer().getModels().getModelManager();
        BakedModel bakedModel = modelManager.getMissingModel();

        String modelPath = determineModelPath(item);
        if (!modelPath.isEmpty()) {
            bakedModel = modelManager.getModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, modelPath, "inventory"));
        }

        ClientWorld clientWorld = entity != null ? entity.getWorld() instanceof ClientWorld ? (ClientWorld) entity.getWorld() : null : null;
        BakedModel overrideModel = bakedModel.getOverrides().apply(bakedModel, item, clientWorld, entity, seed);

        if (overrideModel != null) {
            bakedModel = overrideModel;
        }

        ItemRenderer itemRenderer = (ItemRenderer) (Object) this;
        itemRenderer.renderItem(item, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, bakedModel);

        ci.cancel();
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