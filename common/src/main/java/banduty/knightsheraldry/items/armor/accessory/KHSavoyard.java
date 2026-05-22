package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHSavoyardAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.item.crafting.Ingredient;

public class KHSavoyard extends KHHelmetAccessory implements SCAccessory, AccessoryRenderProvider {
    public KHSavoyard(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHSavoyard(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHSavoyardAccessoryRenderer();
        }
        return this.cachedRenderer;
    }
}
