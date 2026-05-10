package banduty.knightsheraldry.sounds;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModSounds {
    DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KnightsHeraldry.MOD_ID);

    RegistryObject<SoundEvent> ARQUEBUS_CLOSE = registerSoundEvent("arquebus_close");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(KnightsHeraldry.MOD_ID, name)));
    }

    static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}