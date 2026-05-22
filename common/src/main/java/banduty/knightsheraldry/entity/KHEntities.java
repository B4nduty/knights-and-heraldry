package banduty.knightsheraldry.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.*;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public interface KHEntities {
    Supplier<EntityType<KHBodkinArrowEntity>> BODKIN_ARROW =
            registerEntity("bodkin_arrow",
                    () -> EntityType.Builder.<KHBodkinArrowEntity>of(KHBodkinArrowEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(64)
                            .updateInterval(10)
                            .build("bodkin_arrow")
            );

    Supplier<EntityType<KHBroadheadArrowEntity>> BROADHEAD_ARROW =
            registerEntity("broadhead_arrow",
                    () -> EntityType.Builder.<KHBroadheadArrowEntity>of(KHBroadheadArrowEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(64)
                            .updateInterval(10)
                            .build("broadhead_arrow")
            );

    Supplier<EntityType<KHClothArrowEntity>> CLOTH_ARROW =
            registerEntity("cloth_arrow",
                    () -> EntityType.Builder.<KHClothArrowEntity>of(KHClothArrowEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(64)
                            .updateInterval(10)
                            .build("cloth_arrow")
            );

    Supplier<EntityType<KHSwallowTailArrowEntity>> SWALLOWTAIL_ARROW =
            registerEntity("swallowtail_arrow",
                    () -> EntityType.Builder.<KHSwallowTailArrowEntity>of(KHSwallowTailArrowEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(64)
                            .updateInterval(10)
                            .build("swallowtail_arrow")
            );

    Supplier<EntityType<WarDartEntity>> WARDART_PROJECTILE =
            registerEntity("wardart_projectile",
                    () -> EntityType.Builder.<WarDartEntity>of(WarDartEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(64)
                            .updateInterval(10)
                            .build("wardart_projectile")
            );

    @SuppressWarnings("unchecked")
    private static <T extends Entity> Supplier<EntityType<T>> registerEntity(String name, Supplier<EntityType<T>> entitySupplier) {
        return Services.PLATFORM.register((Registry<EntityType<T>>) (Registry<?>) BuiltInRegistries.ENTITY_TYPE, name, entitySupplier);
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering Entities for " + KnightsHeraldry.MOD_ID);
    }
}
