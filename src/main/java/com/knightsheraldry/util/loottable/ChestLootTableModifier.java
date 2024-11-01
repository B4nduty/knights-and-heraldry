package com.knightsheraldry.util.loottable;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
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
                tableBuilder.pool(createLootPool(0.1f, ModItems.SMITHING_HAMMER));
                tableBuilder.pool(createWeaponLootPool());
            }

            if (LootTables.VILLAGE_ARMORER_CHEST.equals(id)) {
                tableBuilder.pool(createArmorLootPool());
            }

            if (LootTables.VILLAGE_SHEPARD_CHEST.equals(id)) {
                tableBuilder.pool(createLootPool(0.15f,
                        ModItems.SURCOAT, ModItems.SURCOAT_SLEEVELESS, ModItems.CLOAK, ModItems.TORN_CLOAK,
                        ModItems.HOOD, ModItems.TORN_HOOD));
            }

            if (LootTables.VILLAGE_FLETCHER_CHEST.equals(id)) {
                tableBuilder.pool(createLootPool(0.15f,
                        ModItems.LONGBOW, ModItems.SWALLOWTAIL_ARROW, ModItems.BODKIN_ARROW, ModItems.BROADHEAD_ARROW,
                        ModItems.CLOTH_ARROW, ModItems.HEAVY_CROSSBOW));
            }
        });
    }

    private static LootPool createLootPool(float mainChance, Item... items) {
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(mainChance));

        for (ItemConvertible item : items) {
            poolBuilder.with(ItemEntry.builder(item).conditionally(RandomChanceLootCondition.builder((float) 1 / items.length)));
        }

        poolBuilder.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
        return poolBuilder.build();
    }

    private static LootPool createWeaponLootPool() {
        return createLootPool(0.15f,
                ModItems.DAGGER, ModItems.STILETTO, ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD,
                ModItems.ARMING_SWORD, ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE,
                ModItems.STRAIGHT_CROOKED_AXE, ModItems.MACE, ModItems.SPIKED_MACE, ModItems.FLAIL,
                ModItems.BALL_FLAIL, ModItems.HAMMER, ModItems.WAR_HAMMER, ModItems.LONGSWORD, ModItems.V_LONGSWORD,
                ModItems.FALCHION, ModItems.SCIMITAR, ModItems.KATANA, ModItems.PITCHFORK, ModItems.SPEAR,
                ModItems.PIKE, ModItems.BILLHOOK, ModItems.GLAIVE, ModItems.CURVED_GLAIVE, ModItems.HALBERD,
                ModItems.LANCE, ModItems.WOODEN_LANCE, ModItems.POLEAXE, ModItems.POLEHAMMER,
                ModItems.BEC_DE_CORBIN, ModItems.MORNING_STAR, ModItems.BARDICHE, ModItems.WARSWORD,
                ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER, ModItems.WARDART
        );
    }

    private static LootPool createArmorLootPool() {
        return createLootPool(0.15f,
                ModItems.QUILTED_COIF, ModItems.GAMBESON, ModItems.GAMBESON_BREECHES, ModItems.GAMBESON_BOOTS,
                ModItems.MAIL_COIF, ModItems.HAUBERK, ModItems.MAIL_BREECHES, ModItems.MAIL_BOOTS,
                ModItems.MAIL_PAULDRON, ModItems.BRIGANDINE_PAULDRON, ModItems.PLATE_PAULDRON,
                ModItems.BRIGANDINE, ModItems.BRIG_BREASTPLATE, ModItems.BRIG_BREASTPLATE_TASSETS,
                ModItems.PLATE_CUIRASS, ModItems.PLATE_CUIRASS_TASSETS, ModItems.MAXIMILLIAN_CUIRASS,
                ModItems.MAXIMILLIAN_CUIRASS_TASSETS, ModItems.XIIII_PLATE_CUIRASS, ModItems.XIIII_PLATE_CUIRASS_TASSETS,
                ModItems.XIIII_PLATE_BREASTPLATE, ModItems.BARBUTE_NO_VISOR, ModItems.BASCINET_NO_VISOR,
                ModItems.KETTLE_HELM, ModItems.NASAL_HELM, ModItems.VIKING_HELM, ModItems.ARMET, ModItems.ARMET_2,
                ModItems.BARBUTE, ModItems.BASCINET, ModItems.CAGE, ModItems.CAGE_2, ModItems.FLAT_BASCINET,
                ModItems.GREAT_HELM, ModItems.GREAT_HELM_2, ModItems.SALLET, ModItems.FROGMOUTH, ModItems.GREAT_ARMET,
                ModItems.GREAT_ARMET_2, ModItems.GREAT_BASCINET, ModItems.GREAT_HOUNDSKUL_BASCINET,
                ModItems.MAXIMILLIAN_HELMET, ModItems.GAUNTLET, ModItems.BRIGANDINE_REREBRACE, ModItems.PLATE_REREBRACE,
                ModItems.BRIGANDINE_CHAUSSES, ModItems.PLATE_CHAUSSES, ModItems.SABATONS, ModItems.AVENTAIL,
                ModItems.RIM_GUARDS, ModItems.BESAGEWS
        );
    }
}