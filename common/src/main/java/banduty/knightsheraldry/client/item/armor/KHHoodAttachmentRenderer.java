package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.items.armor.attachment.KHHood;
import banduty.knightsheraldry.model.CloakHoodModel;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class KHHoodAttachmentRenderer implements ArmorAttachmentRenderer {
    private CloakHoodModel model;

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, LivingEntity livingEntity,
                       ItemStack itemStack, HumanoidModel<LivingEntity> humanoidModel,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean overHelmet = KHHood.hasHelmet(SCUnderArmor.getArmorAttachments(livingEntity.getItemBySlot(EquipmentSlot.HEAD)));
        renderHood(poseStack, multiBufferSource, light, livingEntity, itemStack, humanoidModel, overHelmet);
    }

    /** Shared by the attachment path and the Fabric renderer for a hood worn straight on the head. */
    public void renderHood(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, LivingEntity livingEntity,
                           ItemStack itemStack, HumanoidModel<LivingEntity> humanoidModel, boolean overHelmet) {
        if (!(itemStack.getItem() instanceof KHHood hood)) return;
        humanoidModel.copyPropertiesTo(getModel());
        model.setupAnim(livingEntity, 0, 0, 0, 0, 0);
        model.renderToBuffer(poseStack,
                multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(KHHood.getTexture(itemStack, overHelmet))),
                light, OverlayTexture.NO_OVERLAY, DyedItemColor.getOrDefault(itemStack, hood.getDefaultColor()));
    }

    public CloakHoodModel getModel() {
        if (model == null) model = new CloakHoodModel(CloakHoodModel.getTexturedModelData().bakeRoot());
        return model;
    }
}
