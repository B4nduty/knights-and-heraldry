package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class SavoyardOpened extends HumanoidModel<LivingEntity> {
    private final ModelPart armorHead;

	public SavoyardOpened(ModelPart root) {
        super(root);
		this.armorHead = root.getChild("armorHead");
	}

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.armorHead);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition armorHead = partdefinition.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F))
                .texOffs(104, 35).addBox(-1.0F, -10.0F, -2.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(104, 33).addBox(-1.0F, -8.0F, 4.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition visor = armorHead.addOrReplaceChild("visor", CubeListBuilder.create().texOffs(0, 4).addBox(-4.0F, 1.0F, -5.4142F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(83, 16).addBox(-4.0F, -1.75F, -1.9F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(0.0F, -6.0F, -2.0F, -1.5708F, 0.0F, 0.0F));

        visor.addOrReplaceChild("visor_left_r1", CubeListBuilder.create().texOffs(114, 67).addBox(-1.61F, -8.0F, -6.0F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, 2.0F, 0.0F, -0.2618F, 0.0F));

        visor.addOrReplaceChild("virsor_right_r1", CubeListBuilder.create().texOffs(100, 67).addBox(-3.39F, -8.0F, -6.0F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, 2.0F, 0.0F, 0.2618F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.armorHead.copyFrom(this.head);
        armorHead.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}