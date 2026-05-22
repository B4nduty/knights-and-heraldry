package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHCloseHelmetAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.item.crafting.Ingredient;

public class KHCloseHelmet extends KHHelmetAccessory implements SCAccessory, AccessoryRenderProvider {
    public KHCloseHelmet(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHCloseHelmet(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHCloseHelmetAccessoryRenderer();
        }
        return this.cachedRenderer;
    }
}
