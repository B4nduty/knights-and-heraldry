package com.knightsheraldry.util;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.List;

public class VillagerTradesModifier {
    public static void registerCustomTrades() {

        for (int level = 1; level <= 5; level++) {
            registerArmorTrades(level);
            registerWeaponTrades(level);
            registerClothsTrades(level);
            registerRangedWeaponsTrades(level);
        }
    }

    private static void addTradeOffer(List<TradeOffers.Factory> factories, int emeraldCount, int maxUses, int merchantExperience, Item item) {
        factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, emeraldCount), new ItemStack(item, 1), maxUses, merchantExperience, 0.05f));
    }

    private static void addRandomTradeOffer(List<TradeOffers.Factory> factories, int emeraldCount, int maxUses, int merchantExperience, Item... items) {
        factories.add((entity, random) -> {
            for (ItemConvertible item : items) {
                return new TradeOffer(new ItemStack(Items.EMERALD, emeraldCount), new ItemStack(item, 1), maxUses, merchantExperience, 0.05f);
            }
            return null;
        });
    }

    private static void registerArmorTrades(int level) {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, level, factories -> {
            if (level == 1) {
                addTradeOffer(factories, 8, 12, 1, ModItems.QUILTED_COIF);
                addTradeOffer(factories, 11, 12, 1, ModItems.GAMBESON);
                addTradeOffer(factories, 8, 12, 1, ModItems.GAMBESON_BREECHES);
                addTradeOffer(factories, 6, 12, 1, ModItems.GAMBESON_BOOTS);
            }

            if (level == 2) {
                addTradeOffer(factories, 9, 12, 5, ModItems.MAIL_COIF);
                addTradeOffer(factories, 14, 12, 5, ModItems.HAUBERK);
                addTradeOffer(factories, 11, 12, 5, ModItems.MAIL_BREECHES);
                addTradeOffer(factories, 9, 12, 5, ModItems.MAIL_BOOTS);
                addTradeOffer(factories, 8, 12, 5, ModItems.MAIL_PAULDRON);
                addTradeOffer(factories, 9, 12, 5, ModItems.AVENTAIL);
            }

            if (level == 3) {
                addTradeOffer(factories, 23, 12, 10, ModItems.BRIGANDINE);
                addTradeOffer(factories, 11, 12, 10, ModItems.GAUNTLET);
                addRandomTradeOffer(factories, 9, 12, 10,
                        ModItems.BASCINET_NO_VISOR, ModItems.KETTLE_HELM, ModItems.NASAL_HELM, ModItems.VIKING_HELM,
                        ModItems.BARBUTE_NO_VISOR);
            }

            if (level == 4) {
                addTradeOffer(factories, 15, 12, 15, ModItems.BRIGANDINE_PAULDRON);
                addTradeOffer(factories, 21, 12, 15, ModItems.BRIGANDINE_REREBRACE);
                addTradeOffer(factories, 21, 12, 15, ModItems.BRIGANDINE_CHAUSSES);
                addRandomTradeOffer(factories, 24, 12, 15,
                        ModItems.ARMET_2, ModItems.BARBUTE, ModItems.BASCINET, ModItems.CAGE,
                        ModItems.CAGE_2, ModItems.FLAT_BASCINET, ModItems.GREAT_HELM, ModItems.GREAT_HELM_2,
                        ModItems.SALLET, ModItems.ARMET);
            }

            if (level == 5) {
                addTradeOffer(factories, 18, 12, 15, ModItems.PLATE_PAULDRON);
                addTradeOffer(factories, 11, 12, 15, ModItems.SABATONS);
                addTradeOffer(factories, 24, 12, 15, ModItems.PLATE_REREBRACE);
                addTradeOffer(factories, 24, 12, 15, ModItems.PLATE_CHAUSSES);
                addTradeOffer(factories, 4, 12, 15, ModItems.RIM_GUARDS);
                addTradeOffer(factories, 3, 12, 15, ModItems.BESAGEWS);
                addRandomTradeOffer(factories, 26, 12, 15,
                        ModItems.BRIG_BREASTPLATE, ModItems.BRIG_BREASTPLATE_TASSETS);
                addRandomTradeOffer(factories, 26, 12, 15,
                        ModItems.PLATE_CUIRASS_TASSETS, ModItems.MAXIMILLIAN_CUIRASS,
                        ModItems.MAXIMILLIAN_CUIRASS_TASSETS, ModItems.XIIII_PLATE_CUIRASS,
                        ModItems.XIIII_PLATE_CUIRASS_TASSETS, ModItems.XIIII_PLATE_BREASTPLATE);
            }
        });
    }

    private static void registerWeaponTrades(int level) {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, level, factories -> {
            if (level == 1) {
                addTradeOffer(factories, 8, 12, 1, ModItems.PITCHFORK);
                addTradeOffer(factories, 8, 12, 1, ModItems.SPEAR);
                addRandomTradeOffer(factories, 13, 12, 1,
                        ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE);
                addTradeOffer(factories, 5, 12, 1, ModItems.DAGGER);
            }

            if (level == 2) {
                addRandomTradeOffer(factories, 18, 12, 5,
                        ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD);
                addRandomTradeOffer(factories, 13, 12, 5,
                        ModItems.MACE, ModItems.SPIKED_MACE);
                addRandomTradeOffer(factories, 15, 12, 5,
                        ModItems.FLAIL, ModItems.BALL_FLAIL);
                addRandomTradeOffer(factories, 13, 12, 5,
                        ModItems.HAMMER, ModItems.WAR_HAMMER);
                addTradeOffer(factories, 20, 12, 5, ModItems.WARDART);
            }

            if (level == 3) {
                addTradeOffer(factories, 15, 12, 10, ModItems.BILLHOOK);
                addRandomTradeOffer(factories, 30, 12, 10, ModItems.LONGSWORD, ModItems.V_LONGSWORD);
                addRandomTradeOffer(factories, 15, 12, 10, ModItems.GLAIVE, ModItems.CURVED_GLAIVE);
                addTradeOffer(factories, 18, 12, 10, ModItems.KATANA);
                addTradeOffer(factories, 13, 12, 10, ModItems.LANCE);
                addTradeOffer(factories, 5, 12, 10, ModItems.STILETTO);
            }

            if (level == 4) {
                addTradeOffer(factories, 23, 12, 15, ModItems.POLEAXE);
                addRandomTradeOffer(factories, 23, 12, 15,
                        ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN);
                addRandomTradeOffer(factories, 35, 12, 15,
                        ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE,
                        ModItems.WARSWORD_ZWEIHANDER);
                addTradeOffer(factories, 15, 12, 15, ModItems.RAPIER);
                addTradeOffer(factories, 20, 12, 15, ModItems.MORNING_STAR);
            }

            if (level == 5) {
                addTradeOffer(factories, 18, 12, 15, ModItems.HALBERD);
                addTradeOffer(factories, 18, 12, 15, ModItems.BARDICHE);
            }
        });
    }

    private static void registerClothsTrades(int level) {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, level, factories -> {
            if (level == 1) {
                addRandomTradeOffer(factories, 6, 32, 1,
                        ModItems.CLOAK, ModItems.TORN_CLOAK);
                addRandomTradeOffer(factories, 32, 32, 1,
                        ModItems.HOOD, ModItems.TORN_HOOD);
            }

            if (level == 2) {
                addRandomTradeOffer(factories, 6, 32, 1,
                        ModItems.SURCOAT, ModItems.SURCOAT_SLEEVELESS);
            }
        });
    }

    private static void registerRangedWeaponsTrades(int level) {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, level, factories -> {
            if (level == 1) {
                addTradeOffer(factories, 5, 32, 1, ModItems.BROADHEAD_ARROW);
            }

            if (level == 2) {
                addTradeOffer(factories, 5, 32, 1, ModItems.SWALLOWTAIL_ARROW);
                addTradeOffer(factories, 5, 32, 1, ModItems.BODKIN_ARROW);
                addTradeOffer(factories, 5, 32, 1, ModItems.CLOTH_ARROW);
                addTradeOffer(factories, 13, 12, 1, ModItems.LONGBOW);
            }
        });
    }
}