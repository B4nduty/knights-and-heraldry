package com.knightsheraldry.items.armor.accessory;

    import banduty.stoneycore.items.armor.SCAccessoryItem;
    import com.knightsheraldry.KnightsHeraldry;
    import com.knightsheraldry.model.ChaperonModel;
    import io.wispforest.accessories.api.AccessoryItem;
    import net.fabricmc.api.EnvType;
    import net.fabricmc.api.Environment;
    import net.minecraft.resources.ResourceLocation;
    import net.minecraft.world.item.DyeableLeatherItem;
    import net.minecraft.world.item.Item;
    import net.minecraft.world.item.ItemStack;

public class KHChaperon extends AccessoryItem implements SCAccessoryItem, DyeableLeatherItem {
        boolean hasOverlay;

        public KHChaperon(Item.Properties properties, boolean hasOverlay) {
            super(properties);
            this.hasOverlay = hasOverlay;
        }

        @Environment(EnvType.CLIENT)
        public ModelBundle getModels(ItemStack itemStack) {
            return ModelBundle.ofBase(new ChaperonModel(ChaperonModel.getTexturedModelData().bakeRoot()));
        }

        @Override
        public ResourceLocation getTexturePath(ItemStack itemStack) {
            if (itemStack.getDamageValue() > itemStack.getMaxDamage() * 0.9f) return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + "_lowd.png");
            return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/chaperon.png");
        }

        @Override
        public RenderSettings getRenderSettings(ItemStack stack) {
            return new RenderSettings(hasOverlay, false, true);
        }
    }
