package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.SurcoatItemRenderer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

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

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        SurcoatItemRenderer.register(consumer);
    }
}