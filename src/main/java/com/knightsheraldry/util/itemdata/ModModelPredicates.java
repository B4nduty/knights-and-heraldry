package com.knightsheraldry.util.itemdata;

import com.knightsheraldry.items.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class ModModelPredicates {
    public static void registerModelPredicates(Item item) {
        registerEasterEggPredicates(item);
        registerArmorPredicates(item);
        registerWeaponPredicates(item);
        registerBowPredicates(item);
    }

    private static void registerBowPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        ModelPredicateProviderRegistry.register(item, new Identifier("pulling"), (stack, world, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
    }

    private static void registerEasterEggPredicates(Item item) {
        if (item == ModItems.LONGBOW) ModelPredicateProviderRegistry.register(item, new Identifier("longbow_xxxl"), (stack, world, entity, seed) ->
                Objects.equals(stack.getName(), Text.translatable("item.knightsheraldry.easter_egg.longbow_xxxl")) ? 1.0F : 0.0F);
    }

    private static void registerArmorPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("aventail"),
                (stack, world, entity, seed) -> stack.getOrCreateNbt() != null
                        && stack.getOrCreateNbt().getBoolean("aventail") ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(item, new Identifier("rimmed"),
                (stack, world, entity, seed) -> stack.getOrCreateNbt() != null
                        && stack.getOrCreateNbt().getBoolean("rimmed") ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(item, new Identifier("besagews"),
                (stack, world, entity, seed) -> stack.getOrCreateNbt() != null
                        && stack.getOrCreateNbt().getBoolean("besagews") ? 1.0F : 0.0F);
    }

    private static void registerWeaponPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("charged"),
                (stack, world, entity, seed) -> entity != null
                        && (entity.getMainHandStack() == stack || entity.getOffHandStack() == stack)
                        && stack.getOrCreateNbt() != null
                        && stack.getOrCreateNbt().getBoolean("Charged") ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(item, new Identifier("blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);

        ModelPredicateProviderRegistry.register(item, new Identifier("bludgeoning"),
                (stack, world, entity, seed) -> stack.getOrCreateNbt() != null
                        && stack.getOrCreateNbt().getBoolean("Bludgeoning") ? 1.0F : 0.0F);
    }
}
