package com.knightsheraldry.effect;

import com.knightsheraldry.KnightsHeraldry;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static final DeferredRegister<MobEffect> STATUS_EFFECTS = DeferredRegister.create(KnightsHeraldry.MOD_ID, Registries.MOB_EFFECT);

    public static final MobEffect PIN = new PinEffect(MobEffectCategory.HARMFUL, 0xFFFF00);

    public static void registerEffects() {
        STATUS_EFFECTS.register();
        STATUS_EFFECTS.register("pin", () -> PIN);
        KnightsHeraldry.LOGGER.info("Registering Effects for " + KnightsHeraldry.MOD_ID);
    }
}
