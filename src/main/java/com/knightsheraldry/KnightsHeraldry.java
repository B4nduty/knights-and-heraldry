package com.knightsheraldry;

import com.knightsheraldry.client.*;
import com.knightsheraldry.config.ModConfigs;
import com.knightsheraldry.datagen.*;
import com.knightsheraldry.event.*;
import com.knightsheraldry.items.*;
import com.knightsheraldry.networking.ModMessages;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.*;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.slf4j.*;

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
        AttackEntityCallback.EVENT.register(new AttackEntityEventHandler());
    }

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new StaminaOverlay());
        HudRenderCallback.EVENT.register(new DistanceCrosshairOverlay());
        ModMessages.registerS2CPackets();
        ClientPreAttackCallback.EVENT.register(new AttackCancelHandler());
        registerModelPredicate(ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD,
                ModItems.ARMING_SWORD, ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE,
                ModItems.WARSWORD_ZWEIHANDER);
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModItemTagProvider::new);
    }

    private void registerModelPredicate(Item... items) {
        for (Item item : items) {
            ModelPredicateProviderRegistry.register(item, new Identifier("blocking"),
                    (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
            );
        }
    }
}