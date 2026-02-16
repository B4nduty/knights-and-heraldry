package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.items.ModItems;
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
        addTradeOffer(VillagerProfession.ARMORER, 1, 5, 3, 1, ModItems.QUILTED_COIF);
        addTradeOffer(VillagerProfession.ARMORER, 1, 8, 3, 1, ModItems.GAMBESON);
        addTradeOffer(VillagerProfession.ARMORER, 1, 5, 3, 1, ModItems.GAMBESON_BREECHES);
        addTradeOffer(VillagerProfession.ARMORER, 1, 3, 3, 1, ModItems.GAMBESON_BOOTS);

        addTradeOffer(VillagerProfession.ARMORER, 2, 7, 6, 5, ModItems.MAIL_COIF);
        addTradeOffer(VillagerProfession.ARMORER, 2, 12, 6, 5, ModItems.HAUBERK);
        addTradeOffer(VillagerProfession.ARMORER, 2, 7, 6, 5, ModItems.MAIL_BREECHES);
        addTradeOffer(VillagerProfession.ARMORER, 2, 5, 6, 5, ModItems.MAIL_BOOTS);

        addTradeOffer(VillagerProfession.ARMORER, 1, 8, 3, 1, ModItems.MAIL_SPAULDERS);
        addTradeOffer(VillagerProfession.ARMORER, 3, 15, 6, 10, ModItems.BRIGANDINE_SPAULDERS);

        addTradeOffer(VillagerProfession.ARMORER, 2, 23, 6, 5, ModItems.BRIGANDINE);
        addTradeOffer(VillagerProfession.ARMORER, 3, 26, 6, 10, ModItems.BRIG_BREASTPLATE);
        addTradeOffer(VillagerProfession.ARMORER, 4, 29, 6, 15, ModItems.BRIG_BREASTPLATE_TASSETS);

        addTradeOffer(VillagerProfession.ARMORER, 1, 9, 3, 1,
                ModItems.BARBUTE,
                ModItems.BASCINET,
                ModItems.KETTLE_HELM,
                ModItems.NASAL_HELM,
                ModItems.VIKING_HELM,
                ModItems.BURGONET
        );

        addTradeOffer(VillagerProfession.ARMORER, 1, 11, 3, 1, ModItems.GAUNTLET);
        addTradeOffer(VillagerProfession.ARMORER, 2, 21, 6, 5, ModItems.BRIGANDINE_HARNESS);

        addTradeOffer(VillagerProfession.ARMORER, 2, 21, 6, 5, ModItems.BRIGANDINE_CUISSES);

        addTradeOffer(VillagerProfession.ARMORER, 2, 12, 6, 5, ModItems.BEVOR);

        addTradeOffer(VillagerProfession.ARMORER, 1, 3, 3, 1, ModItems.GREAVES);

        addTradeOffer(VillagerProfession.ARMORER, 4, 11, 6, 15, ModItems.SABATONS);

        addTradeOffer(VillagerProfession.ARMORER, 5, 4, 3, 20, ModItems.AVENTAIL);

        addTradeOffer(VillagerProfession.ARMORER, 5, 4, 3, 20, ModItems.RIM_GUARDS);
        addTradeOffer(VillagerProfession.ARMORER, 5, 3, 3, 20, ModItems.BESAGEWS);

        addTradeOffer(VillagerProfession.ARMORER, 3, 12, 6, 10, ModItems.HORSE_BARDING);
    }

    private static void registerFarmerTrades() {
        addTradeOffer(VillagerProfession.FARMER, 4, 6, 6, 15, ModItems.PITCHFORK);
    }

    private static void registerMasonTrades() {
        addTradeOffer(VillagerProfession.MASON, 1, 1, 3, 1, ModItems.WOODEN_LANCE);
        addTradeOffer(VillagerProfession.MASON, 5, 7, 6, 1,
                ModItems.TEUTONIC_SNAKES,
                ModItems.TEUTONIC_BLACK_SNAKES,
                ModItems.GOLD_HORNS,
                ModItems.BLACK_HORNS,
                ModItems.TEUTONIC_GOLD_WINGS,
                ModItems.TEUTONIC_BLACK_WINGS,
                ModItems.TEUTONIC_WINGS_BALL_ENDS,
                ModItems.TEUTONIC_WINGS_SHARP_ENDS,
                ModItems.DRAGON,
                ModItems.LION,
                ModItems.SNAKE,
                ModItems.UNICORN,
                ModItems.STAG,
                ModItems.BOAR,
                ModItems.EAGLE,
                ModItems.PEGASUS
        );
    }

    private static void registerWeaponsmithTrades() {
        addTradeOffer(VillagerProfession.WEAPONSMITH, 1,3, 3, 1, ModItems.DAGGER);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,15, 6, 10, ModItems.SWORD, ModItems.V_SWORD);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,10, 6, 10, ModItems.AXE,
                ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 4,10, 6, 15, ModItems.MACE, ModItems.SPIKED_MACE);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 2,10, 6, 5, ModItems.HAMMER, ModItems.WAR_HAMMER);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 3,12, 6, 10, ModItems.HEAVY_CROSSBOW);
        addTradeOffer(VillagerProfession.WEAPONSMITH, 5,20, 3, 20, ModItems.ARQUEBUS, ModItems.HANDGONNE);
    }

    private static void registerShepherdTrades() {
        addTradeOffer(VillagerProfession.SHEPHERD, 2,2, 6, 5,
                ModItems.SURCOAT, ModItems.SURCOAT_SLEEVELESS);
        addTradeOffer(VillagerProfession.SHEPHERD, 3,2, 6, 10,
                ModItems.CIVILIAN_SURCOAT, ModItems.GIORNEA);
        addTradeOffer(VillagerProfession.SHEPHERD, 1,15, 3, 1,
                ModItems.HOOD, ModItems.TORN_HOOD);
        addTradeOffer(VillagerProfession.SHEPHERD, 4,20, 6, 1,
                ModItems.JESTER_HOOD);
        addTradeOffer(VillagerProfession.SHEPHERD, 1,6, 3, 1,
                ModItems.CLOAK, ModItems.TORN_CLOAK);
        addTradeOffer(VillagerProfession.SHEPHERD, 2,30, 6, 5,
                ModItems.HELMET_HOOD, ModItems.HELMET_TORN_HOOD);
        addTradeOffer(VillagerProfession.SHEPHERD, 1,1, 3, 1, ModItems.CHAPERON);
        addTradeOffer(VillagerProfession.SHEPHERD, 5,32, 3, 20, ModItems.GILDED_CHAPERON);
        addTradeOffer(VillagerProfession.SHEPHERD, 3,5, 6, 10, ModItems.TORSE);
    }

    private static void registerFletcherTrades() {
        addTradeOffer(VillagerProfession.FLETCHER, 1,6, 3, 1, ModItems.LONGBOW);
        addTradeOffer(VillagerProfession.FLETCHER, 2,2, 6, 5, ModItems.SWALLOWTAIL_ARROW);
        addTradeOffer(VillagerProfession.FLETCHER, 2,4, 6, 5, ModItems.BODKIN_ARROW);
        addTradeOffer(VillagerProfession.FLETCHER, 1,1, 3, 1, ModItems.BROADHEAD_ARROW);
        addTradeOffer(VillagerProfession.FLETCHER, 3,6, 6, 10, ModItems.CLOTH_ARROW);
        addTradeOffer(VillagerProfession.FLETCHER, 4,3, 6, 15, ModItems.PLUME);
    }
}