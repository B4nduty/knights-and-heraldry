package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HeavyCrossbowModel extends GeoModel<HeavyCrossbow> {
    @Override
    public ResourceLocation getModelResource(HeavyCrossbow animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "geo/heavy_crossbow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HeavyCrossbow animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/item/heavy_crossbow.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HeavyCrossbow animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "animations/heavy_crossbow.animation.json");
    }
}