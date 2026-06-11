package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID)
public class VillagerTradesModifier {

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.ARMORER) {
            registerArmorerTrades(event.getTrades());
        }
        if (event.getType() == VillagerProfession.FARMER) {
            registerFarmerTrades(event.getTrades());
        }
        if (event.getType() == VillagerProfession.MASON) {
            registerMasonTrades(event.getTrades());
        }
        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            registerWeaponsmithTrades(event.getTrades());
        }
        if (event.getType() == VillagerProfession.SHEPHERD) {
            registerShepherdTrades(event.getTrades());
        }
        if (event.getType() == VillagerProfession.FLETCHER) {
            registerFletcherTrades(event.getTrades());
        }
    }

    @SafeVarargs
    private static void addTradeOffer(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades,
                                      int level, int emeraldCount, int maxUses, int xp, Supplier<Item>... itemSuppliers) {
        List<VillagerTrades.ItemListing> list = trades.get(level);
        if (list == null) return;

        list.add((entity, random) -> {
            Item item = itemSuppliers[random.nextInt(itemSuppliers.length)].get();
            // In 1.21.1, MerchantOffer uses ItemCost for the price
            return new MerchantOffer(
                    new ItemCost(Items.EMERALD, emeraldCount),
                    Optional.empty(), // Secondary cost (optional)
                    new ItemStack(item, 1),
                    maxUses,
                    xp,
                    0.05f // Price multiplier
            );
        });
    }

    private static void registerArmorerTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 1, 5, 3, 1, KHItems.QUILTED_COIF);
        addTradeOffer(trades, 1, 8, 3, 1, KHItems.GAMBESON);
        addTradeOffer(trades, 1, 5, 3, 1, KHItems.GAMBESON_BREECHES);
        addTradeOffer(trades, 1, 3, 3, 1, KHItems.GAMBESON_BOOTS);

        addTradeOffer(trades, 2, 7, 6, 5, KHItems.MAIL_COIF);
        addTradeOffer(trades, 2, 12, 6, 5, KHItems.HAUBERK);
        addTradeOffer(trades, 2, 7, 6, 5, KHItems.MAIL_BREECHES);
        addTradeOffer(trades, 2, 5, 6, 5, KHItems.MAIL_BOOTS);

        addTradeOffer(trades, 3, 10, 6, 10, KHItems.ARMING_DOUBLET);
        addTradeOffer(trades, 3, 6, 6, 10, KHItems.ARMING_HOSE);

        addTradeOffer(trades, 1, 8, 3, 1, KHItems.MAIL_SPAULDERS);
        addTradeOffer(trades, 3, 15, 6, 10, KHItems.BRIGANDINE_SPAULDERS);

        addTradeOffer(trades, 2, 23, 6, 5, KHItems.BRIGANDINE);

        addTradeOffer(trades, 1, 9, 3, 1,
                KHItems.BARBUTE, KHItems.BASCINET, KHItems.KETTLE_HELM,
                KHItems.NASAL_HELM, KHItems.VIKING_HELM, KHItems.BURGONET, KHItems.VISORLESS_SALLET
        );

        addTradeOffer(trades, 1, 2, 3, 1, KHItems.LEATHER_GLOVES);
        addTradeOffer(trades, 2, 5, 3, 1, KHItems.MAIL_GLOVES);
        addTradeOffer(trades, 4, 11, 3, 1, KHItems.GAUNTLET);
        addTradeOffer(trades, 2, 21, 6, 5, KHItems.BRIGANDINE_HARNESS);
        addTradeOffer(trades, 2, 21, 6, 5, KHItems.BRIGANDINE_CUISSES);
        addTradeOffer(trades, 1, 3, 3, 1, KHItems.GREAVES);
        addTradeOffer(trades, 4, 11, 6, 15, KHItems.SABATONS);
        addTradeOffer(trades, 5, 4, 3, 20, KHItems.AVENTAIL);
        addTradeOffer(trades, 5, 4, 3, 20, KHItems.RIM_GUARDS);
        addTradeOffer(trades, 5, 3, 3, 20, KHItems.BESAGEWS);
        addTradeOffer(trades, 3, 12, 6, 10, KHItems.HORSE_BARDING);
    }

    private static void registerFarmerTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 4, 6, 6, 15, KHItems.PITCHFORK);
    }

    private static void registerMasonTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 1, 1, 3, 1, KHItems.WOODEN_LANCE);
        addTradeOffer(trades, 5, 7, 6, 1,
                KHItems.TEUTONIC_SNAKES, KHItems.TEUTONIC_BLACK_SNAKES, KHItems.GOLD_HORNS,
                KHItems.BLACK_HORNS, KHItems.TEUTONIC_GOLD_WINGS, KHItems.TEUTONIC_BLACK_WINGS,
                KHItems.TEUTONIC_WINGS_BALL_ENDS, KHItems.TEUTONIC_WINGS_SHARP_ENDS, KHItems.DRAGON,
                KHItems.LION, KHItems.SNAKE, KHItems.UNICORN, KHItems.STAG, KHItems.BOAR,
                KHItems.EAGLE, KHItems.PEGASUS
        );
    }

    private static void registerWeaponsmithTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 1, 3, 3, 1, KHItems.DAGGER);
        addTradeOffer(trades, 3, 15, 6, 10, KHItems.SWORD, KHItems.V_SWORD);
        addTradeOffer(trades, 3, 10, 6, 10, KHItems.AXE, KHItems.BROAD_AXE, KHItems.CROOKED_AXE, KHItems.STRAIGHT_CROOKED_AXE);
        addTradeOffer(trades, 4, 10, 6, 15, KHItems.MACE, KHItems.SPIKED_MACE);
        addTradeOffer(trades, 2, 10, 6, 5, KHItems.HAMMER, KHItems.WAR_HAMMER);
        addTradeOffer(trades, 3, 12, 6, 10, KHItems.HEAVY_CROSSBOW);
        addTradeOffer(trades, 5, 20, 3, 20, KHItems.ARQUEBUS, KHItems.HANDGONNE);
    }

    private static void registerShepherdTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 2, 2, 6, 5, KHItems.SURCOAT, KHItems.SURCOAT_SLEEVELESS);
        addTradeOffer(trades, 3, 2, 6, 10, KHItems.CIVILIAN_SURCOAT, KHItems.GIORNEA);
        addTradeOffer(trades, 1, 15, 3, 1, KHItems.HOOD, KHItems.TORN_HOOD);
        addTradeOffer(trades, 4, 20, 6, 1, KHItems.JESTER_HOOD);
        addTradeOffer(trades, 1, 6, 3, 1, KHItems.CLOAK, KHItems.TORN_CLOAK);
        addTradeOffer(trades, 2, 30, 6, 5, KHItems.HELMET_HOOD, KHItems.HELMET_TORN_HOOD);
        addTradeOffer(trades, 1, 1, 3, 1, KHItems.CHAPERON);
        addTradeOffer(trades, 5, 32, 3, 20, KHItems.GILDED_CHAPERON);
        addTradeOffer(trades, 3, 5, 6, 10, KHItems.TORSE);
    }

    private static void registerFletcherTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 1, 6, 3, 1, KHItems.LONGBOW);
        addTradeOffer(trades, 2, 2, 6, 5, KHItems.SWALLOWTAIL_ARROW);
        addTradeOffer(trades, 2, 4, 6, 5, KHItems.BODKIN_ARROW);
        addTradeOffer(trades, 1, 1, 3, 1, KHItems.BROADHEAD_ARROW);
        addTradeOffer(trades, 3, 6, 6, 10, KHItems.CLOTH_ARROW);
        addTradeOffer(trades, 4, 3, 6, 15, KHItems.PLUME);
    }
}