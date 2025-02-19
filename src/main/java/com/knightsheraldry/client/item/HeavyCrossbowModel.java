package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HeavyCrossbowModel extends GeoModel<HeavyCrossbow> {
    @Override
    public Identifier getModelResource(HeavyCrossbow animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "geo/heavy_crossbow.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeavyCrossbow animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/heavy_crossbow.png");
    }

    @Override
    public Identifier getAnimationResource(HeavyCrossbow animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "animations/heavy_crossbow.animation.json");
    }
}