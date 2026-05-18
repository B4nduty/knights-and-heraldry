package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.model.KHModels;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.Ingredient;

public class KHChestplateAccessory extends AccessoryItem implements SCAccessoryItem {
    private final Ingredient ingredient;
    boolean overlay;
    int defaultColor;

    public KHChestplateAccessory(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.overlay = false;
        this.defaultColor = 0xffffff;
    }

    public KHChestplateAccessory(Properties properties, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.overlay = false;
        this.defaultColor = defaultColor;
    }

    public KHChestplateAccessory(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBaseAndFirstPerson(KHModels.getChestplateModel(), KHModels.getArmModel());
    }

    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        String itemName = BuiltInRegistries.ITEM.getKey(this).getPath();
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + itemName + ".png");
    }

    @Override
    public int getColor(ItemStack itemStack) {
        DyedItemColor dyedItemColor = itemStack.get(DataComponents.DYED_COLOR);
        return dyedItemColor != null ? dyedItemColor.rgb() : this.defaultColor;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(overlay, false, false);
    }

}
