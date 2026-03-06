package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.stoneycore.StoneyCore;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.itemgroup.gui.ItemGroupTab;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public interface ModItemGroups {
    ResourceLocation DISCORD_ICON = new ResourceLocation(StoneyCore.MOD_ID, "textures/gui/button/discord.png");
    ResourceLocation BACKGROUND = new ResourceLocation(StoneyCore.MOD_ID, "textures/gui/group.png");
    ResourceLocation TABS = new ResourceLocation(StoneyCore.MOD_ID, "textures/gui/tabs.png");

    private static ItemStack itemStack(ItemLike item) {
        return new ItemStack(item);
    }

    private static void deco(CreativeModeTab.ItemDisplayParameters ctx, CreativeModeTab.Output stacks) {
        stacks.acceptAll(List.of(
                itemStack(ModItems.PLUME),
                itemStack(ModItems.TRI_PLUME),
                itemStack(ModItems.FLUFFY_PLUME),
                itemStack(ModItems.TORSE),
                itemStack(ModItems.TEUTONIC_SNAKES),
                itemStack(ModItems.TEUTONIC_BLACK_SNAKES),
                itemStack(ModItems.GOLD_HORNS),
                itemStack(ModItems.BLACK_HORNS),
                itemStack(ModItems.TEUTONIC_GOLD_WINGS),
                itemStack(ModItems.TEUTONIC_BLACK_WINGS),
                itemStack(ModItems.TEUTONIC_WINGS_BALL_ENDS),
                itemStack(ModItems.TEUTONIC_WINGS_SHARP_ENDS),
                itemStack(ModItems.DRAGON),
                itemStack(ModItems.LION),
                itemStack(ModItems.SNAKE),
                itemStack(ModItems.UNICORN),
                itemStack(ModItems.STAG),
                itemStack(ModItems.BOAR),
                itemStack(ModItems.EAGLE),
                itemStack(ModItems.PEGASUS)
        ));
    }

    private static void weapons(CreativeModeTab.ItemDisplayParameters ctx, CreativeModeTab.Output stacks) {
        stacks.acceptAll(List.of(
                itemStack(ModItems.DAGGER),
                itemStack(ModItems.STILETTO),
                itemStack(ModItems.RAPIER),
                itemStack(ModItems.SWORD),
                itemStack(ModItems.V_SWORD),
                itemStack(ModItems.ARMING_SWORD),
                itemStack(ModItems.AXE),
                itemStack(ModItems.BROAD_AXE),
                itemStack(ModItems.CROOKED_AXE),
                itemStack(ModItems.STRAIGHT_CROOKED_AXE),
                itemStack(ModItems.MACE),
                itemStack(ModItems.SPIKED_MACE),
                itemStack(ModItems.FLAIL),
                itemStack(ModItems.BALL_FLAIL),
                itemStack(ModItems.HAMMER),
                itemStack(ModItems.WAR_HAMMER),
                itemStack(ModItems.LONGSWORD),
                itemStack(ModItems.V_LONGSWORD),
                itemStack(ModItems.FALCHION),
                itemStack(ModItems.SCIMITAR),
                itemStack(ModItems.PITCHFORK),
                itemStack(ModItems.SPEAR),
                itemStack(ModItems.PIKE),
                itemStack(ModItems.BILLHOOK),
                itemStack(ModItems.GLAIVE),
                itemStack(ModItems.CURVED_GLAIVE),
                itemStack(ModItems.HALBERD),
                itemStack(ModItems.LANCE),
                itemStack(ModItems.WOODEN_LANCE),
                itemStack(ModItems.POLEAXE),
                itemStack(ModItems.POLEHAMMER),
                itemStack(ModItems.BEC_DE_CORBIN),
                itemStack(ModItems.MORNING_STAR),
                itemStack(ModItems.BARDICHE),
                itemStack(ModItems.GREATSWORD),
                itemStack(ModItems.CLAYMORE),
                itemStack(ModItems.FLAMBERGE),
                itemStack(ModItems.ZWEIHANDER),
                itemStack(ModItems.WARDART),

                itemStack(ModItems.LONGBOW),
                itemStack(ModItems.HEAVY_CROSSBOW),
                itemStack(ModItems.ARQUEBUS),
                itemStack(ModItems.HANDGONNE),

                itemStack(ModItems.SWALLOWTAIL_ARROW),
                itemStack(ModItems.BODKIN_ARROW),
                itemStack(ModItems.BROADHEAD_ARROW),
                itemStack(ModItems.CLOTH_ARROW)
        ));
    }

    private static void armors(CreativeModeTab.ItemDisplayParameters ctx, CreativeModeTab.Output stacks) {
        stacks.acceptAll(List.of(
                itemStack(ModItems.QUILTED_COIF),
                itemStack(ModItems.GAMBESON),
                itemStack(ModItems.GAMBESON_BREECHES),
                itemStack(ModItems.GAMBESON_BOOTS),

                itemStack(ModItems.MAIL_COIF),
                itemStack(ModItems.HAUBERK),
                itemStack(ModItems.MAIL_BREECHES),
                itemStack(ModItems.MAIL_BOOTS),

                itemStack(ModItems.BARBUTE),
                itemStack(ModItems.BASCINET),
                itemStack(ModItems.KETTLE_HELM),
                itemStack(ModItems.NASAL_HELM),
                itemStack(ModItems.VIKING_HELM),
                itemStack(ModItems.BURGONET),

                itemStack(ModItems.DARK_BARBUTE),
                itemStack(ModItems.DARK_BASCINET),
                itemStack(ModItems.DARK_KETTLE_HELM),
                itemStack(ModItems.DARK_NASAL_HELM),
                itemStack(ModItems.DARK_VIKING_HELM),
                itemStack(ModItems.DARK_BURGONET),

                itemStack(ModItems.GOLDEN_BARBUTE),
                itemStack(ModItems.GOLDEN_BASCINET),
                itemStack(ModItems.GOLDEN_KETTLE_HELM),
                itemStack(ModItems.GOLDEN_NASAL_HELM),
                itemStack(ModItems.GOLDEN_VIKING_HELM),
                itemStack(ModItems.GOLDEN_BURGONET),

                itemStack(ModItems.ARMET),
                itemStack(ModItems.ARMET_2),
                itemStack(ModItems.VISORED_BARBUTE),
                itemStack(ModItems.HOUNDSKULL),
                itemStack(ModItems.CAGE),
                itemStack(ModItems.VISORED_BASCINET),
                itemStack(ModItems.GREAT_HELM),
                itemStack(ModItems.GREAT_HELM_2),
                itemStack(ModItems.SALLET),
                itemStack(ModItems.BURGONET_FALLING_BUFFE),
                itemStack(ModItems.CLOSE_HELM),

                itemStack(ModItems.DARK_ARMET),
                itemStack(ModItems.DARK_ARMET_2),
                itemStack(ModItems.DARK_VISORED_BARBUTE),
                itemStack(ModItems.DARK_HOUNDSKULL),
                itemStack(ModItems.DARK_CAGE),
                itemStack(ModItems.DARK_VISORED_BASCINET),
                itemStack(ModItems.DARK_GREAT_HELM),
                itemStack(ModItems.DARK_GREAT_HELM_2),
                itemStack(ModItems.DARK_SALLET),
                itemStack(ModItems.DARK_BURGONET_FALLING_BUFFE),
                itemStack(ModItems.DARK_CLOSE_HELM),

                itemStack(ModItems.GOLDEN_ARMET),
                itemStack(ModItems.GOLDEN_ARMET_2),
                itemStack(ModItems.GOLDEN_VISORED_BARBUTE),
                itemStack(ModItems.GOLDEN_HOUNDSKULL),
                itemStack(ModItems.GOLDEN_CAGE),
                itemStack(ModItems.GOLDEN_VISORED_BASCINET),
                itemStack(ModItems.GOLDEN_GREAT_HELM),
                itemStack(ModItems.GOLDEN_GREAT_HELM_2),
                itemStack(ModItems.GOLDEN_SALLET),
                itemStack(ModItems.GOLDEN_BURGONET_FALLING_BUFFE),
                itemStack(ModItems.GOLDEN_CLOSE_HELM),

                itemStack(ModItems.FROGMOUTH),
                itemStack(ModItems.GREAT_ARMET),
                itemStack(ModItems.GREAT_ARMET_2),
                itemStack(ModItems.GREAT_BASCINET),
                itemStack(ModItems.GREAT_HOUNDSKUL_BASCINET),
                itemStack(ModItems.MAXIMILLIAN_HELMET),
                itemStack(ModItems.SAVOYARD),

                itemStack(ModItems.DARK_FROGMOUTH),
                itemStack(ModItems.DARK_GREAT_ARMET),
                itemStack(ModItems.DARK_GREAT_ARMET_2),
                itemStack(ModItems.DARK_GREAT_BASCINET),
                itemStack(ModItems.DARK_GREAT_HOUNDSKUL_BASCINET),
                itemStack(ModItems.DARK_MAXIMILLIAN_HELMET),
                itemStack(ModItems.DARK_SAVOYARD),

                itemStack(ModItems.GOLDEN_FROGMOUTH),
                itemStack(ModItems.GOLDEN_GREAT_ARMET),
                itemStack(ModItems.GOLDEN_GREAT_ARMET_2),
                itemStack(ModItems.GOLDEN_GREAT_BASCINET),
                itemStack(ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET),
                itemStack(ModItems.GOLDEN_MAXIMILLIAN_HELMET),
                itemStack(ModItems.GOLDEN_SAVOYARD),

                itemStack(ModItems.AVENTAIL),
                itemStack(ModItems.BEVOR),
                itemStack(ModItems.DARK_BEVOR),
                itemStack(ModItems.GOLDEN_BEVOR),

                itemStack(ModItems.CHAPERON),
                itemStack(ModItems.GILDED_CHAPERON),

                itemStack(ModItems.BRIGANDINE),
                itemStack(ModItems.BRIG_BREASTPLATE),
                itemStack(ModItems.BRIG_BREASTPLATE_TASSETS),
                itemStack(ModItems.PLATE_CUIRASS),
                itemStack(ModItems.PLATE_CUIRASS_TASSETS),
                itemStack(ModItems.MAXIMILLIAN_CUIRASS),
                itemStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS),
                itemStack(ModItems.XIIII_PLATE_CUIRASS),
                itemStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS),
                itemStack(ModItems.XIIII_PLATE_BREASTPLATE),

                itemStack(ModItems.DARK_BRIGANDINE),
                itemStack(ModItems.DARK_BRIG_BREASTPLATE),
                itemStack(ModItems.DARK_BRIG_BREASTPLATE_TASSETS),
                itemStack(ModItems.DARK_PLATE_CUIRASS),
                itemStack(ModItems.DARK_PLATE_CUIRASS_TASSETS),
                itemStack(ModItems.DARK_MAXIMILLIAN_CUIRASS),
                itemStack(ModItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS),
                itemStack(ModItems.DARK_XIIII_PLATE_CUIRASS),
                itemStack(ModItems.DARK_XIIII_PLATE_CUIRASS_TASSETS),
                itemStack(ModItems.DARK_XIIII_PLATE_BREASTPLATE),

                itemStack(ModItems.GOLDEN_BRIGANDINE),
                itemStack(ModItems.GOLDEN_BRIG_BREASTPLATE),
                itemStack(ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS),
                itemStack(ModItems.GOLDEN_PLATE_CUIRASS),
                itemStack(ModItems.GOLDEN_PLATE_CUIRASS_TASSETS),
                itemStack(ModItems.GOLDEN_MAXIMILLIAN_CUIRASS),
                itemStack(ModItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS),
                itemStack(ModItems.GOLDEN_XIIII_PLATE_CUIRASS),
                itemStack(ModItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS),
                itemStack(ModItems.GOLDEN_XIIII_PLATE_BREASTPLATE),

                itemStack(ModItems.SURCOAT),
                itemStack(ModItems.SURCOAT_SLEEVELESS),
                itemStack(ModItems.CIVILIAN_SURCOAT),
                itemStack(ModItems.GIORNEA),

                itemStack(ModItems.RIM_GUARDS),
                itemStack(ModItems.BESAGEWS),

                itemStack(ModItems.MAIL_SPAULDERS),
                itemStack(ModItems.BRIGANDINE_SPAULDERS),
                itemStack(ModItems.PLATE_SPAULDERS),
                itemStack(ModItems.MAIL_SPAULDERS_BESAGEWS),
                itemStack(ModItems.BRIGANDINE_SPAULDERS_BESAGEWS),
                itemStack(ModItems.PLATE_SPAULDERS_BESAGEWS),
                itemStack(ModItems.PLATE_SPAULDERS_RIMMED),
                itemStack(ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED),

                itemStack(ModItems.DARK_BRIGANDINE_SPAULDERS),
                itemStack(ModItems.DARK_PLATE_SPAULDERS),
                itemStack(ModItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS),
                itemStack(ModItems.DARK_PLATE_SPAULDERS_BESAGEWS),
                itemStack(ModItems.DARK_PLATE_SPAULDERS_RIMMED),
                itemStack(ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED),

                itemStack(ModItems.GOLDEN_MAIL_SPAULDERS),
                itemStack(ModItems.GOLDEN_BRIGANDINE_SPAULDERS),
                itemStack(ModItems.GOLDEN_PLATE_SPAULDERS),
                itemStack(ModItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS),
                itemStack(ModItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS),
                itemStack(ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS),
                itemStack(ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED),
                itemStack(ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED),

                itemStack(ModItems.GAUNTLET),
                itemStack(ModItems.BRIGANDINE_HARNESS),
                itemStack(ModItems.PLATE_HARNESS),

                itemStack(ModItems.DARK_GAUNTLET),
                itemStack(ModItems.DARK_BRIGANDINE_HARNESS),
                itemStack(ModItems.DARK_PLATE_HARNESS),

                itemStack(ModItems.GOLDEN_GAUNTLET),
                itemStack(ModItems.GOLDEN_BRIGANDINE_HARNESS),
                itemStack(ModItems.GOLDEN_PLATE_HARNESS),

                itemStack(ModItems.BRIGANDINE_CUISSES),
                itemStack(ModItems.PLATE_CUISSES),

                itemStack(ModItems.DARK_BRIGANDINE_CUISSES),
                itemStack(ModItems.DARK_PLATE_CUISSES),

                itemStack(ModItems.GOLDEN_BRIGANDINE_CUISSES),
                itemStack(ModItems.GOLDEN_PLATE_CUISSES),

                itemStack(ModItems.GREAVES),

                itemStack(ModItems.DARK_GREAVES),

                itemStack(ModItems.GOLDEN_GREAVES),

                itemStack(ModItems.SABATONS),

                itemStack(ModItems.DARK_SABATONS),

                itemStack(ModItems.GOLDEN_SABATONS),

                itemStack(ModItems.HOOD),
                itemStack(ModItems.TORN_HOOD),
                itemStack(ModItems.JESTER_HOOD),
                itemStack(ModItems.HELMET_HOOD),
                itemStack(ModItems.HELMET_TORN_HOOD),

                itemStack(ModItems.CLOAK),
                itemStack(ModItems.TORN_CLOAK),

                itemStack(ModItems.HORSE_BARDING),

                itemStack(ModItems.DARK_HORSE_BARDING),

                itemStack(ModItems.GOLDEN_HORSE_BARDING)
        ));
    }

    ItemGroupTab KH_WEAPONS_TAB = new ItemGroupTab(
            Icon.of(ModItems.ZWEIHANDER),
            Component.translatable("component.itemgroup.knightsheraldry.tab.kh_weapons").withStyle(ChatFormatting.WHITE),
            ModItemGroups::weapons,
            TABS,
            true
    );

    ItemGroupTab KH_DECO_TAB = new ItemGroupTab(
            Icon.of(ModItems.PLUME),
            Component.translatable("component.itemgroup.knightsheraldry.tab.kh_deco").withStyle(ChatFormatting.WHITE),
            ModItemGroups::deco,
            TABS,
            true
    );

    ItemGroupTab KH_ARMORS_TAB = new ItemGroupTab(
            Icon.of(ModItems.QUILTED_COIF),
            Component.translatable("component.itemgroup.knightsheraldry.tab.kh_armors").withStyle(ChatFormatting.WHITE),
            ModItemGroups::armors,
            TABS,
            true
    );

    OwoItemGroup KH_TAB = OwoItemGroup
        .builder(new ResourceLocation(KnightsHeraldry.MOD_ID, "kh_tab"), () -> Icon.of(new ResourceLocation(KnightsHeraldry.MOD_ID,
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
    
    static void registerItemGroups() {
        KH_TAB.initialize();
    }

    @Environment(EnvType.CLIENT)
    class LinkButton implements OwoItemGroup.ButtonDefinition {
        private final Icon icon;
        private final Component tooltip;
        private final ResourceLocation texture;

        public LinkButton(CreativeModeTab group, Icon icon, String name, ResourceLocation texture) {
            this.icon = icon;
            this.tooltip = OwoItemGroup.ButtonDefinition.tooltipFor(group, "button", name);
            this.texture = texture;
        }

        public static ItemGroupButton discord(String url) {
            return link(Icon.of(DISCORD_ICON, 0, 0, 16, 16), "discord", url);
        }

        public static ItemGroupButton link(Icon icon, String name, String url) {
            return new ItemGroupButton(KH_TAB, icon, name, TABS, () -> {
                final var client = Minecraft.getInstance();
                var screen = client.screen;
                client.setScreen(new ConfirmLinkScreen(confirmed -> {
                    if (confirmed) Util.getPlatform().openUri(url);
                    client.setScreen(screen);
                }, url, true));
            });
        }

        @Override
        public ResourceLocation texture() {
            return this.texture;
        }

        @Override
        public Icon icon() {
            return icon;
        }

        @Override
        public Component tooltip() {
            return tooltip;
        }
    }
}