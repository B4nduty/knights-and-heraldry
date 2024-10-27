package com.knightsheraldry.sounds;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ARQUEBUS_CLOSE_1 = registerSoundEvent("arquebus_close_1");
    public static final SoundEvent ARQUEBUS_CLOSE_2 = registerSoundEvent("arquebus_close_2");
    public static final SoundEvent ARQUEBUS_CLOSE_3 = registerSoundEvent("arquebus_close_3");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(KnightsHeraldry.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        KnightsHeraldry.LOGGER.info("Registering Sounds for " + KnightsHeraldry.MOD_ID);
    }
}
