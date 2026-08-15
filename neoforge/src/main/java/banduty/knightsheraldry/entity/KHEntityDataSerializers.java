package banduty.knightsheraldry.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.Craftman;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class KHEntityDataSerializers {

    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS =
            DeferredRegister.create(
                    NeoForgeRegistries.ENTITY_DATA_SERIALIZERS,
                    KnightsHeraldry.MOD_ID
            );

    public static final DeferredHolder<
            EntityDataSerializer<?>,
            EntityDataSerializer<Craftman.CraftmanData>
            > CRAFTMAN_DATA_SERIALIZER =
            ENTITY_DATA_SERIALIZERS.register(
                    "craftman_data",
                    () -> Craftman.CRAFTMAN_DATA_SERIALIZER
            );

    public static void register(IEventBus eventBus) {
        ENTITY_DATA_SERIALIZERS.register(eventBus);
    }
}