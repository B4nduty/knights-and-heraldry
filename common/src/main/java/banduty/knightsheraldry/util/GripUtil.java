package banduty.knightsheraldry.util;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.knightsheraldry.util.itemdata.KHTags;
import banduty.stoneycore.data.SCDataComponents;
import banduty.stoneycore.definitions.WeaponDefinitionsStorage;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.Map;

/**
 * Weapons in the {@code knightsheraldry:versatile} tag (estoc, bastard sword) can be switched between a
 * one-handed and a two-handed grip, stored per stack in {@link KHDataComponents#TWO_HANDED_GRIP}.
 * <p>
 * In the two-handed grip:
 * <ul>
 *     <li>Better Combat uses {@code weapon_attributes/<item>_two_handed.json} (see {@code WeaponRegistryMixin}).</li>
 *     <li>If a StoneyCore definition {@code definitions/weapon/<item>_two_handed.json} exists, the stack uses it for
 *     damage and reach through StoneyCore's {@code weapon_definition} component.</li>
 *     <li>Weapons listed in {@link #TWO_HANDED_ATTACK_SPEED} get that attack speed.</li>
 * </ul>
 */
public final class GripUtil {
    private GripUtil() {}

    /** Item#BASE_ATTACK_SPEED_ID, which is protected. */
    private static final ResourceLocation BASE_ATTACK_SPEED_ID = ResourceLocation.withDefaultNamespace("base_attack_speed");

    /** Attack speed modifier (added to the base 4.0) while held two-handed. */
    private static final Map<ResourceLocation, Double> TWO_HANDED_ATTACK_SPEED = Map.of(
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "bastard_sword"), -2.475,
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "gilded_bastard_sword"), -2.475
    );

    public static boolean isVersatile(ItemStack stack) {
        return stack.is(KHTags.VERSATILE.getTag());
    }

    public static boolean isTwoHandedGrip(ItemStack stack) {
        return isVersatile(stack) && Boolean.TRUE.equals(stack.get(KHDataComponents.TWO_HANDED_GRIP.get()));
    }

    /** Id of an item's two-handed variant files, e.g. {@code knightsheraldry:bastard_sword_two_handed}. */
    public static ResourceLocation twoHandedId(Item item) {
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
        return ResourceLocation.fromNamespaceAndPath(key.getNamespace(), key.getPath() + "_two_handed");
    }

    /** Server-side: switches the grip of the versatile weapon in the player's main hand. */
    public static void toggle(ServerPlayer player) {
        ItemStack stack = player.getMainHandItem();
        if (!isVersatile(stack)) return;

        boolean twoHanded = !isTwoHandedGrip(stack);
        setGrip(stack, twoHanded);

        player.displayClientMessage(Component.translatable(twoHanded
                ? "text.knightsheraldry.grip.two_handed" : "text.knightsheraldry.grip.one_handed"), true);
    }

    public static void setGrip(ItemStack stack, boolean twoHanded) {
        Item item = stack.getItem();
        ResourceLocation twoHandedId = twoHandedId(item);

        if (twoHanded) {
            stack.set(KHDataComponents.TWO_HANDED_GRIP.get(), true);
            if (WeaponDefinitionsStorage.getDefinitions().containsKey(twoHandedId)) {
                stack.set(SCDataComponents.WEAPON_DEFINITION.get(), twoHandedId);
            }
        } else {
            stack.remove(KHDataComponents.TWO_HANDED_GRIP.get());
            if (twoHandedId.equals(stack.get(SCDataComponents.WEAPON_DEFINITION.get()))) {
                stack.remove(SCDataComponents.WEAPON_DEFINITION.get());
            }
        }

        Double twoHandedSpeed = TWO_HANDED_ATTACK_SPEED.get(BuiltInRegistries.ITEM.getKey(item));
        if (twoHandedSpeed != null) {
            Double speed = twoHanded ? twoHandedSpeed : defaultAttackSpeed(item);
            if (speed != null) setAttackSpeed(stack, speed);
        }
    }

    private static Double defaultAttackSpeed(Item item) {
        ItemAttributeModifiers defaults = item.components()
                .getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        for (ItemAttributeModifiers.Entry entry : defaults.modifiers()) {
            if (entry.matches(Attributes.ATTACK_SPEED, BASE_ATTACK_SPEED_ID)) return entry.modifier().amount();
        }
        return null;
    }

    /**
     * Replaces the base attack speed modifier in place, keeping the other modifiers and their order. Setting the
     * item's default speed back yields the item's default component, which then drops out of the stack's patch.
     */
    private static void setAttackSpeed(ItemStack stack, double amount) {
        ItemAttributeModifiers current = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        ItemAttributeModifiers updated = new ItemAttributeModifiers(current.modifiers().stream()
                .map(entry -> entry.matches(Attributes.ATTACK_SPEED, BASE_ATTACK_SPEED_ID)
                        ? new ItemAttributeModifiers.Entry(entry.attribute(),
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, amount, entry.modifier().operation()), entry.slot())
                        : entry)
                .toList(), current.showInTooltip());
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, updated);
    }
}
