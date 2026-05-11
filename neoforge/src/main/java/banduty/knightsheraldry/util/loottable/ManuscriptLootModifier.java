package banduty.knightsheraldry.util.loottable;

import banduty.stoneycore.items.custom.manuscript.Manuscript;
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

public class ManuscriptLootModifier extends LootModifier {
    public static final Supplier<MapCodec<ManuscriptLootModifier>> CODEC = () ->
            RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
                    .and(BuiltInRegistries.ITEM.byNameCodec()
                            .listOf()
                            .fieldOf("items")
                            .forGetter(m -> m.items))
                    .apply(inst, ManuscriptLootModifier::new)
            );

    private final List<Item> items;

    protected ManuscriptLootModifier(LootItemCondition[] conditionsIn, List<Item> items) {
        super(conditionsIn);
        this.items = items;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Item selectedItem = items.get(context.getRandom().nextInt(items.size()));

        ItemStack manuscriptStack = Manuscript.createForStack(new ItemStack(selectedItem));

        generatedLoot.add(manuscriptStack);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}