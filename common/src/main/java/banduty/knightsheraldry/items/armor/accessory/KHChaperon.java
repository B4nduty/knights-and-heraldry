package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHChaperonAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class KHChaperon extends Item implements SCAccessory, AccessoryRenderProvider {
    boolean hasOverlay;

    public AccessoryRenderer cachedRenderer;

    public KHChaperon(Properties properties, boolean hasOverlay) {
        super(properties);
        this.hasOverlay = hasOverlay;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Override
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.HELMET;
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHChaperonAccessoryRenderer();
        }
        return this.cachedRenderer;
    }

    public int getDefaultColor() {
        return 0xFFA06440;
    }
}
