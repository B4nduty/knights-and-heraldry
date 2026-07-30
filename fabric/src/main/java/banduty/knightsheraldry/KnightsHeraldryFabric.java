package banduty.knightsheraldry;

import banduty.knightsheraldry.config.KHConfigs;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.entity.custom.Craftman;
import banduty.knightsheraldry.entity.custom.CraftmanTradeManager;
import banduty.knightsheraldry.event.UseItemHandler;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.util.loottable.ArchaeologyLootModifier;
import banduty.knightsheraldry.util.loottable.ChestLootTableModifier;
import banduty.knightsheraldry.util.loottable.VillagerTradesModifier;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.CreativeModeTabs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class KnightsHeraldryFabric implements ModInitializer {
    public static KHConfigs CONFIG;
    @Override
    public void onInitialize() {
        KnightsHeraldry.init();

        UseItemCallback.EVENT.register(new UseItemHandler());
        ArchaeologyLootModifier.registerArchaeologyLoot();
        ChestLootTableModifier.modifyChestLootTables();
        VillagerTradesModifier.registerCustomTrades();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(content -> {
            content.accept(KHItems.CRAFTMAN_SPAWN_EGG.get());
        });

        CraftmanTradeManager commonManager = new CraftmanTradeManager();

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(
                new IdentifiableResourceReloadListener() {
                    @Override
                    public ResourceLocation getFabricId() {
                        return ResourceLocation.fromNamespaceAndPath("knightsheraldry", "villager_trades");
                    }

                    @Override
                    public CompletableFuture<Void> reload(PreparationBarrier barrier, ResourceManager manager,
                                                          ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler,
                                                          Executor backgroundExecutor, Executor gameExecutor) {
                        return commonManager.reload(barrier, manager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor);
                    }
                }
        );

        KnightsHeraldry.LOG.info("Registering Entities Attributes for " + KnightsHeraldry.MOD_ID);
        FabricDefaultAttributeRegistry.register(KHEntities.CRAFTMAN.get(), Craftman.createAttributes());

        AutoConfig.register(KHConfigs.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(KHConfigs.class).getConfig();
    }
}
