package banduty.knightsheraldry.client.item.weapon;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.item.khrangeweapon.Handgonne;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HandgonneModel extends GeoModel<Handgonne> {
    @Override
    public ResourceLocation getModelResource(Handgonne animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "geo/handgonne.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Handgonne animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/item/handgonne.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Handgonne animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "animations/handgonne.animation.json");
    }
}