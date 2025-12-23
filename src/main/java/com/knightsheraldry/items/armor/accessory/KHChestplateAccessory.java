package com.knightsheraldry.items.armor.accessory;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.AccessoryArmModel;
import com.knightsheraldry.model.AccessoryChestplateModel;
import io.wispforest.accessories.api.AccessoryItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHChestplateAccessory extends AccessoryItem implements SCAccessoryItem {
    private final Ingredient ingredient;

    public KHChestplateAccessory(Item.Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBaseAndFirstPerson(new AccessoryChestplateModel(AccessoryChestplateModel.getTexturedModelData().bakeRoot()),
                new AccessoryArmModel(AccessoryArmModel.getTexturedModelData().bakeRoot()));
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
