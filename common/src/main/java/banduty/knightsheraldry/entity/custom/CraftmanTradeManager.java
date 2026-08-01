package banduty.knightsheraldry.entity.custom;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;

import java.util.*;

public class CraftmanTradeManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new Gson();

    public static final Map<String, Map<String, TradeDataContainer>> PROFESSION_TRADES = new HashMap<>();

    public CraftmanTradeManager() {
        super(GSON, "villager_trades");
    }

    public static class TradeDataContainer {
        public boolean replace = false;
        public final List<DatapackTrade> trades = new ArrayList<>();
    }

    public record DatapackTrade (int level, int weight, ItemCost costA, ItemCost costB, ItemStack result,
                                 int maxUses, int xp, float priceMultiplier) {
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        PROFESSION_TRADES.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : object.entrySet()) {
            ResourceLocation fileLoc = entry.getKey();
            String fileName = fileLoc.getPath();

            if (!fileName.startsWith("craftman_")) continue;

            String biomeKey;
            if (fileName.equals("craftman_default")) {
                biomeKey = "default";
            } else {
                String remaining = fileName.substring("craftman_".length());
                int underscoreIndex = remaining.indexOf('_');

                String namespace;
                String path;
                if (underscoreIndex == -1) {
                    namespace = "minecraft";
                    path = remaining;
                } else {
                    namespace = remaining.substring(0, underscoreIndex);
                    path = remaining.substring(underscoreIndex + 1);
                }

                biomeKey = namespace.equals("minecraft") ? path : namespace + ":" + path;
            }

            try {
                JsonObject jsonObject = entry.getValue().getAsJsonObject();
                TradeDataContainer container = new TradeDataContainer();
                container.replace = GsonHelper.getAsBoolean(jsonObject, "replace", false);

                JsonArray tradesArray = GsonHelper.getAsJsonArray(jsonObject, "trades", new JsonArray());
                for (JsonElement tradeElem : tradesArray) {
                    JsonObject tradeObj = tradeElem.getAsJsonObject();

                    int level = GsonHelper.getAsInt(tradeObj, "level", 1);
                    int weight = GsonHelper.getAsInt(tradeObj, "weight", 1);

                    JsonObject costAObj = tradeObj.getAsJsonObject("cost_a");
                    Item itemA = BuiltInRegistries.ITEM.get(ResourceLocation.parse(GsonHelper.getAsString(costAObj, "item")));
                    ItemCost costA = new ItemCost(itemA, GsonHelper.getAsInt(costAObj, "count", 1));

                    ItemCost costB = null;
                    if (tradeObj.has("cost_b") && !tradeObj.get("cost_b").isJsonNull()) {
                        JsonObject costBObj = tradeObj.getAsJsonObject("cost_b");
                        Item itemB = BuiltInRegistries.ITEM.get(ResourceLocation.parse(GsonHelper.getAsString(costBObj, "item")));
                        costB = new ItemCost(itemB, GsonHelper.getAsInt(costBObj, "count", 1));
                    }
                    JsonObject resultObj = tradeObj.getAsJsonObject("result");
                    Item itemResult = BuiltInRegistries.ITEM.get(ResourceLocation.parse(GsonHelper.getAsString(resultObj, "item")));
                    ItemStack result = new ItemStack(itemResult, GsonHelper.getAsInt(resultObj, "count", 1));

                    int maxUses = GsonHelper.getAsInt(tradeObj, "max_uses", 12);
                    int xp = GsonHelper.getAsInt(tradeObj, "xp", 2);
                    float priceMultiplier = GsonHelper.getAsFloat(tradeObj, "price_multiplier", 0.05f);

                    DatapackTrade trade = new DatapackTrade(level, weight, costA, costB, result, maxUses, xp, priceMultiplier);

                    container.trades.add(trade);
                }

                PROFESSION_TRADES.computeIfAbsent("craftman", k -> new HashMap<>()).put(biomeKey, container);
            } catch (Exception e) {
                // Log formatting parsing errors safely
            }
        }
    }
}