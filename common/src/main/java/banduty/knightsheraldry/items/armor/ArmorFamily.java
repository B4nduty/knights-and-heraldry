package banduty.knightsheraldry.items.armor;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ArmorFamily implements Supplier<Item> {

    private final Map<VariantCombo, Supplier<Item>> items;
    private final Supplier<Item> base;

    public ArmorFamily(Map<VariantCombo, Supplier<Item>> items, VariantCombo baseCombo) {
        this.items = new LinkedHashMap<>(items);
        this.base = this.items.get(baseCombo);
        if (this.base == null) {
            throw new IllegalArgumentException("Base combo " + baseCombo + " was not registered in this family");
        }
    }

    @Override
    public Item get() {
        return base.get();
    }

    public Supplier<Item> get(ArmorVariant tier, ArmorVariant... structural) {
        return items.get(VariantCombo.of(tier, structural));
    }

    public List<Supplier<Item>> list() {
        return List.copyOf(items.values());
    }

    public Map<VariantCombo, Supplier<Item>> combos() {
        return Map.copyOf(items);
    }

    public static List<Supplier<Item>> allFamilies(ArmorFamily... families) {
        List<Supplier<Item>> out = new ArrayList<>();
        for (ArmorFamily f : families) out.addAll(f.list());
        return out;
    }
}