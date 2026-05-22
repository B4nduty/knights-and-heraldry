package banduty.knightsheraldry.items.armor.accessory;

import banduty.knightsheraldry.client.item.armor.KHCloakAccessoryRenderer;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.items.custom.armor.SCAccessory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class KHCloak extends Item implements SCAccessory, AccessoryRenderProvider {
    boolean hasOverlay;
    int numberSlot;

    public AccessoryRenderer cachedRenderer;

    public KHCloak(Properties properties, int numberSlot) {
        super(properties);
        this.hasOverlay = false;
        this.numberSlot = numberSlot;
    }

    public KHCloak(Properties properties, int numberSlot, boolean hasOverlay) {
        super(properties);
        this.hasOverlay = hasOverlay;
        this.numberSlot = numberSlot;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Override
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.HELMET;
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHCloakAccessoryRenderer();
        }
        return this.cachedRenderer;
    }

    public int getDefaultColor() {
        return 0xFFA06440;
    }

    @Override
    public boolean canEquip(ItemStack stack, Player player) {
        if (this != KHItems.HELMET_HOOD.get() || this != KHItems.HELMET_TORN_HOOD.get()) return SCAccessory.super.canEquip(stack, player);
        for (ItemStack itemStack : player.getArmorSlots()) {
            if (itemStack.getItem() instanceof KHHelmetAccessory) return true;
        }
        return false;
    }

    @Override
    public @Range(from = 0L, to = 2147483647L) int numberSlot() {
        return numberSlot;
    }
}
