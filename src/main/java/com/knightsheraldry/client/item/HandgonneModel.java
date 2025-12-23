package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HandgonneModel extends GeoModel<Handgonne> {
    @Override
    public ResourceLocation getModelResource(Handgonne animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "geo/handgonne.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Handgonne animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/item/handgonne.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Handgonne animatable) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "animations/handgonne.animation.json");
    }
}