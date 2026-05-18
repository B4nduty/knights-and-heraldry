package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.model.HelmetDecoModel;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class RenderOverlayAndAdditionsHandler implements RenderOverlayAndAdditionsEvents {
    private static final ResourceLocation SURCOAT_OVERLAY_TEXTURE = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/surcoat_overlay.png");
    private static final ResourceLocation CIVILIAN_BELT_TEXTURE = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/civilian_belt.png");

    @Override
    public void onRenderOverlayAndAdditionsEvents(LivingEntity entity, ItemStack stack,
                                                  PoseStack poseStack, MultiBufferSource multiBufferSource,
                                                  int light, HumanoidModel<LivingEntity> model) {
        if (!(stack.getItem() instanceof SCAccessoryItem scAccessoryItem)) return;

        renderHelmetDecoIfNeeded(entity, stack, poseStack, multiBufferSource, light);

        if (isSurcoat(stack)) {
            ArmorRenderer.renderPart(poseStack, multiBufferSource, light, stack, model, SURCOAT_OVERLAY_TEXTURE);
        }

        if (stack.getItem() == KHItems.CIVILIAN_SURCOAT.get() || stack.getItem() == KHItems.GIORNEA.get()) {
            ArmorRenderer.renderPart(poseStack, multiBufferSource, light, stack, model, CIVILIAN_BELT_TEXTURE);
        }

        if (scAccessoryItem.getRenderSettings(stack).overlay()) {
            ArmorRenderer.renderPart(poseStack, multiBufferSource, light, stack, model, getResourceLocationWithSuffix(stack));
        }
    }

    private void renderHelmetDecoIfNeeded(LivingEntity entity, ItemStack stack, PoseStack poseStack,
                                          MultiBufferSource multiBufferSource, int light) {
        String basePath = "textures/entity/accessories/deco/";

        for (HelmetDeco deco : HelmetDeco.all()) {
            String key = deco.getNbtKey();

            int i = 0;
            for (Integer color : deco.colors()) {
                String path = basePath + key + "_" + (deco.colors().size() <= 1 ? (i == 1 ? "_base" : "stripe") : "") + ".png";
                ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, path);
                renderHelmetDeco(entity, poseStack, multiBufferSource, light, texture, color);
                i++;
            }
        }
    }

    private void renderHelmetDeco(LivingEntity entity, PoseStack poseStack,
                                  MultiBufferSource multiBufferSource, int light,
                                  ResourceLocation texture, int color) {
        HumanoidModel<LivingEntity> model =
                new HelmetDecoModel(HelmetDecoModel.getTexturedModelData().bakeRoot());
        VertexConsumer consumer =
                multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(texture));

        renderModel(entity, model, poseStack, consumer, light, color);
    }


    private void renderModel(LivingEntity entity, HumanoidModel<LivingEntity> model,
                             PoseStack poseStack, VertexConsumer consumer, int light, int color) {
        AccessoryRenderer.followBodyRotations(entity, model);
        model.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
    }

    private boolean isSurcoat(ItemStack stack) {
        return stack.getItem() == KHItems.SURCOAT.get() || stack.getItem() == KHItems.SURCOAT_SLEEVELESS.get();
    }

    private ResourceLocation getResourceLocationWithSuffix(ItemStack stack) {
        if (!(stack.getItem() instanceof SCAccessoryItem item)) return ResourceLocation.fromNamespaceAndPath("", "");
        String path = item.getTexturePath(stack).getPath().replace(".png", "") + "_overlay" + ".png";
        return ResourceLocation.fromNamespaceAndPath(item.getTexturePath(stack).getNamespace(), path);
    }
}