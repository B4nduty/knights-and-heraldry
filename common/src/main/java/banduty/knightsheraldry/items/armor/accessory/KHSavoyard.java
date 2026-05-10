package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.DecoableItem;
import banduty.knightsheraldry.model.KHModels;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHSavoyard extends AccessoryItem implements SCAccessoryItem, DecoableItem {
    private final boolean openVisor;
    private final Ingredient ingredient;

    public KHSavoyard(Properties properties, Ingredient ingredient) {
        super(properties);
        this.openVisor = false;
        this.ingredient = ingredient;
    }

    public KHSavoyard(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties);
        this.openVisor = openVisor;
        this.ingredient = ingredient;
    }

    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        if (openVisor) {
            return ModelBundle.ofBaseAndVisor(KHModels.getSavoyardClosedModel(), KHModels.getSavoyardOpenedModel());
        }
        return ModelBundle.ofBase(KHModels.getSavoyardClosedModel());
    }

    @Override
    public boolean hasOpenVisor(ItemStack stack) {
        return openVisor;
    }

    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
    }

    @Override
    public int getColor(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }
}
