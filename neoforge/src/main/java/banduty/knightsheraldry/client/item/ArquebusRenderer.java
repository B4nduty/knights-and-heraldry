package banduty.knightsheraldry.client.item;

import banduty.knightsheraldry.items.item.khrangeweapon.Arquebus;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ArquebusRenderer extends GeoItemRenderer<Arquebus> {
    public ArquebusRenderer() {
        super(new ArquebusModel());
    }
}