package banduty.knightsheraldry.sounds;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public interface KHSounds {
    Supplier<SoundEvent> ARQUEBUS_CLOSE = registerSound("arquebus_close");

    private static Supplier<SoundEvent> registerSound(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, name);

        return Services.PLATFORM.register(BuiltInRegistries.SOUND_EVENT, name,
                () -> SoundEvent.createVariableRangeEvent(id));
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering Sounds for " + KnightsHeraldry.MOD_ID);
    }
}
