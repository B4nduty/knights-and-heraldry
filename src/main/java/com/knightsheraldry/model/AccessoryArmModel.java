package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class AccessoryArmModel extends HumanoidModel<LivingEntity> {
	public final ModelPart armorRightArm;
	public final ModelPart armorLeftArm;

	public AccessoryArmModel(ModelPart root) {
		super(root);
		this.armorRightArm = root.getChild("armorRightArm");
		this.armorLeftArm = root.getChild("armorLeftArm");
	}

	@Override
	protected @NotNull Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.armorRightArm, this.armorLeftArm);
	}

	public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition armorRightArm = modelPartData.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(96, 16).addBox(-5.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F))
				.texOffs(112, 15).addBox(-5.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F))
				.texOffs(60, 108).addBox(-5.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.55F))
				.texOffs(85, 58).addBox(-5.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(-0.01F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		armorRightArm.addOrReplaceChild("right_plate_rim_r1", CubeListBuilder.create().texOffs(112, 18).addBox(-1.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		armorRightArm.addOrReplaceChild("right_besagew_r1", CubeListBuilder.create().texOffs(116, 45).addBox(-1.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		armorRightArm.addOrReplaceChild("right_couter_r1", CubeListBuilder.create().texOffs(112, 25).addBox(-4.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 65).addBox(-4.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition armorLeftArm = modelPartData.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(96, 16).mirror().addBox(1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F)).mirror(false)
				.texOffs(112, 15).mirror().addBox(1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false)
				.texOffs(60, 108).mirror().addBox(1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.55F)).mirror(false)
				.texOffs(85, 58).mirror().addBox(0.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		armorLeftArm.addOrReplaceChild("left_plate_rim_r1", CubeListBuilder.create().texOffs(112, 18).mirror().addBox(1.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		armorLeftArm.addOrReplaceChild("left_besagew_r1", CubeListBuilder.create().texOffs(116, 45).mirror().addBox(-2.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		armorLeftArm.addOrReplaceChild("left_couter_r1", CubeListBuilder.create().texOffs(112, 25).mirror().addBox(4.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(48, 65).mirror().addBox(-0.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}
}