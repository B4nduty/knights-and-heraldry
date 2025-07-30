package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class AccessoryArmModel extends BipedEntityModel<LivingEntity> {
	public final ModelPart armorRightArm;
	public final ModelPart armorLeftArm;

	public AccessoryArmModel(ModelPart root) {
		super(root);
		this.armorRightArm = root.getChild("armorRightArm");
		this.armorLeftArm = root.getChild("armorLeftArm");
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.armorRightArm, this.armorLeftArm);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData armorRightArm = modelPartData.addChild("armorRightArm", ModelPartBuilder.create().uv(96, 16).cuboid(-5.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F))
				.uv(112, 15).cuboid(-5.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.45F))
				.uv(60, 108).cuboid(-5.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.55F))
				.uv(85, 58).cuboid(-5.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new Dilation(-0.01F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		armorRightArm.addChild("right_plate_rim_r1", ModelPartBuilder.create().uv(112, 18).cuboid(-1.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		armorRightArm.addChild("right_besagew_r1", ModelPartBuilder.create().uv(116, 45).cuboid(-1.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		armorRightArm.addChild("right_couter_r1", ModelPartBuilder.create().uv(112, 25).cuboid(-4.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
				.uv(48, 65).cuboid(-4.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData armorLeftArm = modelPartData.addChild("armorLeftArm", ModelPartBuilder.create().uv(96, 16).mirrored().cuboid(1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F)).mirrored(false)
				.uv(112, 15).mirrored().cuboid(1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.45F)).mirrored(false)
				.uv(60, 108).mirrored().cuboid(1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.55F)).mirrored(false)
				.uv(85, 58).mirrored().cuboid(0.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new Dilation(-0.01F)).mirrored(false), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		armorLeftArm.addChild("left_plate_rim_r1", ModelPartBuilder.create().uv(112, 18).mirrored().cuboid(1.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		armorLeftArm.addChild("left_besagew_r1", ModelPartBuilder.create().uv(116, 45).mirrored().cuboid(-2.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		armorLeftArm.addChild("left_couter_r1", ModelPartBuilder.create().uv(112, 25).mirrored().cuboid(4.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(48, 65).mirrored().cuboid(-0.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
}