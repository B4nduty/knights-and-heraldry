package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.Services;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin extends AbstractContainerScreen<CreativeModeInventoryScreen.ItemPickerMenu> {

    @Shadow
    private static CreativeModeTab selectedTab;

    @Shadow
    protected abstract boolean canScroll();

    @Unique
    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/scroller");
    @Unique
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/scroller_disabled");

    @Unique
    private static final ResourceLocation[] UNSELECTED_TOP_TABS = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_1"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_2"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_3"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_4"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_5"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_6"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_7")
    };
    @Unique
    private static final ResourceLocation[] SELECTED_TOP_TABS = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_1"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_2"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_3"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_4"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_5"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_6"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_7")
    };
    @Unique
    private static final ResourceLocation[] UNSELECTED_BOTTOM_TABS = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_1"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_2"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_3"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_4"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_5"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_6"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_7")
    };
    @Unique
    private static final ResourceLocation[] SELECTED_BOTTOM_TABS = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_1"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_2"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_3"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_4"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_5"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_6"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_7")
    };

    // ----------------------------------------------------
    // 1. DYNAMIC BACKGROUND TEXTURE INJECTION
    // ----------------------------------------------------
    @ModifyArg(
            method = "renderBg",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V",
                    ordinal = 0
            )
    )
    private ResourceLocation injectCustomGroupTexture(ResourceLocation original) {
        if (selectedTab != null && Services.PLATFORM.isCreativeModeTab(selectedTab)) {
            return selectedTab.getBackgroundTexture();
        }
        return original;
    }

    // ----------------------------------------------------
    // 2. DYNAMIC SCROLLBAR SLIDER TEXTURE INJECTION
    // ----------------------------------------------------
    @ModifyArg(method = "renderBg", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private ResourceLocation injectCustomScrollbarTexture(ResourceLocation texture) {
        if (selectedTab == null || !Services.PLATFORM.isCreativeModeTab(selectedTab)) {
            return texture;
        }
        return this.canScroll() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
    }

    // ----------------------------------------------------
    // 3. DYNAMIC TAB HEADER SPRITE INJECTION
    // ----------------------------------------------------
    @ModifyArg(method = "renderTabButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private ResourceLocation injectCustomTabTexture(ResourceLocation texture, @Local(argsOnly = true) CreativeModeTab currentTab) {
        if (currentTab == null || !Services.PLATFORM.isCreativeModeTab(currentTab)) {
            return texture;
        }

        boolean isSelected = (currentTab == selectedTab);
        boolean isTopRow = (currentTab.row() == CreativeModeTab.Row.TOP);
        int column = Math.min(currentTab.column(), 6);

        if (isTopRow) {
            return isSelected ? SELECTED_TOP_TABS[column] : UNSELECTED_TOP_TABS[column];
        } else {
            return isSelected ? SELECTED_BOTTOM_TABS[column] : UNSELECTED_BOTTOM_TABS[column];
        }
    }

    public CreativeModeInventoryScreenMixin(CreativeModeInventoryScreen.ItemPickerMenu screenHandler, Inventory playerInventory, Component text) {
        super(screenHandler, playerInventory, text);
    }
}