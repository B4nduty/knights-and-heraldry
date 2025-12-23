package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class ChaperonModel extends HumanoidModel<LivingEntity> {
    private final ModelPart armorHead;
    public ChaperonModel(ModelPart root) {
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
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition armorHead = modelPartData.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(88, 31).addBox(-4.5F, -8.5F, -4.5F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        armorHead.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(116, 42).addBox(3.65F, -2.05F, -3.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        armorHead.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(120, 42).addBox(4.0F, -7.75F, -3.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.1309F, 0.0F, -0.1309F));

        armorHead.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(102, 0).addBox(-7.0F, -8.0F, -4.0F, 5.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        armorHead.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 18).addBox(-5.5F, -1.0F, -5.5F, 11.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
        return LayerDefinition.create(modelData, 128, 64);
    }
    
    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorHead.copyFrom(this.head);
        this.armorHead.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}