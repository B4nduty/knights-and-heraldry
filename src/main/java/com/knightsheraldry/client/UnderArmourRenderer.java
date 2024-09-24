package com.knightsheraldry.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.KHArmorItem;
import com.knightsheraldry.model.UnderArmourBootsModel;
import com.knightsheraldry.model.UnderArmourChestplateModel;
import com.knightsheraldry.model.UnderArmourHelmetModel;
import com.knightsheraldry.model.UnderArmourLeggingsModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class UnderArmourRenderer implements ArmorRenderer {
    private BipedEntityModel<LivingEntity> helmetModel;
    private BipedEntityModel<LivingEntity> chestplateModel;
    private BipedEntityModel<LivingEntity> leggingsModel;
    private BipedEntityModel<LivingEntity> bootsModel;

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        BipedEntityModel<LivingEntity> model = getLivingEntityBipedEntityModel(stack);
        if (model != null) {
            contextModel.copyBipedStateTo(model);
            if (stack.getItem() instanceof KHArmorItem khArmorItem) {
                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                        RenderLayer.getArmorCutoutNoCull(new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/" + khArmorItem.getTextureName() + ".png")));
                model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
            }
        }
    }

    private @Nullable BipedEntityModel<LivingEntity> getLivingEntityBipedEntityModel(ItemStack stack) {
        BipedEntityModel<LivingEntity> model = null;
        if (stack.getItem() instanceof KHArmorItem khArmorItem) {
            if (khArmorItem.getSlotType() == ArmorItem.Type.HELMET.getEquipmentSlot()) model = this.getUnderArmourHelmetModel();
            if (khArmorItem.getSlotType() == ArmorItem.Type.CHESTPLATE.getEquipmentSlot()) model = this.getUnderArmourChestplateModel();
            if (khArmorItem.getSlotType() == ArmorItem.Type.LEGGINGS.getEquipmentSlot()) model = this.getUnderArmourLeggingsModel();
            if (khArmorItem.getSlotType() == ArmorItem.Type.BOOTS.getEquipmentSlot()) model = this.getUnderArmourBootsModel();
        }
        return model;
    }

    private BipedEntityModel<LivingEntity> getUnderArmourHelmetModel() {
        if (this.helmetModel == null) {
            this.helmetModel = new UnderArmourHelmetModel(UnderArmourHelmetModel.getTexturedModelData().createModel());
        }

        return this.helmetModel;
    }

    private BipedEntityModel<LivingEntity> getUnderArmourChestplateModel() {
        if (this.chestplateModel == null) {
            this.chestplateModel = new UnderArmourChestplateModel(UnderArmourChestplateModel.getTexturedModelData().createModel());
        }

        return this.chestplateModel;
    }

    private BipedEntityModel<LivingEntity> getUnderArmourLeggingsModel() {
        if (this.leggingsModel == null) {
            this.leggingsModel = new UnderArmourLeggingsModel(UnderArmourLeggingsModel.getTexturedModelData().createModel());
        }

        return this.leggingsModel;
    }

    private BipedEntityModel<LivingEntity> getUnderArmourBootsModel() {
        if (this.bootsModel == null) {
            this.bootsModel = new UnderArmourBootsModel(UnderArmourBootsModel.getTexturedModelData().createModel());
        }

        return this.bootsModel;
    }
}
