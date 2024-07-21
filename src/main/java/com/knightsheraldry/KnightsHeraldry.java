package com.knightsheraldry;

import com.knightsheraldry.client.StaminaOverlay;
import com.knightsheraldry.config.ModConfigs;
import com.knightsheraldry.datagen.ModRecipeProvider;
import com.knightsheraldry.event.AttackEventHandler;
import com.knightsheraldry.event.PlayerTickHandler;
import com.knightsheraldry.items.ModItemGroups;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.networking.ModMessages;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
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
        ModMessages.registerC2SPackets();
        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
        AttackEntityCallback.EVENT.register(new AttackEventHandler());
    }

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new StaminaOverlay());
        ModMessages.registerS2CPackets();
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModRecipeProvider::new);
    }
}
