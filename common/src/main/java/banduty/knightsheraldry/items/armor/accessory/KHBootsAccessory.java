package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.model.KHModels;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHBootsAccessory extends AccessoryItem implements SCAccessoryItem {
    private final Ingredient ingredient;

    public KHBootsAccessory(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
    }

    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBase(KHModels.getBootsModel());
    }

    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        String itemName = BuiltInRegistries.ITEM.getKey(this).getPath();
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + itemName + ".png");
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }
}
