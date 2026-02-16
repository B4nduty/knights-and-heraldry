package banduty.knightsheraldry.platform.services;

import banduty.knightsheraldry.config.KHConfigImpl;
import banduty.knightsheraldry.data.ArrowBehavior;
import banduty.knightsheraldry.recipes.HelmetDecoRecipe;
import banduty.stoneycore.recipes.BannerPatternRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeSerializer;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    KHConfigImpl getConfig();

    int getComboCount(Player player);

    ArrowBehavior getBehavior(ResourceLocation key);

    MobEffect getPinEffect();

    RecipeSerializer<HelmetDecoRecipe> getHelmetDecoRecipe();
}