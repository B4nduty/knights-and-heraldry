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
public class AccessoryBootsModel extends HumanoidModel<LivingEntity> {
    private final ModelPart armorRightBoot;
    private final ModelPart armorLeftBoot;
    public AccessoryBootsModel(ModelPart root) {
        super(root);
        this.setAllVisible(false);
        this.armorRightBoot = root.getChild("armorRightBoot");
        this.armorLeftBoot = root.getChild("armorLeftBoot");
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.armorRightBoot, this.armorLeftBoot);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition armorRightBoot = modelPartData.addOrReplaceChild("armorRightBoot", CubeListBuilder.create().texOffs(12, 89).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        armorRightBoot.addOrReplaceChild("right_sabaton_r1", CubeListBuilder.create().texOffs(28, 93).addBox(-3.25F, 11.25F, -3.25F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition armorLeftBoot = modelPartData.addOrReplaceChild("armorLeftBoot", CubeListBuilder.create().texOffs(12, 89).mirror().addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

        armorLeftBoot.addOrReplaceChild("left_sabaton_r1", CubeListBuilder.create().texOffs(28, 93).addBox(-3.25F, 11.25F, -3.25F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorRightBoot.copyFrom(this.rightLeg);
        this.armorRightBoot.render(poseStack, vertices, light, overlay, red, green, blue, alpha);

        this.armorLeftBoot.copyFrom(this.leftLeg);
        this.armorLeftBoot.render(poseStack, vertices, light, overlay, red, green, blue, alpha);
    }
}