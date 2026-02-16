package banduty.knightsheraldry.sounds;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public interface ModSounds {
    SoundEvent ARQUEBUS_CLOSE = registerSoundEvent("arquebus_close");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(KnightsHeraldry.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    static void registerSounds() {
        KnightsHeraldry.LOG.info("Registering Sounds for " + KnightsHeraldry.MOD_ID);
    }
}
