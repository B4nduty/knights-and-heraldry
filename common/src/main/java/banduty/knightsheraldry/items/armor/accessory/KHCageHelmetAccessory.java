package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHCageHelmetAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.item.crafting.Ingredient;

public class KHCageHelmetAccessory extends KHHelmetAccessory implements SCAccessory, AccessoryRenderProvider {
    public KHCageHelmetAccessory(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHCageHelmetAccessory(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHCageHelmetAccessoryRenderer();
        }
        return this.cachedRenderer;
    }
}
