package banduty.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class CageHelmClosed extends HumanoidModel<LivingEntity> {
	private final ModelPart armorHead;

	public CageHelmClosed(ModelPart root) {
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
		PartDefinition partdefinition = modelData.getRoot();

		PartDefinition armorHead = partdefinition.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition visor = armorHead.addOrReplaceChild("visor", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, -4.0F));

		visor.addOrReplaceChild("helmet_r1", CubeListBuilder.create().texOffs(83, 16).addBox(-4.0F, -1.75F, -1.9F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition grid = visor.addOrReplaceChild("grid", CubeListBuilder.create(), PartPose.offset(0.05F, -0.8F, 0.0F));

		grid.addOrReplaceChild("barbute_2_r1", CubeListBuilder.create().texOffs(114, 67).addBox(-1.61F, -7.25F, -6.1F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 7.5F, 4.0F, 0.0F, -0.2618F, 0.0F));

		grid.addOrReplaceChild("barbute_1_r1", CubeListBuilder.create().texOffs(100, 67).addBox(-3.39F, -7.25F, -6.1F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.5F, 4.0F, 0.0F, 0.2618F, 0.0F));

		grid.addOrReplaceChild("right_grid_2_r1", CubeListBuilder.create().texOffs(112, 54).addBox(0.61F, -4.25F, -6.1F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(118, 54).addBox(-1.39F, -4.25F, -6.1F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, 3.0F, 0.0F, 0.2618F, 0.0F));

		grid.addOrReplaceChild("left_grid_2_r1", CubeListBuilder.create().texOffs(112, 54).addBox(-1.61F, -4.25F, -6.1F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(118, 54).addBox(0.39F, -4.25F, -6.1F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 6.5F, 3.0F, 0.0F, -0.2618F, 0.0F));

		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorHead.copyFrom(this.head);
		this.armorHead.render(poseStack, vertices, light, overlay, red, green, blue, alpha);
	}
}