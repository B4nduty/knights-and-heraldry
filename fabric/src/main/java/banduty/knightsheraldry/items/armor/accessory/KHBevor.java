package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.model.BevorModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHBevor extends KHHelmetAccessory {
        boolean hasOverlay;

        public KHBevor(Properties properties, Ingredient ingredient) {
            super(properties, ingredient);
        }

        @Environment(EnvType.CLIENT)
        public ModelBundle getModels(ItemStack itemStack) {
            return ModelBundle.ofBase(new BevorModel(BevorModel.getTexturedModelData().bakeRoot()));
        }

        @Override
        public ResourceLocation getTexturePath(ItemStack itemStack) {
            return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" + this + ".png");
        }

        @Override
        public RenderSettings getRenderSettings(ItemStack stack) {
            return new RenderSettings(hasOverlay, false, true);
        }
    }
