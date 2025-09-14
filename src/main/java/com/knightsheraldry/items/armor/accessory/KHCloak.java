package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.CloakHoodModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class KHCloak extends AccessoryItem implements SCAccessoryItem, DyeableItem {
    boolean overlay;

    public KHCloak(Settings settings, boolean overlay) {
        super(settings);
        this.overlay = overlay;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBase(new CloakHoodModel(CloakHoodModel.getTexturedModelData().createModel()));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Identifier getTexturePath(ItemStack itemStack) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(overlay, true, false);
    }
}
