package banduty.knightsheraldry.screen;

import banduty.knightsheraldry.items.item.manuscriptbook.IManuscriptBookHelper;
import banduty.knightsheraldry.items.item.manuscriptbook.ManuscriptBookItem;
import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.items.custom.Manuscript;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

import java.util.ArrayList;
import java.util.List;

public class ManuscriptBookScreenHandler extends AbstractContainerMenu {
    public static final int MAX_MANUSCRIPTS = 64;
    public static final int INSERT_X = 136, INSERT_Y = 46;
    public static final int HOTBAR_X = 17, HOTBAR_Y = 112;
    public static final int IMAGE_WIDTH = 196, IMAGE_HEIGHT = 136;

    public static final int INSERT_INDEX = MAX_MANUSCRIPTS;
    private static final int PLAYER_START = INSERT_INDEX + 1;

    private final ItemStack bookStack;
    private boolean loading = true;

    private final SimpleContainer manuscripts = new SimpleContainer(MAX_MANUSCRIPTS) {
        @Override
        public void setChanged() {
            super.setChanged();
            saveToStack();
        }
    };

    private final SimpleContainer insert = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            ManuscriptBookScreenHandler.this.slotsChanged(this);
        }
    };

    public ManuscriptBookScreenHandler(int id, Inventory inv) {
        this(id, inv, ItemStack.EMPTY);
    }

    // Cliente: el stack llega por el StreamCodec
    public ManuscriptBookScreenHandler(int syncId, Inventory inventory, IManuscriptBookHelper.ManuscriptBookData data) {
        this(syncId, inventory, data.stack());
    }

    // Servidor: pasa el stack REAL de la mano, no una copia
    public ManuscriptBookScreenHandler(int syncId, Inventory inventory, ItemStack itemStack) {
        super(Services.MANUSCRIPT_BOOK.manuscriptBookScreenHandler(), syncId);
        this.bookStack = itemStack;

        loadFromStack();

        // 0..63: manuscritos guardados (ocultos, solo sirven para sincronizar)
        for (int i = 0; i < MAX_MANUSCRIPTS; i++) {
            addSlot(new StoredSlot(manuscripts, i));
        }
        // 64: slot "+" de la página derecha
        addSlot(new InsertSlot(insert, 0, INSERT_X, INSERT_Y));

        // Hotbar (el slot con el libro queda bloqueado)
        for (int col = 0; col < 9; col++) {
            final int index = col;
            addSlot(new Slot(inventory, col, HOTBAR_X + col * 18, HOTBAR_Y) {
                private boolean isBook() {
                    return index == inventory.selected && getItem().getItem() instanceof ManuscriptBookItem;
                }
                @Override public boolean mayPickup(Player player) { return !isBook(); }
                @Override public boolean mayPlace(ItemStack stack) { return !isBook(); }
            });
        }
    }

    private void loadFromStack() {
        loading = true;
        if (!bookStack.isEmpty()) {
            List<ItemStack> list = bookStack
                    .getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
                    .nonEmptyStream().toList();
            for (int i = 0; i < list.size() && i < MAX_MANUSCRIPTS; i++) {
                manuscripts.setItem(i, list.get(i).copy());
            }
        }
        loading = false;
    }

    private void saveToStack() {
        if (loading || bookStack.isEmpty()) return;
        bookStack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(getStoredManuscripts()));
    }

    /** Manuscritos guardados, sin huecos. Válido en cliente y servidor. */
    public List<ItemStack> getStoredManuscripts() {
        List<ItemStack> list = new ArrayList<>();
        for (int i = 0; i < manuscripts.getContainerSize(); i++) {
            ItemStack s = manuscripts.getItem(i);
            if (!s.isEmpty()) list.add(s.copy());
        }
        return list;
    }

    /** Slot interno (0..MAX-1) del n-ésimo manuscrito guardado, o -1 si no existe. */
    public int getStoredSlotIndex(int n) {
        int count = 0;
        for (int i = 0; i < manuscripts.getContainerSize(); i++) {
            if (!manuscripts.getItem(i).isEmpty()) {
                if (count == n) return i;
                count++;
            }
        }
        return -1;
    }

    private boolean alreadyStored(ItemStack stack) {
        for (int i = 0; i < manuscripts.getContainerSize(); i++) {
            if (ItemStack.isSameItemSameComponents(manuscripts.getItem(i), stack)) return true;
        }
        return false;
    }

    private boolean hasFreeSlot() {
        for (int i = 0; i < manuscripts.getContainerSize(); i++) {
            if (manuscripts.getItem(i).isEmpty()) return true;
        }
        return false;
    }

    /** Lo que cae en el "+" pasa solo al libro. */
    @Override
    public void slotsChanged(Container container) {
        if (container == insert && !insert.getItem(0).isEmpty()) {
            ItemStack stack = insert.getItem(0);
            for (int i = 0; i < manuscripts.getContainerSize(); i++) {
                if (manuscripts.getItem(i).isEmpty()) {
                    manuscripts.setItem(i, stack.copyWithCount(1));
                    insert.setItem(0, ItemStack.EMPTY);
                    break;
                }
            }
        }
        super.slotsChanged(container);
    }

    /** El cliente envía el índice del slot interno; se ejecuta en el servidor. */
    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (id < 0 || id >= MAX_MANUSCRIPTS) return false;

        ItemStack stack = manuscripts.getItem(id);
        if (stack.isEmpty()) return false;

        ItemStack out = stack.copy();
        manuscripts.setItem(id, ItemStack.EMPTY); // dispara saveToStack()

        if (!player.getInventory().add(out)) {
            player.drop(out, false);
        }
        return true; // el servidor hace broadcastChanges()
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!player.level().isClientSide()) {
            clearContainer(player, insert);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index < MAX_MANUSCRIPTS) return ItemStack.EMPTY;

        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();

        if (index == INSERT_INDEX) {
            if (!moveItemStackTo(stack, PLAYER_START, this.slots.size(), true)) return ItemStack.EMPTY;
        } else {
            if (!moveItemStackTo(stack, INSERT_INDEX, INSERT_INDEX + 1, false)) return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY);
        else slot.setChanged();
        return original;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem().getItem() instanceof ManuscriptBookItem
                || player.getOffhandItem().getItem() instanceof ManuscriptBookItem;
    }

    public ItemStack getItemStack() {
        return bookStack;
    }

    /** Slot oculto: no se ve ni se puede tocar, pero se sincroniza. */
    private static class StoredSlot extends Slot {
        StoredSlot(Container container, int index) {
            super(container, index, -9999, -9999);
        }
        @Override public boolean isActive() { return false; }
        @Override public boolean mayPlace(ItemStack stack) { return false; }
        @Override public boolean mayPickup(Player player) { return false; }
    }

    /** Slot "+": solo manuscritos nuevos y mientras quede sitio. */
    private class InsertSlot extends Slot {
        InsertSlot(Container container, int index, int x, int y) {
            super(container, index, x, y);
        }
        @Override
        public boolean mayPlace(ItemStack stack) {
            return stack.getItem() instanceof Manuscript && hasFreeSlot() && !alreadyStored(stack);
        }
        @Override
        public int getMaxStackSize() {
            return 1;
        }
    }
}