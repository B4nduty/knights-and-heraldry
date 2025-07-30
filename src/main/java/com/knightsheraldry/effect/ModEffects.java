package com.knightsheraldry.effect;

import com.knightsheraldry.KnightsHeraldry;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.RegistryKeys;

public class ModEffects {
    public static final DeferredRegister<StatusEffect> STATUS_EFFECTS = DeferredRegister.create(KnightsHeraldry.MOD_ID, RegistryKeys.STATUS_EFFECT);

    public static final StatusEffect PIN = new PinEffect(StatusEffectCategory.HARMFUL, 0xFFFF00);

    public static void registerEffects() {
        STATUS_EFFECTS.register();
        STATUS_EFFECTS.register("pin", () -> PIN);
        KnightsHeraldry.LOGGER.info("Registering Effects for " + KnightsHeraldry.MOD_ID);
    }
}
