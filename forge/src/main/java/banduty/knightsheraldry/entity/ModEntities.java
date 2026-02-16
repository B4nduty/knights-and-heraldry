package banduty.knightsheraldry.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModEntities {
    DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, KnightsHeraldry.MOD_ID);

    RegistryObject<EntityType<KHBodkinArrowEntity>> BODKIN_ARROW =
            ENTITY_TYPES.register("bodkin_arrow", () -> EntityType.Builder.<KHBodkinArrowEntity>of(KHBodkinArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(64)
                    .updateInterval(10)
                    .build("bodkin_arrow"));

    RegistryObject<EntityType<KHBroadheadArrowEntity>> BROADHEAD_ARROW =
            ENTITY_TYPES.register("broadhead_arrow", () -> EntityType.Builder.<KHBroadheadArrowEntity>of(KHBroadheadArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(64)
                    .updateInterval(10)
                    .build("broadhead_arrow"));

    RegistryObject<EntityType<KHClothArrowEntity>> CLOTH_ARROW =
            ENTITY_TYPES.register("cloth_arrow", () -> EntityType.Builder.<KHClothArrowEntity>of(KHClothArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(64)
                    .updateInterval(10)
                    .build("cloth_arrow"));

    RegistryObject<EntityType<KHSwallowTailArrowEntity>> SWALLOWTAIL_ARROW =
            ENTITY_TYPES.register("swallowtail_arrow", () -> EntityType.Builder.<KHSwallowTailArrowEntity>of(KHSwallowTailArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(64)
                    .updateInterval(10)
                    .build("swallowtail_arrow"));

    RegistryObject<EntityType<WarDartEntity>> WARDART_PROJECTILE =
            ENTITY_TYPES.register("wardart_projectile", () -> EntityType.Builder.<WarDartEntity>of(WarDartEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(64)
                    .updateInterval(10)
                    .build("wardart_projectile"));

    static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}