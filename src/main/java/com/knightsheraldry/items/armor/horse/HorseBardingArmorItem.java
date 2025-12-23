package com.knightsheraldry.items.armor.horse;

import banduty.stoneycore.StoneyCore;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class HorseBardingArmorItem extends HorseArmorItem implements DyeableLeatherItem {
    public HorseBardingArmorItem(int bonus, Item.Properties properties) {
        super(bonus, "", properties);
    }

    @Override
    public @NotNull ResourceLocation getTexture() {
        return new ResourceLocation(StoneyCore.MOD_ID, "textures/models/armor/a_layer_1.png");
    }

    public int getColor(ItemStack stack) {
        CompoundTag compoundTag = stack.getTagElement("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xFFFFFF;
    }
}