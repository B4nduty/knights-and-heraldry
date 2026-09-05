package banduty.knightsheraldry.items.armor;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public enum ArmorVariant {
    PLAIN("", 1.00, 0.0, 1.00, 0.0, 0.0, 0.0, () -> Ingredient.of(Items.IRON_INGOT)),
    DARK("dark_", 1.10, 1.0, 1.10, 0.0, 0.0, 0.0, () -> Ingredient.of(Items.IRON_INGOT)),
    GOLDEN("golden_", 1.20, 0.0, 1.05, 0.0, 0.0, 0.0, () -> Ingredient.of(Items.GOLD_INGOT)),

    BESAGEWS("_besagews", 1.00, 0.0, 1.00, 0.20, 1.0, 0.01, () -> Ingredient.of(Items.IRON_INGOT)),
    RIMMED("_rimmed", 1.00, 1.0, 1.00, 0.20, 0.0, 0.01, () -> Ingredient.of(Items.IRON_INGOT));

    public final String prefix;
    private final double durabilityMult;
    private final double toughnessBonus;
    private final double weightMult;
    private final double weightBonus;
    private final double armorBonus;
    private final double deflectBonus;
    private final Supplier<Ingredient> ingredientSupplier;

    ArmorVariant(String prefix, double durabilityMult, double toughnessBonus,
                 double weightMult, double weightBonus, double armorBonus,
                 double deflectBonus, Supplier<Ingredient> ingredientSupplier) {
        this.prefix = prefix;
        this.durabilityMult = durabilityMult;
        this.toughnessBonus = toughnessBonus;
        this.weightMult = weightMult;
        this.weightBonus = weightBonus;
        this.armorBonus = armorBonus;
        this.deflectBonus = deflectBonus;
        this.ingredientSupplier = ingredientSupplier;
    }

    public String id(String baseName) {
        if (prefix.isEmpty()) {
            return baseName;
        }
        if (prefix.endsWith("_")) {
            return prefix + baseName;
        }
        if (prefix.startsWith("_")) {
            return baseName + prefix;
        }
        throw new IllegalStateException(
                "ArmorVariant affix \"" + prefix + "\" must start or end with '_' so id() knows whether it's a prefix or suffix");
    }

    public int durability(int baseDurability) {
        return Math.round((float) (baseDurability * durabilityMult));
    }

    public double toughness(double baseToughness) {
        return baseToughness + toughnessBonus;
    }

    public double weight(double baseWeight) {
        return baseWeight * weightMult + weightBonus;
    }

    public double armor(double baseArmor) {
        return baseArmor + armorBonus;
    }

    public double deflect(double baseDeflect) {
        return baseDeflect + deflectBonus;
    }

    public Ingredient ingredient() {
        return ingredientSupplier.get();
    }

    public static List<List<ArmorVariant>> powerSet(List<ArmorVariant> toggles) {
        List<List<ArmorVariant>> subsets = new ArrayList<>();
        subsets.add(List.of());
        for (ArmorVariant toggle : toggles) {
            List<List<ArmorVariant>> withToggle = new ArrayList<>();
            for (List<ArmorVariant> existing : subsets) {
                List<ArmorVariant> combo = new ArrayList<>(existing);
                combo.add(toggle);
                withToggle.add(combo);
            }
            subsets.addAll(withToggle);
        }
        return subsets;
    }
}