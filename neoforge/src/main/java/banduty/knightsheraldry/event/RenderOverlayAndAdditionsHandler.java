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
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class RenderOverlayAndAdditionsHandler {
    private static final ResourceLocation SURCOAT_OVERLAY_TEXTURE = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/surcoat_overlay.png");
    private static final ResourceLocation CIVILIAN_BELT_TEXTURE = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/civilian_belt.png");

    @SubscribeEvent
    public static void onRenderOverlayAndAdditionsEvents(RenderOverlayAndAdditionsEvents event) {
        ItemStack stack = event.getStack();

        if (!(stack.getItem() instanceof SCAccessoryItem scAccessoryItem)) return;

        LivingEntity entity = event.getEntity();
        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource multiBufferSource = event.getMultiBufferSource();
        int light = event.getLight();
        HumanoidModel<LivingEntity> model = event.getModel();

        renderHelmetDecoIfNeeded(entity, poseStack, multiBufferSource, light);

        if (isSurcoat(stack)) {
            renderArmorPart(poseStack, multiBufferSource, light, model, SURCOAT_OVERLAY_TEXTURE);
        }

        if (stack.getItem() == KHItems.CIVILIAN_SURCOAT.get() || stack.getItem() == KHItems.GIORNEA.get()) {
            renderArmorPart(poseStack, multiBufferSource, light, model, CIVILIAN_BELT_TEXTURE);
        }

        if (scAccessoryItem.getRenderSettings(stack).overlay()) {
            renderArmorPart(poseStack, multiBufferSource, light, model, getResourceLocationWithSuffix(stack));
        }
    }

    private static void renderArmorPart(PoseStack poseStack,
                                        MultiBufferSource buffer,
                                        int light,
                                        HumanoidModel<LivingEntity> model,
                                        ResourceLocation texture) {

        VertexConsumer consumer = buffer.getBuffer(RenderType.armorCutoutNoCull(texture));

        model.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, -1);
    }

    private static void renderHelmetDecoIfNeeded(LivingEntity entity, PoseStack poseStack,
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

    private static void renderHelmetDeco(LivingEntity entity, PoseStack poseStack,
                                         MultiBufferSource multiBufferSource, int light,
                                         ResourceLocation texture, int color) {
        HumanoidModel<LivingEntity> model =
                new HelmetDecoModel(HelmetDecoModel.getTexturedModelData().bakeRoot());
        VertexConsumer consumer =
                multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(texture));

        renderModel(entity, model, poseStack, consumer, light, color);
    }


    private static void renderModel(LivingEntity entity, HumanoidModel<LivingEntity> model,
                                    PoseStack poseStack, VertexConsumer consumer, int light, int color) {
        AccessoryRenderer.followBodyRotations(entity, model);
        model.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
    }

    private static boolean isSurcoat(ItemStack stack) {
        return stack.getItem() == KHItems.SURCOAT.get() || stack.getItem() == KHItems.SURCOAT_SLEEVELESS.get();
    }

    private static ResourceLocation getResourceLocationWithSuffix(ItemStack stack) {
        if (!(stack.getItem() instanceof SCAccessoryItem item)) return ResourceLocation.tryParse("");
        String path = item.getTexturePath(stack).getPath().replace(".png", "") + "_overlay" + ".png";
        return ResourceLocation.fromNamespaceAndPath(item.getTexturePath(stack).getNamespace(), path);
    }
}