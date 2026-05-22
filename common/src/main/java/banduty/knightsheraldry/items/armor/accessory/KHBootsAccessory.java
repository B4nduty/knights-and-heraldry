package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHBootsAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class KHBootsAccessory extends Item implements SCAccessory, AccessoryRenderProvider {
    private final Ingredient ingredient;

    public AccessoryRenderer cachedRenderer;

    public KHBootsAccessory(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    @Override
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.BOOTS;
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHBootsAccessoryRenderer();
        }
        return this.cachedRenderer;
    }
}
