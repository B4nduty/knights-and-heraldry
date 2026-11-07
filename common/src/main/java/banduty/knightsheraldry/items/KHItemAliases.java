package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

/**
 * Old item ids that now load as another item, so stacks saved under a removed id
 * (in inventories, containers, or under-armor attachments) convert instead of vanishing.
 */
public final class KHItemAliases {
    private KHItemAliases() {}

    public static final Map<ResourceLocation, ResourceLocation> ALIASES = Map.of(
            // Helmet hoods were merged into the hood, which now switches model when worn over a helmet
            id("helmet_hood"), id("hood"),
            id("helmet_torn_hood"), id("torn_hood")
    );

    public static ResourceLocation resolve(ResourceLocation location) {
        return location == null ? null : ALIASES.getOrDefault(location, location);
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, path);
    }
}
