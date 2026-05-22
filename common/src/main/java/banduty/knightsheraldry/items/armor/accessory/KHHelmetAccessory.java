package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHHelmetAccessoryRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import banduty.stoneycore.items.custom.armor.deco.DecorableItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class KHHelmetAccessory extends DecorableItem implements SCAccessory, AccessoryRenderProvider {
    private final boolean openVisor;
    private final Ingredient ingredient;

    public AccessoryRenderer cachedRenderer;

    public KHHelmetAccessory(Properties properties, Ingredient ingredient) {
        super(properties);
        this.openVisor = false;
        this.ingredient = ingredient;
    }

    public KHHelmetAccessory(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties);
        this.openVisor = openVisor;
        this.ingredient = ingredient;
    }

    @Override
    public boolean hasOpenVisor(ItemStack stack) {
        return openVisor;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    @Override
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.HELMET;
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHHelmetAccessoryRenderer();
        }
        return this.cachedRenderer;
    }
}
