package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.AccessoryBootsModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHBootsAccessory extends AccessoryItem implements SCAccessoryItem {
    private final Ingredient ingredient;

    public KHBootsAccessory(Item.Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBase(new AccessoryBootsModel(AccessoryBootsModel.getTexturedModelData().bakeRoot()));
    }

    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        if (itemStack.getDamageValue() > itemStack.getMaxDamage() * 0.9f) return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + "_lowd.png");
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }
}
