package com.knightsheraldry.client.item;

import com.knightsheraldry.items.custom.item.KHGeoRangeWeapons;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class KHGeoRangeWeaponsRenderer extends GeoItemRenderer<KHGeoRangeWeapons> {
    public KHGeoRangeWeaponsRenderer() {
        super(new KHGeoRangeWeaponsModel());
    }
}