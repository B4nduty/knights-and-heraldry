package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.services.IClientPlatformHelper;

import java.util.ServiceLoader;

public class ClientServices {
    private static IClientPlatformHelper CLIENT_PLATFORM;

    public static IClientPlatformHelper getClientHelper() {
        if (CLIENT_PLATFORM == null) {
            CLIENT_PLATFORM = load(IClientPlatformHelper.class);
        }
        return CLIENT_PLATFORM;
    }

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        KnightsHeraldry.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}