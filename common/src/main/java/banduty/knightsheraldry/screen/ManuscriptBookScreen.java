package banduty.knightsheraldry.screen;

import banduty.knightsheraldry.KnightsHeraldry; // adjust the package if it's different
import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.items.custom.Manuscript;
import banduty.stoneycore.recipes.CraftmanAnvilRecipe;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ManuscriptBookScreen extends AbstractContainerScreen<ManuscriptBookScreenHandler> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            KnightsHeraldry.MOD_ID, "textures/gui/manuscript_book/manuscript_book.png");

    // Pages in the texture: left x 8..92, right x 102..186, both y 4..104 (relative to leftPos/topPos)
    private static final int LEFT_X = 8, RIGHT_X = 102, PAGE_W = 84;
    private static final int RESULT_X = 12, RESULT_Y = 30;        // result item (x2) inside a 34x34 frame
    private static final int TAKE_X = 49, TAKE_Y = 51;            // hits/chance text and Take button
    private static final int CRAFT_X = 23, CRAFT_Y = 67;          // 3x2 grid (54x36)

    // Crafting tree (right page, under the header)
    private static final int TREE_X = 104, TREE_Y = 31, TREE_W = 80;
    private static final int ROW_H = 14, VIEW_ROWS = 5, INDENT = 8;

    private static final float TS = 0.75f;      // default text scale for everything drawn on the pages
    private static final float MIN_TS = 0.55f;  // long names shrink down to this before being cut with "..."

    // Palette
    private static final int TEXT = 0xFF3B2A14, MUTED = 0xFF7A6548, LINE = 0x887A6548;
    private static final int FRAME_BORDER = 0xFF8C7650, FRAME_INNER = 0xFFEFE0BA, FRAME_SHADE = 0xFFD9C79C;
    private static final int ACCENT_BORDER = 0xFF5E442A;
    private static final int READY_BORDER = 0xFF4E7A3A;           // green: you can craft this now
    private static final int MANUSCRIPT_BORDER = 0xFFB8862B;      // gold: the manuscript itself
    private static final int PREVIOUS_BORDER = 0xFF3E6E8E;        // blue: made in the previous step of the tree

    private record Clickable(int x, int y, Item manuscript) {}

    /** One visible line of the tree. hasNext.get(k) = does the ancestor at depth k+1 have a later sibling. */
    private record Row(ManuscriptRecipeIndex.TreeNode node, List<Boolean> hasNext) {
        int depth() { return hasNext.size(); }
    }

    private ManuscriptRecipeIndex index;
    private int selected = 0;
    private Item currentManuscript = null;
    private List<ManuscriptRecipeIndex.Entry> currentRecipes = List.of();
    private List<Row> rows = List.of();          // flattened tree, row 0 = the manuscript itself
    private int selectedRow = 0, treeScroll = 0;
    private List<ItemStack> storedNow = List.of();
    private Button prevButton, nextButton, takeButton;
    private final List<Clickable> clickables = new ArrayList<>();
    private final List<Component> hoverLines = new ArrayList<>();

    // Recipe selection / page-turn state
    private int pinnedRecipe = -1;          // -1 = auto-cycle, otherwise the recipe the player picked
    private int shownRecipeIdx = 0;
    private long switchTime = 0;            // when the manuscript last changed (page-turn fade)
    private int dotsX = -1, dotsY = -1;     // recipe dots, for clicking
    private int resultBoxX = -1, resultBoxY = -1;

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
        this.currentManuscript = null; // force recompute
        prevButton = addRenderableWidget(new PageButton(leftPos + LEFT_X + 1, topPos + 6, 11, 12,
                Component.literal("<"), true, b -> selected -= hasShiftDown() ? 5 : 1));
        nextButton = addRenderableWidget(new PageButton(leftPos + LEFT_X + PAGE_W - 12, topPos + 6, 11, 12,
                Component.literal(">"), true, b -> selected += hasShiftDown() ? 5 : 1));
        takeButton = addRenderableWidget(new PageButton(leftPos + TAKE_X, topPos + TAKE_Y, 34, 12,
                Component.literal("Take"), false, b -> takeSelected()));
        prevButton.setTooltip(Tooltip.create(Component.literal("Previous manuscript (Shift: skip 5)")));
        nextButton.setTooltip(Tooltip.create(Component.literal("Next manuscript (Shift: skip 5)")));
        takeButton.setTooltip(Tooltip.create(Component.literal("Take this manuscript out of the book")));
    }

    private void refreshSelection(List<ItemStack> stored) {
        if (stored.isEmpty()) {
            selected = 0;
            currentManuscript = null;
            currentRecipes = List.of();
            rows = List.of();
            selectedRow = 0;
        } else {
            selected = Mth.clamp(selected, 0, stored.size() - 1);
            Item item = stored.get(selected).getItem();
            if (item != currentManuscript && index != null) {
                if (currentManuscript != null) playPageTurn();
                currentManuscript = item;
                buildRows(item);
            }
        }
        takeButton.visible = !stored.isEmpty();
        prevButton.active = selected > 0;
        nextButton.active = selected < stored.size() - 1;
    }

    private void buildRows(Item manuscript) {
        ManuscriptRecipeIndex.TreeNode root = index.tree(manuscript);
        List<Row> list = new ArrayList<>();
        list.add(new Row(root, List.of()));
        flatten(root.children(), List.of(), list);
        rows = list;
        treeScroll = 0;
        selectRow(list.size() > 1 ? 1 : 0, false);
    }

    private void flatten(List<ManuscriptRecipeIndex.TreeNode> nodes, List<Boolean> parentPath, List<Row> out) {
        for (int i = 0; i < nodes.size(); i++) {
            ManuscriptRecipeIndex.TreeNode node = nodes.get(i);
            List<Boolean> path = new ArrayList<>(parentPath);
            path.add(i < nodes.size() - 1);
            out.add(new Row(node, path));
            flatten(node.children(), path, out);
        }
    }

    /** Shows how to craft the item of the given tree row on the left page. */
    private void selectRow(int idx, boolean sound) {
        if (idx < 0 || idx >= rows.size()) return;
        if (sound && idx != selectedRow) playPageTurn();
        selectedRow = idx;
        pinnedRecipe = -1;
        switchTime = Util.getMillis();
        currentRecipes = idx > 0 ? rows.get(idx).node().recipes() : List.of();
        // keep the selected row inside the viewport
        if (idx < treeScroll) treeScroll = idx;
        if (idx >= treeScroll + VIEW_ROWS) treeScroll = idx - VIEW_ROWS + 1;
    }

    private void playPageTurn() {
        if (this.minecraft != null) {
            this.minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
        }
    }

    private void takeSelected() {
        if (currentManuscript == null || this.minecraft == null || this.minecraft.gameMode == null) return;
        int slot = this.menu.getStoredSlotIndex(selected);
        if (slot >= 0) {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, slot);
        }
    }

    // ---------------------------------------------------------------- helpers

    private static boolean inside(int mx, int my, int x, int y, int w, int h) {
        return mx >= x && mx < x + w && my >= y && my < y + h;
    }

    /** A filled rectangle drawn above items (items are rendered at z=150, plain fills at z=0). */
    private static void overlay(GuiGraphics g, int x, int y, int w, int h, int color) {
        g.pose().pushPose();
        g.pose().translate(0, 0, 200);
        g.fill(x, y, x + w, y + h, color);
        g.pose().popPose();
    }

    /** Inset parchment frame of the given size. */
    private static void drawFrame(GuiGraphics g, int x, int y, int size, int border) {
        g.fill(x, y, x + size, y + size, border);
        g.fill(x + 1, y + 1, x + size - 1, y + size - 1, FRAME_INNER);
        g.fill(x + 1, y + 1, x + size - 1, y + 2, FRAME_SHADE);
        g.fill(x + 1, y + 1, x + 2, y + size - 1, FRAME_SHADE);
    }

    /** 18x18 frame around the 16x16 item area whose top-left is (ix, iy). */
    private static void drawSlotFrame(GuiGraphics g, int ix, int iy, int border) {
        drawFrame(g, ix - 1, iy - 1, 18, border);
    }

    private static void drawDivider(GuiGraphics g, int cx, int y, int halfWidth) {
        g.fill(cx - halfWidth, y, cx - 4, y + 1, LINE);
        g.fill(cx + 4, y, cx + halfWidth, y + 1, LINE);
        g.fill(cx - 1, y, cx + 2, y + 1, MUTED);   // little diamond in the middle
        g.fill(cx, y - 1, cx + 1, y + 2, MUTED);
    }

    private int sw(String s) {
        return Mth.ceil(font.width(s) * TS);
    }

    private void drawScaled(GuiGraphics g, String s, float x, float y, float scale, int color) {
        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(scale, scale, 1f);
        g.drawString(font, s, 0, 0, color, false);
        g.pose().popPose();
    }

    private void drawText(GuiGraphics g, String s, int x, int y, int color) {
        drawScaled(g, s, x, y, TS, color);
    }

    private void drawCentered(GuiGraphics g, String s, int centerX, int y, int color) {
        drawText(g, s, centerX - sw(s) / 2, y, color);
    }

    /**
     * Draws text that always fits in maxW: it shrinks (down to MIN_TS) instead of being cut,
     * and is only shortened with "..." as a last resort. "inked" adds a faint highlight under it.
     */
    private void drawFitted(GuiGraphics g, String s, int x, int y, int maxW, int color,
                            boolean centered, boolean inked) {
        float scale = Math.min(TS, maxW / (float) Math.max(1, font.width(s)));
        if (scale < MIN_TS) {
            scale = MIN_TS;
            int room = (int) (maxW / scale) - font.width("...");
            s = font.plainSubstrByWidth(s, Math.max(room, 0)) + "...";
        }
        float w = font.width(s) * scale;
        int dx = Math.round(centered ? x - w / 2f : x);
        int dy = Math.round(y + (TS - scale) * 4.5f); // keep smaller text vertically centred
        if (inked) drawScaled(g, s, dx + 1, dy + 1, scale, 0x66FFF6DC);
        drawScaled(g, s, dx, dy, scale, color);
    }

    /** Page title: dark ink with a faint highlight underneath, like pressed ink. */
    private void drawTitle(GuiGraphics g, String s, int centerX, int y, int maxW) {
        drawFitted(g, s, centerX, y, maxW, TEXT, true, true);
    }

    /** Word-wrapped, centred, italic hint text. */
    private void drawHint(GuiGraphics g, String s, int centerX, int y, int width) {
        List<FormattedCharSequence> lines = font.split(
                Component.literal(s).withStyle(ChatFormatting.ITALIC), (int) (width / TS));
        g.pose().pushPose();
        g.pose().translate(centerX, y, 0);
        g.pose().scale(TS, TS, 1f);
        for (int i = 0; i < lines.size(); i++) {
            FormattedCharSequence line = lines.get(i);
            g.drawString(font, line, -font.width(line) / 2, i * 9, MUTED, false);
        }
        g.pose().popPose();
    }

    private int owned(Item item) {
        if (minecraft == null || minecraft.player == null) return 0;
        Inventory inv = minecraft.player.getInventory();
        int n = 0;
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack s = inv.getItem(i);
            if (s.is(item)) n += s.getCount();
        }
        return n;
    }

    private boolean inBook(Item item) {
        for (ItemStack s : storedNow) if (s.getItem() == item) return true;
        return false;
    }

    private boolean hasAny(List<ItemStack> options) {
        for (ItemStack o : options) {
            if (o.getItem() instanceof Manuscript) {
                if (inBook(o.getItem()) || owned(o.getItem()) >= 1) return true;
                continue;
            }
            if (owned(o.getItem()) >= Math.max(1, o.getCount())) return true;
        }
        return false;
    }

    /** A cell is fine if empty, if it is the item crafted in the previous step of the tree, or if the player has it. */
    private boolean cellOk(List<ItemStack> options, Item parent) {
        if (options.isEmpty()) return true;
        if (isFromParent(options, parent)) return true;
        return hasAny(options);
    }

    private static boolean isFromParent(List<ItemStack> options, Item parent) {
        if (parent == null) return false;
        for (ItemStack o : options) if (o.getItem() == parent && !(o.getItem() instanceof Manuscript)) return true;
        return false;
    }

    /** True when at least one recipe variant of this row can be crafted with what the player has. */
    private boolean isReady(Row row) {
        for (ManuscriptRecipeIndex.Entry e : row.node().recipes()) {
            boolean ok = true;
            for (List<ItemStack> options : ManuscriptRecipeIndex.gridCells(e.recipe())) {
                if (!cellOk(options, row.node().parent())) { ok = false; break; }
            }
            if (ok) return true;
        }
        return false;
    }

    private void drawSmallItem(GuiGraphics g, ItemStack stack, int px, int py) {
        g.pose().pushPose();
        g.pose().translate(px, py, 0);
        g.pose().scale(0.75f, 0.75f, 1f);
        g.renderItem(stack, 0, 0);
        g.pose().popPose();
    }

    private void setHover(ItemStack stack, Component... extra) {
        hoverLines.clear();
        if (this.minecraft != null) hoverLines.addAll(getTooltipFromItem(this.minecraft, stack));
        hoverLines.addAll(List.of(extra));
    }

    private void setHoverText(Component... lines) {
        hoverLines.clear();
        hoverLines.addAll(List.of(lines));
    }

    private void drawItem(GuiGraphics g, ItemStack stack, int px, int py, int mx, int my, Component... extra) {
        g.renderItem(stack, px, py);
        if (stack.getCount() > 1) g.renderItemDecorations(font, stack, px, py);
        if (inside(mx, my, px, py, 16, 16)) setHover(stack, extra);
    }

    private void drawBigItem(GuiGraphics g, ItemStack stack, int px, int py, int mx, int my, Component... extra) {
        g.pose().pushPose();
        g.pose().translate(px, py, 0);
        g.pose().scale(2f, 2f, 1f);
        g.renderItem(stack, 0, 0);
        if (stack.getCount() > 1) g.renderItemDecorations(font, stack, 0, 0);
        g.pose().popPose();
        if (inside(mx, my, px, py, 32, 32)) setHover(stack, extra);
    }

    // ---------------------------------------------------------------- rendering

    @Override
    protected void renderBg(GuiGraphics g, float delta, int mouseX, int mouseY) {
        g.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        int rightCenter = leftPos + RIGHT_X + PAGE_W / 2;
        boolean full = storedNow.size() >= ManuscriptBookScreenHandler.MAX_MANUSCRIPTS;
        boolean carryingManuscript = menu.getCarried().getItem() instanceof Manuscript;

        // Right page header: title + counter on the left, "+" slot on the right
        int sx = leftPos + ManuscriptBookScreenHandler.INSERT_X;
        int sy = topPos + ManuscriptBookScreenHandler.INSERT_Y;
        int textLeft = leftPos + RIGHT_X + 2, textRight = sx - 4;
        int textCenter = (textLeft + textRight) / 2;
        drawTitle(g, "Crafting tree", textCenter, topPos + 8, textRight - textLeft);
        drawCentered(g, storedNow.size() + "/" + ManuscriptBookScreenHandler.MAX_MANUSCRIPTS + " stored",
                textCenter, topPos + 17, MUTED);
        drawDivider(g, rightCenter, topPos + 27, PAGE_W / 2 - 6);

        if (!full && (storedNow.isEmpty() || carryingManuscript)) {
            // Pulsing golden glow: "put it here"
            double wave = 0.5 + 0.5 * Math.sin(Util.getMillis() / 300.0);
            int glow = ((0x50 + (int) (0x70 * wave)) << 24) | 0xD9A93A;
            g.fill(sx - 3, sy - 3, sx + 19, sy - 2, glow);
            g.fill(sx - 3, sy + 18, sx + 19, sy + 19, glow);
            g.fill(sx - 3, sy - 2, sx - 2, sy + 18, glow);
            g.fill(sx + 18, sy - 2, sx + 19, sy + 18, glow);
        }

        drawSlotFrame(g, sx, sy, ACCENT_BORDER);
        if (!full) {
            g.fill(sx + 5, sy + 7, sx + 11, sy + 9, MUTED);   // plus, horizontal bar
            g.fill(sx + 7, sy + 5, sx + 9, sy + 11, MUTED);   // plus, vertical bar
        }

        if (currentManuscript == null) {
            drawHint(g, "Store manuscripts here to see everything you can craft with them.",
                    rightCenter, topPos + 50, PAGE_W - 16);
        }
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        storedNow = this.menu.getStoredManuscripts();
        refreshSelection(storedNow);
        super.render(g, mouseX, mouseY, delta); // background, widgets, slots and slot tooltips
        renderPage(g, mouseX, mouseY);

        // Tooltip for the empty "+" slot (vanilla only shows tooltips for items)
        if (hoverLines.isEmpty()) {
            int sx = leftPos + ManuscriptBookScreenHandler.INSERT_X;
            int sy = topPos + ManuscriptBookScreenHandler.INSERT_Y;
            if (inside(mouseX, mouseY, sx, sy, 16, 16)) {
                if (storedNow.size() >= ManuscriptBookScreenHandler.MAX_MANUSCRIPTS) {
                    setHoverText(Component.literal("Book full").withStyle(ChatFormatting.RED));
                } else {
                    setHoverText(Component.literal("Manuscript slot"),
                            Component.literal("Drop a manuscript here to store it in the book.")
                                    .withStyle(ChatFormatting.GRAY));
                }
            }
        }
        if (!hoverLines.isEmpty()) {
            g.renderComponentTooltip(font, hoverLines, mouseX, mouseY);
        }

        Services.MANUSCRIPT_BOOK.renderManuscriptBookEvents(g, mouseX, mouseY, delta, this);
    }

    private void renderPage(GuiGraphics g, int mx, int my) {
        hoverLines.clear();
        clickables.clear();
        dotsX = -1;
        resultBoxX = -1;
        renderPageContent(g, mx, my);

        // Short parchment fade after changing manuscript: reads like turning a page
        long dt = Util.getMillis() - switchTime;
        if (dt < 200) {
            int color = ((int) (0xC0 * (1f - dt / 200f)) << 24) | 0xF9EED0;
            overlay(g, leftPos + LEFT_X + 13, topPos + 5, PAGE_W - 26, 22, color);
            overlay(g, leftPos + LEFT_X, topPos + 29, PAGE_W, 75, color);
        }
    }

    private void renderPageContent(GuiGraphics g, int mx, int my) {
        int x = leftPos, y = topPos;
        int pageCenter = x + LEFT_X + PAGE_W / 2;

        // ---- Empty book
        if (currentManuscript == null) {
            drawTitle(g, "Empty book", pageCenter, y + 9, PAGE_W - 28);
            drawDivider(g, pageCenter, y + 27, PAGE_W / 2 - 6);
            drawHint(g, "Put a manuscript in the slot at the top of the right page.", pageCenter, y + 36, PAGE_W - 16);
            return;
        }

        // ---- Right page: everything that can be crafted from this manuscript
        renderTree(g, mx, my);

        // ---- The recipe being shown (cycles when several recipes use this manuscript)
        long tick = Util.getMillis() / 1200L;
        int recipeIdx = currentRecipes.isEmpty() ? 0
                : pinnedRecipe >= 0 ? pinnedRecipe % currentRecipes.size()
                : (int) (tick % currentRecipes.size());
        shownRecipeIdx = recipeIdx;
        CraftmanAnvilRecipe recipe = currentRecipes.isEmpty() ? null : currentRecipes.get(recipeIdx).recipe();

        // Title: the item this recipe makes (falls back to the manuscript's own name)
        ItemStack titleStack = recipe != null ? recipe.output() : new ItemStack(currentManuscript);
        Item parent = selectedRow > 0 && selectedRow < rows.size() ? rows.get(selectedRow).node().parent() : null;
        drawTitle(g, titleStack.getHoverName().getString(), pageCenter, y + 8, PAGE_W - 28);
        drawFitted(g, (selected + 1) + "/" + storedNow.size() + "  " + new ItemStack(currentManuscript).getHoverName().getString(),
                pageCenter, y + 17, PAGE_W - 28, MUTED, true, false);
        drawDivider(g, pageCenter, y + 27, PAGE_W / 2 - 6);

        if (recipe == null) {
            drawHint(g, "No recipes use this manuscript.", pageCenter, y + 40, PAGE_W - 12);
            return;
        }

        // ---- What does the player have?
        List<List<ItemStack>> cells = ManuscriptRecipeIndex.gridCells(recipe);
        boolean[] have = new boolean[CraftmanAnvilRecipe.GRID_SIZE];
        boolean craftable = true;
        for (int i = 0; i < CraftmanAnvilRecipe.GRID_SIZE; i++) {
            List<ItemStack> options = cells.get(i);
            have[i] = cellOk(options, parent);
            craftable &= have[i];
        }

        // ---- Result item (x2) in a frame: green when everything is in the inventory
        resultBoxX = x + RESULT_X - 1;
        resultBoxY = y + RESULT_Y - 1;
        drawFrame(g, resultBoxX, resultBoxY, 34, craftable ? READY_BORDER : ACCENT_BORDER);
        List<Component> resultTip = new ArrayList<>();
        resultTip.add(Component.literal("Result").withStyle(ChatFormatting.GOLD));
        resultTip.add(craftable
                ? Component.literal("You have everything you need").withStyle(ChatFormatting.GREEN)
                : Component.literal("You are missing some ingredients").withStyle(ChatFormatting.RED));
        if (currentRecipes.size() > 1) {
            resultTip.add(Component.literal("Click to see another recipe (" + (recipeIdx + 1) + "/"
                    + currentRecipes.size() + ")").withStyle(ChatFormatting.GRAY));
        }
        drawBigItem(g, recipe.output(), x + RESULT_X, y + RESULT_Y, mx, my, resultTip.toArray(new Component[0]));

        // ---- Hits and chance, beside the result
        drawFitted(g, "Hits: " + recipe.hitTimes(), x + TAKE_X, y + 33, 42, TEXT, false, false);
        if (recipe.chance() < 1.0f) {
            drawFitted(g, "Chance " + Math.round(recipe.chance() * 100) + "%", x + TAKE_X, y + 42, 42, MUTED, false, false);
        }
        if (inside(mx, my, x + TAKE_X, y + 32, 40, 17) && hoverLines.isEmpty()) {
            List<Component> tip = new ArrayList<>();
            tip.add(Component.literal("Craftman's Anvil"));
            tip.add(Component.literal("Hit the ingredients " + recipe.hitTimes() + " times to craft.")
                    .withStyle(ChatFormatting.GRAY));
            if (recipe.chance() < 1.0f) {
                tip.add(Component.literal(Math.round(recipe.chance() * 100) + "% chance of success.")
                        .withStyle(ChatFormatting.GRAY));
            }
            setHoverText(tip.toArray(new Component[0]));
        }

        // ---- Dots: which recipe is showing (only when there are several)
        if (currentRecipes.size() > 1) {
            int n = currentRecipes.size();
            int dx = pageCenter - (n * 4 - 2) / 2;
            dotsX = dx;
            dotsY = y + 64;
            for (int i = 0; i < n; i++) {
                boolean hovered = inside(mx, my, dx + i * 4, dotsY - 2, 4, 6);
                g.fill(dx + i * 4, dotsY, dx + i * 4 + 2, dotsY + 2,
                        i == recipeIdx ? ACCENT_BORDER : hovered ? MUTED : 0xFFC9B58C);
            }
        }

        // ---- How to craft (3x2 grid; frames always drawn so it reads as a grid)
        for (int i = 0; i < CraftmanAnvilRecipe.GRID_SIZE; i++) {
            int cx = x + CRAFT_X + (i % CraftmanAnvilRecipe.GRID_WIDTH) * 18 + 1;
            int cy = y + CRAFT_Y + (i / CraftmanAnvilRecipe.GRID_WIDTH) * 18 + 1;
            List<ItemStack> options = cells.get(i);

            boolean isManuscript = !options.isEmpty() && options.get(0).getItem() instanceof Manuscript;
            boolean fromParent = !options.isEmpty() && isFromParent(options, parent);
            drawSlotFrame(g, cx, cy, isManuscript ? MANUSCRIPT_BORDER : fromParent ? PREVIOUS_BORDER : FRAME_BORDER);
            if (options.isEmpty()) continue;

            ItemStack shown = options.get((int) (tick % options.size()));
            List<Component> status = new ArrayList<>();
            if (isManuscript) {
                Item m = options.get(0).getItem();
                status.add(Component.literal("Manuscript").withStyle(ChatFormatting.GOLD));
                if (m != currentManuscript && inBook(m)) {
                    status.add(Component.literal("Click to open").withStyle(ChatFormatting.GRAY));
                    clickables.add(new Clickable(cx, cy, m));
                } else if (!have[i]) {
                    status.add(Component.literal("Not in your book").withStyle(ChatFormatting.RED));
                }
            } else if (fromParent) {
                status.add(Component.literal("Crafted in the previous step").withStyle(ChatFormatting.AQUA));
            } else if (have[i]) {
                status.add(Component.literal("In your inventory").withStyle(ChatFormatting.GREEN));
            } else {
                status.add(Component.literal("You have " + owned(shown.getItem()) + " of "
                        + Math.max(1, shown.getCount())).withStyle(ChatFormatting.RED));
            }
            drawItem(g, shown, cx, cy, mx, my, status.toArray(new Component[0]));
            if (!have[i]) overlay(g, cx, cy, 16, 16, 0x66B03030); // red tint: missing
        }
    }

    /** Right page: the manuscript at the top and, indented below it, everything that comes out of it. */
    private void renderTree(GuiGraphics g, int mx, int my) {
        int tx = leftPos + TREE_X, ty = topPos + TREE_Y;
        int maxScroll = Math.max(0, rows.size() - VIEW_ROWS);
        treeScroll = Mth.clamp(treeScroll, 0, maxScroll);
        int rowW = TREE_W - (maxScroll > 0 ? 4 : 0);

        for (int v = 0; v < VIEW_ROWS; v++) {
            int idx = treeScroll + v;
            if (idx >= rows.size()) break;
            Row row = rows.get(idx);
            ManuscriptRecipeIndex.TreeNode node = row.node();
            int ry = ty + v * ROW_H;
            int depth = row.depth();
            boolean root = idx == 0;
            boolean sel = idx == selectedRow && !root;
            boolean hovered = inside(mx, my, tx, ry, rowW, ROW_H);

            // Background
            if (root) g.fill(tx, ry, tx + rowW, ry + ROW_H - 1, 0x55B8862B);
            else if (sel) {
                g.fill(tx, ry, tx + rowW, ry + ROW_H - 1, FRAME_SHADE);
                g.fill(tx, ry, tx + 2, ry + ROW_H - 1, ACCENT_BORDER);
            } else if (hovered) g.fill(tx, ry, tx + rowW, ry + ROW_H - 1, 0x44D9C79C);

            // Branch lines
            for (int j = 1; j <= depth; j++) {
                int lx = tx + 1 + (j - 1) * INDENT + 5;
                boolean next = row.hasNext().get(j - 1);
                if (j < depth) {
                    if (next) g.fill(lx, ry, lx + 1, ry + ROW_H, LINE);
                } else {
                    g.fill(lx, ry, lx + 1, next ? ry + ROW_H : ry + ROW_H / 2 + 1, LINE);
                    g.fill(lx, ry + ROW_H / 2, tx + 1 + j * INDENT - 1, ry + ROW_H / 2 + 1, LINE);
                }
            }

            // Item + name
            ItemStack stack = new ItemStack(node.item());
            int ix = tx + 1 + depth * INDENT;
            drawSmallItem(g, stack, ix, ry + 1);
            int nameX = ix + 14;
            int nameW = tx + rowW - nameX - (root ? 1 : 5);
            drawFitted(g, stack.getHoverName().getString(), nameX, ry + 3, nameW, TEXT, false, false);

            // Green dot: you can craft this right now
            boolean ready = !root && isReady(row);
            if (ready) g.fill(tx + rowW - 4, ry + 5, tx + rowW - 1, ry + 8, READY_BORDER);

            if (hovered && inside(mx, my, tx, ry, rowW, ROW_H)) {
                if (root) {
                    setHover(stack, Component.literal("Manuscript").withStyle(ChatFormatting.GOLD));
                } else {
                    setHover(stack,
                            ready ? Component.literal("You have everything you need").withStyle(ChatFormatting.GREEN)
                                    : Component.literal("You are missing some ingredients").withStyle(ChatFormatting.RED),
                            Component.literal(sel ? "Shown on the left page" : "Click to see how to craft it")
                                    .withStyle(ChatFormatting.GRAY));
                }
            }
        }

        // Scrollbar
        if (maxScroll > 0) {
            int trackH = VIEW_ROWS * ROW_H - 1;
            int thumbH = Math.max(8, trackH * VIEW_ROWS / rows.size());
            int thumbY = ty + (trackH - thumbH) * treeScroll / maxScroll;
            g.fill(tx + TREE_W - 2, ty, tx + TREE_W, ty + trackH, LINE);
            g.fill(tx + TREE_W - 2, thumbY, tx + TREE_W, thumbY + thumbH, ACCENT_BORDER);
        }
    }
    // ---------------------------------------------------------------- input

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        if (button == 0 && currentManuscript != null) {
            int rowW = TREE_W - (rows.size() > VIEW_ROWS ? 4 : 0);
            int tx = leftPos + TREE_X, ty = topPos + TREE_Y;
            if (inside((int) mx, (int) my, tx, ty, rowW, VIEW_ROWS * ROW_H)) {
                int idx = treeScroll + ((int) my - ty) / ROW_H;
                if (idx > 0 && idx < rows.size()) {
                    selectRow(idx, true);
                    return true;
                }
            }
        }
        if (button == 0 && currentRecipes.size() > 1) {
            int n = currentRecipes.size();
            if (inside((int) mx, (int) my, resultBoxX, resultBoxY, 34, 34)) {
                pinnedRecipe = (shownRecipeIdx + 1) % n;
                playPageTurn();
                return true;
            }
            if (dotsX >= 0 && inside((int) mx, (int) my, dotsX, dotsY - 2, n * 4, 6)) {
                pinnedRecipe = Mth.clamp(((int) mx - dotsX) / 4, 0, n - 1);
                return true;
            }
        }
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

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!storedNow.isEmpty()) {
            if (keyCode == InputConstants.KEY_LEFT) { selected--; return true; }   // clamped in refreshSelection
            if (keyCode == InputConstants.KEY_RIGHT) { selected++; return true; }
            if (keyCode == InputConstants.KEY_UP && rows.size() > 1) { selectRow(Math.max(1, selectedRow - 1), true); return true; }
            if (keyCode == InputConstants.KEY_DOWN && rows.size() > 1) { selectRow(Math.min(rows.size() - 1, selectedRow + 1), true); return true; }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseScrolled(double mx, double my, double scrollX, double scrollY) {
        // Over the tree the wheel scrolls the tree, anywhere else it flips through manuscripts
        if (currentManuscript != null && rows.size() > VIEW_ROWS && scrollY != 0
                && inside((int) mx, (int) my, leftPos + TREE_X, topPos + TREE_Y, TREE_W, VIEW_ROWS * ROW_H)) {
            treeScroll = Mth.clamp(treeScroll - (int) Math.signum(scrollY), 0, rows.size() - VIEW_ROWS);
            return true;
        }
        if (!storedNow.isEmpty() && scrollY != 0) {
            selected -= (int) Math.signum(scrollY); // clamped in refreshSelection
            return true;
        }
        return super.mouseScrolled(mx, my, scrollX, scrollY);
    }

    // ---------------------------------------------------------------- flat parchment button

    private static class PageButton extends Button {
        private final boolean silent; // the page-turn sound is played by the screen itself

        PageButton(int x, int y, int w, int h, Component label, boolean silent, OnPress onPress) {
            super(x, y, w, h, label, onPress, DEFAULT_NARRATION);
            this.silent = silent;
        }

        @Override
        public void playDownSound(SoundManager handler) {
            if (!silent) super.playDownSound(handler);
        }

        @Override
        protected void renderWidget(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
            int x = getX(), y = getY(), r = x + getWidth(), b = y + getHeight();
            int fill = !active ? 0xFFB9A98A : isHoveredOrFocused() ? 0xFF7E6040 : 0xFF5E442A;
            int light = !active ? 0xFFC9BB9E : 0xFF8F7050;
            int dark = !active ? 0xFFA89873 : 0xFF3F2C17;

            g.fill(x, y, r, b, 0xFF2B1B08);          // outline
            g.fill(x + 1, y + 1, r - 1, b - 1, fill); // body
            g.fill(x + 1, y + 1, r - 1, y + 2, light); // top highlight
            g.fill(x + 1, b - 2, r - 1, b - 1, dark);  // bottom shade

            var font = net.minecraft.client.Minecraft.getInstance().font;
            int color = active ? 0xFFFFF3D6 : 0xFF8A7A5C;
            String label = getMessage().getString();
            g.pose().pushPose();
            g.pose().translate(x + getWidth() / 2f, y + getHeight() / 2f, 0);
            g.pose().scale(0.75f, 0.75f, 1f);
            g.drawString(font, label, -font.width(label) / 2, -4, color, active);
            g.pose().popPose();
        }
    }
}