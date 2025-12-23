package com.knightsheraldry.util.loottable;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VillagerTradesModifier {
    // As I can't find the xp and maxUses of vanilla I will do my own
    // Level = 1; XP = 1; Max Uses = 3
    // Level = 2; XP = 5; Max Uses = 6
    // Level = 3; XP = 10; Max Uses = 6
    // Level = 4; XP = 15; Max Uses = 6
    // Level = 5; XP = 20; Max Uses = 3
    private static final Map<VillagerProfession, Map<Integer, List<VillagerTrades.ItemListing>>> PROFESSION_TO_LEVELED_TRADE = new HashMap<>();

    public static void registerCustomTrades() {
        PROFESSION_TO_LEVELED_TRADE.clear();
        registerArmorerTrades();
        registerFarmerTrades();
        registerMasonTrades();
        registerWeaponsmithTrades();
        registerShepherdTrades();
        registerFletcherTrades();

        for (Map.Entry<VillagerProfession, Map<Integer, List<VillagerTrades.ItemListing>>> professionEntry : PROFESSION_TO_LEVELED_TRADE.entrySet()) {
            VillagerProfession profession = professionEntry.getKey();
            for (Map.Entry<Integer, List<VillagerTrades.ItemListing>> levelEntry : professionEntry.getValue().entrySet()) {
                int level = levelEntry.getKey();
                List<VillagerTrades.ItemListing> offers = levelEntry.getValue();
                TradeOfferHelper.registerVillagerOffers(profession, level, factories -> factories.addAll(offers));
            }
        }
    }

    private static void addTrade(VillagerProfession profession, int level, VillagerTrades.ItemListing factory) {
        PROFESSION_TO_LEVELED_TRADE
                .computeIfAbsent(profession, p -> new HashMap<>())
                .computeIfAbsent(level, l -> new ArrayList<>())
                .add(factory);
    }

    private static void addTradeOffer(VillagerProfession profession, int level, int emeraldCount, int maxUses, int xp, Item... items) {
        addTrade(profession, level, (entity, random) -> {
            Item item = items[random.nextInt(items.length)];
            return new MerchantOffer(new ItemStack(Items.EMERALD, emeraldCount), new ItemStack(item, 1), maxUses, xp, 0.05f);
        });
    }

    private static void registerArmorerTrades() {
        addTradeOffer(VillagerProfession.ARMORER, 1, 5, 3, 1, ModItems.QUILTED_COIF.get());
        addTradeOffer(VillagerProfession.ARMORER, 1, 8, 3, 1, ModItems.GAMBESON.get());
        addTradeOffer(VillagerProfession.ARMORER, 1, 5, 3, 1, ModItems.GAMBESON_BREECHES.get());
        addTradeOffer(VillagerProfession.ARMORER, 1, 3, 3, 1, ModItems.GAMBESON_BOOTS.get());

        addTradeOffer(VillagerProfession.ARMORER, 2, 7, 6, 5, ModItems.MAIL_COIF.get());
        addTradeOffer(VillagerProfession.ARMORER, 2, 12, 6, 5, ModItems.HAUBERK.get());
        addTradeOffer(VillagerProfession.ARMORER, 2, 7, 6, 5, ModItems.MAIL_BREECHES.get());
        addTradeOffer(VillagerProfession.ARMORER, 2, 5, 6, 5, ModItems.MAIL_BOOTS.get());

        addTradeOffer(VillagerProfession.ARMORER, 1, 8, 3, 1, ModItems.MAIL_SPAULDERS.get());
        addTradeOffer(VillagerProfession.ARMORER, 3, 15, 6, 10, ModItems.BRIGANDINE_SPAULDERS.get());

        addTradeOffer(VillagerProfession.ARMORER, 2, 23, 6, 5, ModItems.BRIGANDINE.get());
        addTradeOffer(VillagerProfession.ARMORER, 3, 26, 6, 10, ModItems.BRIG_BREASTPLATE.get());
        addTradeOffer(VillagerProfession.ARMORER, 4, 29, 6, 15, ModItems.BRIG_BREASTPLATE_TASSETS.get());

        addTradeOffer(VillagerProfession.ARMORER, 1, 9, 3, 1,
                ModItems.BARBUTE.get(),
                ModItems.BASCINET.get(),
                ModItems.KETTLE_HELM.get(),
                ModItems.NASAL_HELM.get(),
                ModItems.VIKING_HELM.get(),
                ModItems.BURGONET.get()
        );

        addTradeOffer(VillagerProfession.ARMORER, 1, 11, 3, 1, ModItems.GAUNTLET.get());
        addTradeOffer(VillagerProfession.ARMORER, 2, 21, 6, 5, ModItems.BRIGANDINE_HARNESS.get());

        addTradeOffer(VillagerProfession.ARMORER, 2, 21, 6, 5, ModItems.BRIGANDINE_CUISSES.get());

        addTradeOffer(VillagerProfession.ARMORER, 2, 12, 6, 5, ModItems.BEVOR.get());

        addTradeOffer(VillagerProfession.ARMORER, 1, 3, 3, 1, ModItems.GREAVES.get());

        addTradeOffer(VillagerProfession.ARMORER, 4, 11, 6, 15, ModItems.SABATONS.get());

        addTradeOffer(VillagerProfession.ARMORER, 5, 4, 3, 20, ModItems.AVENTAIL.get());

        addTradeOffer(VillagerProfession.ARMORER, 5, 4, 3, 20, ModItems.RIM_GUARDS.get());
        addTradeOffer(VillagerProfession.ARMORER, 5, 3, 3, 20, ModItems.BESAGEWS.get());

        addTradeOffer(VillagerProfession.ARMORER, 3, 12, 6, 10, ModItems.HORSE_BARDING.get());
    }

    private static void registerFarmerTrades() {
        addTradeOffer(VillagerProfession.FARMER, 4, 6, 6, 15, ModItems.PITCHFORK.get());
    }

    private static void registerMasonTrades() {
        addTradeOffer(VillagerProfession.MASON, 1, 1, 3, 1, ModItems.WOODEN_LANCE.get());
        addTradeOffer(VillagerProfession.MASON, 5, 7, 6, 1,
                ModItems.TEUTONIC_SNAKES.get(),
                ModItems.TEUTONIC_BLACK_SNAKES.get(),
                ModItems.GOLD_HORNS.get(),
                ModItems.BLACK_HORNS.get(),
                ModItems.TEUTONIC_GOLD_WINGS.get(),
                ModItems.TEUTONIC_BLACK_WINGS.get(),
                ModItems.TEUTONIC_WINGS_BALL_ENDS.get(),
                ModItems.TEUTONIC_WINGS_SHARP_ENDS.get(),
                ModItems.DRAGON.get(),
                ModItems.LION.get(),
                ModItems.SNAKE.get(),
                ModItems.UNICORN.get(),
                ModItems.STAG.get(),
                ModItems.BOAR.get(),
                ModItems.EAGLE.get(),
                ModItems.PEGASUS.get()
        );
    }

    private static void registerWeaponsmithTrades() {
        addTradeOffer(VillagerProfession.WEAPONSMITH, 1,3, 3, 1, ModItems.DAGGER.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,15, 6, 10, ModItems.SWORD.get(), ModItems.V_SWORD.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,10, 6, 10, ModItems.AXE.get(),
                ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 4,10, 6, 15, ModItems.MACE.get(), ModItems.SPIKED_MACE.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 2,10, 6, 5, ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,12, 6, 10, ModItems.HEAVY_CROSSBOW.get());
        addTradeOffer(VillagerProfession.WEAPONSMITH, 5,20, 3, 20, ModItems.ARQUEBUS.get(), ModItems.HANDGONNE.get());
    }

    private static void registerShepherdTrades() {
        addTradeOffer(VillagerProfession.SHEPHERD, 2,2, 6, 5,
                ModItems.SURCOAT.get(), ModItems.SURCOAT_SLEEVELESS.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 3,2, 6, 10,
                ModItems.CIVILIAN_SURCOAT.get(), ModItems.GIORNEA.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 1,15, 3, 1,
                ModItems.HOOD.get(), ModItems.TORN_HOOD.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 4,20, 6, 1,
                ModItems.JESTER_HOOD.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 1,6, 3, 1,
                ModItems.CLOAK.get(), ModItems.TORN_CLOAK.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 2,30, 6, 5,
                ModItems.HELMET_HOOD.get(), ModItems.HELMET_TORN_HOOD.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 1,1, 3, 1, ModItems.CHAPERON.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 5,32, 3, 20, ModItems.GILDED_CHAPERON.get());
        addTradeOffer(VillagerProfession.SHEPHERD, 3,5, 6, 10, ModItems.TORSE.get());
    }

    private static void registerFletcherTrades() {
        addTradeOffer(VillagerProfession.FLETCHER, 1,6, 3, 1, ModItems.LONGBOW.get());
        addTradeOffer(VillagerProfession.FLETCHER, 2,2, 6, 5, ModItems.SWALLOWTAIL_ARROW.get());
        addTradeOffer(VillagerProfession.FLETCHER, 2,4, 6, 5, ModItems.BODKIN_ARROW.get());
        addTradeOffer(VillagerProfession.FLETCHER, 1,1, 3, 1, ModItems.BROADHEAD_ARROW.get());
        addTradeOffer(VillagerProfession.FLETCHER, 3,6, 6, 10, ModItems.CLOTH_ARROW.get());
        addTradeOffer(VillagerProfession.FLETCHER, 4,3, 6, 15, ModItems.PLUME.get());
    }
}