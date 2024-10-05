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

    private static ItemStack weaponStack(ItemConvertible item) {
        return new ItemStack(item);
    }

    private static ItemStack armorStack(ItemConvertible item, boolean aventail, boolean rimmed, boolean besagews) {
        ItemStack armorStack = new ItemStack(item);
        armorStack.getOrCreateNbt().putBoolean("aventail", aventail);
        armorStack.getOrCreateNbt().putBoolean("rimmed", rimmed);
        armorStack.getOrCreateNbt().putBoolean("besagews", besagews);
        return armorStack;
    }

    private static void toolsAndWeapons(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                weaponStack(ModItems.SMITHING_HAMMER),
                weaponStack(ModItems.DAGGER),
                weaponStack(ModItems.STILETTO),
                weaponStack(ModItems.RAPIER),
                weaponStack(ModItems.SWORD),
                weaponStack(ModItems.V_SWORD),
                weaponStack(ModItems.ARMING_SWORD),
                weaponStack(ModItems.AXE),
                weaponStack(ModItems.BROAD_AXE),
                weaponStack(ModItems.CROOKED_AXE),
                weaponStack(ModItems.STRAIGHT_CROOKED_AXE),
                weaponStack(ModItems.MACE),
                weaponStack(ModItems.SPIKED_MACE),
                weaponStack(ModItems.FLAIL),
                weaponStack(ModItems.BALL_FLAIL),
                weaponStack(ModItems.HAMMER),
                weaponStack(ModItems.WAR_HAMMER),
                weaponStack(ModItems.LONGSWORD),
                weaponStack(ModItems.V_LONGSWORD),
                weaponStack(ModItems.FALCHION),
                weaponStack(ModItems.SCIMITAR),
                weaponStack(ModItems.KATANA),
                weaponStack(ModItems.PITCHFORK),
                weaponStack(ModItems.SPEAR),
                weaponStack(ModItems.PIKE),
                weaponStack(ModItems.BILLHOOK),
                weaponStack(ModItems.GLAIVE),
                weaponStack(ModItems.CURVED_GLAIVE),
                weaponStack(ModItems.HALBERD),
                weaponStack(ModItems.LANCE),
                weaponStack(ModItems.WOODEN_LANCE),
                weaponStack(ModItems.POLEAXE),
                weaponStack(ModItems.POLEHAMMER),
                weaponStack(ModItems.BEC_DE_CORBIN),
                weaponStack(ModItems.MORNING_STAR),
                weaponStack(ModItems.BARDICHE),
                weaponStack(ModItems.WARSWORD),
                weaponStack(ModItems.WARSWORD_CLAYMORE),
                weaponStack(ModItems.WARSWORD_FLAMBERGE),
                weaponStack(ModItems.WARSWORD_ZWEIHANDER),
                weaponStack(ModItems.WARDART),

                weaponStack(ModItems.LONGBOW),

                weaponStack(ModItems.SWALLOWTAIL_ARROW)
        ));
    }

    private static void armors(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                armorStack(ModItems.QUILTED_COIF, false, false, false),
                armorStack(ModItems.GAMBESON, false, false, false),
                armorStack(ModItems.GAMBESON_BREECHES, false, false, false),
                armorStack(ModItems.GAMBESON_BOOTS, false, false, false),

                armorStack(ModItems.MAIL_COIF, false, false, false),
                armorStack(ModItems.HAUBERK, false, false, false),
                armorStack(ModItems.MAIL_BREECHES, false, false, false),
                armorStack(ModItems.MAIL_BOOTS, false, false, false),

                armorStack(ModItems.BARBUTE_NO_VISOR, false, false, false),
                armorStack(ModItems.BASCINET_NO_VISOR, false, false, false),
                armorStack(ModItems.KETTLE_HELM, false, false, false),
                armorStack(ModItems.NASAL_HELM, false, false, false),
                armorStack(ModItems.VIKING_HELM, false, false, false),
                armorStack(ModItems.ARMET, false, false, false),
                armorStack(ModItems.ARMET_2, false, false, false),
                armorStack(ModItems.BARBUTE, false, false, false),
                armorStack(ModItems.BASCINET, false, false, false),
                armorStack(ModItems.CAGE, false, false, false),
                armorStack(ModItems.CAGE_2, false, false, false),
                armorStack(ModItems.FLAT_BASCINET, false, false, false),
                armorStack(ModItems.GREAT_HELM, false, false, false),
                armorStack(ModItems.GREAT_HELM_2, false, false, false),
                armorStack(ModItems.SALLET, false, false, false),

                armorStack(ModItems.AVENTAIL, false, false, false),
                armorStack(ModItems.BARBUTE_NO_VISOR, true, false, false),
                armorStack(ModItems.BASCINET_NO_VISOR, true, false, false),
                armorStack(ModItems.KETTLE_HELM, true, false, false),
                armorStack(ModItems.NASAL_HELM, true, false, false),
                armorStack(ModItems.VIKING_HELM, true, false, false),
                armorStack(ModItems.ARMET, true, false, false),
                armorStack(ModItems.ARMET_2, true, false, false),
                armorStack(ModItems.BARBUTE, true, false, false),
                armorStack(ModItems.BASCINET, true, false, false),
                armorStack(ModItems.CAGE, true, false, false),
                armorStack(ModItems.CAGE_2, true, false, false),
                armorStack(ModItems.FLAT_BASCINET, true, false, false),
                armorStack(ModItems.GREAT_HELM, true, false, false),
                armorStack(ModItems.GREAT_HELM_2, true, false, false),
                armorStack(ModItems.SALLET, true, false, false),

                armorStack(ModItems.FROGMOUTH, false, false, false),
                armorStack(ModItems.GREAT_ARMET, false, false, false),
                armorStack(ModItems.GREAT_ARMET_2, false, false, false),
                armorStack(ModItems.GREAT_BASCINET, false, false, false),
                armorStack(ModItems.GREAT_HOUNDSKUL_BASCINET, false, false, false),
                armorStack(ModItems.MAXIMILLIAN_HELMET, false, false, false),

                armorStack(ModItems.BRIGANDINE, false, false, false),
                armorStack(ModItems.BRIG_BREASTPLATE, false, false, false),
                armorStack(ModItems.BRIG_BREASTPLATE_TASSETS, false, false, false),
                armorStack(ModItems.PLATE_CUIRASS, false, false, false),
                armorStack(ModItems.PLATE_CUIRASS_TASSETS, false, false, false),
                armorStack(ModItems.MAXIMILLIAN_CUIRASS, false, false, false),
                armorStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS, false, false, false),
                armorStack(ModItems.XIIII_PLATE_CUIRASS, false, false, false),
                armorStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS, false, false, false),
                armorStack(ModItems.XIIII_PLATE_BREASTPLATE, false, false, false),

                armorStack(ModItems.SURCOAT, false, false, false),
                armorStack(ModItems.SURCOAT_SLEEVELESS, false, false, false),

                armorStack(ModItems.RIM_GUARDS, false, false, false),
                armorStack(ModItems.BESAGEWS, false, false, false),
                armorStack(ModItems.MAIL_PAULDRON, false, false, false),
                armorStack(ModItems.BRIGANDINE_PAULDRON, false, false, false),
                armorStack(ModItems.PLATE_PAULDRON, false, false, false),
                armorStack(ModItems.MAIL_PAULDRON, false, false, true),
                armorStack(ModItems.BRIGANDINE_PAULDRON, false, false, true),
                armorStack(ModItems.PLATE_PAULDRON, false, false, true),
                armorStack(ModItems.PLATE_PAULDRON, false, true, false),
                armorStack(ModItems.PLATE_PAULDRON, false, true, true),

                armorStack(ModItems.GAUNTLET, false, false, false),
                armorStack(ModItems.BRIGANDINE_REREBRACE, false, false, false),
                armorStack(ModItems.PLATE_REREBRACE, false, false, false),
                armorStack(ModItems.BRIGANDINE_CHAUSSES, false, false, false),
                armorStack(ModItems.PLATE_CHAUSSES, false, false, false),

                armorStack(ModItems.SABATONS, false, false, false),

                armorStack(ModItems.HOOD, false, false, false),
                armorStack(ModItems.TORN_HOOD, false, false, false),

                armorStack(ModItems.CLOAK, false, false, false),
                armorStack(ModItems.TORN_CLOAK, false, false, false)
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