package banduty.knightsheraldry.util.itemdata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record RepairKitContents(List<Integer> counts, int last) {
    public static final RepairKitContents EMPTY = new RepairKitContents(List.of(), -1);

    public static final Codec<RepairKitContents> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.listOf().fieldOf("counts").forGetter(RepairKitContents::counts),
            Codec.INT.optionalFieldOf("last", -1).forGetter(RepairKitContents::last)
    ).apply(instance, RepairKitContents::new));

    public static final StreamCodec<ByteBuf, RepairKitContents> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT.apply(ByteBufCodecs.list()), RepairKitContents::counts,
            ByteBufCodecs.VAR_INT, RepairKitContents::last,
            RepairKitContents::new
    );

    public RepairKitContents {
        counts = List.copyOf(counts);
    }

    public int get(int index) {
        return index >= 0 && index < counts.size() ? counts.get(index) : 0;
    }
}