package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.itemgroup.gui.ItemGroupTab;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class ModItemGroups {
    private static final Identifier DISCORD_ICON = new Identifier(KnightsHeraldry.MOD_ID, "textures/gui/button/discord.png");
    private static final Identifier BACKGROUND = new Identifier(KnightsHeraldry.MOD_ID, "textures/gui/group.png");
    private static final Identifier TABS = new Identifier(KnightsHeraldry.MOD_ID, "textures/gui/tabs.png");

    private static ItemStack stack(ItemConvertible item) {
        return new ItemStack(item);
    }

    private static void toolsAndWeapons(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                stack(ModItems.SMITHING_HAMMER),
                stack(ModItems.DAGGER),
                stack(ModItems.STILETTO),
                stack(ModItems.RAPIER),
                stack(ModItems.SWORD),
                stack(ModItems.V_SWORD),
                stack(ModItems.ARMING_SWORD),
                stack(ModItems.AXE),
                stack(ModItems.BROAD_AXE),
                stack(ModItems.CROOKED_AXE),
                stack(ModItems.STRAIGHT_CROOKED_AXE),
                stack(ModItems.MACE),
                stack(ModItems.SPIKED_MACE),
                stack(ModItems.FLAIL),
                stack(ModItems.BALL_FLAIL),
                stack(ModItems.HAMMER),
                stack(ModItems.WAR_HAMMER),
                stack(ModItems.LONGSWORD),
                stack(ModItems.V_LONGSWORD),
                stack(ModItems.FALCHION),
                stack(ModItems.SCIMITAR),
                stack(ModItems.KATANA),
                stack(ModItems.PITCHFORK),
                stack(ModItems.SPEAR),
                stack(ModItems.PIKE),
                stack(ModItems.BILLHOOK),
                stack(ModItems.GLAIVE),
                stack(ModItems.CURVED_GLAIVE),
                stack(ModItems.HALBERD),
                stack(ModItems.LANCE),
                stack(ModItems.WOODEN_LANCE),
                stack(ModItems.POLEAXE),
                stack(ModItems.POLEHAMMER),
                stack(ModItems.BEC_DE_CORBIN),
                stack(ModItems.MORNING_STAR),
                stack(ModItems.BARDICHE),
                stack(ModItems.WARSWORD),
                stack(ModItems.WARSWORD_CLAYMORE),
                stack(ModItems.WARSWORD_FLAMBERGE),
                stack(ModItems.WARSWORD_ZWEIHANDER),
                stack(ModItems.WARDART)
        ));
    }

    private static void armors(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                stack(ModItems.QUILTED_COIF),
                stack(ModItems.GAMBESON),
                stack(ModItems.GAMBESON_BREECHES),
                stack(ModItems.GAMBESON_BOOTS),

                stack(ModItems.MAIL_COIF),
                stack(ModItems.HAUBERK),
                stack(ModItems.MAIL_BREECHES),
                stack(ModItems.MAIL_BOOTS),

                stack(ModItems.MAIL_PAULDRON),
                stack(ModItems.BRIGANDINE_PAULDRON),
                stack(ModItems.PLATE_PAULDRON),
                stack(ModItems.BRIGANDINE),
                stack(ModItems.BRIG_BREASTPLATE),
                stack(ModItems.BRIG_BREASTPLATE_TASSETS),
                stack(ModItems.PLATE_CUIRASS),
                stack(ModItems.PLATE_CUIRASS_TASSETS),
                stack(ModItems.MAXIMILIAN_CUIRASS),
                stack(ModItems.MAXIMILIAN_CUIRASS_TASSETS),
                stack(ModItems.XIIII_PLATE_CUIRASS),
                stack(ModItems.XIIII_PLATE_CUIRASS_TASSETS),
                stack(ModItems.XIIII_PLATE_BREASTPLATE),
                stack(ModItems.SABATONS)
        ));
    }

    public static final ItemGroupTab KH_TOOLS_WEAPONS_TAB = new ItemGroupTab(
            Icon.of(ModItems.WARSWORD_ZWEIHANDER),
            Text.literal("KH Tools & Weapons").formatted(Formatting.WHITE),
            ModItemGroups::toolsAndWeapons,
            TABS,
            true
    );

    public static final ItemGroupTab KH_ARMORS_TAB = new ItemGroupTab(
            Icon.of(ModItems.QUILTED_COIF),
            Text.literal("KH Armors").formatted(Formatting.WHITE),
            ModItemGroups::armors,
            TABS,
            true
    );

    public static final OwoItemGroup KH_TAB = OwoItemGroup
        .builder(new Identifier(KnightsHeraldry.MOD_ID, "kh_tab"), () -> Icon.of(new Identifier(KnightsHeraldry.MOD_ID,
                "icon.png"), 0, 0, 16, 16))
            .customTexture(BACKGROUND)
            .initializer(ModItemGroups::initializeGroup)
            .displaySingleTab()
            .build();

    private static void initializeGroup(OwoItemGroup group) {
        group.tabs.add(KH_TOOLS_WEAPONS_TAB);
        group.tabs.add(KH_ARMORS_TAB);
        group.addButton(LinkButton.discord("https://discord.gg/AbtCqntN9S"));
    }
    
    public static void registerItemGroups() {
        KH_TAB.initialize();
    }

    @Environment(EnvType.CLIENT)
    public static final class LinkButton implements OwoItemGroup.ButtonDefinition {
        private final Icon icon;
        private final Text tooltip;
        private final Identifier texture;

        public LinkButton(ItemGroup group, Icon icon, String name, Identifier texture) {
            this.icon = icon;
            this.tooltip = OwoItemGroup.ButtonDefinition.tooltipFor(group, "button", name);
            this.texture = texture;
        }

        public static ItemGroupButton discord(String url) {
            return link(Icon.of(DISCORD_ICON, 0, 0, 16, 16), "discord", url);
        }

        public static ItemGroupButton link(Icon icon, String name, String url) {
            return new ItemGroupButton(KH_TAB, icon, name, TABS, () -> {
                final var client = MinecraftClient.getInstance();
                var screen = client.currentScreen;
                client.setScreen(new ConfirmLinkScreen(confirmed -> {
                    if (confirmed) Util.getOperatingSystem().open(url);
                    client.setScreen(screen);
                }, url, true));
            });
        }

        @Override
        public Identifier texture() {
            return this.texture;
        }

        @Override
        public Icon icon() {
            return icon;
        }

        @Override
        public Text tooltip() {
            return tooltip;
        }
    }
}