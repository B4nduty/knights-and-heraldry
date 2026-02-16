package banduty.knightsheraldry.effect;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public interface ModEffects {
    MobEffect PIN = new PinEffect(MobEffectCategory.HARMFUL, 0xFFFF00);

    static void registerStatusEffect(String name, MobEffect effect) {
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KnightsHeraldry.MOD_ID, name), effect);
    }

    static void registerEffects() {
        registerStatusEffect("pin", PIN);
        KnightsHeraldry.LOG.info("Registering Effects for " + KnightsHeraldry.MOD_ID);
    }
}
