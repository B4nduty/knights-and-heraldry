package com.knightsheraldry.items.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.GambesonItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GambesonModel extends GeoModel<GambesonItem> {

    @Override
    public Identifier getModelResource(GambesonItem animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "geo/under_armour.geo.json");
    }

    @Override
    public Identifier getTextureResource(GambesonItem animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/armor/gambeson.png");
    }

    @Override
    public Identifier getAnimationResource(GambesonItem animatable) {
        return null;
    }
}
