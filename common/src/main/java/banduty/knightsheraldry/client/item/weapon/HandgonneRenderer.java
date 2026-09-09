package banduty.knightsheraldry.client.item.weapon;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.item.khrangeweapon.Handgonne;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class HandgonneRenderer extends GeoItemRenderer<Handgonne> {
    public HandgonneRenderer(GeoModel<Handgonne> model) {
        super(model);
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Handgonne animatable) {
        ItemStack stack = this.getCurrentItemStack();

        String texturePath = this.getGeoModel().getTextureResource(animatable).getPath();
        if (stack.getOrDefault(KHDataComponents.PIGLIN.get(), false)) {
            texturePath = "textures/item/golden_handgonne.png";
        }

        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, texturePath);
    }
}
