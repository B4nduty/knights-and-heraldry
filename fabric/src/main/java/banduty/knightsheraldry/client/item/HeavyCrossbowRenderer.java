package banduty.knightsheraldry.client.item;

import banduty.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import banduty.stoneycore.util.weaponutil.SCRangeWeaponUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HeavyCrossbowRenderer extends GeoItemRenderer<HeavyCrossbow> {
    public HeavyCrossbowRenderer() {
        super(new HeavyCrossbowModel());
    }

    @Override
    public void preRender(PoseStack poseStack, HeavyCrossbow animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource,
                          VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha) {

        if (this.renderPerspective != null && this.renderPerspective.firstPerson()) {
            boolean charged = SCRangeWeaponUtil
                    .getWeaponState(this.currentItemStack)
                    .isCharged();

            if (charged) {
                poseStack.translate(-0.5625, 0, 0.1);
            }
        }

        super.preRender(poseStack, animatable, model, bufferSource,
                buffer, isReRender, partialTick,
                packedLight, packedOverlay,
                red, green, blue, alpha);
    }
}