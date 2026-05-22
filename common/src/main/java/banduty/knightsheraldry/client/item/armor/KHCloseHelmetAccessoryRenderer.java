package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.accessory.KHCloseHelmet;
import banduty.knightsheraldry.model.CloseHelmClosed;
import banduty.knightsheraldry.model.CloseHelmOpened;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
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

public class KHCloseHelmetAccessoryRenderer implements AccessoryRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity entity, ItemStack accessoryStack, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model;
        if (!(accessoryStack.getItem() instanceof KHCloseHelmet khCloseHelmet)) return;
        if (khCloseHelmet.hasOpenVisor(accessoryStack)) {
            boolean current = accessoryStack.getOrDefault(SCDataComponents.VISOR_OPEN.get(), false);
            if (current) model = new CloseHelmOpened(CloseHelmOpened.getTexturedModelData().bakeRoot());
            else model = new CloseHelmClosed(CloseHelmClosed.getTexturedModelData().bakeRoot());
        } else model = new CloseHelmClosed(CloseHelmClosed.getTexturedModelData().bakeRoot());
        contextModel.copyPropertiesTo(model);
        model.setupAnim(entity, entity.walkAnimation.position(), entity.walkAnimation.speed(),
                (float) entity.tickCount + Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true),
                entity.getYHeadRot() - entity.yBodyRot,
                entity.getXRot());
        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" +
                        BuiltInRegistries.ITEM.getKey(accessoryStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(accessoryStack, -1);
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
    }
}
