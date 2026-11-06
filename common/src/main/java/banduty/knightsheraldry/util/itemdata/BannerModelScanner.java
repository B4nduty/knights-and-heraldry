package banduty.knightsheraldry.util.itemdata;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds the base and per-pattern models for banner-patterned items by scanning the loaded
 * resource packs, so vanilla, modded and resource-pack patterns are all picked up without config.
 * <p>
 * Banner patterns live in a datapack registry that isn't available at model-load time, so the
 * model files on disk are the source of truth: {@code <ns>:models/item/<item>/<patternNs>/<pattern>.json}.
 */
public final class BannerModelScanner {
    private static final String MODELS_PREFIX = "models/";
    private static final String JSON_SUFFIX = ".json";

    private BannerModelScanner() {
    }

    public static List<ResourceLocation> collect(ResourceManager resourceManager, List<String> itemIds) {
        List<ResourceLocation> models = new ArrayList<>();

        for (String entry : itemIds) {
            ResourceLocation itemId = ResourceLocation.parse(entry);
            String namespace = itemId.getNamespace();
            String itemPath = itemId.getPath();

            models.add(itemId.withPath("item/" + itemPath + "_base"));

            resourceManager.listResources(
                    MODELS_PREFIX + "item/" + itemPath,
                    file -> file.getPath().endsWith(JSON_SUFFIX)
            ).keySet().forEach(file -> {
                if (!file.getNamespace().equals(namespace)) {
                    return;
                }

                String path = file.getPath();
                models.add(file.withPath(path.substring(
                        MODELS_PREFIX.length(),
                        path.length() - JSON_SUFFIX.length()
                )));
            });
        }

        return models;
    }
}
