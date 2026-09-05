package banduty.knightsheraldry.items.armor;

import java.util.List;

public record VariantCombo(ArmorVariant tier, List<ArmorVariant> structural) {

    public static VariantCombo of(ArmorVariant tier) {
        return new VariantCombo(tier, List.of());
    }

    public static VariantCombo of(ArmorVariant tier, ArmorVariant... structural) {
        return new VariantCombo(tier, List.of(structural));
    }
}