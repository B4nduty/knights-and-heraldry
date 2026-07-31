package banduty.knightsheraldry.items.item;

import banduty.stoneycore.items.custom.hotiron.QuenchItem;
import net.minecraft.world.item.Item;

public class QuenchGenericItem extends Item implements QuenchItem {

    public QuenchGenericItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getIgniteDuration() {
        return 20*30;
    }

    @Override
    public boolean destroysOnQuench() {
        return false;
    }
}
