package banduty.knightsheraldry.client.item;

import banduty.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HeavyCrossbowRenderer extends GeoItemRenderer<HeavyCrossbow> {
    public HeavyCrossbowRenderer() {
        super(new HeavyCrossbowModel());
    }
}