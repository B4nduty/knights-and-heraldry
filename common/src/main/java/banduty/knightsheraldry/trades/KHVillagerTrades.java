package banduty.knightsheraldry.trades;

import banduty.knightsheraldry.items.KHItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class KHVillagerTrades {
    public static final Map<Integer, List<VillagerTrades.ItemListing>> FLETCHER_TRADES = Map.of(
            1, List.of(
                    new SellItemFromEmeralds(KHItems.WOODEN_LANCE, 1, 1, 12, 2, 0.05F),
                    new SellItemFromEmeralds(KHItems.MANUSCRIPT_BROADHEAD, 1, 1, 12, 2, 0.05F)
            ),
            2, List.of(
                    new SellItemFromEmeralds(KHItems.LONGBOW, 1, 8, 12, 5, 0.05F),
                    new SellItemFromEmeralds(KHItems.MANUSCRIPT_SWALLOWTAIL, 1, 2, 12, 5, 0.05F)
            ),
            3, List.of(
                    new SellItemFromEmeralds(KHItems.HEAVY_CROSSBOW, 1, 25, 12, 10, 0.05F),
                    new SellItemFromEmeralds(KHItems.MANUSCRIPT_BODKIN, 1, 3, 12, 10, 0.05F)
            ),
            4, List.of(
                    new SellItemFromEmeralds(KHItems.MANUSCRIPT_CLOTH, 1, 4, 12, 15, 0.05F)
            )
    );

    public record SellItemFromEmeralds(Supplier<Item> item, int itemCount, int emeraldCost, int maxUses,
                                       int villagerXp, float priceMultiplier) implements VillagerTrades.ItemListing {
        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            ItemStack result = new ItemStack(item.get(), itemCount);
            return new MerchantOffer(
                    new ItemCost(Items.EMERALD, emeraldCost),
                    Optional.empty(),
                    result,
                    maxUses,
                    villagerXp,
                    priceMultiplier
            );
        }
    }
}