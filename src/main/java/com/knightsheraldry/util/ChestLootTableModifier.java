package com.knightsheraldry.util;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ChestLootTableModifier {

    public static void modifyChestLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(ModItems.SMITHING_HAMMER))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());

                LootPool.Builder poolBuilder2 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.15f))
                        .with(ItemEntry.builder(ModItems.DAGGER).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.STILETTO).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.RAPIER).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.SWORD).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.V_SWORD).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.ARMING_SWORD).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.AXE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.BROAD_AXE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.CROOKED_AXE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.STRAIGHT_CROOKED_AXE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.MACE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.SPIKED_MACE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.FLAIL).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.BALL_FLAIL).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.HAMMER).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.WAR_HAMMER).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.LONGSWORD).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.V_LONGSWORD).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.FALCHION).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.SCIMITAR).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.KATANA).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.PITCHFORK).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.SPEAR).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.PIKE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.BILLHOOK).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.GLAIVE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.CURVED_GLAIVE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.HALBERD).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.LANCE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.WOODEN_LANCE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.POLEAXE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.POLEHAMMER).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.BEC_DE_CORBIN).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.MORNING_STAR).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.BARDICHE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.WARSWORD).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.WARSWORD_CLAYMORE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.WARSWORD_FLAMBERGE).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.WARSWORD_ZWEIHANDER).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .with(ItemEntry.builder(ModItems.WARDART).conditionally(RandomChanceLootCondition.builder(0.025f)))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder2.build());
            }

            if (LootTables.VILLAGE_ARMORER_CHEST.equals(id)) {
                LootPool.Builder poolBuilder2 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.15f))
                        .with(ItemEntry.builder(ModItems.QUILTED_COIF).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GAMBESON).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GAMBESON_BREECHES).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GAMBESON_BOOTS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.MAIL_COIF).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.HAUBERK).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.MAIL_BREECHES).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.MAIL_BOOTS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.MAIL_PAULDRON).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BRIGANDINE_PAULDRON).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.PLATE_PAULDRON).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BRIGANDINE).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BRIG_BREASTPLATE).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BRIG_BREASTPLATE_TASSETS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.PLATE_CUIRASS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.PLATE_CUIRASS_TASSETS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.MAXIMILLIAN_CUIRASS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.MAXIMILLIAN_CUIRASS_TASSETS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.XIIII_PLATE_CUIRASS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.XIIII_PLATE_CUIRASS_TASSETS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.XIIII_PLATE_BREASTPLATE).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BARBUTE_NO_VISOR).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BASCINET_NO_VISOR).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.KETTLE_HELM).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.NASAL_HELM).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.VIKING_HELM).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.ARMET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.ARMET_2).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BARBUTE).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BASCINET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.CAGE).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.CAGE_2).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.FLAT_BASCINET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GREAT_HELM).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GREAT_HELM_2).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.SALLET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.FROGMOUTH).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GREAT_ARMET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GREAT_ARMET_2).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GREAT_BASCINET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GREAT_HOUNDSKUL_BASCINET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.MAXIMILLIAN_HELMET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.GAUNTLET).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BRIGANDINE_REREBRACE).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.PLATE_REREBRACE).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BRIGANDINE_CHAUSSES).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.PLATE_CHAUSSES).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.SABATONS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.AVENTAIL).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.RIM_GUARDS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .with(ItemEntry.builder(ModItems.BESAGEWS).conditionally(RandomChanceLootCondition.builder(0.02f)))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder2.build());
            }

            if (LootTables.VILLAGE_SHEPARD_CHEST.equals(id)) {
                LootPool.Builder poolBuilder2 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.15f))
                        .with(ItemEntry.builder(ModItems.SURCOAT).conditionally(RandomChanceLootCondition.builder(0.16f)))
                        .with(ItemEntry.builder(ModItems.SURCOAT_SLEEVELESS).conditionally(RandomChanceLootCondition.builder(0.16f)))
                        .with(ItemEntry.builder(ModItems.CLOAK).conditionally(RandomChanceLootCondition.builder(0.16f)))
                        .with(ItemEntry.builder(ModItems.TORN_CLOAK).conditionally(RandomChanceLootCondition.builder(0.16f)))
                        .with(ItemEntry.builder(ModItems.HOOD).conditionally(RandomChanceLootCondition.builder(0.16f)))
                        .with(ItemEntry.builder(ModItems.TORN_HOOD).conditionally(RandomChanceLootCondition.builder(0.16f)))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder2.build());
            }

            if (LootTables.VILLAGE_FLETCHER_CHEST.equals(id)) {
                LootPool.Builder poolBuilder2 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.15f))
                        .with(ItemEntry.builder(ModItems.LONGBOW).conditionally(RandomChanceLootCondition.builder(0.25f)))
                        .with(ItemEntry.builder(ModItems.SWALLOWTAIL_ARROW).conditionally(RandomChanceLootCondition.builder(0.25f)))
                        .with(ItemEntry.builder(ModItems.BODKIN_ARROW).conditionally(RandomChanceLootCondition.builder(0.25f)))
                        .with(ItemEntry.builder(ModItems.BROADHEAD_ARROW).conditionally(RandomChanceLootCondition.builder(0.25f)))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder2.build());
            }
        });
    }
}
