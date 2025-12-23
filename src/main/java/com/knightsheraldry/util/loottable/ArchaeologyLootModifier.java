package com.knightsheraldry.util.loottable;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ArchaeologyLootModifier {
    public static void registerArchaeologyLoot() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(id)) {
                tableBuilder.modifyPools(builder -> {
                    for (Item item : modifyDesertPyramidArchaeologyLoot()) {
                        addBrokenItemToLoot(builder, item);
                    }
                });
            }
            if (BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_COMMON.equals(id)) {
                tableBuilder.modifyPools(builder -> {
                    for (Item item : modifyTrailRuinsCommonArchaeologyLoot()) {
                        addBrokenItemToLoot(builder, item);
                    }
                });
            }
            if (BuiltInLootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.equals(id)) {
                tableBuilder.modifyPools(builder -> {
                    for (Item item : modifyColdOceansRuinsArchaeologyLoot()) {
                        addBrokenItemToLoot(builder, item);
                    }
                });
            }
            if (BuiltInLootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY.equals(id)) {
                tableBuilder.modifyPools(builder -> {
                    for (Item item : modifyWarmOceansRuinsArchaeologyLoot()) {
                        addBrokenItemToLoot(builder, item);
                    }
                });
            }
        });
    }

    private static Item[] modifyDesertPyramidArchaeologyLoot() {
        return new Item[] {
                ModItems.STILETTO.get(),
                ModItems.RAPIER.get(),
                ModItems.POLEAXE.get(),
                ModItems.BARDICHE.get(),
                ModItems.HOUNDSKULL.get(),
                ModItems.CAGE.get(),
                ModItems.VISORED_BASCINET.get(),
                ModItems.GREAT_HELM.get(),
                ModItems.SALLET.get(),
                ModItems.BURGONET_FALLING_BUFFE.get(),
                ModItems.CLOSE_HELM.get()
        };
    }

    private static Item[] modifyTrailRuinsCommonArchaeologyLoot() {
        return new Item[] {
                ModItems.FLAIL.get(),
                ModItems.LONGSWORD.get(),
                ModItems.BILLHOOK.get(),
                ModItems.HALBERD.get(),
                ModItems.MAXIMILLIAN_CUIRASS.get(),
                ModItems.XIIII_PLATE_CUIRASS.get()
        };
    }

    private static Item[] modifyColdOceansRuinsArchaeologyLoot() {
        return new Item[] {
                ModItems.POLEHAMMER.get(),
                ModItems.MORNING_STAR.get(),
                ModItems.PLATE_SPAULDERS.get(),
                ModItems.PLATE_CUIRASS.get()
        };
    }

    private static Item[] modifyWarmOceansRuinsArchaeologyLoot() {
        return new Item[] {
                ModItems.FALCHION.get(),
                ModItems.SPEAR.get(),
                ModItems.PIKE.get(),
                ModItems.GLAIVE.get(),
                ModItems.GREAT_BASCINET.get(),
                ModItems.GREAT_HOUNDSKUL_BASCINET.get(),
                ModItems.MAXIMILLIAN_HELMET.get(),
                ModItems.SAVOYARD.get(),
        };
    }

    private static void addBrokenItemToLoot(LootPool.Builder builder, Item item) {
        float minDamagePercent = 0.01f;
        float maxDamagePercent = 1.0f;

        builder.with(LootItem.lootTableItem(item)
                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(minDamagePercent, maxDamagePercent)))
                .build());
    }
}