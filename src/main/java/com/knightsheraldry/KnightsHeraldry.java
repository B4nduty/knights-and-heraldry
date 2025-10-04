package com.knightsheraldry;

import banduty.stoneycore.event.custom.CraftingPreviewCallback;
import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import com.knightsheraldry.config.KHConfig;
import com.knightsheraldry.data.ArrowBehaviorManager;
import com.knightsheraldry.effect.ModEffects;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.event.*;
import com.knightsheraldry.items.ModItemGroups;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.sounds.ModSounds;
import com.knightsheraldry.util.loottable.VillagerTradesModifier;
import dev.architectury.event.events.common.LifecycleEvent;
import io.wispforest.accessories.api.events.CanEquipCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnightsHeraldry implements ModInitializer {
    public static final String MOD_ID = "knightsheraldry";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final KHConfig CONFIG = KHConfig.createAndLoad();
    private static final String FIRST_JOIN_TAG = MOD_ID + ":first_join";

    @Override
    public void onInitialize() {
        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModEffects.registerEffects();
        ModItems.registerItems();
        LifecycleEvent.SETUP.register(() -> {
            ModItemGroups.registerItemGroups();
            VillagerTradesModifier.registerCustomTrades();
        });
        ModMessages.registerC2SPackets();
        CanEquipCallback.EVENT.register(new CanEquipHandler());
        ServerTickEvents.START_SERVER_TICK.register(new StartTickHandler());
        UseItemCallback.EVENT.register(new UseItemHandler());
        CraftingPreviewCallback.EVENT.register(new CraftingPreviewHandler());
        ArrowBehaviorManager.register();
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            if (player != null) {
                NbtCompound playerData = ((IEntityDataSaver) player).stoneycore$getPersistentData();
                if (!playerData.getBoolean(FIRST_JOIN_TAG)) {
                    player.sendMessage(Text.literal("""
                                            §4Knights & Heraldry §ris in §6Beta
                                            §fMany things you see here can change in future updates.
                                            
                                            If you want to help improving this mod,
                                            send your feedback in the KH Discord Server.
                                            
                                            """)
                                    .append(Text.literal("§n§9Click here to join the KH Discord Server")
                                            .styled(style -> style
                                                    .withColor(Formatting.BLUE)
                                                    .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/NvXG4ZWFXc"))))
                                    .append(Text.literal("""
                                            
                                            Thanks for playing this mod,
                                            §4The Knights Heraldry Team""")),
                            false);

                    playerData.putBoolean(FIRST_JOIN_TAG, true);
                }
            }
        });
    }

    public static KHConfig getConfig() {
        return CONFIG;
    }
}