package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHBlackSalletHelmet;
import banduty.knightsheraldry.model.BlackSalletClosed;
import banduty.knightsheraldry.model.BlackSalletOpened;
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

public class KHBlackSalletHelmetAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, LivingEntity livingEntity,
                       ItemStack itemStack, HumanoidModel<LivingEntity> humanoidModel,
                       float v, float v1, float v2, float v3, float v4, float v5) {
        HumanoidModel<LivingEntity> model;
        if (!(itemStack.getItem() instanceof KHBlackSalletHelmet khBlackSalletHelmet)) return;
        if (khBlackSalletHelmet.hasOpenVisor(itemStack)) {
            boolean current = itemStack.getOrDefault(SCDataComponents.VISOR_OPEN.get(), false);
            if (current) model = new BlackSalletOpened(BlackSalletOpened.getTexturedModelData().bakeRoot());
            else model = new BlackSalletClosed(BlackSalletClosed.getTexturedModelData().bakeRoot());
        } else model = new BlackSalletClosed(BlackSalletClosed.getTexturedModelData().bakeRoot());
        humanoidModel.copyPropertiesTo(model);
        String name = BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath();

        String customName = itemStack.getHoverName().getString();

        if (customName.endsWith(" Rat")) {
            name = name + "_rat";
        }

        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                        name + ".png")));
        int color = DyedItemColor.getOrDefault(itemStack, -1);
        model.renderToBuffer(poseStack, baseConsumer, i, OverlayTexture.NO_OVERLAY, color);
    }
}
