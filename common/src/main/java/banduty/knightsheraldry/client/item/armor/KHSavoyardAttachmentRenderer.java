package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHSavoyard;
import banduty.knightsheraldry.model.SavoyardClosed;
import banduty.knightsheraldry.model.SavoyardOpened;
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

public class KHSavoyardAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity entity, ItemStack itemStack, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model;
        if (!(itemStack.getItem() instanceof KHSavoyard khSavoyard)) return;
        if (khSavoyard.hasOpenVisor(itemStack)) {
            boolean current = itemStack.getOrDefault(SCDataComponents.VISOR_OPEN.get(), false);
            if (current) model = new SavoyardOpened(SavoyardOpened.getTexturedModelData().bakeRoot());
            else model = new SavoyardClosed(SavoyardClosed.getTexturedModelData().bakeRoot());
        } else model = new SavoyardClosed(SavoyardClosed.getTexturedModelData().bakeRoot());
        contextModel.copyPropertiesTo(model);
        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                        BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(itemStack, -1);
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
    }
}
