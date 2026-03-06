package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.DecoableItem;
import banduty.knightsheraldry.model.CageHelmClosed;
import banduty.knightsheraldry.model.CageHelmOpened;
import banduty.stoneycore.items.armor.SCAccessoryItem;
import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHCageHelmetAccessory extends AccessoryItem implements SCAccessoryItem, DecoableItem {
    private final boolean openVisor;
    private final Ingredient ingredient;

    public KHCageHelmetAccessory(Properties properties, Ingredient ingredient) {
        super(properties);
        this.openVisor = false;
        this.ingredient = ingredient;
    }

    public KHCageHelmetAccessory(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties);
        this.openVisor = openVisor;
        this.ingredient = ingredient;
    }

    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        if (openVisor) {
            return ModelBundle.ofBaseAndVisor(new CageHelmClosed(CageHelmClosed.getTexturedModelData().bakeRoot()), new CageHelmOpened(CageHelmOpened.getTexturedModelData().bakeRoot()));
        }
        return ModelBundle.ofBase(new CageHelmClosed(CageHelmClosed.getTexturedModelData().bakeRoot()));
    }

    @Override
    public boolean hasOpenVisor(ItemStack stack) {
        return openVisor;
    }

    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }
}
