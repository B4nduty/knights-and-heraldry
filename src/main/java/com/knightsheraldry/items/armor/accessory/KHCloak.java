package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.CloakHoodModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class KHCloak extends AccessoryItem implements SCAccessoryItem, DyeableLeatherItem {
    boolean overlay;

    public KHCloak(Item.Properties properties) {
        super(properties);
        this.overlay = false;
    }

    public KHCloak(Item.Properties properties, boolean overlay) {
        super(properties);
        this.overlay = overlay;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBase(new CloakHoodModel(CloakHoodModel.getTexturedModelData().bakeRoot()));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        if (itemStack.getDamageValue() > itemStack.getMaxDamage() * 0.9f) return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + "_lowd.png");
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(overlay, true, false);
    }
}
