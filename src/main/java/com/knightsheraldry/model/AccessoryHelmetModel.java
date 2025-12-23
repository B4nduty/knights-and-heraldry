package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class AccessoryHelmetModel extends HumanoidModel<LivingEntity> {
    private final ModelPart armorHead;
    public AccessoryHelmetModel(ModelPart root) {
        super(root);
        this.setAllVisible(false);
        this.armorHead = root.getChild("armorHead");
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.armorHead);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition armorHead = modelPartData.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F))
                .texOffs(72, 97).addBox(-7.0F, -5.2F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(86, 84).addBox(-5.0F, -2.7F, -4.0F, 10.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(104, 33).addBox(-1.0F, -8.0F, 4.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 35).addBox(-1.0F, -10.0F, -2.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-4.0F, -5.0F, -7.4142F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition frogmouth = armorHead.addOrReplaceChild("frogmouth", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.5236F, 0.0F, 0.0F));

        frogmouth.addOrReplaceChild("frogmouth_r1", CubeListBuilder.create().texOffs(65, 72).addBox(-6.0F, -7.5F, -6.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.05F, 1.7F, 0.0F, -0.7854F, 0.0F));

        armorHead.addOrReplaceChild("nasal", CubeListBuilder.create().texOffs(0, 97).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(14, 108).addBox(-1.0F, -9.0F, -5.0F, 2.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 108).addBox(-5.0F, -9.0F, -1.0F, 10.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 114).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 114).addBox(-1.0F, -4.0F, -5.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition visor = armorHead.addOrReplaceChild("visor", CubeListBuilder.create().texOffs(83, 16).addBox(-4.0F, -1.75F, -1.9F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(0.0F, -6.0F, -2.0F));

        PartDefinition houndskul = visor.addOrReplaceChild("houndskul", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 2.0F));

        houndskul.addOrReplaceChild("eyeslit_1_r1", CubeListBuilder.create().texOffs(100, 58).addBox(0.5F, -2.6F, -5.7F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(100, 58).addBox(-3.5F, -2.6F, -5.7F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        houndskul.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(61, 8).addBox(-3.95F, -1.15F, -1.2F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -1.5F, -5.75F, 0.7854F, 0.6981F, 1.5708F));

        houndskul.addOrReplaceChild("houndskul_2_r1", CubeListBuilder.create().texOffs(108, 58).addBox(-4.5F, -2.8F, -4.85F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        houndskul.addOrReplaceChild("houndskul_1_r1", CubeListBuilder.create().texOffs(104, 50).addBox(-4.5F, -6.4F, -5.4F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        houndskul.addOrReplaceChild("eyeslit_3_r1", CubeListBuilder.create().texOffs(107, 24).addBox(-9.0F, -7.21F, -1.9F, 8.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F))
                .texOffs(107, 28).addBox(-9.0F, -7.2F, -1.45F, 8.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition great_bascinet = visor.addOrReplaceChild("great_bascinet", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 2.0F));

        great_bascinet.addOrReplaceChild("eyeslit_2_r1", CubeListBuilder.create().texOffs(100, 60).addBox(-3.5F, -0.6F, -7.2F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(100, 60).addBox(0.5F, -0.6F, -7.2F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        great_bascinet.addOrReplaceChild("houndskul_2_r2", CubeListBuilder.create().texOffs(104, 50).addBox(-4.5F, -6.4F, -5.4F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition cone = great_bascinet.addOrReplaceChild("cone", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -5.75F));

        cone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(39, 79).addBox(-4.5F, -1.5F, -1.2F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition maximillian = visor.addOrReplaceChild("maximillian", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 2.0F));

        maximillian.addOrReplaceChild("maximillian_2_r1", CubeListBuilder.create().texOffs(64, 67).addBox(-4.5F, -2.8F, -4.85F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.1F, 0.1309F, 0.0F, 0.0F));

        maximillian.addOrReplaceChild("maximillian_1_r1", CubeListBuilder.create().texOffs(60, 59).addBox(-4.5F, -6.8F, -5.4F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition ridge_2 = maximillian.addOrReplaceChild("ridge_2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.25F, -4.0F, 0.0F, -0.2618F, 0.0F));

        ridge_2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(85, 116).addBox(-2.5F, -0.2172F, -3.0328F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3882F, 2.0F, -0.0511F, 0.7854F, 0.0F, 0.0F));

        ridge_2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(84, 116).addBox(-3.5F, -0.0793F, -2.0707F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3307F, 1.0F, -1.0435F, 0.7854F, 0.0F, 0.0F));

        ridge_2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 116).addBox(-4.0F, -0.6414F, -1.8586F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7666F, 0.0F, -1.0351F, 0.7854F, 0.0F, 0.0F));

        PartDefinition ridge_5 = maximillian.addOrReplaceChild("ridge_5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.25F, -4.0F, 0.0F, 0.2618F, 0.0F));

        ridge_5.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(85, 116).mirror().addBox(-0.5F, -0.2172F, -3.0328F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3882F, 2.0F, -0.0511F, 0.7854F, 0.0F, 0.0F));

        ridge_5.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(84, 116).mirror().addBox(-0.5F, -0.0793F, -2.0707F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.3307F, 1.0F, -1.0435F, 0.7854F, 0.0F, 0.0F));

        ridge_5.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(84, 116).mirror().addBox(1.0F, -0.6414F, -1.8586F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.7666F, 0.0F, -1.0351F, 0.7854F, 0.0F, 0.0F));

        PartDefinition middle_ridge = visor.addOrReplaceChild("middle_ridge", CubeListBuilder.create(), PartPose.offset(0.0F, 30.0F, 2.0F));

        middle_ridge.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(102, 116).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.45F, -5.0F, 0.7854F, 0.0F, 0.0F));

        middle_ridge.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(102, 116).addBox(-0.5F, -0.15F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.15F, -5.0F, 0.7854F, 0.0F, 0.0F));

        middle_ridge.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(102, 116).addBox(-0.5F, -0.5F, -2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.85F, -4.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition armet = visor.addOrReplaceChild("armet", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 2.0F));

        armet.addOrReplaceChild("armet_2_r1", CubeListBuilder.create().texOffs(80, 50).addBox(-4.5F, -6.4F, -5.4F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        armet.addOrReplaceChild("armet_1_r1", CubeListBuilder.create().texOffs(104, 0).addBox(-4.5F, -4.4F, -4.5F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition barbute = visor.addOrReplaceChild("barbute", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 2.0F));

        barbute.addOrReplaceChild("barbute_3_r1", CubeListBuilder.create().texOffs(116, 122).addBox(0.39F, -4.0F, -6.2F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 1.8F, -0.9F, 0.0F, -0.3491F, -0.1309F));

        barbute.addOrReplaceChild("barbute_2_r1", CubeListBuilder.create().texOffs(104, 122).addBox(-2.39F, -4.0F, -6.2F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 1.6F, -0.2F, 0.0F, 0.3491F, 0.1309F));

        barbute.addOrReplaceChild("barbute_2_r2", CubeListBuilder.create().texOffs(114, 67).addBox(-1.61F, -8.0F, -6.0F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, -0.2618F, 0.0F));

        barbute.addOrReplaceChild("barbute_1_r1", CubeListBuilder.create().texOffs(100, 67).addBox(-3.39F, -8.0F, -6.0F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.2618F, 0.0F));

        PartDefinition barbute_nose = barbute.addOrReplaceChild("barbute_nose", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        barbute_nose.addOrReplaceChild("barbute_3_r2", CubeListBuilder.create().texOffs(120, 79).addBox(-5.0196F, -3.5174F, -5.0196F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.9F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition barbute_nose2 = barbute.addOrReplaceChild("barbute_nose2", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

        barbute_nose2.addOrReplaceChild("barbute_4_r1", CubeListBuilder.create().texOffs(120, 79).addBox(-4.95F, -3.5F, -4.95F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -0.1F, 0.0F, -0.7854F, 0.0F));

        PartDefinition sallet = visor.addOrReplaceChild("sallet", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 2.0F));

        sallet.addOrReplaceChild("sallet_1_r1", CubeListBuilder.create().texOffs(73, 87).addBox(-4.5F, -1.3038F, -2.7645F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(98, 77).addBox(-5.0F, 3.4962F, -3.2645F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -2.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition ridged = armorHead.addOrReplaceChild("ridged", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.25F, -4.0F, 0.0F, 0.2618F, 0.0F));

        ridged.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(66, 5).mirror().addBox(-0.3784F, 0.2071F, -0.6287F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3882F, 3.0F, -2.0511F, 1.5708F, 0.0F, 0.0F));

        ridged.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(65, 5).mirror().addBox(-0.4359F, -0.15F, -0.5858F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.3307F, 2.0F, -2.0435F, 1.5708F, 0.0F, 0.0F));

        ridged.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(65, 5).mirror().addBox(-2.0F, -0.5F, -0.5858F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.7666F, 1.0F, -2.0351F, 1.5708F, 0.0F, 0.0F));

        PartDefinition ridgedd = armorHead.addOrReplaceChild("ridgedd", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.25F, -4.0F, 0.0F, -0.2618F, 0.0F));

        ridgedd.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(66, 5).addBox(-2.5F, 0.2071F, -0.6287F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3882F, 3.0F, -2.0511F, 1.5708F, 0.0F, 0.0F));

        ridgedd.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(65, 5).addBox(-3.5F, -0.15F, -0.5858F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3307F, 2.0F, -2.0435F, 1.5708F, 0.0F, 0.0F));

        ridgedd.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(65, 5).addBox(-2.0F, -0.5F, -0.5858F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7666F, 1.0F, -2.0351F, 1.5708F, 0.0F, 0.0F));

        PartDefinition mid_ridge = armorHead.addOrReplaceChild("mid_ridge", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        mid_ridge.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(83, 5).addBox(-0.5F, -0.5F, -0.5858F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.25F, -6.0F, 1.5708F, 0.0F, 0.0F));

        mid_ridge.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(83, 5).addBox(-0.5F, -0.15F, -0.5858F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -25.25F, -6.0F, 1.5708F, 0.0F, 0.0F));

        mid_ridge.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(83, 5).addBox(-0.5F, 0.2071F, -0.6287F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.25F, -6.0F, 1.5708F, 0.0F, 0.0F));

        armorHead.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(56, 118).addBox(-5.0F, -24.0F, -3.3F, 10.0F, 4.0F, 6.0F, new CubeDeformation(0.66F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorHead.copyFrom(this.head);
        this.armorHead.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}