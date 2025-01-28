package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.item.khrangeweapon.Arquebus;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ArquebusModel extends GeoModel<Arquebus> {
    @Override
    public Identifier getModelResource(Arquebus animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "geo/arquebus.geo.json");
    }

    @Override
    public Identifier getTextureResource(Arquebus animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/arquebus.png");
    }

    @Override
    public Identifier getAnimationResource(Arquebus animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "animations/arquebus.animation.json");
    }
}