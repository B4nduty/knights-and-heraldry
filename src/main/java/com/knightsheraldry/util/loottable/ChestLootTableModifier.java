package com.knightsheraldry.util.loottable;

import banduty.stoneycore.items.item.Manuscript;
import com.knightsheraldry.items.ModItems;
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
                ModItems.BRIGANDINE_HARNESS.get(),
                ModItems.BRIGANDINE_CUISSES.get(),
                ModItems.SABATONS.get(),
                ModItems.AVENTAIL.get(),
                ModItems.RIM_GUARDS.get(),
                ModItems.BESAGEWS.get()
        );
    }

    private static void modifyStrongholdLibraryChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.8f,
                ModItems.FLAIL.get(),
                ModItems.SPEAR.get(),
                ModItems.WARDART.get(),
                ModItems.XIIII_PLATE_CUIRASS.get(),
                ModItems.XIIII_PLATE_BREASTPLATE.get(),
                ModItems.FROGMOUTH.get(),
                ModItems.GREAT_ARMET.get()
        );
    }

    private static void modifyWoodlandMansionsChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.8f,
                ModItems.BARDICHE.get(),
                ModItems.GREATSWORD.get(),
                ModItems.PLATE_CUIRASS.get(),
                ModItems.MAXIMILLIAN_CUIRASS.get(),
                ModItems.PLATE_HARNESS.get(),
                ModItems.PLATE_CUISSES.get()
        );
    }

    private static void modifyPillagerOutpostChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.6f,
                ModItems.HALBERD.get(),
                ModItems.LANCE.get(),
                ModItems.ARMET.get(),
                ModItems.VISORED_BARBUTE.get()
        );
    }

    private static void modifyVillageWeaponChestLoot(LootTable.Builder tableBuilder) {
        addChestLootPool(tableBuilder, 0.3f,
                ModItems.DAGGER.get(),
                ModItems.SWORD.get(),
                ModItems.AXE.get(),
                ModItems.MACE.get(),
                ModItems.HAMMER.get(),
                ModItems.HEAVY_CROSSBOW.get()
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
