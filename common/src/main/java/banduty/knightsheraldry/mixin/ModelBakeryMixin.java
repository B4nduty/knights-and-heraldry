package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.KnightsHeraldry;
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

        for (Item item : patternedItems) {
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
            for (String pattern : bannerPatternNames) {
                String modelPath = itemId.getPath() + "/" + pattern;
                this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(
                        ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, modelPath)));
            }
        }
    }
}