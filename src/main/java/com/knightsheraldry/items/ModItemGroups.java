package com.knightsheraldry.items;

import banduty.stoneycore.StoneyCore;
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
    private static final Identifier DISCORD_ICON = new Identifier(StoneyCore.MOD_ID, "textures/gui/button/discord.png");
    private static final Identifier BACKGROUND = new Identifier(StoneyCore.MOD_ID, "textures/gui/group.png");
    private static final Identifier TABS = new Identifier(StoneyCore.MOD_ID, "textures/gui/tabs.png");

    private static ItemStack weaponStack(ItemConvertible item) {
        return new ItemStack(item);
    }

    private static ItemStack armorStack(ItemConvertible item, boolean aventail, boolean rimmed, boolean besagews) {
        ItemStack armorStack = new ItemStack(item);
        if (aventail) armorStack.getOrCreateNbt().putBoolean("kh_aventail", true);
        if (rimmed) armorStack.getOrCreateNbt().putBoolean("kh_rimmed", true);
        if (besagews) armorStack.getOrCreateNbt().putBoolean("kh_besagews", true);
        return armorStack;
    }

    private static ItemStack ammoStack(ItemConvertible item) {
        return new ItemStack(item);
    }

    private static void ammo(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                ammoStack(ModItems.SWALLOWTAIL_ARROW.get()),
                ammoStack(ModItems.BODKIN_ARROW.get()),
                ammoStack(ModItems.BROADHEAD_ARROW.get()),
                ammoStack(ModItems.CLOTH_ARROW.get())
        ));
    }

    private static void weapons(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                weaponStack(ModItems.DAGGER.get()),
                weaponStack(ModItems.STILETTO.get()),
                weaponStack(ModItems.RAPIER.get()),
                weaponStack(ModItems.SWORD.get()),
                weaponStack(ModItems.V_SWORD.get()),
                weaponStack(ModItems.ARMING_SWORD.get()),
                weaponStack(ModItems.AXE.get()),
                weaponStack(ModItems.BROAD_AXE.get()),
                weaponStack(ModItems.CROOKED_AXE.get()),
                weaponStack(ModItems.STRAIGHT_CROOKED_AXE.get()),
                weaponStack(ModItems.MACE.get()),
                weaponStack(ModItems.SPIKED_MACE.get()),
                weaponStack(ModItems.FLAIL.get()),
                weaponStack(ModItems.BALL_FLAIL.get()),
                weaponStack(ModItems.HAMMER.get()),
                weaponStack(ModItems.WAR_HAMMER.get()),
                weaponStack(ModItems.LONGSWORD.get()),
                weaponStack(ModItems.V_LONGSWORD.get()),
                weaponStack(ModItems.FALCHION.get()),
                weaponStack(ModItems.SCIMITAR.get()),
                weaponStack(ModItems.PITCHFORK.get()),
                weaponStack(ModItems.SPEAR.get()),
                weaponStack(ModItems.PIKE.get()),
                weaponStack(ModItems.BILLHOOK.get()),
                weaponStack(ModItems.GLAIVE.get()),
                weaponStack(ModItems.CURVED_GLAIVE.get()),
                weaponStack(ModItems.HALBERD.get()),
                weaponStack(ModItems.LANCE.get()),
                weaponStack(ModItems.WOODEN_LANCE.get()),
                weaponStack(ModItems.POLEAXE.get()),
                weaponStack(ModItems.POLEHAMMER.get()),
                weaponStack(ModItems.BEC_DE_CORBIN.get()),
                weaponStack(ModItems.MORNING_STAR.get()),
                weaponStack(ModItems.BARDICHE.get()),
                weaponStack(ModItems.WARSWORD.get()),
                weaponStack(ModItems.WARSWORD_CLAYMORE.get()),
                weaponStack(ModItems.WARSWORD_FLAMBERGE.get()),
                weaponStack(ModItems.WARSWORD_ZWEIHANDER.get()),
                weaponStack(ModItems.WARDART.get()),

                weaponStack(ModItems.LONGBOW.get()),
                weaponStack(ModItems.HEAVY_CROSSBOW.get()),
                weaponStack(ModItems.ARQUEBUS.get()),
                weaponStack(ModItems.HANDGONNE.get())
        ));
    }

    private static void armors(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                armorStack(ModItems.QUILTED_COIF.get(), false, false, false),
                armorStack(ModItems.GAMBESON.get(), false, false, false),
                armorStack(ModItems.GAMBESON_BREECHES.get(), false, false, false),
                armorStack(ModItems.GAMBESON_BOOTS.get(), false, false, false),

                armorStack(ModItems.MAIL_COIF.get(), false, false, false),
                armorStack(ModItems.HAUBERK.get(), false, false, false),
                armorStack(ModItems.MAIL_BREECHES.get(), false, false, false),
                armorStack(ModItems.MAIL_BOOTS.get(), false, false, false),

                armorStack(ModItems.BARBUTE_NO_VISOR.get(), false, false, false),
                armorStack(ModItems.BASCINET_NO_VISOR.get(), false, false, false),
                armorStack(ModItems.KETTLE_HELM.get(), false, false, false),
                armorStack(ModItems.NASAL_HELM.get(), false, false, false),
                armorStack(ModItems.VIKING_HELM.get(), false, false, false),
                armorStack(ModItems.ARMET.get(), false, false, false),
                armorStack(ModItems.ARMET_2.get(), false, false, false),
                armorStack(ModItems.BARBUTE.get(), false, false, false),
                armorStack(ModItems.BASCINET.get(), false, false, false),
                armorStack(ModItems.CAGE.get(), false, false, false),
                armorStack(ModItems.CAGE_2.get(), false, false, false),
                armorStack(ModItems.FLAT_BASCINET.get(), false, false, false),
                armorStack(ModItems.GREAT_HELM.get(), false, false, false),
                armorStack(ModItems.GREAT_HELM_2.get(), false, false, false),
                armorStack(ModItems.SALLET.get(), false, false, false),

                armorStack(ModItems.AVENTAIL.get(), false, false, false),
                armorStack(ModItems.BARBUTE_NO_VISOR.get(), true, false, false),
                armorStack(ModItems.BASCINET_NO_VISOR.get(), true, false, false),
                armorStack(ModItems.KETTLE_HELM.get(), true, false, false),
                armorStack(ModItems.NASAL_HELM.get(), true, false, false),
                armorStack(ModItems.VIKING_HELM.get(), true, false, false),
                armorStack(ModItems.ARMET.get(), true, false, false),
                armorStack(ModItems.ARMET_2.get(), true, false, false),
                armorStack(ModItems.BARBUTE.get(), true, false, false),
                armorStack(ModItems.BASCINET.get(), true, false, false),
                armorStack(ModItems.CAGE.get(), true, false, false),
                armorStack(ModItems.CAGE_2.get(), true, false, false),
                armorStack(ModItems.FLAT_BASCINET.get(), true, false, false),
                armorStack(ModItems.GREAT_HELM.get(), true, false, false),
                armorStack(ModItems.GREAT_HELM_2.get(), true, false, false),
                armorStack(ModItems.SALLET.get(), true, false, false),

                armorStack(ModItems.FROGMOUTH.get(), false, false, false),
                armorStack(ModItems.GREAT_ARMET.get(), false, false, false),
                armorStack(ModItems.GREAT_ARMET_2.get(), false, false, false),
                armorStack(ModItems.GREAT_BASCINET.get(), false, false, false),
                armorStack(ModItems.GREAT_HOUNDSKUL_BASCINET.get(), false, false, false),
                armorStack(ModItems.MAXIMILLIAN_HELMET.get(), false, false, false),

                armorStack(ModItems.CHAPERON.get(), false, false, false),
                armorStack(ModItems.GILDED_CHAPERON.get(), false, false, false),

                armorStack(ModItems.PLUME.get(), false, false, false),

                armorStack(ModItems.BRIGANDINE.get(), false, false, false),
                armorStack(ModItems.BRIG_BREASTPLATE.get(), false, false, false),
                armorStack(ModItems.BRIG_BREASTPLATE_TASSETS.get(), false, false, false),
                armorStack(ModItems.PLATE_CUIRASS.get(), false, false, false),
                armorStack(ModItems.PLATE_CUIRASS_TASSETS.get(), false, false, false),
                armorStack(ModItems.MAXIMILLIAN_CUIRASS.get(), false, false, false),
                armorStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), false, false, false),
                armorStack(ModItems.XIIII_PLATE_CUIRASS.get(), false, false, false),
                armorStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(), false, false, false),
                armorStack(ModItems.XIIII_PLATE_BREASTPLATE.get(), false, false, false),

                armorStack(ModItems.SURCOAT.get(), false, false, false),
                armorStack(ModItems.SURCOAT_SLEEVELESS.get(), false, false, false),

                armorStack(ModItems.RIM_GUARDS.get(), false, false, false),
                armorStack(ModItems.BESAGEWS.get(), false, false, false),
                armorStack(ModItems.MAIL_PAULDRON.get(), false, false, false),
                armorStack(ModItems.BRIGANDINE_PAULDRON.get(), false, false, false),
                armorStack(ModItems.PLATE_PAULDRON.get(), false, false, false),
                armorStack(ModItems.MAIL_PAULDRON.get(), false, false, true),
                armorStack(ModItems.BRIGANDINE_PAULDRON.get(), false, false, true),
                armorStack(ModItems.PLATE_PAULDRON.get(), false, false, true),
                armorStack(ModItems.PLATE_PAULDRON.get(), false, true, false),
                armorStack(ModItems.PLATE_PAULDRON.get(), false, true, true),

                armorStack(ModItems.GAUNTLET.get(), false, false, false),
                armorStack(ModItems.BRIGANDINE_REREBRACE.get(), false, false, false),
                armorStack(ModItems.PLATE_REREBRACE.get(), false, false, false),
                armorStack(ModItems.BRIGANDINE_CHAUSSES.get(), false, false, false),
                armorStack(ModItems.PLATE_CHAUSSES.get(), false, false, false),

                armorStack(ModItems.SABATONS.get(), false, false, false),

                armorStack(ModItems.HOOD.get(), false, false, false),
                armorStack(ModItems.TORN_HOOD.get(), false, false, false),
                armorStack(ModItems.JESTER_HOOD.get(), false, false, false),
                armorStack(ModItems.HELMET_HOOD.get(), false, false, false),
                armorStack(ModItems.HELMET_TORN_HOOD.get(), false, false, false),

                armorStack(ModItems.CLOAK.get(), false, false, false),
                armorStack(ModItems.TORN_CLOAK.get(), false, false, false),

                armorStack(ModItems.HORSE_BARDING.get(), false, false, false)
        ));
    }

    public static final ItemGroupTab KH_WEAPONS_TAB = new ItemGroupTab(
            Icon.of(ModItems.WARSWORD_ZWEIHANDER.get()),
            Text.translatable("text.itemgroup.kingdomsieges.tab.kh_weapons").formatted(Formatting.WHITE),
            ModItemGroups::weapons,
            TABS,
            true
    );

    public static final ItemGroupTab KH_AMMO_TAB = new ItemGroupTab(
            Icon.of(ModItems.BROADHEAD_ARROW.get()),
            Text.translatable("text.itemgroup.kingdomsieges.tab.kh_ammo").formatted(Formatting.WHITE),
            ModItemGroups::ammo,
            TABS,
            true
    );

    public static final ItemGroupTab KH_ARMORS_TAB = new ItemGroupTab(
            Icon.of(ModItems.QUILTED_COIF.get()),
            Text.translatable("text.itemgroup.kingdomsieges.tab.kh_armors").formatted(Formatting.WHITE),
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
        group.tabs.add(KH_WEAPONS_TAB);
        group.tabs.add(KH_ARMORS_TAB);
        group.tabs.add(KH_AMMO_TAB);
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