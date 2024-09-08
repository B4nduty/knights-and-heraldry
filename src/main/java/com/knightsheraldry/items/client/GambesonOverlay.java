package com.knightsheraldry.items.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.GambesonItem;
import com.knightsheraldry.mixin.DyeableGeoArmorRendererInvoker;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class GambesonOverlay extends GeoRenderLayer<GambesonItem> {
    private static final Identifier TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/armor/gambeson_overlay.png");

    public GambesonOverlay(GeoRenderer<GambesonItem> entityRenderer) {
        super(entityRenderer);
    }

    private GeoBone getBoneYouAreInterestedIn(BakedGeoModel bakedModel) {
        return bakedModel.topLevelBones().stream().findFirst().orElse(null);
    }

    @Override
    public void render(MatrixStack poseStack, GambesonItem animatable, BakedGeoModel bakedModel, RenderLayer renderType,
                       VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer armorRenderType = RenderLayer.getArmorCutoutNoCull(TEXTURE);
        GeoBone bone = getBoneYouAreInterestedIn(bakedModel);
        DyeableGeoArmorRendererInvoker invoker = (DyeableGeoArmorRendererInvoker) getRenderer();

        Color colorObj = invoker.invokeGetColorForBone(bone);
        int color = colorObj.getColor();
        var r = ColorHelper.Argb.getRed(color);
        var g = ColorHelper.Argb.getGreen(color);
        var b = ColorHelper.Argb.getBlue(color);

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, armorRenderType,
                bufferSource.getBuffer(armorRenderType), partialTick, packedLight, OverlayTexture.DEFAULT_UV,
                (float) r / 375, (float) g / 375, (float) b / 375, 1);
    }
}