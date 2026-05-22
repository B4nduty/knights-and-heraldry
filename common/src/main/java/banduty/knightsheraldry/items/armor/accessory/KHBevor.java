package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHBevorAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHBevor extends KHHelmetAccessory {
    public KHBevor(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHBevorAccessoryRenderer();
        }
        return this.cachedRenderer;
    }
}
