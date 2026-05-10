package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.model.KHModels;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHBevor extends KHHelmetAccessory {
        boolean hasOverlay;

        public KHBevor(Properties properties, Ingredient ingredient) {
            super(properties, ingredient);
        }

        public ModelBundle getModels(ItemStack itemStack) {
            return SCAccessoryItem.ModelBundle.ofBase(KHModels.getBevorModel());
        }

        @Override
        public ResourceLocation getTexturePath(ItemStack itemStack) {
            return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
        }

        @Override
        public SCAccessoryItem.RenderSettings getRenderSettings(ItemStack stack) {
            return new SCAccessoryItem.RenderSettings(hasOverlay, false, true);
        }
    }
