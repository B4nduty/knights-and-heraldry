package banduty.knightsheraldry.util.itemdata;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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

    public static int getColor(ItemStack stack, String key, int defaultColor) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("HelmetDeco")) return defaultColor;

        CompoundTag deco = tag.getCompound("HelmetDeco");

        if (!deco.contains(key)) return defaultColor;

        return deco.getInt(key);
    }

    public static List<Item> getDecoGroup(int group) {
        return HELMET_DECO.values().stream()
                .filter(deco -> deco.group() == group)
                .map(HelmetDeco::item)
                .toList();
    }
}