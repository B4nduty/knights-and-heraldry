package com.knightsheraldry.util.loottable;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VillagerTradesModifier {
    private static final Map<VillagerProfession, Map<Integer, List<TradeOffers.Factory>>> PROFESSION_TO_LEVELED_TRADE = new HashMap<>();

    public static void registerCustomTrades() {
        PROFESSION_TO_LEVELED_TRADE.clear();
        registerArmorerTrades();
        registerWeaponsmithTrades();
        registerShepherdTrades();
        registerFletcherTrades();

        for (Map.Entry<VillagerProfession, Map<Integer, List<TradeOffers.Factory>>> professionEntry : PROFESSION_TO_LEVELED_TRADE.entrySet()) {
            VillagerProfession profession = professionEntry.getKey();
            for (Map.Entry<Integer, List<TradeOffers.Factory>> levelEntry : professionEntry.getValue().entrySet()) {
                int level = levelEntry.getKey();
                List<TradeOffers.Factory> offers = levelEntry.getValue();
                TradeOfferHelper.registerVillagerOffers(profession, level, factories -> factories.addAll(offers));
            }
        }
    }

    private static void addTrade(VillagerProfession profession, int level, TradeOffers.Factory factory) {
        PROFESSION_TO_LEVELED_TRADE
                .computeIfAbsent(profession, p -> new HashMap<>())
                .computeIfAbsent(level, l -> new ArrayList<>())
                .add(factory);
    }

    private static void addTradeOffer(VillagerProfession profession, int level, int emeraldCount, int maxUses, int xp, Item item) {
        addTrade(profession, level, (entity, random) -> {
            return new TradeOffer(
                    new ItemStack(Items.EMERALD, emeraldCount), new ItemStack(item, 1), maxUses, xp, 0.05f);
        });
    }

    private static void addRandomTradeOffer(VillagerProfession profession, int level, int emeraldCount, int maxUses, int xp, Item... items) {
        addTrade(profession, level, (entity, random) -> {
            Item item = items[random.nextInt(items.length)];
            return new TradeOffer(new ItemStack(Items.EMERALD, emeraldCount), new ItemStack(item, 1), maxUses, xp, 0.05f);
        });
    }

    private static void registerArmorerTrades() {
        addTradeOffer(VillagerProfession.ARMORER, 1, 8, 12, 1, ModItems.QUILTED_COIF.get());
        addTradeOffer(VillagerProfession.ARMORER, 1, 11, 12, 1, ModItems.GAMBESON.get());
        addTradeOffer(VillagerProfession.ARMORER, 1, 8, 12, 1, ModItems.GAMBESON_BREECHES.get());
        addTradeOffer(VillagerProfession.ARMORER, 1, 6, 12, 1, ModItems.GAMBESON_BOOTS.get());

        addTradeOffer(VillagerProfession.ARMORER, 2,9, 12, 5, ModItems.MAIL_COIF.get());
        addTradeOffer(VillagerProfession.ARMORER, 2,14, 12, 5, ModItems.HAUBERK.get());
        addTradeOffer(VillagerProfession.ARMORER, 2,11, 12, 5, ModItems.MAIL_BREECHES.get());
        addTradeOffer(VillagerProfession.ARMORER, 2,9, 12, 5, ModItems.MAIL_BOOTS.get());
        addTradeOffer(VillagerProfession.ARMORER, 2,8, 12, 5, ModItems.MAIL_PAULDRON.get());
        addTradeOffer(VillagerProfession.ARMORER, 2,9, 12, 5, ModItems.AVENTAIL.get());


        addTradeOffer(VillagerProfession.ARMORER, 3,23, 12, 10, ModItems.BRIGANDINE.get());
        addTradeOffer(VillagerProfession.ARMORER, 3,11, 12, 10, ModItems.GAUNTLET.get());
        addTradeOffer(VillagerProfession.ARMORER, 3,12, 12, 15, ModItems.HORSE_BARDING.get());
        addRandomTradeOffer(VillagerProfession.ARMORER, 3,9, 12, 10,
                ModItems.BASCINET_NO_VISOR.get(), ModItems.KETTLE_HELM.get(), ModItems.NASAL_HELM.get(), ModItems.VIKING_HELM.get(),
                ModItems.BARBUTE_NO_VISOR.get());


        addTradeOffer(VillagerProfession.ARMORER, 4,15, 12, 15, ModItems.BRIGANDINE_PAULDRON.get());
        addTradeOffer(VillagerProfession.ARMORER, 4,21, 12, 15, ModItems.BRIGANDINE_REREBRACE.get());
        addTradeOffer(VillagerProfession.ARMORER, 4,21, 12, 15, ModItems.BRIGANDINE_CHAUSSES.get());
        addRandomTradeOffer(VillagerProfession.ARMORER, 4,24, 12, 15,
                ModItems.ARMET_2.get(), ModItems.BARBUTE.get(), ModItems.BASCINET.get(), ModItems.CAGE.get(),
                ModItems.CAGE_2.get(), ModItems.FLAT_BASCINET.get(), ModItems.GREAT_HELM.get(), ModItems.GREAT_HELM_2.get(),
                ModItems.SALLET.get(), ModItems.ARMET.get());


        addTradeOffer(VillagerProfession.ARMORER, 5,18, 12, 15, ModItems.PLATE_PAULDRON.get());
        addTradeOffer(VillagerProfession.ARMORER, 5,13, 12, 15, ModItems.GREAVES.get());
        addTradeOffer(VillagerProfession.ARMORER, 5,11, 12, 15, ModItems.SABATONS.get());
        addTradeOffer(VillagerProfession.ARMORER, 5,24, 12, 15, ModItems.PLATE_REREBRACE.get());
        addTradeOffer(VillagerProfession.ARMORER, 5,15, 12, 15, ModItems.PLATE_CUISSES.get());
        addTradeOffer(VillagerProfession.ARMORER, 5,4, 12, 15, ModItems.RIM_GUARDS.get());
        addTradeOffer(VillagerProfession.ARMORER, 5,3, 12, 15, ModItems.BESAGEWS.get());
        addRandomTradeOffer(VillagerProfession.ARMORER, 5,26, 12, 15,
                ModItems.BRIG_BREASTPLATE.get(), ModItems.BRIG_BREASTPLATE_TASSETS.get());
        addRandomTradeOffer(VillagerProfession.ARMORER, 5,26, 12, 15,
                ModItems.PLATE_CUIRASS_TASSETS.get(), ModItems.MAXIMILLIAN_CUIRASS.get(),
                ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), ModItems.XIIII_PLATE_CUIRASS.get(),
                ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(), ModItems.XIIII_PLATE_BREASTPLATE.get());
        addRandomTradeOffer(VillagerProfession.ARMORER, 5,24, 12, 15,
                ModItems.FROGMOUTH.get(), ModItems.GREAT_ARMET.get(), ModItems.GREAT_ARMET_2.get(),
                ModItems.GREAT_HOUNDSKUL_BASCINET.get(), ModItems.MAXIMILLIAN_HELMET.get());
    }

    private static void registerWeaponsmithTrades() {
        addTradeOffer(VillagerProfession.WEAPONSMITH, 1,8, 12, 1, ModItems.PITCHFORK.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 1,8, 12, 1, ModItems.SPEAR.get());
        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 1,13, 12, 1,
                ModItems.AXE.get(), ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 1,5, 12, 1, ModItems.DAGGER.get());


        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 2,18, 12, 5,
                ModItems.SWORD.get(), ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get());
        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 2,13, 12, 5,
                ModItems.MACE.get(), ModItems.SPIKED_MACE.get());
        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 2,15, 12, 5,
                ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get());
        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 2,13, 12, 5,
                ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 2,20, 12, 5, ModItems.WARDART.get());


        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,15, 12, 10, ModItems.BILLHOOK.get());
        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 3,30, 12, 10, ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get());
        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 3,15, 12, 10, ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,13, 12, 10, ModItems.LANCE.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,5, 12, 10, ModItems.STILETTO.get());


        addTradeOffer(VillagerProfession.WEAPONSMITH, 4,23, 12, 15, ModItems.POLEAXE.get());
        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 4,23, 12, 15,
                ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get());
        addRandomTradeOffer(VillagerProfession.WEAPONSMITH, 4,35, 12, 15,
                ModItems.WARSWORD.get(), ModItems.WARSWORD_CLAYMORE.get(), ModItems.WARSWORD_FLAMBERGE.get(),
                ModItems.WARSWORD_ZWEIHANDER.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 4,15, 12, 15, ModItems.RAPIER.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 4,20, 12, 15, ModItems.MORNING_STAR.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 4,18, 12, 15, ModItems.HANDGONNE.get());


        addTradeOffer(VillagerProfession.WEAPONSMITH, 5,18, 12, 15, ModItems.HALBERD.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 5,18, 12, 15, ModItems.BARDICHE.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 5,25, 12, 15, ModItems.ARQUEBUS.get());
    }

    private static void registerShepherdTrades() {
        addRandomTradeOffer(VillagerProfession.SHEPHERD, 1,6, 32, 1,
                ModItems.CLOAK.get(), ModItems.TORN_CLOAK.get());
        addRandomTradeOffer(VillagerProfession.SHEPHERD, 1,15, 32, 1,
                ModItems.HOOD.get(), ModItems.TORN_HOOD.get());
        addRandomTradeOffer(VillagerProfession.SHEPHERD, 1,30, 12, 5, ModItems.HELMET_HOOD.get(), ModItems.HELMET_TORN_HOOD.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 1,1, 32, 1, ModItems.CHAPERON.get());


        addRandomTradeOffer(VillagerProfession.SHEPHERD, 2,2, 32, 1,
                ModItems.SURCOAT.get(), ModItems.SURCOAT_SLEEVELESS.get());


        addTradeOffer(VillagerProfession.SHEPHERD, 4,20, 12, 5, ModItems.JESTER_HOOD.get());


        addTradeOffer(VillagerProfession.SHEPHERD, 5,32, 12, 15, ModItems.GILDED_CHAPERON.get());
    }

    private static void registerFletcherTrades() {
        addTradeOffer(VillagerProfession.FLETCHER, 1,1, 32, 1, ModItems.BROADHEAD_ARROW.get());


        addTradeOffer(VillagerProfession.FLETCHER, 2,2, 32, 5, ModItems.SWALLOWTAIL_ARROW.get());
        addTradeOffer(VillagerProfession.FLETCHER, 2,4, 32, 5, ModItems.BODKIN_ARROW.get());
        addTradeOffer(VillagerProfession.FLETCHER, 2,13, 12, 5, ModItems.LONGBOW.get());


        addTradeOffer(VillagerProfession.FLETCHER, 3,6, 32, 5, ModItems.CLOTH_ARROW.get());
        addTradeOffer(VillagerProfession.FLETCHER, 3,15, 12, 10, ModItems.HEAVY_CROSSBOW.get());


        addTradeOffer(VillagerProfession.FLETCHER, 4,3, 12, 5, ModItems.PLUME.get());

    }
}