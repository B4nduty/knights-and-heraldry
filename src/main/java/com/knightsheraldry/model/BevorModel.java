package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class BevorModel extends HumanoidModel<LivingEntity> {
    private final ModelPart bevor;

	public BevorModel(ModelPart root) {
        super(root);
        this.setAllVisible(false);
        this.bevor = root.getChild("bevor");
	}

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.bevor);
    }

	public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bevor = partdefinition.addOrReplaceChild("bevor", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bevor.copyFrom(this.head);
        this.bevor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}