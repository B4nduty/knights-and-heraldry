package banduty.knightsheraldry.util.itemdata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

import java.util.*;

public record HelmetDeco(Item item, int colorN, List<Integer> colors, int group) {
    private static final Map<Item, HelmetDeco> REGISTRY = new HashMap<>();

    public static final Codec<HelmetDeco> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(HelmetDeco::item),
            Codec.INT.fieldOf("colorN").forGetter(HelmetDeco::colorN),
            Codec.INT.listOf().fieldOf("colors").forGetter(HelmetDeco::colors),
            Codec.INT.fieldOf("group").forGetter(HelmetDeco::group)
    ).apply(instance, HelmetDeco::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, HelmetDeco> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.registry(BuiltInRegistries.ITEM.key()), HelmetDeco::item,
            ByteBufCodecs.VAR_INT, HelmetDeco::colorN,
            ByteBufCodecs.VAR_INT.apply(ByteBufCodecs.list()), HelmetDeco::colors,
            ByteBufCodecs.VAR_INT, HelmetDeco::group,
            HelmetDeco::new
    );

    public static void register(Item item, int color, int group) {
        if (REGISTRY.containsKey(item)) {
            throw new IllegalArgumentException("Item " + item + " is already registered as a HelmetDeco!");
        }
        REGISTRY.put(item, new HelmetDeco(item, color, new ArrayList<>(), group));
    }

    public static Optional<HelmetDeco> getFromItem(Item item) {
        return Optional.ofNullable(REGISTRY.get(item));
    }

    public String getNbtKey() {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    public static Collection<HelmetDeco> all() {
        return Collections.unmodifiableCollection(REGISTRY.values());
    }

    public static List<Item> getItemsInGroup(int group) {
        return REGISTRY.values().stream()
                .filter(deco -> deco.group() == group)
                .map(HelmetDeco::item)
                .toList();
    }

    public static List<Integer> getColorFromStack(ItemStack stack, int colorN, int defaultColor) {
        List<Integer> colors = new ArrayList<>();
        switch (colorN) {
            case 0: colors.add(-1);
            case 1: {
                HelmetDeco deco = stack.get(KHDataComponents.HELMET_DECO.get());
                colors.add(deco != null ? deco.colorN() : defaultColor);
            }
            case 2: {
                DyedItemColor color1 = stack.get(KHDataComponents.COLOR_1.get());
                colors.add(color1 != null ? color1.rgb() : defaultColor);
                DyedItemColor color2 = stack.get(KHDataComponents.COLOR_2.get());
                colors.add(color2 != null ? color2.rgb() : defaultColor);
            }
        }
        return colors;
    }
}