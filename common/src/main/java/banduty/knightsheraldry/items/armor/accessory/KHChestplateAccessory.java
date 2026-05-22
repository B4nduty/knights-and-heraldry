package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHChestplateAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class KHChestplateAccessory extends Item implements SCAccessory, AccessoryRenderProvider {
    private final Ingredient ingredient;
    boolean hasOverlay;
    int defaultColor;

    public AccessoryRenderer cachedRenderer;

    public KHChestplateAccessory(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.hasOverlay = false;
        this.defaultColor = -1;
    }

    public KHChestplateAccessory(Properties properties, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.hasOverlay = false;
        this.defaultColor = defaultColor;
    }

    public KHChestplateAccessory(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.hasOverlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    @Override
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.CHESTPLATE;
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHChestplateAccessoryRenderer();
        }
        return this.cachedRenderer;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }
}
