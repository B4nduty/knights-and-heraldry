package com.knightsheraldry.items.armor.horse;

import banduty.stoneycore.StoneyCore;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class HorseBardingArmorItem extends HorseArmorItem implements DyeableItem {
    public HorseBardingArmorItem(int bonus, Settings settings) {
        super(bonus, "", settings);
    }

    @Override
    public Identifier getEntityTexture() {
        return new Identifier(StoneyCore.MOD_ID, "textures/models/armor/a_layer_1.png");
    }

    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 0xFFFFFF;
    }
}