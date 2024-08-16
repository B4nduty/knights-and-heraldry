package com.knightsheraldry.datagen;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_SHIELD)
                .add(ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.AXE,
                        ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE, ModItems.MACE,
                        ModItems.SPIKED_MACE, ModItems.HAMMER, ModItems.WAR_HAMMER, ModItems.LONGSWORD,
                        ModItems.V_LONGSWORD, ModItems.FALCHION, ModItems.SCIMITAR, ModItems.KATANA, ModItems.PITCHFORK,
                        ModItems.SPEAR, ModItems.PIKE, ModItems.BILLHOOK, ModItems.GLAIVE, ModItems.CURVED_GLAIVE,
                        ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_DAMAGE_BEHIND)
                .add(ModItems.DAGGER);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_IGNORES_ARMOR)
                .add(ModItems.STILETTO);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_ONLY_BLUDGEONING)
                .add(ModItems.MACE, ModItems.SPIKED_MACE, ModItems.FLAIL, ModItems.BALL_FLAIL);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_BLUDGEONING)
                .add(ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.LONGSWORD, ModItems.V_LONGSWORD,
                        ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE,
                        ModItems.WARSWORD_ZWEIHANDER);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_PIERCING)
                .add(ModItems.DAGGER, ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.LONGSWORD,
                        ModItems.V_LONGSWORD, ModItems.BILLHOOK, ModItems.GLAIVE, ModItems.CURVED_GLAIVE,
                        ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_ONLY_PIERCING)
                .add(ModItems.STILETTO, ModItems.RAPIER, ModItems.PITCHFORK, ModItems.SPEAR, ModItems.PIKE);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_DISABLE_SHIELD)
                .add(ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_BYPASS_BLOCK)
                .add(ModItems.FLAIL, ModItems.BALL_FLAIL);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_BLUDGEONING_PIERCING)
                .add(ModItems.HAMMER, ModItems.WAR_HAMMER);

        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_HARVEST)
                .add(ModItems.PITCHFORK);

        getOrCreateTagBuilder(ModTags.Items.KH_ARMORS);
    }
}