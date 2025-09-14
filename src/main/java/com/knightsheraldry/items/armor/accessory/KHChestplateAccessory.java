package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.AccessoryArmModel;
import com.knightsheraldry.model.AccessoryChestplateModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class KHChestplateAccessory extends AccessoryItem implements SCAccessoryItem {
    private final Ingredient ingredient;

    public KHChestplateAccessory(Settings settings, Ingredient ingredient) {
        super(settings);
        this.ingredient = ingredient;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBaseAndFirstPerson(new AccessoryChestplateModel(AccessoryChestplateModel.getTexturedModelData().createModel()),
                new AccessoryArmModel(AccessoryArmModel.getTexturedModelData().createModel()));
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
