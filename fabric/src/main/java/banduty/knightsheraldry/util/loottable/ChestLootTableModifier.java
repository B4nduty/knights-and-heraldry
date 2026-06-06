package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.items.SCItems;
import banduty.stoneycore.util.data.itemdata.ItemStackHolder;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.Supplier;

public class ChestLootTableModifier {
    public static void modifyChestLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (BuiltInLootTables.ABANDONED_MINESHAFT.equals(key)) {
                modifyAbandonedMineshaftChestLoot(tableBuilder);
            }
            if (BuiltInLootTables.STRONGHOLD_LIBRARY.equals(key)) {
                modifyStrongholdLibraryChestLoot(tableBuilder);
            }
            if (BuiltInLootTables.WOODLAND_MANSION.equals(key)) {
                modifyWoodlandMansionChestLoot(tableBuilder);
            }
            if (BuiltInLootTables.PILLAGER_OUTPOST.equals(key)) {
                modifyPillagerOutpostChestLoot(tableBuilder);
            }
            if (BuiltInLootTables.VILLAGE_WEAPONSMITH.equals(key)) {
                modifyVillageWeaponChestLoot(tableBuilder);
            }
        });
    }

    private static void modifyAbandonedMineshaftChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.4f,
                KHItems.BRIGANDINE_HARNESS,
                KHItems.BRIGANDINE_CUISSES,
                KHItems.SABATONS,
                KHItems.AVENTAIL,
                KHItems.RIM_GUARDS,
                KHItems.BESAGEWS
        );
    }

    private static void modifyStrongholdLibraryChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.8f,
                KHItems.FLAIL,
                KHItems.SPEAR,
                KHItems.WARDART,
                KHItems.XIIII_PLATE_CUIRASS,
                KHItems.XIIII_PLATE_BREASTPLATE,
                KHItems.FROGMOUTH,
                KHItems.GREAT_ARMET
        );
    }

    private static void modifyWoodlandMansionChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.8f,
                KHItems.BARDICHE,
                KHItems.GREATSWORD,
                KHItems.PLATE_CUIRASS,
                KHItems.MAXIMILLIAN_CUIRASS,
                KHItems.PLATE_HARNESS,
                KHItems.PLATE_CUISSES,
                KHItems.PLACKART
        );
    }

    private static void modifyPillagerOutpostChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.6f,
                KHItems.HALBERD,
                KHItems.LANCE,
                KHItems.ARMET,
                KHItems.VISORED_BARBUTE,
                KHItems.TASSETS
        );
    }

    private static void modifyVillageWeaponChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.3f,
                KHItems.DAGGER,
                KHItems.SWORD,
                KHItems.AXE,
                KHItems.MACE,
                KHItems.HAMMER,
                KHItems.HEAVY_CROSSBOW
        );
    }

    @SafeVarargs
    private static void addChestLootPool(LootTable.Builder tableBuilder, float chance, Supplier<Item>... items) {
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(chance));

        for (Supplier<Item> itemSupplier : items) {
            Item weapon = itemSupplier.get();
            ItemStack weaponStack = new ItemStack(weapon);

            poolBuilder.add(
                    LootItem.lootTableItem(SCItems.MANUSCRIPT.get())
                            .apply(SetComponentsFunction.setComponent(SCDataComponents.TARGET_STACK.get(), new ItemStackHolder(weaponStack)))
                            .setWeight(1)
            );
        }

        tableBuilder.pool(poolBuilder.build());
    }
}