package com.knightsheraldry.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<WarDartEntity> WARDART_PROJECTILE = registerEntity("wardart_projectile", WarDartEntity::new);
    public static final EntityType<KHArrowEntity> KH_ARROW = registerEntity("kh_arrow", KHArrowEntity::new);
    public static final EntityType<KHSwallowTailArrowEntity> KH_SWALLOW_TAIL_ARROW_ENTITY = registerEntity("kh_swallow_tail_arrow", KHSwallowTailArrowEntity::new);
    public static final EntityType<KHBodkinArrowEntity> KH_BODKIN_ARROW_ENTITY = registerEntity("kh_bodkin_arrow", KHBodkinArrowEntity::new);
    public static final EntityType<KHBroadheadArrowEntity> KH_BROADHEAD_ARROW_ENTITY = registerEntity("kh_broadhead_arrow", KHBroadheadArrowEntity::new);

    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.EntityFactory<T> factory) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(KnightsHeraldry.MOD_ID, name),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).build()
        );
    }

    public static void registerModEntities() {
        KnightsHeraldry.LOGGER.info("Registering Entities for " + KnightsHeraldry.MOD_ID);
    }
}
