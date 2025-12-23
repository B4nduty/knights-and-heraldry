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
public class AccessoryLeggingsModel extends HumanoidModel<LivingEntity> {
    private final ModelPart armorRightLeg;
    private final ModelPart armorLeftLeg;
    public AccessoryLeggingsModel(ModelPart root) {
        super(root);
        this.setAllVisible(false);
        this.armorRightLeg = root.getChild("armorRightLeg");
        this.armorLeftLeg = root.getChild("armorLeftLeg");
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.armorRightLeg, this.armorLeftLeg);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition armorRightLeg = modelPartData.addOrReplaceChild("armorRightLeg", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.36F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));

        armorRightLeg.addOrReplaceChild("right_fan_r1", CubeListBuilder.create().texOffs(112, 25).addBox(-4.55F, 2.0F, 1.6F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 82).addBox(-4.5F, 4.45F, 1.15F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        armorRightLeg.addOrReplaceChild("right_poleyn_r1", CubeListBuilder.create().texOffs(16, 82).addBox(1.5F, 4.45F, 1.15F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition armorLeftLeg = modelPartData.addOrReplaceChild("armorLeftLeg", CubeListBuilder.create().texOffs(0, 74).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.36F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        armorLeftLeg.addOrReplaceChild("left_poleyn_r1", CubeListBuilder.create().texOffs(16, 82).mirror().addBox(-6.5F, 4.45F, 1.15F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        armorLeftLeg.addOrReplaceChild("left_fan_r1", CubeListBuilder.create().texOffs(112, 25).addBox(4.55F, 2.0F, 1.6F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 82).mirror().addBox(-0.5F, 4.45F, 1.15F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorRightLeg.copyFrom(this.rightLeg);
        this.armorRightLeg.render(poseStack, vertices, light, overlay, red, green, blue, alpha);

        this.armorLeftLeg.copyFrom(this.leftLeg);
        this.armorLeftLeg.render(poseStack, vertices, light, overlay, red, green, blue, alpha);
    }
}