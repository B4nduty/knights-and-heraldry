package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.config.ForgeKHConfigImpl;
import banduty.knightsheraldry.config.KHConfigImpl;
import banduty.knightsheraldry.data.ArrowBehavior;
import banduty.knightsheraldry.data.ArrowBehaviorManager;
import banduty.knightsheraldry.effect.ModEffects;
import banduty.knightsheraldry.platform.services.IPlatformHelper;
import banduty.knightsheraldry.recipe.TwoLayerDyeRecipe;
import banduty.knightsheraldry.recipes.HelmetDecoRecipe;
import banduty.knightsheraldry.recipes.ModRecipes;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {
    private final ForgeKHConfigImpl config;

    public ForgePlatformHelper() {
        this.config = new ForgeKHConfigImpl();
    }

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public KHConfigImpl getConfig() {
        return config;
    }

    @Override
    public int getComboCount(Player player) {
        return ((PlayerAttackProperties) player).getComboCount();
    }

    @Override
    public ArrowBehavior getBehavior(ResourceLocation key) {
        return ArrowBehaviorManager.INSTANCE.getBehavior(key);
    }


    @Override
    public MobEffect getPinEffect() {
        return ModEffects.PIN.get();
    }

    @Override
    public RecipeSerializer<HelmetDecoRecipe> getHelmetDecoRecipe() {
        return ModRecipes.HELMET_DECO_SERIALIZER.get();
    }

    @Override
    public RecipeSerializer<TwoLayerDyeRecipe> getTwoLayerDyeRecipe() {
        return ModRecipes.TWO_LAYER_DYEING_SERIALIZER.get();
    }
}