package banduty.knightsheraldry.util.loottable;

import banduty.knightsheraldry.KnightsHeraldry;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModLootTable {
    DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "knightsheraldry");

    RegistryObject<Codec<ArchaeologyItemsModifier>> ARCHAEOLOGY_ITEM_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("add_archaeology_items", ArchaeologyItemsModifier.CODEC);

    RegistryObject<Codec<ManuscriptLootModifier>> MANUSCRIPT_LOOT_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("add_manuscript", () -> ManuscriptLootModifier.CODEC);

    static void registerLootTables(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
        KnightsHeraldry.LOG.info("Registering Mod Loot Table for " + KnightsHeraldry.MOD_ID);
    }
}
