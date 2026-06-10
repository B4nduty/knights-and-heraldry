package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHSalletHelmet;
import banduty.knightsheraldry.model.Sallet;
import banduty.knightsheraldry.model.SalletOpen;
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

public class KHSalletHelmetAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, LivingEntity livingEntity,
                       ItemStack itemStack, HumanoidModel<LivingEntity> humanoidModel,
                       float v, float v1, float v2, float v3, float v4, float v5) {
        HumanoidModel<LivingEntity> model;
        if (!(itemStack.getItem() instanceof KHSalletHelmet khSalletHelmet)) return;
        if (khSalletHelmet.hasOpenVisor(itemStack)) {
            boolean current = itemStack.getOrDefault(SCDataComponents.VISOR_OPEN.get(), false);
            if (current) model = new SalletOpen(SalletOpen.getTexturedModelData().bakeRoot());
            else model = new Sallet(Sallet.getTexturedModelData().bakeRoot());
        } else model = new Sallet(Sallet.getTexturedModelData().bakeRoot());
        humanoidModel.copyPropertiesTo(model);
        String name = BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath();

        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                        name + ".png")));
        int color = DyedItemColor.getOrDefault(itemStack, -1);
        model.renderToBuffer(poseStack, baseConsumer, i, OverlayTexture.NO_OVERLAY, color);
    }
}
