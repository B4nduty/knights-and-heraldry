package banduty.knightsheraldry.screen;

import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.recipes.CraftmanAnvilRecipe;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ManuscriptBookScreen extends AbstractContainerScreen<ManuscriptBookScreenHandler> {
    // Layout de la página izquierda (relativo a leftPos/topPos)
    private static final int PAGE_W = 128;
    private static final int RESULT_X = 48, RESULT_Y = 30;      // ítem resultante (x2)
    private static final int TAKE_X = 88, TAKE_Y = 38;          // botón Take
    private static final int CRAFT_X = 8, CRAFT_Y = 76;         // rejilla 3x2
    private static final int RELATED_X = 70, RELATED_Y = 76;    // 2x2
    private static final int MAX_RELATED = 4;

    private static final int TEXT = 0xFF3B2A14, MUTED = 0xFF7A6548;

    private record Clickable(int x, int y, Item manuscript) {}

    private ManuscriptRecipeIndex index;
    private int selected = 0;
    private Item currentManuscript = null;
    private List<ManuscriptRecipeIndex.Entry> currentRecipes = List.of();
    private List<Item> currentRelated = List.of();
    private Button prevButton, nextButton, takeButton;
    private final List<Clickable> clickables = new ArrayList<>();
    private ItemStack hoveredStack = ItemStack.EMPTY;

    public ManuscriptBookScreen(ManuscriptBookScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        this.imageWidth = ManuscriptBookScreenHandler.IMAGE_WIDTH;
        this.imageHeight = ManuscriptBookScreenHandler.IMAGE_HEIGHT;
        this.inventoryLabelY = 1000;
        this.titleLabelY = 1000;
    }

    public int getX() { return this.leftPos; }
    public int getY() { return this.topPos; }
    public Font getTextRenderer() { return this.font; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

    @Override
    protected void init() {
        super.init();
        if (this.minecraft != null && this.minecraft.level != null) {
            this.index = ManuscriptRecipeIndex.build(this.minecraft.level);
        }
        this.currentManuscript = null; // fuerza recalcular
        prevButton = addRenderableWidget(Button.builder(Component.literal("<"), b -> selected--)
                .bounds(leftPos + 4, topPos + 4, 14, 14).build());
        nextButton = addRenderableWidget(Button.builder(Component.literal(">"), b -> selected++)
                .bounds(leftPos + PAGE_W - 18, topPos + 4, 14, 14).build());
        takeButton = addRenderableWidget(Button.builder(Component.literal("Take"), b -> takeSelected())
                .bounds(leftPos + TAKE_X, topPos + TAKE_Y, 34, 14)
                .tooltip(Tooltip.create(Component.literal("Take this manuscript out of the book")))
                .build());
    }

    private void refreshSelection(List<ItemStack> stored) {
        if (stored.isEmpty()) {
            selected = 0;
            currentManuscript = null;
            currentRecipes = List.of();
            currentRelated = List.of();
        } else {
            selected = Mth.clamp(selected, 0, stored.size() - 1);
            Item item = stored.get(selected).getItem();
            if (item != currentManuscript && index != null) {
                currentManuscript = item;
                currentRecipes = index.recipesUsing(item);
                currentRelated = index.related(item);
            }
        }
        takeButton.visible = !stored.isEmpty();
        prevButton.active = selected > 0;
        nextButton.active = selected < stored.size() - 1;
    }

    private void takeSelected() {
        if (currentManuscript == null || this.minecraft == null || this.minecraft.gameMode == null) return;
        int slot = this.menu.getStoredSlotIndex(selected);
        if (slot >= 0) {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, slot);
        }
    }

    @Override
    protected void renderBg(GuiGraphics g, float delta, int mouseX, int mouseY) {
        // Intentionally empty: draw your own textures here (book, "+" slot, inventory).
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        refreshSelection(this.menu.getStoredManuscripts());
        super.render(g, mouseX, mouseY, delta); // fondo, widgets, slots y tooltip de slots
        renderPage(g, mouseX, mouseY);
        Services.MANUSCRIPT_BOOK.renderManuscriptBookEvents(g, mouseX, mouseY, delta, this);
    }

    private void renderPage(GuiGraphics g, int mx, int my) {
        int x = leftPos, y = topPos;
        hoveredStack = ItemStack.EMPTY;
        clickables.clear();

        if (currentManuscript == null) {
            g.drawWordWrap(font, Component.literal("The book is empty. Add a manuscript on the right page."),
                    x + 12, y + 50, PAGE_W - 24, MUTED);
            return;
        }

        List<ItemStack> stored = menu.getStoredManuscripts();

        // Título + contador
        String name = font.plainSubstrByWidth(new ItemStack(currentManuscript).getHoverName().getString(), PAGE_W - 44);
        g.drawString(font, name, x + PAGE_W / 2 - font.width(name) / 2, y + 8, TEXT, false);
        String counter = (selected + 1) + "/" + stored.size();
        g.drawString(font, counter, x + PAGE_W / 2 - font.width(counter) / 2, y + 20, MUTED, false);

        if (currentRecipes.isEmpty()) {
            g.drawWordWrap(font, Component.literal("No recipes use this manuscript."),
                    x + 12, y + 60, PAGE_W - 24, MUTED);
            return;
        }

        long tick = Util.getMillis() / 1200L;
        ManuscriptRecipeIndex.Entry entry = currentRecipes.get((int) (tick % currentRecipes.size()));
        CraftmanAnvilRecipe recipe = entry.recipe();

        // Ítem resultante (x2) + golpes y probabilidad
        drawBigItem(g, recipe.output(), x + RESULT_X, y + RESULT_Y, mx, my);
        g.drawString(font, recipe.hitTimes() + " hits", x + 8, y + 34, TEXT, false);
        if (recipe.chance() < 1.0f) {
            g.drawString(font, Math.round(recipe.chance() * 100) + "%", x + 8, y + 44, TEXT, false);
        }

        // How to craft
        List<List<ItemStack>> cells = ManuscriptRecipeIndex.gridCells(recipe);
        for (int i = 0; i < CraftmanAnvilRecipe.GRID_SIZE; i++) {
            int cx = x + CRAFT_X + (i % CraftmanAnvilRecipe.GRID_WIDTH) * 18;
            int cy = y + CRAFT_Y + (i / CraftmanAnvilRecipe.GRID_WIDTH) * 18;
            List<ItemStack> options = cells.get(i);
            if (!options.isEmpty()) {
                drawItem(g, options.get((int) (tick % options.size())), cx + 1, cy + 1, mx, my);
            }
        }

        // Related manuscripts
        if (!currentRelated.isEmpty()) {
            int shown = Math.min(MAX_RELATED, currentRelated.size());
            for (int i = 0; i < shown; i++) {
                Item related = currentRelated.get(i);
                int rx = x + RELATED_X + (i % 2) * 18 + 1;
                int ry = y + RELATED_Y + (i / 2) * 18 + 1;
                drawItem(g, new ItemStack(related), rx, ry, mx, my);

                boolean inBook = stored.stream().anyMatch(s -> s.getItem() == related);
                if (inBook) {
                    clickables.add(new Clickable(rx, ry, related));
                } else {
                    g.fill(rx, ry, rx + 16, ry + 16, 0x99000000); // no está en el libro
                }
            }
            if (currentRelated.size() > shown) {
                g.drawString(font, "+" + (currentRelated.size() - shown), x + RELATED_X + 40, y + RELATED_Y + 12, MUTED, false);
            }
        }

        if (!hoveredStack.isEmpty()) {
            g.renderTooltip(font, hoveredStack, mx, my);
        }
    }

    private void drawItem(GuiGraphics g, ItemStack stack, int px, int py, int mx, int my) {
        g.renderItem(stack, px, py);
        if (mx >= px && mx < px + 16 && my >= py && my < py + 16) hoveredStack = stack;
    }

    private void drawBigItem(GuiGraphics g, ItemStack stack, int px, int py, int mx, int my) {
        g.pose().pushPose();
        g.pose().translate(px, py, 0);
        g.pose().scale(2f, 2f, 1f);
        g.renderItem(stack, 0, 0);
        g.pose().popPose();
        if (mx >= px && mx < px + 32 && my >= py && my < py + 32) hoveredStack = stack;
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        if (button == 0) {
            for (Clickable c : clickables) {
                if (mx >= c.x() && mx < c.x() + 16 && my >= c.y() && my < c.y() + 16) {
                    List<ItemStack> stored = menu.getStoredManuscripts();
                    for (int i = 0; i < stored.size(); i++) {
                        if (stored.get(i).getItem() == c.manuscript()) {
                            selected = i;
                            return true;
                        }
                    }
                }
            }
        }
        return super.mouseClicked(mx, my, button);
    }
}