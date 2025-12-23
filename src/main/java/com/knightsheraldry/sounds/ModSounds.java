package com.knightsheraldry.sounds;

import com.knightsheraldry.KnightsHeraldry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(KnightsHeraldry.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> ARQUEBUS_CLOSE = SOUND_EVENTS.register("arquebus_close",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(KnightsHeraldry.MOD_ID, "arquebus_close")));

    public static void registerSounds() {
        SOUND_EVENTS.register();
        KnightsHeraldry.LOGGER.info("Registering Sounds for " + KnightsHeraldry.MOD_ID);
    }
}
