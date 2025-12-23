package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.item.khrangeweapon.Arquebus;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ArquebusModel extends GeoModel<Arquebus> {
    @Override
    public ResourceLocation getModelResource(Arquebus animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "geo/arquebus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Arquebus animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/item/arquebus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Arquebus animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "animations/arquebus.animation.json");
    }
}