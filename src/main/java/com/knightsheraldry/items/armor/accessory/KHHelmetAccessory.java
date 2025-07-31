package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.AccessoryHelmetModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class KHHelmetAccessory extends AccessoryItem implements SCAccessoryItem {

    public KHHelmetAccessory(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<LivingEntity> getModel(ItemStack itemStack) {
        return new AccessoryHelmetModel(AccessoryHelmetModel.getTexturedModelData().createModel());
    }

    @Override
    public boolean shouldNotRenderOnHeadInFirstPerson() {
        return true;
    }

    @Override
    public Identifier getTexturePath(ItemStack itemStack) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
    }
}
