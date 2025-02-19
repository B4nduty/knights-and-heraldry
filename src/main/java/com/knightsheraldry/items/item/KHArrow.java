package com.knightsheraldry.items.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.function.BiFunction;

public class KHArrow extends Item {
    private final BiFunction<PlayerEntity, World, Entity> arrowEntityFactory;

    /**
     * <p><b>Warning:</b>
     * This class is made for use of KnightsHeraldry, you can use it, but it isn't made to use by external people.
     * It is a class made only to reduce files and storage space.
     */
    public KHArrow(Settings settings, BiFunction<PlayerEntity, World, Entity> arrowEntityFactory) {
        super(settings);
        this.arrowEntityFactory = arrowEntityFactory;
    }

    public final Entity createArrowEntity(PlayerEntity playerEntity, World world) {
        return arrowEntityFactory.apply(playerEntity, world);
    }
}