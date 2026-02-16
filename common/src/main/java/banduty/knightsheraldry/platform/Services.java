package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.services.IEntityHelper;
import banduty.knightsheraldry.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IEntityHelper ENTITY = load(IEntityHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        KnightsHeraldry.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}