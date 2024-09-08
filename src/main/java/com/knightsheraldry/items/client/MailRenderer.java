package com.knightsheraldry.items.client;

import com.knightsheraldry.items.custom.armor.MailItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class MailRenderer extends GeoArmorRenderer<MailItem> {
    public MailRenderer() {
        super(new MailModel());
    }
}
