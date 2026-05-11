package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.KnightsHeraldry;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public interface ModLootTable {
    DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, KnightsHeraldry.MOD_ID);

    DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ArchaeologyItemsModifier>> ARCHAEOLOGY_ITEM_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("add_archaeology_items", ArchaeologyItemsModifier.CODEC);

    DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ManuscriptLootModifier>> MANUSCRIPT_LOOT_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("add_manuscript", ManuscriptLootModifier.CODEC);

    static void registerLootTables(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
        KnightsHeraldry.LOG.info("Registering Mod Loot Table for " + KnightsHeraldry.MOD_ID);
    }
}
