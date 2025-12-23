package com.knightsheraldry.model;

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
public class HelmetDecoModel extends HumanoidModel<LivingEntity> {
	private final ModelPart armorHead;
	public HelmetDecoModel(ModelPart root) {
        super(root);
        this.setAllVisible(false);
		this.armorHead = root.getChild("armorHead");
	}
	public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition modelPartData = modelData.getRoot();
        PartDefinition armorHead = modelPartData.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(0, 43).addBox(0.0F, -22.5F, -11.0F, 0.0F, 22.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(45, 88).addBox(-11.0F, -22.5F, 0.0F, 22.0F, 22.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		armorHead.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(81, 110).addBox(-1.5F, 1.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 5.5F, 0.5672F, 0.0F, 0.0F));

		armorHead.addOrReplaceChild("crown_r1", CubeListBuilder.create().texOffs(48, 110).addBox(-5.5F, -10.0F, -6.05F, 11.0F, 5.0F, 11.0F, new CubeDeformation(0.05F))
		.texOffs(0, 110).addBox(-6.0F, -7.0F, -6.85F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        armorHead.addOrReplaceChild("plume_r1", CubeListBuilder.create().texOffs(0, 88).addBox(-11.0F, -46.0F, 0.0F, 22.0F, 22.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(45, 43).addBox(0.0F, -46.0F, -11.0F, 0.0F, 22.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 23.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition fluff = armorHead.addOrReplaceChild("fluff", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        fluff.addOrReplaceChild("plume_fluffy_r1", CubeListBuilder.create().texOffs(0, -3).addBox(-0.3F, -13.0F, -11.0F, 0.0F, 22.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, -0.2182F, 0.0F));

        fluff.addOrReplaceChild("plume_fluffy_r2", CubeListBuilder.create().texOffs(0, -3).addBox(0.3F, -13.0F, -11.0F, 0.0F, 22.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.2182F, 0.0F));

        fluff.addOrReplaceChild("plume_fluffy_r3", CubeListBuilder.create().texOffs(45, -3).addBox(-0.3F, -46.0F, -11.0F, 0.0F, 22.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-11.0F, -46.0F, -0.3F, 22.0F, 22.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 33.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorHead.copyFrom(this.head);
        this.armorHead.render(poseStack, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}