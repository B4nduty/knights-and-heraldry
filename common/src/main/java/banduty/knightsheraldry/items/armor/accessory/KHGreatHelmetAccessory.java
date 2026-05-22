package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHGreatHelmetAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.item.crafting.Ingredient;

public class KHGreatHelmetAccessory extends KHHelmetAccessory implements SCAccessory, AccessoryRenderProvider {
    public KHGreatHelmetAccessory(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHGreatHelmetAccessoryRenderer();
        }
        return this.cachedRenderer;
    }
}
