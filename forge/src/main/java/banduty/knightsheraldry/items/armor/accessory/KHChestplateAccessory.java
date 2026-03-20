package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.model.AccessoryArmModel;
import banduty.knightsheraldry.model.AccessoryChestplateModel;
import banduty.stoneycore.items.armor.SCAccessoryItem;
import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KHChestplateAccessory extends AccessoryItem implements SCAccessoryItem {
    private final Ingredient ingredient;

    public KHChestplateAccessory(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBaseAndFirstPerson(new AccessoryChestplateModel(AccessoryChestplateModel.getTexturedModelData().bakeRoot()),
                new AccessoryArmModel(AccessoryArmModel.getTexturedModelData().bakeRoot()));
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
