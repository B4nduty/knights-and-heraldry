package com.knightsheraldry.sounds;

import com.knightsheraldry.KnightsHeraldry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(KnightsHeraldry.MOD_ID, RegistryKeys.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> ARQUEBUS_CLOSE_1 = SOUND_EVENTS.register("arquebus_close",
            () -> SoundEvent.of(new Identifier(KnightsHeraldry.MOD_ID, "arquebus_close")));

    public static void registerSounds() {
        SOUND_EVENTS.register();
        KnightsHeraldry.LOGGER.info("Registering Sounds for " + KnightsHeraldry.MOD_ID);
    }
}
