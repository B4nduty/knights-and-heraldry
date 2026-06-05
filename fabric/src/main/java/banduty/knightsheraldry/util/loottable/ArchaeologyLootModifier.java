package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.items.KHItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.function.Supplier;

public class ArchaeologyLootModifier {
    public static void registerArchaeologyLoot() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {

            if (BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(key)) {
                tableBuilder.modifyPools(builder -> addBrokenItemsToPool(builder, modifyDesertPyramidArchaeologyLoot()));
            }

            if (BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_COMMON.equals(key)) {
                tableBuilder.modifyPools(builder -> addBrokenItemsToPool(builder, modifyTrailRuinsCommonArchaeologyLoot()));
            }

            if (BuiltInLootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.equals(key)) {
                tableBuilder.modifyPools(builder -> addBrokenItemsToPool(builder, modifyColdOceansRuinsArchaeologyLoot()));
            }

            if (BuiltInLootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY.equals(key)) {
                tableBuilder.modifyPools(builder -> addBrokenItemsToPool(builder, modifyWarmOceansRuinsArchaeologyLoot()));
            }
        });
    }

    private static List<Supplier<Item>> modifyDesertPyramidArchaeologyLoot() {
        return List.of(
                KHItems.STILETTO,
                KHItems.RAPIER,
                KHItems.POLEAXE,
                KHItems.BARDICHE,
                KHItems.HOUNDSKULL,
                KHItems.CAGE,
                KHItems.VISORED_BASCINET,
                KHItems.GREAT_HELM,
                KHItems.BURGONET_FALLING_BUFFE,
                KHItems.CLOSE_HELM
        );
    }

    private static List<Supplier<Item>> modifyTrailRuinsCommonArchaeologyLoot() {
        return List.of(
                KHItems.FLAIL,
                KHItems.LONGSWORD,
                KHItems.BILLHOOK,
                KHItems.HALBERD,
                KHItems.MAXIMILLIAN_CUIRASS,
                KHItems.XIIII_PLATE_CUIRASS
        );
    }

    private static List<Supplier<Item>> modifyColdOceansRuinsArchaeologyLoot() {
        return List.of(
                KHItems.POLEHAMMER,
                KHItems.MORNING_STAR,
                KHItems.PLATE_SPAULDERS,
                KHItems.PLATE_CUIRASS
        );
    }

    private static List<Supplier<Item>> modifyWarmOceansRuinsArchaeologyLoot() {
        return List.of(
                KHItems.FALCHION,
                KHItems.SPEAR,
                KHItems.PIKE,
                KHItems.GLAIVE,
                KHItems.GREAT_BASCINET,
                KHItems.GREAT_HOUNDSKUL_BASCINET,
                KHItems.MAXIMILLIAN_HELMET,
                KHItems.SAVOYARD
        );
    }

    private static void addBrokenItemsToPool(LootPool.Builder pool, List<Supplier<Item>> items) {
        float minDamagePercent = 0.01f;
        float maxDamagePercent = 1.0f;

        for (Supplier<Item> item : items) {
            pool.add(
                    LootItem.lootTableItem(item.get())
                            .apply(SetItemDamageFunction.setDamage(
                                    UniformGenerator.between(minDamagePercent, maxDamagePercent)
                            ))
            );
        }
    }
}