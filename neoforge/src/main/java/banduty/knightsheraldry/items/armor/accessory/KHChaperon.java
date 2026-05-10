package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.model.ChaperonModel;
import banduty.stoneycore.items.armor.SCAccessoryItem;
import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KHChaperon extends AccessoryItem implements SCAccessoryItem, DyeableLeatherItem {
    boolean hasOverlay;

    public KHChaperon(Properties properties, boolean hasOverlay) {
        super(properties);
        this.hasOverlay = hasOverlay;
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelBundle getModels(ItemStack itemStack) {
        return ModelBundle.ofBase(new ChaperonModel(ChaperonModel.getTexturedModelData().bakeRoot()));
    }

    @Override
    public ResourceLocation getTexturePath(ItemStack itemStack) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/chaperon.png");
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(hasOverlay, false, true);
    }
}
