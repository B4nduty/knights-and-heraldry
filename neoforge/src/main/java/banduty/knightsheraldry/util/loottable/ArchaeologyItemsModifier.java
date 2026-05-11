package banduty.knightsheraldry.util.loottable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;
import java.util.function.Supplier;

public class ArchaeologyItemsModifier extends LootModifier {

    public static final Supplier<MapCodec<ArchaeologyItemsModifier>> CODEC = () ->
            RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
                    .and(BuiltInRegistries.ITEM.byNameCodec()
                            .listOf()
                            .fieldOf("items")
                            .forGetter(m -> m.items))
                    .apply(inst, ArchaeologyItemsModifier::new)
            );

    private final List<Item> items;

    protected ArchaeologyItemsModifier(LootItemCondition[] conditionsIn, List<Item> items) {
        super(conditionsIn);
        this.items = items;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        if (items.isEmpty()) return generatedLoot;

        float roll = context.getRandom().nextFloat();

        if (roll < 0.7f) {
            return generatedLoot;
        }

        generatedLoot.clear();

        Item item = items.get(context.getRandom().nextInt(items.size()));
        ItemStack stack = new ItemStack(item);

        if (stack.isDamageableItem()) {
            float damagePercent = 0.01f + context.getRandom().nextFloat() * 0.99f;
            stack.setDamageValue((int) (stack.getMaxDamage() * damagePercent));
        }

        generatedLoot.add(stack);

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}