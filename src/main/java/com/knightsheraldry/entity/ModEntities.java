package com.knightsheraldry.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.WarDartEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<WarDartEntity> WARDART_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(KnightsHeraldry.MOD_ID, "wardart_projectile"),
            FabricEntityTypeBuilder.<WarDartEntity>create(SpawnGroup.MISC, WarDartEntity::new).build());

    public static void registerModEntities() {
        KnightsHeraldry.LOGGER.info("Registering Entities for " + KnightsHeraldry.MOD_ID);
    }
}
