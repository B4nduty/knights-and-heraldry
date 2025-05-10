package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
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
            at = @At("HEAD"))
    public void knightsheraldry$onRenderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci) {
        if (stack.isEmpty() || !(stack.getNbt() != null && stack.getNbt().contains("kh_plume"))) {
            return;
        }

        BakedModel bakedModel = getCustomBakedModel(stack, entity, seed);
        if (bakedModel != null) {
            ItemRenderer itemRenderer = (ItemRenderer) (Object) this;
            itemRenderer.renderItem(stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, bakedModel);
        }
    }

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At("HEAD"))
    public void knightsheraldry$renderGUIItem(ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if (renderMode == ModelTransformationMode.GUI && stack.getNbt() != null && stack.getNbt().contains("kh_plume")) {
            BakedModel guiBakedModel = getCustomBakedModel(stack, null, 0);
            if (guiBakedModel != null) {
                matrices.push();
                matrices.translate(-0.5F, -0.5F, -0.5F);
                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCutout());
                renderBakedItemModel(stack, guiBakedModel, light, overlay, matrices, vertexConsumer);
                matrices.pop();
            }
        }
    }

    @Unique
    private BakedModel getCustomBakedModel(ItemStack itemStack, LivingEntity entity, int seed) {
        MinecraftClient client = MinecraftClient.getInstance();
        BakedModelManager modelManager = client.getItemRenderer().getModels().getModelManager();

        BakedModel bakedModel = modelManager.getModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, "plume_helmet", "inventory"));
        if (itemStack.getItem() instanceof HorseBardingArmorItem) bakedModel = modelManager.getModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, "plume_horse", "inventory"));

        if (entity != null) {
            ClientWorld clientWorld = entity.getWorld() instanceof ClientWorld ? (ClientWorld) entity.getWorld() : null;
            BakedModel overrideModel = bakedModel.getOverrides().apply(bakedModel, itemStack, clientWorld, entity, seed);
            if (overrideModel != null) {
                bakedModel = overrideModel;
            }
        }

        return bakedModel;
    }

    @Unique
    private void renderBakedItemModel(ItemStack itemStack, BakedModel model, int light, int overlay, MatrixStack matrices, VertexConsumer vertices) {
        Random random = Random.create();
        for (Direction direction : Direction.values()) {
            random.setSeed(42L);
            renderBakedItemQuads(itemStack, matrices, vertices, model.getQuads(null, direction, random), light, overlay);
        }

        random.setSeed(42L);
        renderBakedItemQuads(itemStack, matrices, vertices, model.getQuads(null, null, random), light, overlay);
    }

    @Unique
    private void renderBakedItemQuads(ItemStack itemStack, MatrixStack matrices, VertexConsumer vertices, List<BakedQuad> quads, int light, int overlay) {
        MatrixStack.Entry entry = matrices.peek();
        for (BakedQuad quad : quads) {
            if (itemStack.getNbt() != null && itemStack.getNbt().contains("kh_plume")) {
                int color = itemStack.getNbt().getInt("kh_plume");
                float r = (color >> 16 & 0xFF) / 255.0F;
                float g = (color >> 8 & 0xFF) / 255.0F;
                float b = (color & 0xFF) / 255.0F;
                vertices.quad(entry, quad, r, g, b, light, overlay);
            } else {
                vertices.quad(entry, quad, 1.0F, 1.0F, 1.0F, light, overlay);
            }
        }
    }
}