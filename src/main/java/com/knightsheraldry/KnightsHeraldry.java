package com.knightsheraldry;

import com.knightsheraldry.client.*;
import com.knightsheraldry.config.KHConfig;
import com.knightsheraldry.datagen.*;
import com.knightsheraldry.effect.ModEffects;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.event.*;
import com.knightsheraldry.items.*;
import com.knightsheraldry.items.custom.item.KHWeapons;
import com.knightsheraldry.networking.ModMessages;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.*;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.*;
import software.bernie.geckolib.GeckoLib;

import java.util.List;

public class KnightsHeraldry implements ModInitializer, ClientModInitializer, DataGeneratorEntrypoint {
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
                        §4Knights & Heraldry §ris in §bAlpha \s
                        §fMany things you see here can change in future updates.
                        Thanks for playing this mod,\s
                        §4The Knights Heraldry Team"""), false);
            }
        });
    }

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        ClientPreAttackCallback.EVENT.register(new AttackCancelHandler());
        registerModelPredicate(ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.AXE,
                ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE, ModItems.MACE,
                ModItems.SPIKED_MACE, ModItems.HAMMER, ModItems.WAR_HAMMER, ModItems.LONGSWORD, ModItems.V_LONGSWORD,
                ModItems.FALCHION, ModItems.SCIMITAR, ModItems.KATANA, ModItems.PITCHFORK, ModItems.SPEAR, ModItems.PIKE,
                ModItems.BILLHOOK, ModItems.GLAIVE, ModItems.CURVED_GLAIVE, ModItems.HALBERD, ModItems.LANCE,
                ModItems.WOODEN_LANCE, ModItems.POLEAXE, ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN,
                ModItems.MORNING_STAR, ModItems.BARDICHE, ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE,
                ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER, ModItems.WARDART);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), ModItems.WOODEN_LANCE,
                ModItems.QUILTED_COIF, ModItems.GAMBESON, ModItems.GAMBESON_BREECHES, ModItems.GAMBESON_BOOTS);

        EntityRendererRegistry.register(ModEntities.WARDART_PROJECTILE, WarDartRenderer::new);
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            removeAttackDamage(stack, lines);
        });
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModModelProvider::new);
    }

    private void registerModelPredicate(Item... items) {
        for (Item item : items) {
            ModelPredicateProviderRegistry.register(item, new Identifier("charged"),
                    (stack, world, entity, seed) -> entity != null
                            && (entity.getMainHandStack() == stack || entity.getOffHandStack() == stack)
                            && stack.getNbt() != null
                            && stack.getNbt().getBoolean("Charged") ? 1.0F : 0.0F);
            ModelPredicateProviderRegistry.register(item, new Identifier("blocking"),
                    (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
        }
    }

    private void removeAttackDamage(ItemStack itemStack, List<Text> tooltip) {
        if (itemStack.getItem() instanceof KHWeapons || itemStack.getItem() == ModItems.LANCE
                || itemStack.getItem() == ModItems.WOODEN_LANCE)
            tooltip.removeIf(line -> line.getString().contains("Attack Damage"));
    }

    public static KHConfig config() {
        return CONFIG;
    }
}