package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHCageHelmetAttachment;
import banduty.knightsheraldry.model.CageHelmClosed;
import banduty.knightsheraldry.model.CageHelmOpened;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class KHCageHelmetAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity entity, ItemStack itemStack, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model;
        if (!(itemStack.getItem() instanceof KHCageHelmetAttachment khCageHelmet)) return;
        if (khCageHelmet.hasOpenVisor(itemStack)) {
            boolean current = itemStack.getOrDefault(SCDataComponents.VISOR_OPEN.get(), false);
            if (current) model = new CageHelmOpened(CageHelmOpened.getTexturedModelData().bakeRoot());
            else model = new CageHelmClosed(CageHelmClosed.getTexturedModelData().bakeRoot());
        } else model = new CageHelmClosed(CageHelmClosed.getTexturedModelData().bakeRoot());
        contextModel.copyPropertiesTo(model);
        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                        BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(itemStack, -1);
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
    }
}
