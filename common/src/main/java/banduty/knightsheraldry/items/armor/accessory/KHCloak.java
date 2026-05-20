package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.model.KHModels;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class KHCloak extends AccessoryItem implements SCAccessoryItem {
    boolean overlay;

    public KHCloak(Properties properties) {
        super(properties);
        this.overlay = false;
    }

    public KHCloak(Properties properties, boolean overlay) {
        super(properties);
        this.overlay = overlay;
    }

    @Override
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBase(KHModels.getCloakHoodModel());
    }

    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        String itemName = BuiltInRegistries.ITEM.getKey(this).getPath();
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + itemName + ".png");
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(overlay, true, false);
    }

    @Override
    public int getDefaultColor() {
        return 0xFFA06440;
    }
}
