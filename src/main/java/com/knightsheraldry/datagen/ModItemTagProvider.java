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
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS)
                .add(ModItems.DAGGER, ModItems.STILETTO, ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD,
                        ModItems.ARMING_SWORD, ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE,
                        ModItems.STRAIGHT_CROOKED_AXE, ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE,
                        ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER);
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_SHIELD)
                .add(ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.AXE,
                        ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE, ModItems.WARSWORD,
                        ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER);
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_DAMAGE_BEHIND)
                .add(ModItems.DAGGER);
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_IGNORES_ARMOR)
                .add(ModItems.STILETTO);
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_BLUDGEONING)
                .add(ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.WARSWORD,
                        ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER);
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_PIERCING)
                .add(ModItems.DAGGER, ModItems.STILETTO, ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD,
                        ModItems.ARMING_SWORD, ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE,
                        ModItems.WARSWORD_ZWEIHANDER);
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_ONLY_PIERCING)
                .add(ModItems.STILETTO, ModItems.RAPIER);
        getOrCreateTagBuilder(ModTags.Items.KH_WEAPONS_DISABLE_SHIELD)
                .add(ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE);
        getOrCreateTagBuilder(ModTags.Items.KH_ARMORS);
    }
}