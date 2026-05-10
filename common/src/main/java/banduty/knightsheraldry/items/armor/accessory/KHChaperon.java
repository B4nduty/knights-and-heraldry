package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.model.KHModels;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class KHChaperon extends AccessoryItem implements SCAccessoryItem {
    boolean hasOverlay;

    public KHChaperon(Properties properties, boolean hasOverlay) {
        super(properties);
        this.hasOverlay = hasOverlay;
    }

    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBase(KHModels.getChaperonModel());
    }

    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/chaperon.png");
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new SCAccessoryItem.RenderSettings(hasOverlay, false, true);
    }

    @Override
    public int getColor(ItemStack stack) {
        return 0;
    }
}
