package com.knightsheraldry.effect;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect PIN = new PinEffect(StatusEffectCategory.HARMFUL, 16776960);

    public static void registerStatusEffect(String name, StatusEffect effect) {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(KnightsHeraldry.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        registerStatusEffect("pin", PIN);
    }
}
