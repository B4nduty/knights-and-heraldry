package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.items.ModItems;
import banduty.stoneycore.items.manuscript.Manuscript;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ChestLootTableModifier {
    public static void modifyChestLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (BuiltInLootTables.ABANDONED_MINESHAFT.equals(id)) {
                modifyAbandonedMineshaftChestLoot(tableBuilder);
            }
            if (BuiltInLootTables.STRONGHOLD_LIBRARY.equals(id)) {
                modifyStrongholdLibraryChestLoot(tableBuilder);
            }
            if (BuiltInLootTables.WOODLAND_MANSION.equals(id)) {
                modifyWoodlandMansionsChestLoot(tableBuilder);
            }
            if (BuiltInLootTables.PILLAGER_OUTPOST.equals(id)) {
                modifyPillagerOutpostChestLoot(tableBuilder);
            }
            if (BuiltInLootTables.VILLAGE_WEAPONSMITH.equals(id)) {
                modifyVillageWeaponChestLoot(tableBuilder);
            }
        });
    }

    private static void modifyAbandonedMineshaftChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.4f,
                ModItems.BRIGANDINE_HARNESS,
                ModItems.BRIGANDINE_CUISSES,
                ModItems.SABATONS,
                ModItems.AVENTAIL,
                ModItems.RIM_GUARDS,
                ModItems.BESAGEWS
        );
    }

    private static void modifyStrongholdLibraryChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.8f,
                ModItems.FLAIL,
                ModItems.SPEAR,
                ModItems.WARDART,
                ModItems.XIIII_PLATE_CUIRASS,
                ModItems.XIIII_PLATE_BREASTPLATE,
                ModItems.FROGMOUTH,
                ModItems.GREAT_ARMET
        );
    }

    private static void modifyWoodlandMansionsChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.8f,
                ModItems.BARDICHE,
                ModItems.GREATSWORD,
                ModItems.PLATE_CUIRASS,
                ModItems.MAXIMILLIAN_CUIRASS,
                ModItems.PLATE_HARNESS,
                ModItems.PLATE_CUISSES
        );
    }

    private static void modifyPillagerOutpostChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.6f,
                ModItems.HALBERD,
                ModItems.LANCE,
                ModItems.ARMET,
                ModItems.VISORED_BARBUTE
        );
    }

    private static void modifyVillageWeaponChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.3f,
                ModItems.DAGGER,
                ModItems.SWORD,
                ModItems.AXE,
                ModItems.MACE,
                ModItems.HAMMER,
                ModItems.HEAVY_CROSSBOW
        );
    }

    private static void addChestLootPool(LootTable.Builder tableBuilder, float chance, Item... items) {
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(chance));

        for (Item item : items) {
            var manuscriptStack = Manuscript.createForStack(new ItemStack(item));
            CompoundTag nbt = manuscriptStack.getOrCreateTag();

            poolBuilder.with(LootItem.lootTableItem(manuscriptStack.getItem())
                    .apply(SetNbtFunction.setTag(nbt))
                    .setWeight(1)
                    .build());
        }

        tableBuilder.pool(poolBuilder.build());
    }
}
