package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class HorseBardingModel<T extends AbstractHorse> extends HorseModel<T> {
	private final ModelPart armorHead;
	public final ModelPart plume;
	private final ModelPart mouth;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart neck;
	private final ModelPart mane;
	private final ModelPart armorBody;
	private final ModelPart saddle;
	private final ModelPart headpiece;

	public HorseBardingModel(ModelPart root) {
		super(root);

		this.armorHead = root.getChild("armorHead");
        this.plume = root.getChild("plume");
		this.mouth = this.armorHead.getChild("mouth");
		this.left_ear = this.armorHead.getChild("left_ear");
		this.right_ear = this.armorHead.getChild("right_ear");
		this.neck = this.armorHead.getChild("neck");
		this.mane = this.armorHead.getChild("mane");
		this.headpiece = this.armorHead.getChild("headpiece");
		this.armorBody = root.getChild("armorBody");
		this.saddle = this.armorBody.getChild("saddle");
	}

	public @NotNull Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.armorHead, this.plume, this.mouth, this.left_ear, this.right_ear, this.neck, this.mane, this.headpiece);
	}

    @Override
    protected @NotNull Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.armorBody, this.saddle);
	}

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshDefinition = HorseModel.createBodyMesh(CubeDeformation.NONE);
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition armorHead = partDefinition.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 2.0F, -9.0F));

        partDefinition.addOrReplaceChild("plume", CubeListBuilder.create().texOffs(96, 80).addBox(0.0F, -19.0F, 3.0F, 0.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -9.0F));

        armorHead.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -11.0F, -7.2F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        armorHead.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(19, 16).addBox(0.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -0.01F));

        armorHead.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(19, 16).addBox(-2.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -0.01F));

        armorHead.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 33).addBox(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        armorHead.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(56, 36).addBox(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, -0.01F));

        armorHead.addOrReplaceChild("headpiece", CubeListBuilder.create().texOffs(30, 68).addBox(-0.5F, -14.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 64).addBox(-1.5F, -12.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armorBody = partDefinition.addOrReplaceChild("armorBody", CubeListBuilder.create().texOffs(53, 1).addBox(-5.5F, -6.0F, -21.5F, 11.0F, 11.0F, 23.0F, new CubeDeformation(0.05F))
                .texOffs(50, 41).addBox(-5.5F, 1.5F, -21.5F, 11.0F, 8.0F, 23.0F, new CubeDeformation(0.0F))
                .texOffs(28, 103).addBox(-6.5F, -10.5F, -15.0F, 13.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 103).addBox(-6.5F, -8.5F, -5.0F, 13.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, 9.0F));

        armorBody.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(58, 75).addBox(-5.0F, -3.6F, -2.2F, 10.0F, 7.0F, 4.0F, new CubeDeformation(0.06F)), PartPose.offsetAndRotation(0.0F, -1.9F, 1.2F, 0.5672F, 0.0F, 0.0F));

        armorBody.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(30, 75).addBox(-5.0F, -3.6F, -1.8F, 10.0F, 7.0F, 4.0F, new CubeDeformation(0.06F)), PartPose.offsetAndRotation(0.0F, 0.5F, -21.2F, -0.5672F, 0.0F, 0.0F));

        armorBody.addOrReplaceChild("body_r3", CubeListBuilder.create().texOffs(69, 92).addBox(-1.8F, -3.6F, -11.0F, 4.0F, 7.0F, 22.0F, new CubeDeformation(0.04F)), PartPose.offsetAndRotation(-5.2F, 0.5F, -10.0F, 0.0F, 0.0F, 0.5672F));

        armorBody.addOrReplaceChild("body_r4", CubeListBuilder.create().texOffs(0, 64).addBox(-2.1F, -3.6F, -11.0F, 4.0F, 7.0F, 22.0F, new CubeDeformation(0.04F)), PartPose.offsetAndRotation(5.1F, 0.5F, -10.0F, 0.0F, 0.0F, -0.5672F));

        armorBody.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(30, 2).addBox(-6.0F, -6.3F, -11.0F, 12.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.5F));
        return LayerDefinition.create(meshDefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorHead.copyFrom(this.headParts);
		armorHead.render(poseStack, vertexConsumer, light, overlay, red, green, blue, alpha);
		this.armorBody.copyFrom(this.body);
		this.armorBody.y += 8f;
		this.armorBody.z += 3.75f;
		armorBody.render(poseStack, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.saddle.visible = entity.isSaddled();
		ItemStack armorStack = ItemStack.EMPTY;
		if (entity instanceof Horse horse) armorStack = horse.getArmor();
        this.plume.visible = armorStack.getTag() != null && armorStack.getTag().contains("plume");
	}

	@Override
	public void prepareMobModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		super.prepareMobModel(entity, limbAngle, limbDistance, tickDelta);

        this.plume.y = this.headParts.y;
        this.plume.z = this.headParts.z;
        this.plume.xRot = this.headParts.xRot;
        this.plume.yRot = this.headParts.yRot;
	}
}