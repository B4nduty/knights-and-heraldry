package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(
        modid = KnightsHeraldry.MOD_ID
)
public final class KHModelLoadingPlugin {

    @SubscribeEvent
    public static void onRegisterAdditional(ModelEvent.RegisterAdditional event) {
        List<ResourceLocation> models = new ArrayList<>();

        for (String entry : Services.PLATFORM.getConfig().getBannerModelItemPaths()) {
            ResourceLocation itemId = ResourceLocation.parse(entry);
            String modId = itemId.getNamespace();
            String itemPath = itemId.getPath();

            models.add(ResourceLocation.fromNamespaceAndPath(
                    modId,
                    "item/" + itemPath + "_base"
            ));

            if (Services.PLATFORM.getConfig().includeVanillaBannerPatterns()) {
                addVanillaPatterns(models, modId, itemPath);
            }

            for (String patternName : Services.PLATFORM.getConfig().getBannerPatternNames()) {
                models.add(createPatternModel(
                        modId,
                        itemPath,
                        ResourceLocation.parse(patternName)
                ));
            }
        }

        for (ResourceLocation model : models) {
            event.register(ModelResourceLocation.standalone(
                    model
            ));
        }
    }

    private static void addVanillaPatterns(
            List<ResourceLocation> models,
            String modId,
            String itemPath
    ) {
        for (Field field : BannerPatterns.class.getDeclaredFields()) {
            if (field.getType() != ResourceKey.class
                    || !Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            try {
                ResourceKey<?> key = (ResourceKey<?>) field.get(null);

                if (key == null || !key.isFor(Registries.BANNER_PATTERN)) {
                    continue;
                }

                models.add(createPatternModel(
                        modId,
                        itemPath,
                        key.location()
                ));
            } catch (IllegalAccessException exception) {
                throw new RuntimeException(
                        "Failed to register banner pattern models",
                        exception
                );
            }
        }
    }

    private static ResourceLocation createPatternModel(
            String modId,
            String itemPath,
            ResourceLocation patternId
    ) {
        return ResourceLocation.fromNamespaceAndPath(
                modId,
                "item/" + itemPath + "/"
                        + patternId.getNamespace() + "/"
                        + patternId.getPath()
        );
    }
}