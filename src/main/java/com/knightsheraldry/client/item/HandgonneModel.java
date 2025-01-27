package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.item.khrangeweapon.Handgonne;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HandgonneModel extends GeoModel<Handgonne> {
    @Override
    public Identifier getModelResource(Handgonne animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "geo/handgonne.geo.json");
    }

    @Override
    public Identifier getTextureResource(Handgonne animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/handgonne.png");
    }

    @Override
    public Identifier getAnimationResource(Handgonne animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "animations/handgonne.animation.json");
    }
}