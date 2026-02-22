package banduty.knightsheraldry.items.armor.accessory;

import net.minecraft.world.item.crafting.Ingredient;

public class SurcoatItem extends KHDyeableChestplateAccessory {
    public SurcoatItem(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public SurcoatItem(Properties properties, int defaultColor, Ingredient ingredient) {
        super(properties, defaultColor, ingredient);
    }

    public SurcoatItem(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties, overlay, defaultColor, ingredient);
    }
}