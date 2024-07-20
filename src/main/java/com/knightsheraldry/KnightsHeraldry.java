package com.knightsheraldry;

import com.knightsheraldry.config.ModConfigs;
import com.knightsheraldry.datagen.ModRecipeProvider;
import com.knightsheraldry.items.ModItemGroups;
import com.knightsheraldry.items.ModItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnightsHeraldry implements ModInitializer, ClientModInitializer, DataGeneratorEntrypoint {
    public static final String MOD_ID = "knightsheraldry";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ModConfigs CONFIG;

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfigs.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(ModConfigs.class).getConfig();
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
    }

    @Override
    public void onInitializeClient() {

    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModRecipeProvider::new);
    }
}
