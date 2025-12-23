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
public class AccessoryChestplateModel extends HumanoidModel<LivingEntity> {
	private final ModelPart armorBody;
	private final ModelPart armorRightArm;
	private final ModelPart armorLeftArm;

	public AccessoryChestplateModel(ModelPart root) {
		super(root);
		this.setAllVisible(false);
		this.armorBody = root.getChild("armorBody");
		this.armorRightArm = root.getChild("armorRightArm");
		this.armorLeftArm = root.getChild("armorLeftArm");
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.armorBody, this.armorRightArm, this.armorLeftArm);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition modelPartData = modelData.getRoot();
        PartDefinition armorBody = modelPartData.addOrReplaceChild("armorBody", CubeListBuilder.create().texOffs(80, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.4F))
                .texOffs(93, 112).addBox(-5.0F, 0.0F, -3.3F, 10.0F, 4.0F, 6.0F, new CubeDeformation(0.65F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        armorBody.addOrReplaceChild("breastplate_2_r1", CubeListBuilder.create().texOffs(72, 24).addBox(-4.5F, 2.8F, -4.9F, 9.0F, 5.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

        armorBody.addOrReplaceChild("breastplate_1_r1", CubeListBuilder.create().texOffs(72, 16).addBox(-4.5F, 0.0F, -2.65F, 9.0F, 5.0F, 3.0F, new CubeDeformation(-0.01F))
                .texOffs(0, 65).addBox(-4.5F, 9.0F, -0.1F, 9.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        armorBody.addOrReplaceChild("backfauld_r1", CubeListBuilder.create().texOffs(26, 65).addBox(-4.5F, 9.0F, -3.85F, 9.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        armorBody.addOrReplaceChild("leftTasset", CubeListBuilder.create().texOffs(16, 74).mirror().addBox(-2.5F, 0.0F, 0.1F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(3.0F, 11.5F, -3.5F));

        armorBody.addOrReplaceChild("rightTasset", CubeListBuilder.create().texOffs(16, 74).addBox(-1.5F, 0.0F, 0.1F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-3.0F, 11.5F, -3.5F));

        armorBody.addOrReplaceChild("surcoatFront", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 8.5F, -2.5F, -0.4363F, 0.0F, 0.0F));

        armorBody.addOrReplaceChild("surcoatBack", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 8.5F, 2.5F, 0.2618F, 0.0F, 0.0F));

        PartDefinition civilian_surcoat = armorBody.addOrReplaceChild("civilian_surcoat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        civilian_surcoat.addOrReplaceChild("bottom_civilian_surcoat_r1", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-4.5F, -2.8F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -12.8F, 3.6F, 2.9671F, 0.0F, 3.1416F));

        civilian_surcoat.addOrReplaceChild("top_civilian_surcoat_r1", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.5F, -5.2F, 0.0F, 9.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -14.5F, 3.1F, 3.098F, 0.0F, 3.1416F));

        civilian_surcoat.addOrReplaceChild("back_civilian_surcoat_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0.0F, -0.45F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        civilian_surcoat.addOrReplaceChild("front_civilian_surcoat_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -1.0F, -2.4755F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.0349F, 0.0F, 0.0F));

        civilian_surcoat.addOrReplaceChild("top_civilian_surcoat_r2", CubeListBuilder.create().texOffs(16, 16).addBox(-4.5F, -0.2743F, 1.3466F, 9.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.8F, -3.9F, -0.0175F, 0.0F, 0.0F));

        civilian_surcoat.addOrReplaceChild("bottom_civilian_surcoat_r2", CubeListBuilder.create().texOffs(0, 8).addBox(-4.5F, -1.0071F, 1.0505F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.9087F, -3.8996F, -0.3054F, 0.0F, 0.0F));

        civilian_surcoat.addOrReplaceChild("civilian_belt", CubeListBuilder.create().texOffs(18, 8).addBox(-5.0F, -16.25F, -2.75F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 21).addBox(-5.0F, -16.25F, -0.75F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition surcoat = armorBody.addOrReplaceChild("surcoat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        surcoat.addOrReplaceChild("back_surcoat_r1", CubeListBuilder.create().texOffs(40, 97).mirror().addBox(-4.5F, 0.0F, -0.45F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        surcoat.addOrReplaceChild("front_surcoat_r1", CubeListBuilder.create().texOffs(40, 97).addBox(-4.5F, 0.0F, -2.65F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        surcoat.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(0, 123).addBox(-5.0F, 8.75F, -3.25F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 123).addBox(-5.0F, 8.75F, -0.75F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition top_back_surcoat = surcoat.addOrReplaceChild("top_back_surcoat", CubeListBuilder.create(), PartPose.offset(0.0F, 4.5F, 3.1F));

        top_back_surcoat.addOrReplaceChild("top_surcoat_r1", CubeListBuilder.create().texOffs(40, 105).mirror().addBox(-4.5F, -0.2F, 0.0F, 9.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition bottom_back_surcoat = top_back_surcoat.addOrReplaceChild("bottom_back_surcoat", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.8F, 0.0F, 0.0873F, 0.0F, 0.0F));

        bottom_back_surcoat.addOrReplaceChild("bottom_surcoat_r1", CubeListBuilder.create().texOffs(40, 110).mirror().addBox(-4.5F, -3.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.9F, 0.5F, 2.9671F, 0.0F, 3.1416F));

        PartDefinition top_front_surcoat = surcoat.addOrReplaceChild("top_front_surcoat", CubeListBuilder.create().texOffs(40, 105).addBox(-4.5F, -0.1F, 0.0F, 9.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.2F, -3.9F, 0.2182F, 0.0F, 0.0F));

        top_front_surcoat.addOrReplaceChild("bottom_front_surcoat", CubeListBuilder.create().texOffs(40, 110).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.8913F, 0.0004F, -0.5236F, 0.0F, 0.0F));

        PartDefinition armorRightArm = modelPartData.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(96, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F))
                .texOffs(112, 15).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F))
                .texOffs(60, 108).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.55F))
                .texOffs(85, 58).addBox(-3.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(-0.01F))
                .texOffs(0, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.55F)), PartPose.offset(-3.0F, 2.0F, 0.0F));

        armorRightArm.addOrReplaceChild("right_plate_rim_r1", CubeListBuilder.create().texOffs(112, 18).addBox(0.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		armorRightArm.addOrReplaceChild("right_besagew_r1", CubeListBuilder.create().texOffs(116, 45).addBox(0.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		armorRightArm.addOrReplaceChild("right_couter_r1", CubeListBuilder.create().texOffs(112, 25).addBox(-3.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 65).addBox(-3.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition armorLeftArm = modelPartData.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(96, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F)).mirror(false)
                .texOffs(112, 15).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false)
                .texOffs(60, 108).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.55F)).mirror(false)
                .texOffs(85, 58).mirror().addBox(-1.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(-0.01F)).mirror(false)
                .texOffs(0, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.55F)).mirror(false), PartPose.offset(3.0F, 2.0F, 0.0F));

        armorLeftArm.addOrReplaceChild("left_plate_rim_r1", CubeListBuilder.create().texOffs(112, 18).addBox(0.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		armorLeftArm.addOrReplaceChild("left_besagew_r1", CubeListBuilder.create().texOffs(116, 45).addBox(-3.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		armorLeftArm.addOrReplaceChild("left_couter_r1", CubeListBuilder.create().texOffs(112, 25).addBox(3.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 65).mirror().addBox(-1.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorBody.copyFrom(this.body);
		this.armorBody.xScale += 0.05F;
		this.armorBody.yScale += 0.05F;
		this.armorBody.zScale += 0.1F;
		this.armorBody.render(poseStack, vertices, light, overlay, red, green, blue, alpha);

		this.armorRightArm.copyFrom(this.rightArm);
		this.armorRightArm.render(poseStack, vertices, light, overlay, red, green, blue, alpha);

		this.armorLeftArm.copyFrom(this.leftArm);
		this.armorLeftArm.render(poseStack, vertices, light, overlay, red, green, blue, alpha);
	}
}