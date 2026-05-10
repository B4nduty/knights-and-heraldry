package banduty.knightsheraldry.items.armor.horse;

import banduty.knightsheraldry.items.DecoableItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class HorseBardingArmorItem extends AnimalArmorItem implements DecoableItem {

    public HorseBardingArmorItem(int protection, Properties properties) {
        super(ArmorMaterials.IRON, AnimalArmorItem.BodyType.EQUESTRIAN, true, properties);
    }

    public ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath("stoneycore", "textures/models/armor/a_layer_1.png");
    }

    public int getColor(ItemStack stack) {
        DyedItemColor dyedItemColor = stack.get(DataComponents.DYED_COLOR);
        return dyedItemColor != null ? dyedItemColor.rgb() : 0xFFFFFF;
    }

    public boolean hasColor(ItemStack stack) {
        return stack.has(DataComponents.DYED_COLOR);
    }

    public void clearColor(ItemStack stack) {
        stack.remove(DataComponents.DYED_COLOR);
    }

    public void setColor(ItemStack stack, int color) {
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color, true));
    }
}