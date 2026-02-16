package banduty.knightsheraldry.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.*;
import banduty.stoneycore.StoneyCore;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public interface ModEntities {
    EntityType<KHBodkinArrowEntity> BODKIN_ARROW =
            registerEntity("bodkin_arrow",
                    FabricEntityTypeBuilder.<KHBodkinArrowEntity>create(MobCategory.MISC, KHBodkinArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    EntityType<KHBroadheadArrowEntity> BROADHEAD_ARROW =
            registerEntity("broadhead_arrow",
                    FabricEntityTypeBuilder.<KHBroadheadArrowEntity>create(MobCategory.MISC, KHBroadheadArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    EntityType<KHClothArrowEntity> CLOTH_ARROW =
            registerEntity("cloth_arrow",
                    FabricEntityTypeBuilder.<KHClothArrowEntity>create(MobCategory.MISC, KHClothArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    EntityType<KHSwallowTailArrowEntity> SWALLOWTAIL_ARROW =
            registerEntity("swallowtail_arrow",
                    FabricEntityTypeBuilder.<KHSwallowTailArrowEntity>create(MobCategory.MISC, KHSwallowTailArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    EntityType<WarDartEntity> WARDART_PROJECTILE =
            registerEntity("wardart_projectile",
                    FabricEntityTypeBuilder.<WarDartEntity>create(MobCategory.MISC, WarDartEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType<T> entityType) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(StoneyCore.MOD_ID, name), entityType);
    }

    static void registerEntities() {
        KnightsHeraldry.LOG.info("Registering Entities for " + KnightsHeraldry.MOD_ID);
    }
}
