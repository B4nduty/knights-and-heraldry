package com.knightsheraldry.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.KHArrowEntity;
import com.knightsheraldry.entity.custom.KHBulletEntity;
import com.knightsheraldry.entity.custom.WarDartEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<WarDartEntity> WARDART_PROJECTILE = registerEntity("wardart_projectile", WarDartEntity::new, 0.5f, 0.5f);
    public static final EntityType<KHArrowEntity> KH_ARROW = registerEntity("kh_arrow", KHArrowEntity::new, 0.5f, 0.5f);
    public static final EntityType<KHBulletEntity> KH_BULLET = registerEntity("kh_bullet", KHBulletEntity::new, 0.05f, 0.05f);

    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.EntityFactory<T> factory, float width, float height) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(KnightsHeraldry.MOD_ID, name),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).dimensions(EntityDimensions.fixed(width, height)).build()
        );
    }

    public static void registerEntities() {
        KnightsHeraldry.LOGGER.info("Registering Entities for " + KnightsHeraldry.MOD_ID);
    }
}
