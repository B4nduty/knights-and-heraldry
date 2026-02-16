package banduty.knightsheraldry.util.itemdata;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.*;

public record HelmetDeco(Item item, int color, int group) {
    private static final Map<Item, HelmetDeco> HELMET_DECO = new HashMap<>();

    public static void register(Item item, int color, int group) {
        HELMET_DECO.put(item, new HelmetDeco(item, color, group));
    }

    public static Set<Item> getRegisteredItems() {
        return HELMET_DECO.keySet();
    }

    public static Collection<HelmetDeco> getValues() {
        return HELMET_DECO.values();
    }

    public static Optional<HelmetDeco> getDecoFromItem(Item item) {
        return Optional.ofNullable(HELMET_DECO.get(item));
    }

    public String getNbtKey() {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    public static List<Item> getDecoGroup(int group) {
        return HELMET_DECO.values().stream()
                .filter(deco -> deco.group() == group)
                .map(HelmetDeco::item)
                .toList();
    }
}