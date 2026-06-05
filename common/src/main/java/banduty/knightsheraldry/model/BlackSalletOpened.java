package banduty.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class BlackSalletOpened extends HumanoidModel<LivingEntity> {
	private final ModelPart headh;

	public BlackSalletOpened(ModelPart root) {
		super(root);
		this.headh = root.getChild("headh");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition headh = partdefinition.addOrReplaceChild("headh", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition helmet = headh.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(0.0F, -5.3F, -0.5F, -0.3054F, 0.0F, 0.0F));

		PartDefinition tail_r1 = helmet.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(43, 2).addBox(-2.5F, -2.696F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.755F)), PartPose.offsetAndRotation(-0.0033F, -4.504F, 3.2884F, 2.6662F, -0.7268F, -2.812F));

		PartDefinition visor_r1 = helmet.addOrReplaceChild("visor_r1", CubeListBuilder.create().texOffs(72, 25).addBox(-2.5F, -5.75F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.755F)), PartPose.offsetAndRotation(-0.0033F, -1.75F, -4.6F, -0.4754F, 0.7268F, -0.3295F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.headh.copyFrom(this.head);
		this.headh.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}