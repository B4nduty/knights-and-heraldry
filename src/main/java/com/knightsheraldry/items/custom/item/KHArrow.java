package com.knightsheraldry.items.custom.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.function.BiFunction;

public class KHArrow extends Item {
    private final BiFunction<PlayerEntity, World, Entity> arrowEntityFactory;

    public KHArrow(Settings settings, BiFunction<PlayerEntity, World, Entity> arrowEntityFactory) {
        super(settings);
        this.arrowEntityFactory = arrowEntityFactory;
    }

    public Entity createArrowEntity(PlayerEntity playerEntity, World world) {
        return this.arrowEntityFactory.apply(playerEntity, world);
    }
}