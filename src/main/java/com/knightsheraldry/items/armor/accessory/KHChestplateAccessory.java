package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.AccessoryArmModel;
import com.knightsheraldry.model.AccessoryChestplateModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class KHChestplateAccessory extends AccessoryItem implements SCAccessoryItem {

    public KHChestplateAccessory(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<LivingEntity> getModel(ItemStack itemStack) {
        return new AccessoryChestplateModel(AccessoryChestplateModel.getTexturedModelData().createModel());
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<LivingEntity> getFirstPersonModel(ItemStack itemStack) {
        return new AccessoryArmModel(AccessoryArmModel.getTexturedModelData().createModel());
    }

    @Override
    public Identifier getTexturePath(ItemStack itemStack) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
    }
}
