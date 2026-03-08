package banduty.knightsheraldry.items.armor.horse;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;

public class HorseBardingArmorItem extends HorseArmorItem implements DyeableLeatherItem {
    public HorseBardingArmorItem(int bonus, Properties properties) {
        super(bonus, "", properties);
    }

    public int getColor(ItemStack stack) {
        CompoundTag compoundTag = stack.getTagElement("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xFFFFFF;
    }
}