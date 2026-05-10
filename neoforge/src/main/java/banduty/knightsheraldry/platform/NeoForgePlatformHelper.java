package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.config.ForgeKHConfigImpl;
import banduty.knightsheraldry.config.KHConfigImpl;
import banduty.knightsheraldry.data.ArrowBehavior;
import banduty.knightsheraldry.data.ArrowBehaviorManager;
import banduty.knightsheraldry.platform.services.IPlatformHelper;
import banduty.stoneycore.StoneyCore;
import net.bettercombat.api.AttackHand;
import net.bettercombat.logic.PlayerAttackHelper;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.registries.DeferredRegister;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class NeoForgePlatformHelper implements IPlatformHelper {
    private static final Map<ResourceKey<? extends Registry<?>>, DeferredRegister<?>> REGISTRIES = new HashMap<>();
    private final ForgeKHConfigImpl config;

    public NeoForgePlatformHelper() {
        this.config = new ForgeKHConfigImpl();
    }

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
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
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> register(Registry<T> registry, String name, Supplier<T> entry) {
        DeferredRegister<T> deferredRegister = (DeferredRegister<T>) REGISTRIES.computeIfAbsent(
                registry.key(),
                k -> DeferredRegister.create(registry.key(), StoneyCore.MOD_ID)
        );

        return deferredRegister.register(name, entry);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Holder<T> registerHolder(ResourceKey<Registry<T>> registryKey, String name, Supplier<T> value) {
        DeferredRegister<T> deferredRegister = (DeferredRegister<T>) REGISTRIES.computeIfAbsent(
                registryKey,
                k -> DeferredRegister.create(registryKey, StoneyCore.MOD_ID)
        );

        return deferredRegister.register(name, value);
    }

    @Override
    public RawAnimation loadAnimation(Minecraft client) {
        if (client.player == null) return RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        AttackHand hand = PlayerAttackHelper.getCurrentAttack(client.player, ((PlayerAttackProperties)client.player).getComboCount());
        RawAnimation animation;
        if (hand != null && client.player.getAttackStrengthScale(0) < (1.0 - hand.upswingRate())) {
            animation = RawAnimation.begin().then("attack", Animation.LoopType.LOOP);
        } else {
            animation = RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        }
        return animation;
    }

    @Override
    public void syncSpeedHistory(ServerPlayer player, float speedHistory) {

    }

    public static void registerRegistries(IEventBus eventBus) {
        REGISTRIES.values().forEach(dr -> dr.register(eventBus));
    }
}