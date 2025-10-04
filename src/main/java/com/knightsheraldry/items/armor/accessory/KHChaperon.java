package com.knightsheraldry.items.armor.accessory;

    import banduty.stoneycore.items.armor.SCAccessoryItem;
    import com.knightsheraldry.KnightsHeraldry;
    import com.knightsheraldry.model.ChaperonModel;
    import io.wispforest.accessories.api.AccessoryItem;
    import net.fabricmc.api.EnvType;
    import net.fabricmc.api.Environment;
    import net.minecraft.item.DyeableItem;
    import net.minecraft.item.ItemStack;
    import net.minecraft.util.Identifier;

    public class KHChaperon extends AccessoryItem implements SCAccessoryItem, DyeableItem {
        boolean hasOverlay;

        public KHChaperon(Settings settings, boolean hasOverlay) {
            super(settings);
            this.hasOverlay = hasOverlay;
        }

        @Environment(EnvType.CLIENT)
        public ModelBundle getModels(ItemStack itemStack) {
            return ModelBundle.ofBase(new ChaperonModel(ChaperonModel.getTexturedModelData().createModel()));
        }

        @Override
        public Identifier getTexturePath(ItemStack itemStack) {
            return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/accessories/chaperon.png");
        }

        @Override
        public RenderSettings getRenderSettings(ItemStack stack) {
            return new RenderSettings(hasOverlay, false, true);
        }
    }
