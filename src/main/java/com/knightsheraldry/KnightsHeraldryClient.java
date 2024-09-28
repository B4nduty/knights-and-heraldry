package com.knightsheraldry;

import com.knightsheraldry.client.UnderArmourRenderer;
import com.knightsheraldry.client.WarDartRenderer;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.event.AttackCancelHandler;
import com.knightsheraldry.event.ItemTooltipHandler;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.armor.KHArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.networking.ModMessages;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class KnightsHeraldryClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        ClientPreAttackCallback.EVENT.register(new AttackCancelHandler());

        EntityRendererRegistry.register(ModEntities.WARDART_PROJECTILE, WarDartRenderer::new);
        ItemTooltipCallback.EVENT.register(new ItemTooltipHandler());

        ModItems.items.forEach(item -> {
            registerModelPredicate(item);
            if ((item instanceof KHTrinketsItem khTrinketsItem && khTrinketsItem.isDyeable())
                    || (item instanceof KHArmorItem khArmorItem && khArmorItem.isDyeable())) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), item);
            }
            if (item instanceof KHTrinketsItem) {
                TrinketRendererRegistry.registerRenderer(item, (TrinketRenderer) item);
            }
            if (item instanceof KHArmorItem khArmorItem && khArmorItem.getPath() != null) {
                ArmorRenderer.register(new UnderArmourRenderer(), item);
            }
        });
    }

    private void registerModelPredicate(Item... items) {
        for (Item item : items) {
            ModelPredicateProviderRegistry.register(item, new Identifier("charged"),
                    (stack, world, entity, seed) -> entity != null
                            && (entity.getMainHandStack() == stack || entity.getOffHandStack() == stack)
                            && stack.getOrCreateNbt() != null
                            && stack.getOrCreateNbt().getBoolean("Charged") ? 1.0F : 0.0F);
            ModelPredicateProviderRegistry.register(item, new Identifier("blocking"),
                    (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
            ModelPredicateProviderRegistry.register(item, new Identifier("aventail"),
                    (stack, world, entity, seed) -> stack.getOrCreateNbt() != null
                            && stack.getOrCreateNbt().getBoolean("aventail") ? 1.0F : 0.0F);
        }
    }
}