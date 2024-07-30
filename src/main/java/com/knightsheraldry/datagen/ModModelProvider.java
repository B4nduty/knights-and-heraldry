package com.knightsheraldry.datagen;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.DAGGER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STILETTO, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RAPIER, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.V_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ARMING_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BROAD_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CROOKED_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STRAIGHT_CROOKED_AXE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.WARSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_CLAYMORE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_FLAMBERGE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_ZWEIHANDER, Models.HANDHELD);

    }
}
