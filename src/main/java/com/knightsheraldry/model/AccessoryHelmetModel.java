package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class AccessoryHelmetModel extends BipedEntityModel<LivingEntity> {
    private final ModelPart armorHead;
    public AccessoryHelmetModel(ModelPart root) {
        super(root);
        this.setVisible(false);
        this.armorHead = root.getChild("armorHead");
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.armorHead);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData armorHead = modelPartData.addChild("armorHead", ModelPartBuilder.create().uv(80, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.6F))
                .uv(72, 97).cuboid(-7.0F, -5.2F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
                .uv(56, 118).cuboid(-5.0F, 0.0F, -3.3F, 10.0F, 4.0F, 6.0F, new Dilation(0.66F))
                .uv(86, 84).cuboid(-5.0F, -1.9F, -4.0F, 10.0F, 2.0F, 11.0F, new Dilation(0.0F))
                .uv(104, 33).cuboid(-1.0F, -8.0F, 4.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(104, 35).cuboid(-1.0F, -10.0F, -2.0F, 2.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData frogmouth = armorHead.addChild("frogmouth", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 1.0F, 0.5236F, 0.0F, 0.0F));

        frogmouth.addChild("frogmouth_r1", ModelPartBuilder.create().uv(65, 72).cuboid(-6.0F, -7.5F, -6.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.05F, 1.7F, 0.0F, -0.7854F, 0.0F));

        armorHead.addChild("nasal", ModelPartBuilder.create().uv(0, 97).cuboid(-5.0F, -5.0F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F))
                .uv(14, 108).cuboid(-1.0F, -9.0F, -5.0F, 2.0F, 4.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 108).cuboid(-5.0F, -9.0F, -1.0F, 10.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 114).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(20, 114).cuboid(-1.0F, -4.0F, -5.0F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData visor = armorHead.addChild("visor", ModelPartBuilder.create().uv(83, 16).cuboid(-4.0F, -1.75F, -1.9F, 8.0F, 3.0F, 4.0F, new Dilation(0.65F)), ModelTransform.pivot(0.0F, -6.0F, -2.0F));

        ModelPartData houndskul = visor.addChild("houndskul", ModelPartBuilder.create().uv(100, 58).cuboid(0.5F, -3.1F, -5.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(100, 58).cuboid(-3.5F, -3.1F, -5.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 2.0F));

        houndskul.addChild("cone_2_r1", ModelPartBuilder.create().uv(103, 62).cuboid(-5.6F, -1.23F, -5.6F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        houndskul.addChild("cone_1_r1", ModelPartBuilder.create().uv(118, 57).cuboid(0.0F, -6.5F, -4.75F, 0.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        houndskul.addChild("houndskul_2_r1", ModelPartBuilder.create().uv(108, 58).cuboid(-4.5F, -2.8F, -4.85F, 9.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        houndskul.addChild("houndskul_1_r1", ModelPartBuilder.create().uv(104, 50).cuboid(-4.5F, -6.4F, -5.4F, 9.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        ModelPartData great_bascinet = visor.addChild("great_bascinet", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.0F, 2.0F));

        great_bascinet.addChild("eyeslit_2_r1", ModelPartBuilder.create().uv(100, 60).cuboid(-3.5F, -0.6F, -7.2F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(100, 60).cuboid(0.5F, -0.6F, -7.2F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        great_bascinet.addChild("houndskul_2_r2", ModelPartBuilder.create().uv(104, 50).cuboid(-4.5F, -6.4F, -5.4F, 9.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        ModelPartData cone = great_bascinet.addChild("cone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.0F, -5.75F));

        cone.addChild("cube_r1", ModelPartBuilder.create().uv(39, 79).cuboid(-4.5F, -1.5F, -1.2F, 9.0F, 4.0F, 4.0F, new Dilation(0.01F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.7854F, 0.0F, 0.0F));

        ModelPartData maximillian = visor.addChild("maximillian", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.0F, 2.0F));

        maximillian.addChild("maximillian_2_r1", ModelPartBuilder.create().uv(64, 67).cuboid(-4.5F, -2.8F, -4.85F, 9.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        maximillian.addChild("maximillian_1_r1", ModelPartBuilder.create().uv(60, 59).cuboid(-4.5F, -6.8F, -5.4F, 9.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        ModelPartData ridge_1 = maximillian.addChild("ridge_1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.5F, -4.75F));

        ridge_1.addChild("cube_r2", ModelPartBuilder.create().uv(79, 116).cuboid(-4.5F, -0.5F, -0.5F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        ModelPartData ridge_2 = maximillian.addChild("ridge_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.25F, -5.0F));

        ridge_2.addChild("cube_r3", ModelPartBuilder.create().uv(79, 116).cuboid(-4.5F, -0.5F, -0.5F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        ModelPartData ridge_3 = maximillian.addChild("ridge_3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -0.95F, -5.0F));

        ridge_3.addChild("cube_r4", ModelPartBuilder.create().uv(79, 116).cuboid(-4.5F, -0.5F, -0.5F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        ModelPartData armet = visor.addChild("armet", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.0F, 2.0F));

        armet.addChild("armet_2_r1", ModelPartBuilder.create().uv(80, 50).cuboid(-4.5F, -6.4F, -5.4F, 9.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        armet.addChild("armet_1_r1", ModelPartBuilder.create().uv(104, 0).cuboid(-4.5F, -4.4F, -4.5F, 9.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        ModelPartData barbute = visor.addChild("barbute", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.0F, 2.0F));

        barbute.addChild("barbute_3_r1", ModelPartBuilder.create().uv(116, 122).cuboid(0.39F, -4.0F, -6.2F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.7F, 1.8F, -0.9F, 0.0F, -0.3491F, -0.1309F));

        barbute.addChild("barbute_2_r1", ModelPartBuilder.create().uv(104, 122).cuboid(-2.39F, -4.0F, -6.2F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.2F, 1.6F, -0.2F, 0.0F, 0.3491F, 0.1309F));

        barbute.addChild("barbute_2_r2", ModelPartBuilder.create().uv(114, 67).cuboid(-1.61F, -8.0F, -6.0F, 5.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, -0.2618F, 0.0F));

        barbute.addChild("barbute_1_r1", ModelPartBuilder.create().uv(100, 67).cuboid(-3.39F, -8.0F, -6.0F, 5.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 0.2618F, 0.0F));

        ModelPartData barbute_nose = barbute.addChild("barbute_nose", ModelPartBuilder.create(), ModelTransform.of(0.0F, -3.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        barbute_nose.addChild("barbute_3_r2", ModelPartBuilder.create().uv(120, 79).cuboid(-5.0196F, -3.5174F, -5.0196F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.9F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData barbute_nose2 = barbute.addChild("barbute_nose2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        barbute_nose2.addChild("barbute_4_r1", ModelPartBuilder.create().uv(120, 79).cuboid(-4.95F, -3.5F, -4.95F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -0.1F, 0.0F, -0.7854F, 0.0F));

        ModelPartData sallet = visor.addChild("sallet", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.0F, 2.0F));

        sallet.addChild("sallet_2_r1", ModelPartBuilder.create().uv(98, 77).cuboid(-5.0F, -1.4F, -5.9F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(73, 87).cuboid(-4.5F, -6.4F, -5.4F, 9.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorHead.copyTransform(this.head);
        this.armorHead.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}