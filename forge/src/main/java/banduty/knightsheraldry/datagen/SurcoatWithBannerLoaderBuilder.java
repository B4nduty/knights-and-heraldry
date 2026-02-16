package banduty.knightsheraldry.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SurcoatWithBannerLoaderBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {

    public SurcoatWithBannerLoaderBuilder(T parent, ExistingFileHelper existingFileHelper) {
        super(new ResourceLocation("knightsheraldry", "surcoat_with_banner"), parent, existingFileHelper);
    }

    public static <T extends ModelBuilder<T>> SurcoatWithBannerLoaderBuilder<T> begin(T parent, ExistingFileHelper existingFileHelper) {
        return new SurcoatWithBannerLoaderBuilder<>(parent, existingFileHelper);
    }
}