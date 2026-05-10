package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.stoneycore.platform.Services;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.component.DyedItemColor;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface KHDataComponents {
    Supplier<DataComponentType<DyedItemColor>> COLOR_1 = register("color_1",
            builder -> builder.persistent(DyedItemColor.CODEC).networkSynchronized(DyedItemColor.STREAM_CODEC));
    Supplier<DataComponentType<DyedItemColor>> COLOR_2 = register("color_2",
            builder -> builder.persistent(DyedItemColor.CODEC).networkSynchronized(DyedItemColor.STREAM_CODEC));
    Supplier<DataComponentType<HelmetDeco>> HELMET_DECO = register("helmet_deco",
            builder -> builder.persistent(HelmetDeco.CODEC).networkSynchronized(HelmetDeco.STREAM_CODEC));
    Supplier<DataComponentType<Boolean>> EXTINGUISHED = register("extinguished", b -> b.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    @SuppressWarnings("unchecked")
    private static <T> Supplier<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Services.PLATFORM.register(
                (Registry<DataComponentType<T>>) (Registry<?>) BuiltInRegistries.DATA_COMPONENT_TYPE,
                name,
                () -> builderOperator.apply(DataComponentType.builder()).build()
        );
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering Data Components for " + KnightsHeraldry.MOD_ID);
    }
}