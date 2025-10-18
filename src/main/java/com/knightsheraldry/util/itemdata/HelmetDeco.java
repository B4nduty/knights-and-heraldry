package com.knightsheraldry.util.itemdata;

import banduty.stoneycore.items.SCItems;
import com.knightsheraldry.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.List;
import java.util.Map;

public record HelmetDeco(Item item, int color, int group) {
    public static final Map<Item, HelmetDeco> HELMET_DECO = Map.ofEntries(
            entry(ModItems.PLUME.get(), 1, 0),
            entry(SCItems.CROWN.get(), 0, 1),
            entry(ModItems.TRI_PLUME.get(), 1, 0),
            entry(ModItems.FLUFFY_PLUME.get(), 1, 0),
            entry(ModItems.TORSE.get(), 2, 1),
            entry(ModItems.TEUTONIC_SNAKES.get(), 0, 0),
            entry(ModItems.TEUTONIC_BLACK_SNAKES.get(), 0, 0),
            entry(ModItems.GOLD_HORNS.get(), 0, 0),
            entry(ModItems.BLACK_HORNS.get(), 0, 0),
            entry(ModItems.TEUTONIC_GOLD_WINGS.get(), 0, 0),
            entry(ModItems.TEUTONIC_BLACK_WINGS.get(), 0, 0),
            entry(ModItems.TEUTONIC_WINGS_BALL_ENDS.get(), 0, 0),
            entry(ModItems.TEUTONIC_WINGS_SHARP_ENDS.get(), 0, 0),
            entry(ModItems.DRAGON.get(), 0, 0),
            entry(ModItems.LION.get(), 0, 0),
            entry(ModItems.SNAKE.get(), 0, 0),
            entry(ModItems.UNICORN.get(), 0, 0),
            entry(ModItems.STAG.get(), 0, 0),
            entry(ModItems.BOAR.get(), 0, 0),
            entry(ModItems.EAGLE.get(), 0, 0),
            entry(ModItems.PEGASUS.get(), 0, 0)
    );

    private static Map.Entry<Item, HelmetDeco> entry(Item item, int color, int group) {
        return Map.entry(item, new HelmetDeco(item, color, group));
    }

    public String getNbtKey() {
        return Registries.ITEM.getId(item).getPath();
    }

    public static List<Item> getDecoGroup(int group) {
        return HELMET_DECO.values().stream()
                .filter(deco -> deco.group() == group)
                .map(HelmetDeco::item)
                .toList();
    }

}
