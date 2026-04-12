package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.items.ModItems;
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
                    addBrokenItemsToPool(builder, modifyDesertPyramidArchaeologyLoot());
                });
            }
            if (BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_COMMON.equals(id)) {
                tableBuilder.modifyPools(builder -> {
                    addBrokenItemsToPool(builder, modifyTrailRuinsCommonArchaeologyLoot());
                });
            }
            if (BuiltInLootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.equals(id)) {
                tableBuilder.modifyPools(builder -> {
                    addBrokenItemsToPool(builder, modifyColdOceansRuinsArchaeologyLoot());
                });
            }
            if (BuiltInLootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY.equals(id)) {
                tableBuilder.modifyPools(builder -> {
                    addBrokenItemsToPool(builder, modifyWarmOceansRuinsArchaeologyLoot());
                });
            }
        });
    }

    private static Item[] modifyDesertPyramidArchaeologyLoot() {
        return new Item[]{
                ModItems.STILETTO,
                ModItems.RAPIER,
                ModItems.POLEAXE,
                ModItems.BARDICHE,
                ModItems.HOUNDSKULL,
                ModItems.CAGE,
                ModItems.VISORED_BASCINET,
                ModItems.GREAT_HELM,
                ModItems.SALLET,
                ModItems.BURGONET_FALLING_BUFFE,
                ModItems.CLOSE_HELM
        };
    }

    private static Item[] modifyTrailRuinsCommonArchaeologyLoot() {
        return new Item[]{
                ModItems.FLAIL,
                ModItems.LONGSWORD,
                ModItems.BILLHOOK,
                ModItems.HALBERD,
                ModItems.MAXIMILLIAN_CUIRASS,
                ModItems.XIIII_PLATE_CUIRASS
        };
    }

    private static Item[] modifyColdOceansRuinsArchaeologyLoot() {
        return new Item[]{
                ModItems.POLEHAMMER,
                ModItems.MORNING_STAR,
                ModItems.PLATE_SPAULDERS,
                ModItems.PLATE_CUIRASS
        };
    }

    private static Item[] modifyWarmOceansRuinsArchaeologyLoot() {
        return new Item[]{
                ModItems.FALCHION,
                ModItems.SPEAR,
                ModItems.PIKE,
                ModItems.GLAIVE,
                ModItems.GREAT_BASCINET,
                ModItems.GREAT_HOUNDSKUL_BASCINET,
                ModItems.MAXIMILLIAN_HELMET,
                ModItems.SAVOYARD,
        };
    }

    private static void addBrokenItemsToPool(LootPool.Builder pool, Item[] items) {
        float minDamagePercent = 0.01f;
        float maxDamagePercent = 1.0f;

        for (Item item : items) {
            pool.add(
                    LootItem.lootTableItem(item)
                            .apply(SetItemDamageFunction.setDamage(
                                    UniformGenerator.between(minDamagePercent, maxDamagePercent)
                            ))
            );
        }
    }
}