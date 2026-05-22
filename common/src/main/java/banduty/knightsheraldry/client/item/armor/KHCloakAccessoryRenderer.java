package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.accessory.KHCloak;
import banduty.knightsheraldry.model.CloakHoodModel;
import banduty.stoneycore.client.render.AccessoryRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class KHCloakAccessoryRenderer implements AccessoryRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity entity, ItemStack accessoryStack, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model = new CloakHoodModel(CloakHoodModel.getTexturedModelData().bakeRoot());
        if (!(accessoryStack.getItem() instanceof KHCloak khCloak)) return;
        contextModel.copyPropertiesTo(model);
        model.setupAnim(entity, entity.walkAnimation.position(), entity.walkAnimation.speed(),
                (float) entity.tickCount + Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true),
                entity.getYHeadRot() - entity.yBodyRot,
                entity.getXRot());
        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" +
                        BuiltInRegistries.ITEM.getKey(accessoryStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(accessoryStack, khCloak.getDefaultColor());
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);

        if (khCloak.hasOverlay()) {
            VertexConsumer baseConsumer2 = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                    ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" +
                            BuiltInRegistries.ITEM.getKey(accessoryStack.getItem()).getPath() + "_overlay.png")));
            model.renderToBuffer(poseStack, baseConsumer2, packedLight, OverlayTexture.NO_OVERLAY, -1);
        }
    }
}
