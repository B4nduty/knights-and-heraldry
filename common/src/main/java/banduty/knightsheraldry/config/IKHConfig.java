package banduty.knightsheraldry.config;

import java.util.List;

public interface IKHConfig {
    int getLanceCooldown();

    boolean getDamageTamedEntities();

    int getWardartCooldown();

    List<String> getBannerModelItemPaths();

    boolean includeVanillaBannerPatterns();

    List<String> getBannerPatternNames();
}
