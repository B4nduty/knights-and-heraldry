package banduty.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class GlovesModel extends HumanoidModel<LivingEntity> {
	public final ModelPart armorRightArm;
	public final ModelPart armorLeftArm;

	public GlovesModel(ModelPart root) {
		super(root);
		this.armorRightArm = root.getChild("armorRightArm");
		this.armorLeftArm = root.getChild("armorLeftArm");
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.armorRightArm, this.armorLeftArm);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition armorRightArm = partdefinition.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(112, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.34F)), PartPose.offset(-4.0F, 2.0F, 0.0F));

		PartDefinition armorLeftArm = partdefinition.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(112, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.34F)).mirror(false), PartPose.offset(4.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.armorRightArm.copyFrom(this.rightArm);
		this.armorRightArm.render(poseStack, buffer, packedLight, packedOverlay, color);

		this.armorLeftArm.copyFrom(this.leftArm);
		this.armorLeftArm.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}