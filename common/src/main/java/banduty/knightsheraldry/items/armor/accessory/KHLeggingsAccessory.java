package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHLeggingsAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class KHLeggingsAccessory extends Item implements SCAccessory, AccessoryRenderProvider {
    private final Ingredient ingredient;
    boolean overlay;
    int defaultColor;

    public AccessoryRenderer cachedRenderer;

    public KHLeggingsAccessory(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.overlay = false;
        this.defaultColor = -1;
    }

    public KHLeggingsAccessory(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    @Override
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.LEGGINGS;
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHLeggingsAccessoryRenderer();
        }
        return this.cachedRenderer;
    }

    public int getDefaultColor() {
        return this.defaultColor;
    }
}
