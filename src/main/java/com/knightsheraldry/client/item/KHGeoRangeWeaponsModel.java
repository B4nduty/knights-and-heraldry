package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.item.KHGeoRangeWeapons;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class KHGeoRangeWeaponsModel extends GeoModel<KHGeoRangeWeapons> {
    @Override
    public Identifier getModelResource(KHGeoRangeWeapons animatable, @Nullable GeoRenderer<KHGeoRangeWeapons> renderer) {
        if (animatable == ModItems.HEAVY_CROSSBOW) return new Identifier(KnightsHeraldry.MOD_ID, "geo/heavy_crossbow.geo.json");
        if (animatable == ModItems.ARQUEBUS) return new Identifier(KnightsHeraldry.MOD_ID, "geo/arquebus.geo.json");
        if (animatable == ModItems.HANDGONNE) return new Identifier(KnightsHeraldry.MOD_ID, "geo/handgonne.geo.json");
        return new Identifier("missing");
    }

    @Override
    public Identifier getModelResource(KHGeoRangeWeapons animatable) {
        return this.getModelResource(animatable, null);
    }

    @Override
    public Identifier getTextureResource(KHGeoRangeWeapons animatable, @Nullable GeoRenderer<KHGeoRangeWeapons> renderer) {
        if (animatable == ModItems.HEAVY_CROSSBOW) return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/heavy_crossbow.png");
        if (animatable == ModItems.ARQUEBUS) return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/arquebus.png");
        if (animatable == ModItems.HANDGONNE) return new Identifier(KnightsHeraldry.MOD_ID, "textures/item/handgonne.png");
        return new Identifier("missing");
    }

    @Override
    public Identifier getTextureResource(KHGeoRangeWeapons animatable) {
        return this.getTextureResource(animatable, null);
    }

    @Override
    public Identifier getAnimationResource(KHGeoRangeWeapons animatable) {
        if (animatable == ModItems.HEAVY_CROSSBOW) return new Identifier(KnightsHeraldry.MOD_ID, "animations/heavy_crossbow.animation.json");
        if (animatable == ModItems.ARQUEBUS) return new Identifier(KnightsHeraldry.MOD_ID, "animations/arquebus.animation.json");
        if (animatable == ModItems.HANDGONNE) return new Identifier(KnightsHeraldry.MOD_ID, "animations/handgonne.animation.json");
        return new Identifier("missing");
    }
}