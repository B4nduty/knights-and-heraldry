package banduty.knightsheraldry.client.item.weapon;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.item.khweapon.flail.Flail;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FlailModel extends GeoModel<Flail> {
    @Override
    public ResourceLocation getModelResource(Flail animatable) {
        if (animatable == KHItems.FLAIL.get()) return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "geo/flail.geo.json");
        if (animatable == KHItems.BALL_FLAIL.get()) return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "geo/ball_flail.geo.json");
        return ResourceLocation.fromNamespaceAndPath("", "missing");
    }

    @Override
    public ResourceLocation getTextureResource(Flail animatable) {
        if (animatable == KHItems.FLAIL.get()) return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/item/flail.png");
        if (animatable == KHItems.BALL_FLAIL.get()) return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/item/ball_flail.png");
        return ResourceLocation.fromNamespaceAndPath("", "missing");
    }

    @Override
    public ResourceLocation getAnimationResource(Flail animatable) {
        if (animatable == KHItems.FLAIL.get()) return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "animations/flail.animation.json");
        if (animatable == KHItems.BALL_FLAIL.get()) return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "animations/ball_flail.animation.json");
        return ResourceLocation.fromNamespaceAndPath("", "missing");
    }
}