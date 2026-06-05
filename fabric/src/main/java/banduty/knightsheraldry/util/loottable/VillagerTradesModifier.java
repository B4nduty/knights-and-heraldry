package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.items.KHItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class VillagerTradesModifier {
    // As I can't find the xp and maxUses of vanilla I will do my own
    // Level = 1; XP = 1; Max Uses = 3
    // Level = 2; XP = 5; Max Uses = 6
    // Level = 3; XP = 10; Max Uses = 6
    // Level = 4; XP = 15; Max Uses = 6
    // Level = 5; XP = 20; Max Uses = 3
    private static final Map<VillagerProfession, Map<Integer, List<VillagerTrades.ItemListing>>> PROFESSION_TO_LEVELED_TRADE = new HashMap<>();

    public static void registerCustomTrades() {
        registerArmorerTrades();
        registerFarmerTrades();
        registerFletcherTrades();
        registerShepherdTrades();
        registerWeaponsmithTrades();
        registerMasonTrades();
    }

    @SafeVarargs
    private static void addTradeOffer(VillagerProfession profession, int level, int emeraldCount, int maxUses, int xp, Supplier<Item>... items) {
        for (Supplier<Item> itemSupplier : items) {
            TradeOfferHelper.registerVillagerOffers(profession, level, factories -> {
                factories.add((entity, random) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, emeraldCount),
                        new ItemStack(itemSupplier.get()),
                        maxUses,
                        xp,
                        0.05f
                ));
            });
        }
    }

    private static void registerArmorerTrades() {
        addTradeOffer(VillagerProfession.ARMORER, 1, 5, 3, 1, KHItems.QUILTED_COIF);
        addTradeOffer(VillagerProfession.ARMORER, 1, 8, 3, 1, KHItems.GAMBESON);
        addTradeOffer(VillagerProfession.ARMORER, 1, 5, 3, 1, KHItems.GAMBESON_BREECHES);
        addTradeOffer(VillagerProfession.ARMORER, 1, 3, 3, 1, KHItems.GAMBESON_BOOTS);

        addTradeOffer(VillagerProfession.ARMORER, 2, 7, 6, 5, KHItems.MAIL_COIF);
        addTradeOffer(VillagerProfession.ARMORER, 2, 12, 6, 5, KHItems.HAUBERK);
        addTradeOffer(VillagerProfession.ARMORER, 2, 7, 6, 5, KHItems.MAIL_BREECHES);
        addTradeOffer(VillagerProfession.ARMORER, 2, 5, 6, 5, KHItems.MAIL_BOOTS);

        addTradeOffer(VillagerProfession.ARMORER, 3, 10, 6, 10, KHItems.ARMING_DOUBLET);
        addTradeOffer(VillagerProfession.ARMORER, 3, 6, 6, 10, KHItems.ARMING_HOSE);

        addTradeOffer(VillagerProfession.ARMORER, 1, 8, 3, 1, KHItems.MAIL_SPAULDERS);
        addTradeOffer(VillagerProfession.ARMORER, 3, 15, 6, 10, KHItems.BRIGANDINE_SPAULDERS);

        addTradeOffer(VillagerProfession.ARMORER, 2, 23, 6, 5, KHItems.BRIGANDINE);

        addTradeOffer(VillagerProfession.ARMORER, 1, 9, 3, 1,
                KHItems.BARBUTE,
                KHItems.BASCINET,
                KHItems.KETTLE_HELM,
                KHItems.NASAL_HELM,
                KHItems.VIKING_HELM,
                KHItems.BURGONET,
                KHItems.VISORLESS_SALLET
        );

        addTradeOffer(VillagerProfession.ARMORER, 1, 11, 3, 1, KHItems.GAUNTLET);
        addTradeOffer(VillagerProfession.ARMORER, 2, 21, 6, 5, KHItems.BRIGANDINE_HARNESS);

        addTradeOffer(VillagerProfession.ARMORER, 2, 21, 6, 5, KHItems.BRIGANDINE_CUISSES);

        addTradeOffer(VillagerProfession.ARMORER, 1, 3, 3, 1, KHItems.GREAVES);

        addTradeOffer(VillagerProfession.ARMORER, 4, 11, 6, 15, KHItems.SABATONS);

        addTradeOffer(VillagerProfession.ARMORER, 5, 4, 3, 20, KHItems.AVENTAIL);

        addTradeOffer(VillagerProfession.ARMORER, 5, 4, 3, 20, KHItems.RIM_GUARDS);
        addTradeOffer(VillagerProfession.ARMORER, 5, 3, 3, 20, KHItems.BESAGEWS);

        addTradeOffer(VillagerProfession.ARMORER, 3, 12, 6, 10, KHItems.HORSE_BARDING);
    }

    private static void registerFarmerTrades() {
        addTradeOffer(VillagerProfession.FARMER, 4, 6, 6, 15, KHItems.PITCHFORK);
    }

    private static void registerMasonTrades() {
        addTradeOffer(VillagerProfession.MASON, 1, 1, 3, 1, KHItems.WOODEN_LANCE);
        addTradeOffer(VillagerProfession.MASON, 5, 7, 6, 1,
                KHItems.TEUTONIC_SNAKES,
                KHItems.TEUTONIC_BLACK_SNAKES,
                KHItems.GOLD_HORNS,
                KHItems.BLACK_HORNS,
                KHItems.TEUTONIC_GOLD_WINGS,
                KHItems.TEUTONIC_BLACK_WINGS,
                KHItems.TEUTONIC_WINGS_BALL_ENDS,
                KHItems.TEUTONIC_WINGS_SHARP_ENDS,
                KHItems.DRAGON,
                KHItems.LION,
                KHItems.SNAKE,
                KHItems.UNICORN,
                KHItems.STAG,
                KHItems.BOAR,
                KHItems.EAGLE,
                KHItems.PEGASUS
        );
    }

    private static void registerWeaponsmithTrades() {
        addTradeOffer(VillagerProfession.WEAPONSMITH, 1,3, 3, 1, KHItems.DAGGER);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,15, 6, 10, KHItems.SWORD, KHItems.V_SWORD);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,10, 6, 10, KHItems.AXE,
                KHItems.BROAD_AXE, KHItems.CROOKED_AXE, KHItems.STRAIGHT_CROOKED_AXE);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 4,10, 6, 15, KHItems.MACE, KHItems.SPIKED_MACE);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 2,10, 6, 5, KHItems.HAMMER, KHItems.WAR_HAMMER);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,12, 6, 10, KHItems.HEAVY_CROSSBOW);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 5,20, 3, 20, KHItems.ARQUEBUS, KHItems.HANDGONNE);
    }

    private static void registerShepherdTrades() {
        addTradeOffer(VillagerProfession.SHEPHERD, 2,2, 6, 5,
                KHItems.SURCOAT, KHItems.SURCOAT_SLEEVELESS);
        addTradeOffer(VillagerProfession.SHEPHERD, 3,2, 6, 10,
                KHItems.CIVILIAN_SURCOAT, KHItems.GIORNEA);
        addTradeOffer(VillagerProfession.SHEPHERD, 1,15, 3, 1,
                KHItems.HOOD, KHItems.TORN_HOOD);
        addTradeOffer(VillagerProfession.SHEPHERD, 4,20, 6, 1,
                KHItems.JESTER_HOOD);
        addTradeOffer(VillagerProfession.SHEPHERD, 1,6, 3, 1,
                KHItems.CLOAK, KHItems.TORN_CLOAK);
        addTradeOffer(VillagerProfession.SHEPHERD, 2,30, 6, 5,
                KHItems.HELMET_HOOD, KHItems.HELMET_TORN_HOOD);
        addTradeOffer(VillagerProfession.SHEPHERD, 1,1, 3, 1, KHItems.CHAPERON);
        addTradeOffer(VillagerProfession.SHEPHERD, 5,32, 3, 20, KHItems.GILDED_CHAPERON);
        addTradeOffer(VillagerProfession.SHEPHERD, 3,5, 6, 10, KHItems.TORSE);
    }

    private static void registerFletcherTrades() {
        addTradeOffer(VillagerProfession.FLETCHER, 1,6, 3, 1, KHItems.LONGBOW);
        addTradeOffer(VillagerProfession.FLETCHER, 2,2, 6, 5, KHItems.SWALLOWTAIL_ARROW);
        addTradeOffer(VillagerProfession.FLETCHER, 2,4, 6, 5, KHItems.BODKIN_ARROW);
        addTradeOffer(VillagerProfession.FLETCHER, 1,1, 3, 1, KHItems.BROADHEAD_ARROW);
        addTradeOffer(VillagerProfession.FLETCHER, 3,6, 6, 10, KHItems.CLOTH_ARROW);
        addTradeOffer(VillagerProfession.FLETCHER, 4,3, 6, 15, KHItems.PLUME);
    }
}