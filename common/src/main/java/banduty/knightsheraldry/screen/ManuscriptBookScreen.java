package banduty.knightsheraldry.screen;

import banduty.knightsheraldry.KnightsHeraldry; // adjust the package if it's different
import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.items.custom.Manuscript;
import banduty.stoneycore.recipes.CraftmanAnvilRecipe;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
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

    private static final int LEFT_X = 8, RIGHT_X = 102, PAGE_W = 84;
    private static final int RESULT_X = 12, RESULT_Y = 30;
    private static final int TAKE_X = 49, TAKE_Y = 51;
    private static final int CRAFT_X = 23, CRAFT_Y = 67;

    private static final int TREE_X = 104, TREE_Y = 31, TREE_W = 80;
    private static final int ROW_H = 14, VIEW_ROWS = 5, INDENT = 8;

    private static final float TS = 0.75f;
    private static final float MIN_TS = 0.55f;

    private static final int TEXT = 0xFF3B2A14, MUTED = 0xFF7A6548;

    private static final ResourceLocation WIDGETS = ResourceLocation.fromNamespaceAndPath(
            KnightsHeraldry.MOD_ID, "textures/gui/manuscript_book/widgets.png");
    private static final int ATLAS = 256;

    private record Sprite(int u, int v, int w, int h) {}

    private static final Sprite FRAME_BORDER = new Sprite(0, 0, 18, 18);       // normal
    private static final Sprite ACCENT_BORDER = new Sprite(18, 0, 18, 18);
    private static final Sprite READY_BORDER = new Sprite(36, 0, 18, 18);      // green: you can craft this now
    private static final Sprite MANUSCRIPT_BORDER = new Sprite(54, 0, 18, 18); // gold: the manuscript itself
    private static final Sprite PREVIOUS_BORDER = new Sprite(72, 0, 18, 18);   // blue: made in the previous step
    private static final Sprite PLUS = new Sprite(100, 0, 6, 6);

    private static final Sprite RESULT_ACCENT = new Sprite(0, 18, 34, 34);
    private static final Sprite RESULT_READY = new Sprite(34, 18, 34, 34);
    private static final Sprite GLOW = new Sprite(68, 18, 22, 22);

    private static final Sprite[] BTN_SMALL = {new Sprite(0, 52, 11, 12), new Sprite(11, 52, 11, 12), new Sprite(22, 52, 11, 12)};
    private static final Sprite[] BTN_LARGE = {new Sprite(40, 52, 34, 12), new Sprite(74, 52, 34, 12), new Sprite(108, 52, 34, 12)};
    private static final Sprite DIVIDER = new Sprite(0, 64, 72, 3);

    private static final Sprite ROW_ROOT = new Sprite(0, 70, 80, 13);
    private static final Sprite ROW_SELECTED = new Sprite(0, 83, 80, 13);
    private static final Sprite ROW_HOVER = new Sprite(0, 96, 80, 13);
    private static final Sprite DOT = new Sprite(8, 110, 3, 3);

    private static final Sprite PX_LINE = new Sprite(0, 110, 1, 1);
    private static final Sprite PX_TRACK = new Sprite(1, 110, 1, 1);
    private static final Sprite PX_THUMB = new Sprite(2, 110, 1, 1);
    private static final Sprite PX_RED = new Sprite(3, 110, 1, 1);
    private static final Sprite PX_PARCHMENT = new Sprite(4, 110, 1, 1);
    private static final Sprite PX_DOT_ACTIVE = new Sprite(5, 110, 1, 1);
    private static final Sprite PX_DOT_HOVER = new Sprite(6, 110, 1, 1);
    private static final Sprite PX_DOT_IDLE = new Sprite(7, 110, 1, 1);

    private record Clickable(int x, int y, Item manuscript) {}

    private record Row(ManuscriptRecipeIndex.TreeNode node, List<Boolean> hasNext) {
        int depth() { return hasNext.size(); }
    }

    private ManuscriptRecipeIndex index;
    private int selected = 0;
    private Item currentManuscript = null;
    private List<ManuscriptRecipeIndex.Entry> currentRecipes = List.of();
    private List<Row> rows = List.of();
    private int selectedRow = 0, treeScroll = 0;
    private List<ItemStack> storedNow = List.of();
    private Button prevButton, nextButton, takeButton;
    private final List<Clickable> clickables = new ArrayList<>();
    private final List<Component> hoverLines = new ArrayList<>();

    private int pinnedRecipe = -1;
    private int shownRecipeIdx = 0;
    private long switchTime = 0;
    private int dotsX = -1, dotsY = -1;
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

    private void selectRow(int idx, boolean sound) {
        if (idx < 0 || idx >= rows.size()) return;
        if (sound && idx != selectedRow) playPageTurn();
        selectedRow = idx;
        pinnedRecipe = -1;
        switchTime = Util.getMillis();
        currentRecipes = idx > 0 ? rows.get(idx).node().recipes() : List.of();
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

    private static boolean inside(int mx, int my, int x, int y, int w, int h) {
        return mx >= x && mx < x + w && my >= y && my < y + h;
    }

    private static void sprite(GuiGraphics g, int x, int y, Sprite s) {
        spriteW(g, x, y, s, s.w());
    }

    private static void spriteW(GuiGraphics g, int x, int y, Sprite s, int width) {
        RenderSystem.enableBlend();
        g.blit(WIDGETS, x, y, s.u(), s.v(), width, s.h(), ATLAS, ATLAS);
    }

    private static void pixel(GuiGraphics g, Sprite px, int x, int y, int w, int h) {
        if (w <= 0 || h <= 0) return;
        RenderSystem.enableBlend();
        g.blit(WIDGETS, x, y, w, h, (float) px.u(), (float) px.v(), 1, 1, ATLAS, ATLAS);
    }

    private static void overlay(GuiGraphics g, int x, int y, int w, int h, Sprite px, float alpha) {
        g.pose().pushPose();
        g.pose().translate(0, 0, 200);
        g.setColor(1f, 1f, 1f, alpha);
        pixel(g, px, x, y, w, h);
        g.setColor(1f, 1f, 1f, 1f);
        g.pose().popPose();
    }

    private static void drawSlotFrame(GuiGraphics g, int ix, int iy, Sprite frame) {
        sprite(g, ix - 1, iy - 1, frame);
    }

    private static void drawDivider(GuiGraphics g, int cx, int y) {
        sprite(g, cx - DIVIDER.w() / 2, y - 1, DIVIDER);
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

    private void drawTitle(GuiGraphics g, String s, int centerX, int y, int maxW) {
        drawFitted(g, s, centerX, y, maxW, TEXT, true, true);
    }

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

    @Override
    protected void renderBg(GuiGraphics g, float delta, int mouseX, int mouseY) {
        g.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        int rightCenter = leftPos + RIGHT_X + PAGE_W / 2;
        boolean full = storedNow.size() >= ManuscriptBookScreenHandler.MAX_MANUSCRIPTS;
        boolean carryingManuscript = menu.getCarried().getItem() instanceof Manuscript;

        int sx = leftPos + ManuscriptBookScreenHandler.INSERT_X;
        int sy = topPos + ManuscriptBookScreenHandler.INSERT_Y;
        int textLeft = leftPos + RIGHT_X + 2, textRight = sx - 4;
        int textCenter = (textLeft + textRight) / 2;
        drawTitle(g, "Crafting tree", textCenter, topPos + 8, textRight - textLeft);
        drawCentered(g, storedNow.size() + "/" + ManuscriptBookScreenHandler.MAX_MANUSCRIPTS + " stored",
                textCenter, topPos + 17, MUTED);
        drawDivider(g, rightCenter, topPos + 27);

        if (!full && (storedNow.isEmpty() || carryingManuscript)) {
            double wave = 0.5 + 0.5 * Math.sin(Util.getMillis() / 300.0);
            g.setColor(1f, 1f, 1f, (0x50 + (int) (0x70 * wave)) / 255f);
            sprite(g, sx - 3, sy - 3, GLOW);
            g.setColor(1f, 1f, 1f, 1f);
        }

        drawSlotFrame(g, sx, sy, ACCENT_BORDER);
        if (!full) {
            sprite(g, sx + 5, sy + 5, PLUS);
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
        super.render(g, mouseX, mouseY, delta);
        renderPage(g, mouseX, mouseY);

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

        long dt = Util.getMillis() - switchTime;
        if (dt < 200) {
            float a = 0.75f * (1f - dt / 200f);
            overlay(g, leftPos + LEFT_X + 13, topPos + 5, PAGE_W - 26, 22, PX_PARCHMENT, a);
            overlay(g, leftPos + LEFT_X, topPos + 29, PAGE_W, 75, PX_PARCHMENT, a);
        }
    }

    private void renderPageContent(GuiGraphics g, int mx, int my) {
        int x = leftPos, y = topPos;
        int pageCenter = x + LEFT_X + PAGE_W / 2;

        if (currentManuscript == null) {
            drawTitle(g, "Empty book", pageCenter, y + 9, PAGE_W - 28);
            drawDivider(g, pageCenter, y + 27);
            drawHint(g, "Put a manuscript in the slot at the top of the right page.", pageCenter, y + 36, PAGE_W - 16);
            return;
        }

        renderTree(g, mx, my);

        long tick = Util.getMillis() / 1200L;
        int recipeIdx = currentRecipes.isEmpty() ? 0
                : pinnedRecipe >= 0 ? pinnedRecipe % currentRecipes.size()
                : (int) (tick % currentRecipes.size());
        shownRecipeIdx = recipeIdx;
        CraftmanAnvilRecipe recipe = currentRecipes.isEmpty() ? null : currentRecipes.get(recipeIdx).recipe();

        ItemStack titleStack = recipe != null ? recipe.output() : new ItemStack(currentManuscript);
        Item parent = selectedRow > 0 && selectedRow < rows.size() ? rows.get(selectedRow).node().parent() : null;
        drawTitle(g, titleStack.getHoverName().getString(), pageCenter, y + 8, PAGE_W - 28);
        drawFitted(g, (selected + 1) + "/" + storedNow.size() + "  " + new ItemStack(currentManuscript).getHoverName().getString(),
                pageCenter, y + 17, PAGE_W - 28, MUTED, true, false);
        drawDivider(g, pageCenter, y + 27);

        if (recipe == null) {
            drawHint(g, "No recipes use this manuscript.", pageCenter, y + 40, PAGE_W - 12);
            return;
        }

        List<List<ItemStack>> cells = ManuscriptRecipeIndex.gridCells(recipe);
        boolean[] have = new boolean[CraftmanAnvilRecipe.GRID_SIZE];
        boolean craftable = true;
        for (int i = 0; i < CraftmanAnvilRecipe.GRID_SIZE; i++) {
            List<ItemStack> options = cells.get(i);
            have[i] = cellOk(options, parent);
            craftable &= have[i];
        }

        resultBoxX = x + RESULT_X - 1;
        resultBoxY = y + RESULT_Y - 1;
        sprite(g, resultBoxX, resultBoxY, craftable ? RESULT_READY : RESULT_ACCENT);
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

        if (currentRecipes.size() > 1) {
            int n = currentRecipes.size();
            int dx = pageCenter - (n * 4 - 2) / 2;
            dotsX = dx;
            dotsY = y + 64;
            for (int i = 0; i < n; i++) {
                boolean hovered = inside(mx, my, dx + i * 4, dotsY - 2, 4, 6);
                pixel(g, i == recipeIdx ? PX_DOT_ACTIVE : hovered ? PX_DOT_HOVER : PX_DOT_IDLE,
                        dx + i * 4, dotsY, 2, 2);
            }
        }

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
            if (!have[i]) overlay(g, cx, cy, 16, 16, PX_RED, 1f); // red tint: missing
        }
    }

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

            if (root) spriteW(g, tx, ry, ROW_ROOT, rowW);
            else if (sel) spriteW(g, tx, ry, ROW_SELECTED, rowW);
            else if (hovered) spriteW(g, tx, ry, ROW_HOVER, rowW);

            for (int j = 1; j <= depth; j++) {
                int lx = tx + 1 + (j - 1) * INDENT + 5;
                boolean next = row.hasNext().get(j - 1);
                if (j < depth) {
                    if (next) pixel(g, PX_LINE, lx, ry, 1, ROW_H);
                } else {
                    pixel(g, PX_LINE, lx, ry, 1, next ? ROW_H : ROW_H / 2 + 1);
                    pixel(g, PX_LINE, lx, ry + ROW_H / 2, tx + 1 + j * INDENT - 1 - lx, 1);
                }
            }

            ItemStack stack = new ItemStack(node.item());
            int ix = tx + 1 + depth * INDENT;
            drawSmallItem(g, stack, ix, ry + 1);
            int nameX = ix + 14;
            int nameW = tx + rowW - nameX - (root ? 1 : 5);
            drawFitted(g, stack.getHoverName().getString(), nameX, ry + 3, nameW, TEXT, false, false);

            boolean ready = !root && isReady(row);
            if (ready) sprite(g, tx + rowW - 4, ry + 5, DOT);

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

        if (maxScroll > 0) {
            int trackH = VIEW_ROWS * ROW_H - 1;
            int thumbH = Math.max(8, trackH * VIEW_ROWS / rows.size());
            int thumbY = ty + (trackH - thumbH) * treeScroll / maxScroll;
            pixel(g, PX_TRACK, tx + TREE_W - 2, ty, 2, trackH);
            pixel(g, PX_THUMB, tx + TREE_W - 2, thumbY, 2, thumbH);
        }
    }

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
            if (keyCode == InputConstants.KEY_LEFT) { selected--; return true; }
            if (keyCode == InputConstants.KEY_RIGHT) { selected++; return true; }
            if (keyCode == InputConstants.KEY_UP && rows.size() > 1) { selectRow(Math.max(1, selectedRow - 1), true); return true; }
            if (keyCode == InputConstants.KEY_DOWN && rows.size() > 1) { selectRow(Math.min(rows.size() - 1, selectedRow + 1), true); return true; }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseScrolled(double mx, double my, double scrollX, double scrollY) {
        if (currentManuscript != null && rows.size() > VIEW_ROWS && scrollY != 0
                && inside((int) mx, (int) my, leftPos + TREE_X, topPos + TREE_Y, TREE_W, VIEW_ROWS * ROW_H)) {
            treeScroll = Mth.clamp(treeScroll - (int) Math.signum(scrollY), 0, rows.size() - VIEW_ROWS);
            return true;
        }
        if (!storedNow.isEmpty() && scrollY != 0) {
            selected -= (int) Math.signum(scrollY);
            return true;
        }
        return super.mouseScrolled(mx, my, scrollX, scrollY);
    }

    private static class PageButton extends Button {
        private final boolean silent;

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
            Sprite[] set = getWidth() <= BTN_SMALL[0].w() ? BTN_SMALL : BTN_LARGE;
            sprite(g, getX(), getY(), !active ? set[2] : isHoveredOrFocused() ? set[1] : set[0]);

            var font = net.minecraft.client.Minecraft.getInstance().font;
            int color = active ? 0xFFFFF3D6 : 0xFF8A7A5C;
            String label = getMessage().getString();
            g.pose().pushPose();
            g.pose().translate(getX() + getWidth() / 2f, getY() + getHeight() / 2f, 0);
            g.pose().scale(0.75f, 0.75f, 1f);
            g.drawString(font, label, -font.width(label) / 2, -4, color, active);
            g.pose().popPose();
        }
    }
}