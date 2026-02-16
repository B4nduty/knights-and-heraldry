package banduty.knightsheraldry.effect;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModEffects {
    DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, KnightsHeraldry.MOD_ID);

    RegistryObject<MobEffect> PIN = MOB_EFFECTS.register("pin",
            () -> new PinEffect(MobEffectCategory.HARMFUL, 0xFFFF00));

    static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
        KnightsHeraldry.LOG.info("Registering Effects for " + KnightsHeraldry.MOD_ID);
    }
}