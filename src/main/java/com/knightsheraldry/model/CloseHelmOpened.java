package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class CloseHelmOpened extends HumanoidModel<LivingEntity> {
	private final ModelPart armorHead;

	public CloseHelmOpened(ModelPart root) {
        super(root);
        this.setAllVisible(false);
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
                .texOffs(104, 33).addBox(-1.0F, -8.0F, 4.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 35).addBox(-1.0F, -10.0F, -2.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition visor = armorHead.addOrReplaceChild("visor", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        visor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(80, 42).addBox(-4.5F, -0.7929F, -5.9533F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -29.75F, -2.5F, -0.7854F, 0.0F, 0.0F));

        visor.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(82, 36).addBox(-4.5F, -4.2041F, -2.0319F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -29.75F, -2.5F, 0.48F, 0.0F, 0.0F));

        visor.addOrReplaceChild("armet_2_r1", CubeListBuilder.create().texOffs(80, 50).addBox(-4.5F, -1.9514F, -2.6152F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -29.75F, -2.5F, -1.789F, 0.0F, 0.0F));

        visor.addOrReplaceChild("helmet_r1", CubeListBuilder.create().texOffs(83, 16).addBox(-4.0F, -2.0F, -1.4F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(0.0F, -29.75F, -2.5F, -1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.armorHead.copyFrom(this.head);
        this.armorHead.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}