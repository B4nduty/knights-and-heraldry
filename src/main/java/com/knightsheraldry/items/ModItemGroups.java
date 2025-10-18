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

    private static ItemStack armorStack(ItemConvertible item, boolean rimmed, boolean besagews) {
        ItemStack armorStack = new ItemStack(item);
        if (rimmed) armorStack.getOrCreateNbt().putBoolean("rimmed", true);
        if (besagews) armorStack.getOrCreateNbt().putBoolean("besagews", true);
        return armorStack;
    }

    private static ItemStack decorStack(ItemConvertible item) {
        return new ItemStack(item);
    }

    private static void deco(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                decorStack(ModItems.PLUME.get()),
                decorStack(ModItems.TRI_PLUME.get()),
                decorStack(ModItems.FLUFFY_PLUME.get()),
                decorStack(ModItems.TORSE.get()),
                decorStack(ModItems.TEUTONIC_SNAKES.get()),
                decorStack(ModItems.TEUTONIC_BLACK_SNAKES.get()),
                decorStack(ModItems.GOLD_HORNS.get()),
                decorStack(ModItems.BLACK_HORNS.get()),
                decorStack(ModItems.TEUTONIC_GOLD_WINGS.get()),
                decorStack(ModItems.TEUTONIC_BLACK_WINGS.get()),
                decorStack(ModItems.TEUTONIC_WINGS_BALL_ENDS.get()),
                decorStack(ModItems.TEUTONIC_WINGS_SHARP_ENDS.get()),
                decorStack(ModItems.DRAGON.get()),
                decorStack(ModItems.LION.get()),
                decorStack(ModItems.SNAKE.get()),
                decorStack(ModItems.UNICORN.get()),
                decorStack(ModItems.STAG.get()),
                decorStack(ModItems.BOAR.get()),
                decorStack(ModItems.EAGLE.get()),
                decorStack(ModItems.PEGASUS.get())
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
                weaponStack(ModItems.GREATSWORD.get()),
                weaponStack(ModItems.CLAYMORE.get()),
                weaponStack(ModItems.FLAMBERGE.get()),
                weaponStack(ModItems.ZWEIHANDER.get()),
                weaponStack(ModItems.WARDART.get()),

                weaponStack(ModItems.LONGBOW.get()),
                weaponStack(ModItems.HEAVY_CROSSBOW.get()),
                weaponStack(ModItems.ARQUEBUS.get()),
                weaponStack(ModItems.HANDGONNE.get()),

                decorStack(ModItems.SWALLOWTAIL_ARROW.get()),
                decorStack(ModItems.BODKIN_ARROW.get()),
                decorStack(ModItems.BROADHEAD_ARROW.get()),
                decorStack(ModItems.CLOTH_ARROW.get())
        ));
    }

    private static void armors(ItemGroup.DisplayContext ctx, ItemGroup.Entries stacks) {
        stacks.addAll(List.of(
                armorStack(ModItems.QUILTED_COIF.get(), false, false),
                armorStack(ModItems.GAMBESON.get(), false, false),
                armorStack(ModItems.GAMBESON_BREECHES.get(), false, false),
                armorStack(ModItems.GAMBESON_BOOTS.get(), false, false),

                armorStack(ModItems.MAIL_COIF.get(), false, false),
                armorStack(ModItems.HAUBERK.get(), false, false),
                armorStack(ModItems.MAIL_BREECHES.get(), false, false),
                armorStack(ModItems.MAIL_BOOTS.get(),false, false),

                armorStack(ModItems.BARBUTE.get(),false, false),
                armorStack(ModItems.BASCINET.get(),false, false),
                armorStack(ModItems.KETTLE_HELM.get(),false, false),
                armorStack(ModItems.NASAL_HELM.get(),false, false),
                armorStack(ModItems.VIKING_HELM.get(),false, false),
                armorStack(ModItems.ARMET.get(),false, false),
                armorStack(ModItems.ARMET_2.get(),false, false),
                armorStack(ModItems.VISORED_BARBUTE.get(),false, false),
                armorStack(ModItems.HOUNDSKULL.get(),false, false),
                armorStack(ModItems.CAGE.get(),false, false),
                armorStack(ModItems.CAGE_2.get(),false, false),
                armorStack(ModItems.VISORED_BASCINET.get(),false, false),
                armorStack(ModItems.GREAT_HELM.get(),false, false),
                armorStack(ModItems.GREAT_HELM_2.get(),false, false),
                armorStack(ModItems.SALLET.get(),false, false),

                armorStack(ModItems.DARK_BARBUTE.get(),false, false),
                armorStack(ModItems.DARK_BASCINET.get(),false, false),
                armorStack(ModItems.DARK_KETTLE_HELM.get(),false, false),
                armorStack(ModItems.DARK_NASAL_HELM.get(),false, false),
                armorStack(ModItems.DARK_VIKING_HELM.get(),false, false),
                armorStack(ModItems.DARK_ARMET.get(),false, false),
                armorStack(ModItems.DARK_ARMET_2.get(),false, false),
                armorStack(ModItems.DARK_VISORED_BARBUTE.get(),false, false),
                armorStack(ModItems.DARK_HOUNDSKULL.get(),false, false),
                armorStack(ModItems.DARK_CAGE.get(),false, false),
                armorStack(ModItems.DARK_CAGE_2.get(),false, false),
                armorStack(ModItems.DARK_VISORED_BASCINET.get(),false, false),
                armorStack(ModItems.DARK_GREAT_HELM.get(),false, false),
                armorStack(ModItems.DARK_GREAT_HELM_2.get(),false, false),
                armorStack(ModItems.DARK_SALLET.get(),false, false),

                armorStack(ModItems.AVENTAIL.get(),false, false),

                armorStack(ModItems.FROGMOUTH.get(),false, false),
                armorStack(ModItems.GREAT_ARMET.get(),false, false),
                armorStack(ModItems.GREAT_ARMET_2.get(),false, false),
                armorStack(ModItems.GREAT_BASCINET.get(),false, false),
                armorStack(ModItems.GREAT_HOUNDSKUL_BASCINET.get(),false, false),
                armorStack(ModItems.MAXIMILLIAN_HELMET.get(),false, false),

                armorStack(ModItems.DARK_FROGMOUTH.get(),false, false),
                armorStack(ModItems.DARK_GREAT_ARMET.get(),false, false),
                armorStack(ModItems.DARK_GREAT_ARMET_2.get(),false, false),
                armorStack(ModItems.DARK_GREAT_BASCINET.get(),false, false),
                armorStack(ModItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(),false, false),
                armorStack(ModItems.DARK_MAXIMILLIAN_HELMET.get(),false, false),

                armorStack(ModItems.CHAPERON.get(),false, false),
                armorStack(ModItems.GILDED_CHAPERON.get(),false, false),

                armorStack(ModItems.BRIGANDINE.get(),false, false),
                armorStack(ModItems.BRIG_BREASTPLATE.get(),false, false),
                armorStack(ModItems.BRIG_BREASTPLATE_TASSETS.get(),false, false),
                armorStack(ModItems.PLATE_CUIRASS.get(),false, false),
                armorStack(ModItems.PLATE_CUIRASS_TASSETS.get(),false, false),
                armorStack(ModItems.MAXIMILLIAN_CUIRASS.get(),false, false),
                armorStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(),false, false),
                armorStack(ModItems.XIIII_PLATE_CUIRASS.get(),false, false),
                armorStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(),false, false),
                armorStack(ModItems.XIIII_PLATE_BREASTPLATE.get(),false, false),

                armorStack(ModItems.DARK_BRIGANDINE.get(),false, false),
                armorStack(ModItems.DARK_BRIG_BREASTPLATE.get(),false, false),
                armorStack(ModItems.DARK_BRIG_BREASTPLATE_TASSETS.get(),false, false),
                armorStack(ModItems.DARK_PLATE_CUIRASS.get(),false, false),
                armorStack(ModItems.DARK_PLATE_CUIRASS_TASSETS.get(),false, false),
                armorStack(ModItems.DARK_MAXIMILLIAN_CUIRASS.get(),false, false),
                armorStack(ModItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS.get(),false, false),
                armorStack(ModItems.DARK_XIIII_PLATE_CUIRASS.get(),false, false),
                armorStack(ModItems.DARK_XIIII_PLATE_CUIRASS_TASSETS.get(),false, false),
                armorStack(ModItems.DARK_XIIII_PLATE_BREASTPLATE.get(),false, false),

                armorStack(ModItems.SURCOAT.get(),false, false),
                armorStack(ModItems.SURCOAT_SLEEVELESS.get(),false, false),
                armorStack(ModItems.CIVILIAN_SURCOAT.get(),false, false),
                armorStack(ModItems.GIORNEA.get(),false, false),

                armorStack(ModItems.RIM_GUARDS.get(),false, false),
                armorStack(ModItems.BESAGEWS.get(),false, false),
                armorStack(ModItems.MAIL_SPAULDERS.get(),false, false),
                armorStack(ModItems.BRIGANDINE_SPAULDERS.get(),false, false),
                armorStack(ModItems.PLATE_SPAULDERS.get(),false, false),
                armorStack(ModItems.MAIL_SPAULDERS.get(),false, true),
                armorStack(ModItems.BRIGANDINE_SPAULDERS.get(),false, true),
                armorStack(ModItems.PLATE_SPAULDERS.get(),false, true),
                armorStack(ModItems.PLATE_SPAULDERS.get(),true, false),
                armorStack(ModItems.PLATE_SPAULDERS.get(),true, true),

                armorStack(ModItems.DARK_BRIGANDINE_SPAULDERS.get(),false, false),
                armorStack(ModItems.DARK_PLATE_SPAULDERS.get(),false, false),
                armorStack(ModItems.DARK_BRIGANDINE_SPAULDERS.get(),false, true),
                armorStack(ModItems.DARK_PLATE_SPAULDERS.get(),false, true),
                armorStack(ModItems.DARK_PLATE_SPAULDERS.get(),true, false),
                armorStack(ModItems.DARK_PLATE_SPAULDERS.get(),true, true),

                armorStack(ModItems.GAUNTLET.get(),false, false),
                armorStack(ModItems.BRIGANDINE_HARNESS.get(),false, false),
                armorStack(ModItems.PLATE_HARNESS.get(),false, false),
                armorStack(ModItems.BRIGANDINE_CUISSES.get(),false, false),
                armorStack(ModItems.PLATE_CUISSES.get(),false, false),
                armorStack(ModItems.GREAVES.get(),false, false),

                armorStack(ModItems.DARK_GAUNTLET.get(),false, false),
                armorStack(ModItems.DARK_BRIGANDINE_HARNESS.get(),false, false),
                armorStack(ModItems.DARK_PLATE_HARNESS.get(),false, false),
                armorStack(ModItems.DARK_BRIGANDINE_CUISSES.get(),false, false),
                armorStack(ModItems.DARK_PLATE_CUISSES.get(),false, false),
                armorStack(ModItems.DARK_GREAVES.get(),false, false),

                armorStack(ModItems.SABATONS.get(),false, false),

                armorStack(ModItems.DARK_SABATONS.get(),false, false),

                armorStack(ModItems.HOOD.get(),false, false),
                armorStack(ModItems.TORN_HOOD.get(),false, false),
                armorStack(ModItems.JESTER_HOOD.get(),false, false),
                armorStack(ModItems.HELMET_HOOD.get(),false, false),
                armorStack(ModItems.HELMET_TORN_HOOD.get(),false, false),

                armorStack(ModItems.CLOAK.get(),false, false),
                armorStack(ModItems.TORN_CLOAK.get(),false, false),

                armorStack(ModItems.HORSE_BARDING.get(),false, false),

                armorStack(ModItems.DARK_HORSE_BARDING.get(),false, false),

                armorStack(ModItems.DARKENED_ARMOR_TEMPLATE.get(),false, false)
        ));
    }

    public static final ItemGroupTab KH_WEAPONS_TAB = new ItemGroupTab(
            Icon.of(ModItems.ZWEIHANDER.get()),
            Text.translatable("text.itemgroup.kingdomsieges.tab.kh_weapons").formatted(Formatting.WHITE),
            ModItemGroups::weapons,
            TABS,
            true
    );

    public static final ItemGroupTab KH_DECO_TAB = new ItemGroupTab(
            Icon.of(ModItems.PLUME.get()),
            Text.translatable("text.itemgroup.kingdomsieges.tab.kh_deco").formatted(Formatting.WHITE),
            ModItemGroups::deco,
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
        group.tabs.add(KH_DECO_TAB);
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