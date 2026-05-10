package banduty.knightsheraldry.effect;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.function.Supplier;

public interface KHEffects {
    Holder<MobEffect> PIN = registerStatusEffect("pin", () -> new PinEffect(MobEffectCategory.HARMFUL, 0xFFFF00));

    private static Holder<MobEffect> registerStatusEffect(String name, Supplier<MobEffect> effect) {
        return Services.PLATFORM.registerHolder(Registries.MOB_EFFECT, name, effect);
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering Effects for " + KnightsHeraldry.MOD_ID);
    }
}
