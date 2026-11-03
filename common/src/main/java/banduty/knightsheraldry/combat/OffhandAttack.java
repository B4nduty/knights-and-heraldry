package banduty.knightsheraldry.combat;

import banduty.knightsheraldry.networking.payload.OffhandAttackC2SPacket;
import banduty.knightsheraldry.platform.Services;
import banduty.knightsheraldry.util.itemdata.KHTags;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Vanilla (non Better Combat) dual wielding: when both hands hold a {@code knightsheraldry:dual_wieldable}
 * weapon, attacks alternate between the main hand and the offhand.
 */
public final class OffhandAttack {
    private OffhandAttack() {}

    private static Boolean betterCombatLoaded;

    private static boolean betterCombatLoaded() {
        if (betterCombatLoaded == null) betterCombatLoaded = Services.PLATFORM.isModLoaded("bettercombat");
        return betterCombatLoaded;
    }

    /** True when the player should alternate attacks between both hands under vanilla combat. */
    public static boolean canDualWield(Player player) {
        return !betterCombatLoaded()
                && !player.isSpectator()
                && player.getMainHandItem().is(KHTags.DUAL_WIELDABLE.getTag())
                && player.getOffhandItem().is(KHTags.DUAL_WIELDABLE.getTag());
    }

    /** Server-side handling of an offhand attack, mirroring the checks vanilla applies to attack packets. */
    public static void handle(ServerPlayer player, OffhandAttackC2SPacket packet) {
        ServerLevel level = player.serverLevel();
        Entity target = level.getEntity(packet.entityId());
        player.resetLastActionTime();
        player.setShiftKeyDown(packet.sneaking());

        if (!canDualWield(player) || target == null || target == player) return;
        if (!level.getWorldBorder().isWithinBounds(target.blockPosition())) return;
        if (!player.canInteractWithEntity(target.getBoundingBox(), 1.0)) return;
        if (target instanceof ItemEntity || target instanceof ExperienceOrb
                || (target instanceof AbstractArrow arrow && !arrow.isAttackable())) return;

        attackWithOffhand(player, target);
    }

    /**
     * Temporarily swaps the hands (items and their attribute modifiers) so the vanilla attack logic uses the
     * offhand weapon's damage, enchantments and durability, then swaps back.
     */
    private static void attackWithOffhand(Player player, Entity target) {
        Inventory inventory = player.getInventory();
        int selected = inventory.selected;
        AttributeMap attributes = player.getAttributes();

        ItemStack main = inventory.items.get(selected);
        ItemStack off = inventory.offhand.get(0);

        List<Modifier> mainAsMain = collect(main, EquipmentSlot.MAINHAND);
        List<Modifier> offAsOff = collect(off, EquipmentSlot.OFFHAND);
        List<Modifier> offAsMain = collect(off, EquipmentSlot.MAINHAND);
        List<Modifier> mainAsOff = collect(main, EquipmentSlot.OFFHAND);

        remove(attributes, mainAsMain);
        remove(attributes, offAsOff);
        inventory.items.set(selected, off);
        inventory.offhand.set(0, main);
        add(attributes, offAsMain);
        add(attributes, mainAsOff);

        try {
            player.attack(target);
        } finally {
            // The offhand weapon may have broken during the attack, so read the slots back rather than reuse `off`.
            ItemStack usedOff = inventory.items.get(selected);
            ItemStack heldMain = inventory.offhand.get(0);

            remove(attributes, offAsMain);
            remove(attributes, mainAsOff);
            inventory.items.set(selected, heldMain);
            inventory.offhand.set(0, usedOff);
            if (!heldMain.isEmpty()) add(attributes, mainAsMain);
            if (!usedOff.isEmpty()) add(attributes, offAsOff);
        }
    }

    private record Modifier(Holder<Attribute> attribute, AttributeModifier modifier) {}

    private static List<Modifier> collect(ItemStack stack, EquipmentSlot slot) {
        List<Modifier> list = new ArrayList<>();
        if (!stack.isEmpty()) stack.forEachModifier(slot, (attribute, modifier) -> list.add(new Modifier(attribute, modifier)));
        return list;
    }

    private static void remove(AttributeMap attributes, List<Modifier> modifiers) {
        for (Modifier m : modifiers) {
            AttributeInstance instance = attributes.getInstance(m.attribute());
            if (instance != null) instance.removeModifier(m.modifier().id());
        }
    }

    private static void add(AttributeMap attributes, List<Modifier> modifiers) {
        for (Modifier m : modifiers) {
            AttributeInstance instance = attributes.getInstance(m.attribute());
            if (instance != null) {
                instance.removeModifier(m.modifier().id());
                instance.addTransientModifier(m.modifier());
            }
        }
    }
}
