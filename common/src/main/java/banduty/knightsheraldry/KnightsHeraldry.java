package banduty.knightsheraldry;

import banduty.knightsheraldry.config.IKHConfig;
import banduty.knightsheraldry.effect.KHEffects;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.platform.Services;
import banduty.knightsheraldry.recipes.KHRecipes;
import banduty.knightsheraldry.sounds.KHSounds;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnightsHeraldry {

    public static final String MOD_ID = "knightsheraldry";
    public static final String MOD_NAME = "Knights & Heraldry";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        KHItems.init();
        KHEffects.init();
        KHEntities.init();
        KHRecipes.init();
        KHSounds.init();
        KHDataComponents.init();

        if (Services.PLATFORM.isModLoaded(KnightsHeraldry.MOD_ID)) {
            LOG.info("Hello to knightsheraldry");
        }
    }

    public static IKHConfig getConfig() {
        return Services.PLATFORM.getConfig();
    }
}