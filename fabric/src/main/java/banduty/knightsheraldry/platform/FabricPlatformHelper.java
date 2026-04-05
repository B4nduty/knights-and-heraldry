package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.config.FabricKHConfigImpl;
import banduty.knightsheraldry.config.KHConfigImpl;
import banduty.knightsheraldry.data.ArrowBehavior;
import banduty.knightsheraldry.data.ArrowBehaviorManager;
import banduty.knightsheraldry.effect.ModEffects;
import banduty.knightsheraldry.platform.services.IPlatformHelper;
import banduty.knightsheraldry.recipe.TwoLayerDyeRecipe;
import banduty.knightsheraldry.recipes.HelmetDecoRecipe;
import banduty.knightsheraldry.recipes.ModRecipes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class FabricPlatformHelper implements IPlatformHelper {
    private final FabricKHConfigImpl config;

    public FabricPlatformHelper() {
        this.config = new FabricKHConfigImpl();
    }

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public KHConfigImpl getConfig() {
        return config;
    }

    @Override
    public ArrowBehavior getBehavior(ResourceLocation key) {
        return ArrowBehaviorManager.INSTANCE.getBehavior(key);
    }

    @Override
    public MobEffect getPinEffect() {
        return ModEffects.PIN;
    }

    @Override
    public RecipeSerializer<HelmetDecoRecipe> getHelmetDecoRecipe() {
        return ModRecipes.HELMET_DECO_SERIALIZER;
    }

    @Override
    public RecipeSerializer<TwoLayerDyeRecipe> getTwoLayerDyeRecipe() {
        return ModRecipes.TWO_LAYER_DYE_SERIALIZER;
    }
}
