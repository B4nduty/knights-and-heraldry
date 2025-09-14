package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.AccessoryLeggingsModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class KHLeggingsAccessory extends AccessoryItem implements SCAccessoryItem {
    private final Ingredient ingredient;

    public KHLeggingsAccessory(Settings settings, Ingredient ingredient) {
        super(settings);
        this.ingredient = ingredient;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBase(new AccessoryLeggingsModel(AccessoryLeggingsModel.getTexturedModelData().createModel()));
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
