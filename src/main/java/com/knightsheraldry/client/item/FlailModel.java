package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.item.khweapon.Flail;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FlailModel extends GeoModel<Flail> {
    @Override
    public Identifier getModelResource(Flail animatable) {
        if (animatable == ModItems.FLAIL) return new Identifier(KnightsHeraldry.MOD_ID, "geo/flail.geo.json");
        if (animatable == ModItems.BALL_FLAIL) return new Identifier(KnightsHeraldry.MOD_ID, "geo/ball_flail.geo.json");
        return new Identifier("missing");
    }

    @Override
    public Identifier getTextureResource(Flail animatable) {
        if (animatable == ModItems.FLAIL) return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/flail.png");
        if (animatable == ModItems.BALL_FLAIL) return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/ball_flail.png");
        return new Identifier("missing");
    }

    @Override
    public Identifier getAnimationResource(Flail animatable) {
        if (animatable == ModItems.FLAIL) return new Identifier(KnightsHeraldry.MOD_ID, "animations/flail.animation.json");
        if (animatable == ModItems.BALL_FLAIL) return new Identifier(KnightsHeraldry.MOD_ID, "animations/ball_flail.animation.json");
        return new Identifier("missing");
    }
}