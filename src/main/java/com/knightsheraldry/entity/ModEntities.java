package com.knightsheraldry.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.*;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKeys;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(KnightsHeraldry.MOD_ID, RegistryKeys.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<KHBodkinArrowEntity>> BODKING_ARROW =
            ENTITY_TYPES.register("bodkin_arrow", () ->
                    FabricEntityTypeBuilder.<KHBodkinArrowEntity>create(SpawnGroup.MISC, KHBodkinArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    public static final RegistrySupplier<EntityType<KHBroadheadArrowEntity>> BROADHEAD_ARROW =
            ENTITY_TYPES.register("broadhead_arrow", () ->
                    FabricEntityTypeBuilder.<KHBroadheadArrowEntity>create(SpawnGroup.MISC, KHBroadheadArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    public static final RegistrySupplier<EntityType<KHClothArrowEntity>> CLOTH_ARROW =
            ENTITY_TYPES.register("cloth_arrow", () ->
                    FabricEntityTypeBuilder.<KHClothArrowEntity>create(SpawnGroup.MISC, KHClothArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    public static final RegistrySupplier<EntityType<KHSwallowTailArrowEntity>> SWALLOWTAIL_ARROW =
            ENTITY_TYPES.register("swallowtail_arrow", () ->
                    FabricEntityTypeBuilder.<KHSwallowTailArrowEntity>create(SpawnGroup.MISC, KHSwallowTailArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    public static final RegistrySupplier<EntityType<WarDartEntity>> WARDART_PROJECTILE =
            ENTITY_TYPES.register("wardart_projectile", () ->
                    FabricEntityTypeBuilder.<WarDartEntity>create(SpawnGroup.MISC, WarDartEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    public static void registerEntities() {
        ENTITY_TYPES.register();
        KnightsHeraldry.LOGGER.info("Registering Entities for " + KnightsHeraldry.MOD_ID);
    }
}
