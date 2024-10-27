package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.util.KHTags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V",
            at = @At("HEAD"),
            cancellable = true)
    public void knightsheraldry$onRenderItem(LivingEntity entity, ItemStack item, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci) {
        if (item.isEmpty() || !(item.isIn(KHTags.Weapon.KH_WEAPONS_3D))) {
            return;
        }

        if (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND) {
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
        return stack.getItem() + "_3d";
    }

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$renderGUIItem(ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if (stack.isIn(KHTags.Weapon.KH_GEO_2D_ITEMS) && renderMode == ModelTransformationMode.GUI) {
            String modelPath = stack.getItem() + "_icon";
            MinecraftClient client = MinecraftClient.getInstance();
            BakedModelManager modelManager = client.getItemRenderer().getModels().getModelManager();
            BakedModel guiBakedModel = modelManager.getModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, modelPath, "inventory"));
            matrices.translate(-0.5F, -0.5F, -0.5F);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCutout());
            renderBakedItemModel(guiBakedModel, stack, light, overlay, matrices, vertexConsumer);

            ci.cancel();
        }
    }

    @Unique
    private void renderBakedItemModel(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices, VertexConsumer vertices) {
        Random random = Random.create();
        Direction[] var10 = Direction.values();

        for (Direction direction : var10) {
            random.setSeed(42L);
            renderBakedItemQuads(matrices, vertices, model.getQuads(null, direction, random), stack, light, overlay);
        }

        random.setSeed(42L);
        renderBakedItemQuads(matrices, vertices, model.getQuads(null, null, random), stack, light, overlay);
    }

    @Unique
    private void renderBakedItemQuads(MatrixStack matrices, VertexConsumer vertices, List<BakedQuad> quads, ItemStack stack, int light, int overlay) {
        MatrixStack.Entry entry = matrices.peek();

        for (BakedQuad bakedQuad : quads) {
            int i = -1;

            float f = (float) (i >> 16 & 255) / 255.0F;
            float g = (float) (i >> 8 & 255) / 255.0F;
            float h = (float) (i & 255) / 255.0F;
            vertices.quad(entry, bakedQuad, f, g, h, light, overlay);
        }

    }
}