package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.platform.Services;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.PreparableModelLoadingPlugin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public final class KHModelLoadingPlugin
        implements PreparableModelLoadingPlugin<List<ResourceLocation>>,
        PreparableModelLoadingPlugin.DataLoader<List<ResourceLocation>> {

    public static void register() {
        KHModelLoadingPlugin plugin = new KHModelLoadingPlugin();
        PreparableModelLoadingPlugin.register(plugin, plugin);
    }

    @Override
    public CompletableFuture<List<ResourceLocation>> load(ResourceManager resourceManager, Executor executor) {
        return CompletableFuture.supplyAsync(
                () -> BannerModelScanner.collect(
                        resourceManager,
                        Services.PLATFORM.getConfig().getBannerModelItemPaths()
                ),
                executor
        );
    }

    @Override
    public void onInitializeModelLoader(List<ResourceLocation> models, ModelLoadingPlugin.Context context) {
        context.addModels(models);
    }
}
