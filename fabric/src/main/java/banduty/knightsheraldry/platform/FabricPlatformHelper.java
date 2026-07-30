package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.config.FabricKHConfigImpl;
import banduty.knightsheraldry.config.KHConfigImpl;
import banduty.knightsheraldry.data.ArrowBehavior;
import banduty.knightsheraldry.data.ArrowBehaviorManager;
import banduty.knightsheraldry.networking.payload.VelocityS2CPacket;
import banduty.knightsheraldry.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {
    private final FabricKHConfigImpl config;

    public FabricPlatformHelper() {
        this.config = new FabricKHConfigImpl();
    }

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public KHConfigImpl getConfig() {
        return config;
    }

    @Override
    public ArrowBehavior getBehavior(ResourceLocation key) {
        return ArrowBehaviorManager.INSTANCE.getBehavior(key);
    }

    @Override
    public <T> Supplier<T> register(Registry<T> registry, String name, Supplier<T> entry) {
        T result = Registry.register(registry, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, name), entry.get());
        return () -> result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Holder<T> registerHolder(ResourceKey<Registry<T>> registryKey, String name, Supplier<T> value) {
        return Registry.registerForHolder(
                (Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location()),
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, name),
                value.get()
        );
    }

    @Override
    public void syncSpeedHistory(ServerPlayer player, float speedHistory) {
        ServerPlayNetworking.send(player, new VelocityS2CPacket(speedHistory));
    }
}
