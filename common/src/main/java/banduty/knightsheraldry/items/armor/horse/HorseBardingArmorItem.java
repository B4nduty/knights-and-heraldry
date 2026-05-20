package banduty.knightsheraldry.items.armor.horse;

import banduty.knightsheraldry.items.DecoableItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;

public class HorseBardingArmorItem extends AnimalArmorItem implements DecoableItem {

    public HorseBardingArmorItem(Properties properties) {
        super(ArmorMaterials.IRON, AnimalArmorItem.BodyType.EQUESTRIAN, true, properties);
    }

    public ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath("stoneycore", "textures/models/armor/a_layer_1.png");
    }

    public int getDefaultColor() {
        return -1;
    }
}