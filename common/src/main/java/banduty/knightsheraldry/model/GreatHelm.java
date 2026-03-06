package banduty.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class GreatHelm extends HumanoidModel<LivingEntity> {
	private final ModelPart armorHead;

	public GreatHelm(ModelPart root) {
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

		PartDefinition armorHead = partdefinition.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition barbute = armorHead.addOrReplaceChild("barbute", CubeListBuilder.create().texOffs(104, -1).addBox(-4.64F, -5.5F, -3.8F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(104, -1).addBox(4.61F, -5.5F, -3.8F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(112, 8).addBox(-1.0F, -9.51F, -5.6F, 2.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition barbute_3_r1 = barbute.addOrReplaceChild("barbute_3_r1", CubeListBuilder.create().texOffs(104, 0).addBox(-3.99F, -3.25F, -5.8F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(104, 0).addBox(-3.99F, -1.75F, -5.8F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(100, 67).addBox(-3.89F, -8.0F, -5.75F, 5.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.3F, 0.0F, 0.1745F, 0.0F));

		PartDefinition barbute_4_r1 = barbute.addOrReplaceChild("barbute_4_r1", CubeListBuilder.create().texOffs(104, 0).mirror().addBox(-1.01F, -3.25F, -5.8F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(104, 0).mirror().addBox(-1.01F, -1.75F, -5.8F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(114, 67).addBox(-1.11F, -8.0F, -5.75F, 5.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.3F, 0.0F, -0.1745F, 0.0F));

		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorHead.copyFrom(this.head);
		this.armorHead.render(poseStack, vertices, light, overlay, red, green, blue, alpha);
	}
}