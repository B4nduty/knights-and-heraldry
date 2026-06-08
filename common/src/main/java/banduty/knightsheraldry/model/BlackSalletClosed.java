package banduty.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class BlackSalletClosed extends HumanoidModel<LivingEntity> {
	private final ModelPart headh;

	public BlackSalletClosed(ModelPart root) {
		super(root);
		this.headh = root.getChild("headh");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition headh = partdefinition.addOrReplaceChild("headh", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition helmet = headh.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition visor = headh.addOrReplaceChild("visor", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 2.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition visor_r1 = visor.addOrReplaceChild("visor_r1", CubeListBuilder.create().texOffs(72, 25).addBox(0.8033F, 1.25F, -5.8F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(-0.0033F, -0.75F, 0.4F, -0.4754F, 0.7268F, -0.3295F));

		PartDefinition visor2 = headh.addOrReplaceChild("visor2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, -2.0F, 0.0436F, 3.1416F, 0.0F));

		PartDefinition visor_r2 = visor2.addOrReplaceChild("visor_r2", CubeListBuilder.create().texOffs(72, 37).addBox(0.8033F, 1.25F, -5.8F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(-0.0033F, -0.75F, 0.4F, -0.4754F, 0.7268F, -0.3295F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.headh.copyFrom(this.head);
		this.headh.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}