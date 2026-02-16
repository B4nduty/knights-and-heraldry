package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID)
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
        list.add((entity, random) -> {
            Item item = itemSuppliers[random.nextInt(itemSuppliers.length)].get();
            return new MerchantOffer(
                    new ItemStack(Items.EMERALD, emeraldCount),
                    new ItemStack(item, 1),
                    maxUses, xp, 0.05f);
        });
    }

    private static void registerArmorerTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 1, 5, 3, 1, ModItems.QUILTED_COIF);
        addTradeOffer(trades, 1, 8, 3, 1, ModItems.GAMBESON);
        addTradeOffer(trades, 1, 5, 3, 1, ModItems.GAMBESON_BREECHES);
        addTradeOffer(trades, 1, 3, 3, 1, ModItems.GAMBESON_BOOTS);

        addTradeOffer(trades, 2, 7, 6, 5, ModItems.MAIL_COIF);
        addTradeOffer(trades, 2, 12, 6, 5, ModItems.HAUBERK);
        addTradeOffer(trades, 2, 7, 6, 5, ModItems.MAIL_BREECHES);
        addTradeOffer(trades, 2, 5, 6, 5, ModItems.MAIL_BOOTS);

        addTradeOffer(trades, 1, 8, 3, 1, ModItems.MAIL_SPAULDERS);
        addTradeOffer(trades, 3, 15, 6, 10, ModItems.BRIGANDINE_SPAULDERS);

        addTradeOffer(trades, 2, 23, 6, 5, ModItems.BRIGANDINE);
        addTradeOffer(trades, 3, 26, 6, 10, ModItems.BRIG_BREASTPLATE);
        addTradeOffer(trades, 4, 29, 6, 15, ModItems.BRIG_BREASTPLATE_TASSETS);

        addTradeOffer(trades, 1, 9, 3, 1,
                ModItems.BARBUTE, ModItems.BASCINET, ModItems.KETTLE_HELM,
                ModItems.NASAL_HELM, ModItems.VIKING_HELM, ModItems.BURGONET
        );

        addTradeOffer(trades, 1, 11, 3, 1, ModItems.GAUNTLET);
        addTradeOffer(trades, 2, 21, 6, 5, ModItems.BRIGANDINE_HARNESS);
        addTradeOffer(trades, 2, 21, 6, 5, ModItems.BRIGANDINE_CUISSES);
        addTradeOffer(trades, 2, 12, 6, 5, ModItems.BEVOR);
        addTradeOffer(trades, 1, 3, 3, 1, ModItems.GREAVES);
        addTradeOffer(trades, 4, 11, 6, 15, ModItems.SABATONS);
        addTradeOffer(trades, 5, 4, 3, 20, ModItems.AVENTAIL);
        addTradeOffer(trades, 5, 4, 3, 20, ModItems.RIM_GUARDS);
        addTradeOffer(trades, 5, 3, 3, 20, ModItems.BESAGEWS);
        addTradeOffer(trades, 3, 12, 6, 10, ModItems.HORSE_BARDING);
    }

    private static void registerFarmerTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 4, 6, 6, 15, ModItems.PITCHFORK);
    }

    private static void registerMasonTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 1, 1, 3, 1, ModItems.WOODEN_LANCE);
        addTradeOffer(trades, 5, 7, 6, 1,
                ModItems.TEUTONIC_SNAKES, ModItems.TEUTONIC_BLACK_SNAKES, ModItems.GOLD_HORNS,
                ModItems.BLACK_HORNS, ModItems.TEUTONIC_GOLD_WINGS, ModItems.TEUTONIC_BLACK_WINGS,
                ModItems.TEUTONIC_WINGS_BALL_ENDS, ModItems.TEUTONIC_WINGS_SHARP_ENDS, ModItems.DRAGON,
                ModItems.LION, ModItems.SNAKE, ModItems.UNICORN, ModItems.STAG, ModItems.BOAR,
                ModItems.EAGLE, ModItems.PEGASUS
        );
    }

    private static void registerWeaponsmithTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 1, 3, 3, 1, ModItems.DAGGER);
        addTradeOffer(trades, 3, 15, 6, 10, ModItems.SWORD, ModItems.V_SWORD);
        addTradeOffer(trades, 3, 10, 6, 10, ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE);
        addTradeOffer(trades, 4, 10, 6, 15, ModItems.MACE, ModItems.SPIKED_MACE);
        addTradeOffer(trades, 2, 10, 6, 5, ModItems.HAMMER, ModItems.WAR_HAMMER);
        addTradeOffer(trades, 3, 12, 6, 10, ModItems.HEAVY_CROSSBOW);
        addTradeOffer(trades, 5, 20, 3, 20, ModItems.ARQUEBUS, ModItems.HANDGONNE);
    }

    private static void registerShepherdTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 2, 2, 6, 5, ModItems.SURCOAT, ModItems.SURCOAT_SLEEVELESS);
        addTradeOffer(trades, 3, 2, 6, 10, ModItems.CIVILIAN_SURCOAT, ModItems.GIORNEA);
        addTradeOffer(trades, 1, 15, 3, 1, ModItems.HOOD, ModItems.TORN_HOOD);
        addTradeOffer(trades, 4, 20, 6, 1, ModItems.JESTER_HOOD);
        addTradeOffer(trades, 1, 6, 3, 1, ModItems.CLOAK, ModItems.TORN_CLOAK);
        addTradeOffer(trades, 2, 30, 6, 5, ModItems.HELMET_HOOD, ModItems.HELMET_TORN_HOOD);
        addTradeOffer(trades, 1, 1, 3, 1, ModItems.CHAPERON);
        addTradeOffer(trades, 5, 32, 3, 20, ModItems.GILDED_CHAPERON);
        addTradeOffer(trades, 3, 5, 6, 10, ModItems.TORSE);
    }

    private static void registerFletcherTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        addTradeOffer(trades, 1, 6, 3, 1, ModItems.LONGBOW);
        addTradeOffer(trades, 2, 2, 6, 5, ModItems.SWALLOWTAIL_ARROW);
        addTradeOffer(trades, 2, 4, 6, 5, ModItems.BODKIN_ARROW);
        addTradeOffer(trades, 1, 1, 3, 1, ModItems.BROADHEAD_ARROW);
        addTradeOffer(trades, 3, 6, 6, 10, ModItems.CLOTH_ARROW);
        addTradeOffer(trades, 4, 3, 6, 15, ModItems.PLUME);
    }
}