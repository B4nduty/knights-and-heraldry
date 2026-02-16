package banduty.knightsheraldry;

import banduty.knightsheraldry.config.IKHConfig;
import banduty.knightsheraldry.platform.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnightsHeraldry {

    public static final String MOD_ID = "knightsheraldry";
    public static final String MOD_NAME = "Knights & Heraldry";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        if (Services.PLATFORM.isModLoaded(KnightsHeraldry.MOD_ID)) {
            LOG.info("Hello to knightsheraldry");
        }
    }

    public static IKHConfig getConfig() {
        return Services.PLATFORM.getConfig();
    }
}