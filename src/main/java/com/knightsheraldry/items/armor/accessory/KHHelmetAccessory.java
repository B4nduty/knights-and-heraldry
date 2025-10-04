package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.AccessoryHelmetModel;
import com.knightsheraldry.model.AccessoryOpenHelmetModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class KHHelmetAccessory extends AccessoryItem implements SCAccessoryItem {
    private final boolean openVisor;
    private final Ingredient ingredient;

    public KHHelmetAccessory(Settings settings, Ingredient ingredient) {
        super(settings);
        this.openVisor = false;
        this.ingredient = ingredient;
    }

    public KHHelmetAccessory(Settings settings, boolean openVisor, Ingredient ingredient) {
        super(settings);
        this.openVisor = openVisor;
        this.ingredient = ingredient;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        if (openVisor) {
            return ModelBundle.ofBaseAndVisor(new AccessoryHelmetModel(AccessoryHelmetModel.getTexturedModelData().createModel()), new AccessoryOpenHelmetModel(AccessoryOpenHelmetModel.getTexturedModelData().createModel()));
        }
        return ModelBundle.ofBase(new AccessoryHelmetModel(AccessoryHelmetModel.getTexturedModelData().createModel()));
    }

    @Override
    public boolean hasOpenVisor(ItemStack stack) {
        return openVisor;
    }

    @Override
    public Identifier getTexturePath(ItemStack itemStack) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.canRepair(stack, ingredient);
    }
}
