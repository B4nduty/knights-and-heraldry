package banduty.knightsheraldry.items.armor.deco;

import banduty.stoneycore.items.custom.hotiron.QuenchItem;

public class DecoBurnableItem extends DecoItem implements QuenchItem {

    public DecoBurnableItem(Properties properties) {
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