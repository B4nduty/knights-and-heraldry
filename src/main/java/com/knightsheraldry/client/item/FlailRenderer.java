package com.knightsheraldry.client.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.item.khweapon.Flail;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FlailRenderer extends GeoItemRenderer<Flail> {
    public FlailRenderer(GeoModel<Flail> model) {
        super(model);
    }

    @Override
    public ResourceLocation getTextureLocation(Flail animatable) {
        ItemStack stack = this.getCurrentItemStack();

        String texturePath = this.getGeoModel().getTextureResource(animatable).getPath();
        if (stack.getDamageValue() >= stack.getMaxDamage() * 0.9f) {
            texturePath = texturePath.replace(".png", "_broken.png");
        }

        return new ResourceLocation(KnightsHeraldry.MOD_ID, texturePath);
    }
}
