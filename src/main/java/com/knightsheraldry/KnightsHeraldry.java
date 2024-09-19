package com.knightsheraldry;

import com.knightsheraldry.config.KHConfig;
import com.knightsheraldry.datagen.ModItemTagProvider;
import com.knightsheraldry.datagen.ModModelProvider;
import com.knightsheraldry.datagen.ModRecipeProvider;
import com.knightsheraldry.effect.ModEffects;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.event.AttackEntityEventHandler;
import com.knightsheraldry.event.PlayerBlockBreakHandler;
import com.knightsheraldry.event.StartTickHandler;
import com.knightsheraldry.items.ModItemGroups;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.networking.ModMessages;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class KnightsHeraldry implements ModInitializer, DataGeneratorEntrypoint {
    public static final String MOD_ID = "knightsheraldry";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final KHConfig CONFIG = KHConfig.createAndLoad();

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        GeckoLib.initialize();
        ModMessages.registerC2SPackets();
        ModEntities.registerModEntities();
        ModEffects.registerEffects();
        ServerTickEvents.START_SERVER_TICK.register(new StartTickHandler());
        AttackEntityCallback.EVENT.register(new AttackEntityEventHandler());
        PlayerBlockBreakEvents.AFTER.register(new PlayerBlockBreakHandler());
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            if (player != null) {
                player.sendMessage(Text.literal("""
                        §4Knights & Heraldry §ris in §bAlpha
                        §fMany things you see here can change in future updates.
                        
                        By playing in the §bAlpha §fstate,
                        you indicate that you agree not to distribute this project.
                        Non-distribution includes builds and information.
                        
                        Thanks for playing this mod,
                        §4The Knights Heraldry Team"""), false);
            }
        });
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModModelProvider::new);
    }

    public static KHConfig config() {
        return CONFIG;
    }
}