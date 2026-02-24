package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.model.HelmetDecoModel;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
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

        renderHelmetDecoIfNeeded(entity, stack, poseStack, multiBufferSource, light);

        if (isSurcoat(stack)) {
            renderArmorPart(poseStack, multiBufferSource, light, model, SURCOAT_OVERLAY_TEXTURE);
        }

        if (stack.getItem() == ModItems.CIVILIAN_SURCOAT.get() || stack.getItem() == ModItems.GIORNEA.get()) {
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

        model.renderToBuffer(
                poseStack,
                consumer,
                light,
                OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F
        );
    }

    private static void renderHelmetDecoIfNeeded(LivingEntity entity, ItemStack stack, PoseStack poseStack,
                                                 MultiBufferSource multiBufferSource, int light) {
        CompoundTag nbt = stack.getTag();
        if (nbt == null) return;

        String basePath = "textures/entity/accessories/deco/";

        for (HelmetDeco deco : HelmetDeco.getValues()) {
            String key = deco.getNbtKey();

            if (deco.color() == 2) {
                if (nbt.contains(key) && nbt.getCompound(key).contains("color1")) {
                    float[] color = getColorFromNbt(nbt.getCompound(key).getInt("color1"));
                    ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, basePath + key + "_base.png");
                    renderHelmetDeco(entity, poseStack, multiBufferSource, light, texture, color);
                }
                if (nbt.contains(key) && nbt.getCompound(key).contains("color2")) {
                    float[] color = getColorFromNbt(nbt.getCompound(key).getInt("color2"));
                    ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, basePath + key + "_stripe.png");
                    renderHelmetDeco(entity, poseStack, multiBufferSource, light, texture, color);
                }
                continue;
            }

            if (!nbt.contains(key)) continue;
            float[] color = deco.color() == 1 ? getColorFromNbt(nbt.getInt(key)) : new float[]{1.0F, 1.0F, 1.0F};
            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, basePath + key + ".png");
            renderHelmetDeco(entity, poseStack, multiBufferSource, light, texture, color);
        }
    }

    private static void renderHelmetDeco(LivingEntity entity, PoseStack poseStack,
                                         MultiBufferSource multiBufferSource, int light,
                                         ResourceLocation texture, float[] color) {
        HumanoidModel<LivingEntity> model =
                new HelmetDecoModel(HelmetDecoModel.getTexturedModelData().bakeRoot());
        VertexConsumer consumer =
                multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(texture));

        renderModel(entity, model, poseStack, consumer, light, color);
    }


    private static void renderModel(LivingEntity entity, HumanoidModel<LivingEntity> model,
                                    PoseStack poseStack, VertexConsumer consumer, int light, float[] color) {
        AccessoryRenderer.followBodyRotations(entity, model);
        model.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY,
                color[0], color[1], color[2], 1.0F);
    }

    private ResourceLocation getVariantTexture(ResourceLocation baseTexture, String stackName) {
        String variantPrefix = getVariantPrefix(stackName);
        if (variantPrefix == null) {
            return baseTexture;
        }

        String path = baseTexture.getPath();

        int lastSlashIndex = path.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            String newPath = variantPrefix + path;
            return ResourceLocation.fromNamespaceAndPath(baseTexture.getNamespace(), newPath);
        }

        String directory = path.substring(0, lastSlashIndex + 1);
        String filename = path.substring(lastSlashIndex + 1);
        String newPath = directory + variantPrefix + filename;

        return ResourceLocation.fromNamespaceAndPath(baseTexture.getNamespace(), newPath);
    }

    private String getVariantPrefix(String stackName) {
        if (stackName.contains("dark_")) {
            return "dark_";
        } else if (stackName.contains("golden_")) {
            return "golden_";
        }
        return null;
    }

    private static boolean isSurcoat(ItemStack stack) {
        return stack.getItem() == ModItems.SURCOAT.get() || stack.getItem() == ModItems.SURCOAT_SLEEVELESS.get();
    }

    private static float[] getColorFromNbt(int colorInt) {
        return new float[]{
                (colorInt >> 16 & 255) / 255.0F,
                (colorInt >> 8 & 255) / 255.0F,
                (colorInt & 0xFF) / 255.0F
        };
    }

    private static ResourceLocation getResourceLocationWithSuffix(ItemStack stack) {
        if (!(stack.getItem() instanceof SCAccessoryItem item)) return ResourceLocation.tryParse("");
        String path = item.getTexturePath(stack).getPath().replace(".png", "") + "_overlay" + ".png";
        return ResourceLocation.fromNamespaceAndPath(item.getTexturePath(stack).getNamespace(), path);
    }
}