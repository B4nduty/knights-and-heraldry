package banduty.knightsheraldry.client.item;

import banduty.knightsheraldry.items.item.khrangeweapon.Handgonne;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HandgonneRenderer extends GeoItemRenderer<Handgonne> {
    public HandgonneRenderer() {
        super(new HandgonneModel());
    }
}