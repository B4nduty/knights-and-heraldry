package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.item.KHGeoRangeWeapons;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class KHGeoRangeWeaponsModel extends GeoModel<KHGeoRangeWeapons> {
    @Override
    public Identifier getModelResource(KHGeoRangeWeapons animatable) {
        if (animatable == ModItems.HEAVY_CROSSBOW) return new Identifier(KnightsHeraldry.MOD_ID, "geo/heavy_crossbow.geo.json");
        return new Identifier("missing");
    }

    @Override
    public Identifier getTextureResource(KHGeoRangeWeapons animatable) {
        if (animatable == ModItems.HEAVY_CROSSBOW) return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/heavy_crossbow.png");
        return new Identifier("missing");
    }

    @Override
    public Identifier getAnimationResource(KHGeoRangeWeapons animatable) {
        if (animatable == ModItems.HEAVY_CROSSBOW) return new Identifier(KnightsHeraldry.MOD_ID, "animations/heavy_crossbow.animation.json");
        return new Identifier("missing");
    }
}