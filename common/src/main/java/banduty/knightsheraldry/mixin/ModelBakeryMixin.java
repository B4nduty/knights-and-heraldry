package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.KHItems;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @Shadow
    protected abstract void loadSpecialItemModelAndDependencies(ModelResourceLocation modelId);

    @Inject(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/ModelBakery;loadSpecialItemModelAndDependencies(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V", ordinal = 1, shift = At.Shift.AFTER)
    )
    public void knightsheraldry$add3dModels(CallbackInfo ci) {
        Item[] weapons3D = {
                KHItems.DAGGER.get(),
                KHItems.STILETTO.get(),
                KHItems.RAPIER.get(),
                KHItems.SWORD.get(),
                KHItems.V_SWORD.get(),
                KHItems.ARMING_SWORD.get(),
                KHItems.AXE.get(),
                KHItems.BROAD_AXE.get(),
                KHItems.CROOKED_AXE.get(),
                KHItems.STRAIGHT_CROOKED_AXE.get(),
                KHItems.MACE.get(),
                KHItems.SPIKED_MACE.get(),
                KHItems.HAMMER.get(),
                KHItems.WAR_HAMMER.get(),
                KHItems.LONGSWORD.get(),
                KHItems.V_LONGSWORD.get(),
                KHItems.FALCHION.get(),
                KHItems.SCIMITAR.get(),
                KHItems.PITCHFORK.get(),
                KHItems.SPEAR.get(),
                KHItems.PIKE.get(),
                KHItems.BILLHOOK.get(),
                KHItems.GLAIVE.get(),
                KHItems.CURVED_GLAIVE.get(),
                KHItems.HALBERD.get(),
                KHItems.LANCE.get(),
                KHItems.WOODEN_LANCE.get(),
                KHItems.POLEAXE.get(),
                KHItems.POLEHAMMER.get(),
                KHItems.BEC_DE_CORBIN.get(),
                KHItems.MORNING_STAR.get(),
                KHItems.BARDICHE.get(),
                KHItems.GREATSWORD.get(),
                KHItems.CLAYMORE.get(),
                KHItems.FLAMBERGE.get(),
                KHItems.ZWEIHANDER.get(),
                KHItems.WARDART.get(),
                KHItems.LONGBOW.get()
        };

        // 2D ICON-ONLY weapons
        Item[] weaponsIcon = {
                KHItems.FLAIL.get(),
                KHItems.BALL_FLAIL.get(),
                KHItems.HEAVY_CROSSBOW.get(),
                KHItems.ARQUEBUS.get(),
                KHItems.HANDGONNE.get()
        };

        Item[] patternedItems = new Item[]{
                KHItems.SURCOAT.get(),
                KHItems.SURCOAT_SLEEVELESS.get()
        };

        Item[] manuscriptItems = {
                KHItems.DAGGER.get(), KHItems.STILETTO.get(), KHItems.RAPIER.get(), KHItems.SWORD.get(), KHItems.V_SWORD.get(), KHItems.ARMING_SWORD.get(),
                KHItems.AXE.get(), KHItems.BROAD_AXE.get(), KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get(), KHItems.MACE.get(), KHItems.SPIKED_MACE.get(),
                KHItems.FLAIL.get(), KHItems.BALL_FLAIL.get(), KHItems.HAMMER.get(), KHItems.WAR_HAMMER.get(), KHItems.LONGSWORD.get(), KHItems.V_LONGSWORD.get(),
                KHItems.FALCHION.get(), KHItems.SCIMITAR.get(), KHItems.PITCHFORK.get(), KHItems.SPEAR.get(), KHItems.PIKE.get(), KHItems.BILLHOOK.get(),
                KHItems.GLAIVE.get(), KHItems.CURVED_GLAIVE.get(), KHItems.HALBERD.get(), KHItems.LANCE.get(), KHItems.WOODEN_LANCE.get(), KHItems.POLEAXE.get(),
                KHItems.POLEHAMMER.get(), KHItems.BEC_DE_CORBIN.get(), KHItems.MORNING_STAR.get(), KHItems.BARDICHE.get(), KHItems.GREATSWORD.get(),
                KHItems.CLAYMORE.get(), KHItems.FLAMBERGE.get(), KHItems.ZWEIHANDER.get(), KHItems.WARDART.get(), KHItems.QUILTED_COIF.get(), KHItems.GAMBESON.get(),
                KHItems.GAMBESON_BREECHES.get(), KHItems.GAMBESON_BOOTS.get(), KHItems.MAIL_COIF.get(), KHItems.HAUBERK.get(), KHItems.MAIL_BREECHES.get(),
                KHItems.MAIL_BOOTS.get(), KHItems.MAIL_SPAULDERS.get(), KHItems.BRIGANDINE_SPAULDERS.get(), KHItems.PLATE_SPAULDERS.get(), KHItems.BRIGANDINE.get(),
                KHItems.PLATE_CUIRASS.get(), KHItems.MAXIMILLIAN_CUIRASS.get(), KHItems.XIIII_PLATE_CUIRASS.get(), KHItems.PLACKART.get(), KHItems.TASSETS.get(),
                KHItems.XIIII_PLATE_BREASTPLATE.get(), KHItems.BARBUTE.get(), KHItems.BASCINET.get(), KHItems.KETTLE_HELM.get(), KHItems.NASAL_HELM.get(),
                KHItems.VIKING_HELM.get(), KHItems.BURGONET.get(), KHItems.VISORLESS_SALLET.get(), KHItems.ARMET.get(), KHItems.ARMET_2.get(), KHItems.VISORED_BARBUTE.get(), KHItems.HOUNDSKULL.get(),
                KHItems.CAGE.get(), KHItems.VISORED_BASCINET.get(), KHItems.GREAT_HELM.get(), KHItems.BURGONET_FALLING_BUFFE.get(), KHItems.BLACK_SALLET.get(),
                KHItems.CLOSE_HELM.get(), KHItems.FROGMOUTH.get(), KHItems.GREAT_ARMET.get(), KHItems.GREAT_ARMET_2.get(), KHItems.GREAT_BASCINET.get(),
                KHItems.GREAT_HOUNDSKUL_BASCINET.get(), KHItems.MAXIMILLIAN_HELMET.get(), KHItems.SAVOYARD.get(), KHItems.LEATHER_GLOVES.get(), KHItems.MAIL_GLOVES.get(), KHItems.GAUNTLET.get(), KHItems.BRIGANDINE_HARNESS.get(),
                KHItems.PLATE_HARNESS.get(), KHItems.BRIGANDINE_CUISSES.get(), KHItems.PLATE_CUISSES.get(), KHItems.GREAVES.get(), KHItems.SABATONS.get(),
                KHItems.SURCOAT.get(), KHItems.SURCOAT_SLEEVELESS.get(), KHItems.CIVILIAN_SURCOAT.get(), KHItems.GIORNEA.get(), KHItems.CLOAK.get(),
                KHItems.TORN_CLOAK.get(), KHItems.HOOD.get(), KHItems.TORN_HOOD.get(), KHItems.JESTER_HOOD.get(), KHItems.HELMET_HOOD.get(), KHItems.HELMET_TORN_HOOD.get(),
                KHItems.LONGBOW.get(), KHItems.HEAVY_CROSSBOW.get(), KHItems.ARQUEBUS.get(), KHItems.HANDGONNE.get(), KHItems.SWALLOWTAIL_ARROW.get(),
                KHItems.BODKIN_ARROW.get(), KHItems.BROADHEAD_ARROW.get(), KHItems.CLOTH_ARROW.get(), KHItems.HORSE_BARDING.get(), KHItems.RIM_GUARDS.get(),
                KHItems.BESAGEWS.get()
        };

        String[] bannerPatternNames = {
                "border", "bricks", "circle", "creeper", "cross", "curly_border", "diagonal_left", "diagonal_right",
                "diagonal_up_left", "diagonal_up_right", "flow", "flower", "globe", "gradient", "gradient_up", "guster", "half_horizontal",
                "half_horizontal_bottom", "half_vertical", "half_vertical_right", "mojang", "piglin", "rhombus", "skull",
                "small_stripes", "square_bottom_left", "square_bottom_right", "square_top_left", "square_top_right",
                "straight_cross", "stripe_bottom", "stripe_center", "stripe_downleft", "stripe_downright", "stripe_left",
                "stripe_middle", "stripe_right", "stripe_top", "triangle_bottom", "triangle_top", "triangles_bottom", "triangles_top"
        };

        for (Item item : weapons3D) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(
                    ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() + "_3d")));
        }

        for (Item item : weaponsIcon) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(
                    ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() + "_icon")));
        }

        for (Item manuscriptItem : manuscriptItems) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(manuscriptItem);
            this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(
                    ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "manuscript_" + id.getPath())));
        }

        for (Item item : patternedItems) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            for (String pattern : bannerPatternNames) {
                String modelPath = id.getPath() + "/" + pattern;
                this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(
                        ResourceLocation.fromNamespaceAndPath(id.getNamespace(), modelPath)));
            }
        }
    }
}