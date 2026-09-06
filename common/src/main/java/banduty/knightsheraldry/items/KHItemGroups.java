package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.ArmorVariant;
import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.items.custom.hotiron.QuenchItem;
import banduty.stoneycore.items.itemgroup.SCItemGroup;
import banduty.stoneycore.data.SCDataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Supplier;

public interface KHItemGroups {

    ResourceLocation SCROLLER_SPRITE = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/scroller");
    ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/scroller_disabled");

    ResourceLocation[] UNSELECTED_TOP_TABS = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_1"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_2"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_3"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_4"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_5"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_6"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_unselected_7")
    };
    ResourceLocation[] SELECTED_TOP_TABS = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_1"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_2"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_3"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_4"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_5"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_6"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_top_selected_7")
    };
    ResourceLocation[] UNSELECTED_BOTTOM_TABS = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_1"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_2"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_3"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_4"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_5"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_6"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_unselected_7")
    };
    ResourceLocation[] SELECTED_BOTTOM_TABS = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_1"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_2"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_3"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_4"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_5"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_6"),
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "creative_inventory/tab_bottom_selected_7")
    };

    private static ItemStack itemStack(ItemLike item) {
        ItemStack itemStack = new ItemStack(item);
        return itemStack;
    }

    private static ItemStack ignitedItemStack(ItemLike item) {
        ItemStack itemStack = new ItemStack(item);
        if (item instanceof QuenchItem quenchItem && !quenchItem.destroysOnQuench())
            itemStack.set(SCDataComponents.IGNITED.get(), true);
        return itemStack;
    }

    Supplier<CreativeModeTab> KH_WEAPONS_TAB = register("kh_weapons", () -> SCItemGroup.create(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "kh_weapons"))
            .icon(() -> new ItemStack(KHItems.ZWEIHANDER.get()))
            .backgroundTexture(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/gui/container/creative_inventory/tab_items.png"))
            .scrollerSprites(SCROLLER_SPRITE, SCROLLER_DISABLED_SPRITE)
            .topTabSprites(UNSELECTED_TOP_TABS, SELECTED_TOP_TABS)
            .bottomTabSprites(UNSELECTED_BOTTOM_TABS, SELECTED_BOTTOM_TABS)
            .title(Component.translatable("component.itemgroup.knightsheraldry.tab.kh_weapons"))
            .appendItems((output) -> output.acceptAll(List.of(
                    itemStack(KHItems.DAGGER.get()),
                    itemStack(KHItems.STILETTO.get()),
                    itemStack(KHItems.RAPIER.get()),
                    itemStack(KHItems.SWORD.get()),
                    itemStack(KHItems.V_SWORD.get()),
                    itemStack(KHItems.ARMING_SWORD.get()),
                    itemStack(KHItems.AXE.get()),
                    itemStack(KHItems.BROAD_AXE.get()),
                    itemStack(KHItems.CROOKED_AXE.get()),
                    itemStack(KHItems.STRAIGHT_CROOKED_AXE.get()),
                    itemStack(KHItems.MACE.get()),
                    itemStack(KHItems.SPIKED_MACE.get()),
                    itemStack(KHItems.FLAIL.get()),
                    itemStack(KHItems.BALL_FLAIL.get()),
                    itemStack(KHItems.HAMMER.get()),
                    itemStack(KHItems.WAR_HAMMER.get()),
                    itemStack(KHItems.LONGSWORD.get()),
                    itemStack(KHItems.V_LONGSWORD.get()),
                    itemStack(KHItems.FALCHION.get()),
                    itemStack(KHItems.SCIMITAR.get()),
                    itemStack(KHItems.PITCHFORK.get()),
                    itemStack(KHItems.SPEAR.get()),
                    itemStack(KHItems.PIKE.get()),
                    itemStack(KHItems.BILLHOOK.get()),
                    itemStack(KHItems.GLAIVE.get()),
                    itemStack(KHItems.CURVED_GLAIVE.get()),
                    itemStack(KHItems.HALBERD.get()),
                    itemStack(KHItems.LANCE.get()),
                    itemStack(KHItems.WOODEN_LANCE.get()),
                    itemStack(KHItems.POLEAXE.get()),
                    itemStack(KHItems.POLEHAMMER.get()),
                    itemStack(KHItems.BEC_DE_CORBIN.get()),
                    itemStack(KHItems.MORNING_STAR.get()),
                    itemStack(KHItems.BARDICHE.get()),
                    itemStack(KHItems.GREATSWORD.get()),
                    itemStack(KHItems.CLAYMORE.get()),
                    itemStack(KHItems.FLAMBERGE.get()),
                    itemStack(KHItems.ZWEIHANDER.get()),
                    itemStack(KHItems.WARDART.get()),
                    itemStack(KHItems.LONGBOW.get()),
                    itemStack(KHItems.HEAVY_CROSSBOW.get()),
                    itemStack(KHItems.ARQUEBUS.get()),
                    itemStack(KHItems.HANDGONNE.get()),
                    itemStack(KHItems.SWALLOWTAIL_ARROW.get()),
                    itemStack(KHItems.BODKIN_ARROW.get()),
                    itemStack(KHItems.BROADHEAD_ARROW.get()),
                    itemStack(KHItems.CLOTH_ARROW.get())
            )))
            .build());

    Supplier<CreativeModeTab> KH_ARMORS_TAB = register("kh_armors", () -> SCItemGroup.create(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "kh_armors"))
            .icon(() -> new ItemStack(KHItems.QUILTED_COIF.get()))
            .backgroundTexture(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/gui/container/creative_inventory/tab_items.png"))
            .scrollerSprites(SCROLLER_SPRITE, SCROLLER_DISABLED_SPRITE)
            .topTabSprites(UNSELECTED_TOP_TABS, SELECTED_TOP_TABS)
            .bottomTabSprites(UNSELECTED_BOTTOM_TABS, SELECTED_BOTTOM_TABS)
            .title(Component.translatable("component.itemgroup.knightsheraldry.tab.kh_armors"))
            .appendItems((output) -> output.acceptAll(List.of(
                    itemStack(KHItems.QUILTED_COIF.get()),
                    itemStack(KHItems.GAMBESON.get()),
                    itemStack(KHItems.GAMBESON_BREECHES.get()),
                    itemStack(KHItems.GAMBESON_BOOTS.get()),

                    itemStack(KHItems.ARMING_DOUBLET.get()),
                    itemStack(KHItems.ARMING_HOSE.get()),

                    itemStack(KHItems.MAIL_COIF.get()),
                    itemStack(KHItems.HAUBERK.get()),
                    itemStack(KHItems.MAIL_BREECHES.get()),
                    itemStack(KHItems.MAIL_BOOTS.get()),

                    itemStack(KHItems.BARBUTE.get()),
                    itemStack(KHItems.BASCINET.get()),
                    itemStack(KHItems.KETTLE_HELM.get()),
                    itemStack(KHItems.NASAL_HELM.get()),
                    itemStack(KHItems.VIKING_HELM.get()),
                    itemStack(KHItems.BURGONET.get()),
                    itemStack(KHItems.VISORLESS_SALLET.get()),
                    itemStack(KHItems.MORION.get()),

                    itemStack(KHItems.BARBUTE.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.BASCINET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.KETTLE_HELM.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.NASAL_HELM.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.VIKING_HELM.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.BURGONET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.VISORLESS_SALLET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.MORION.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.BARBUTE.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.BASCINET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.KETTLE_HELM.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.NASAL_HELM.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.VIKING_HELM.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.BURGONET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.VISORLESS_SALLET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.MORION.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.ARMET.get()),
                    itemStack(KHItems.ARMET_2.get()),
                    itemStack(KHItems.VISORED_BARBUTE.get()),
                    itemStack(KHItems.HOUNDSKULL.get()),
                    itemStack(KHItems.CAGE.get()),
                    itemStack(KHItems.VISORED_BASCINET.get()),
                    itemStack(KHItems.GREAT_HELM.get()),
                    itemStack(KHItems.GREAT_HELM_2.get()),
                    itemStack(KHItems.SALLET.get()),
                    itemStack(KHItems.BURGONET_FALLING_BUFFE.get()),
                    itemStack(KHItems.CLOSE_HELM.get()),
                    itemStack(KHItems.BLACK_SALLET.get()),
                    itemStack(KHItems.VISORED_MORION.get()),

                    itemStack(KHItems.ARMET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.ARMET_2.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.VISORED_BARBUTE.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.HOUNDSKULL.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.CAGE.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.VISORED_BASCINET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.GREAT_HELM.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.GREAT_HELM_2.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.SALLET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.BURGONET_FALLING_BUFFE.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.CLOSE_HELM.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.BLACK_SALLET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.VISORED_MORION.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.ARMET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.ARMET_2.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.VISORED_BARBUTE.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.HOUNDSKULL.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.CAGE.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.VISORED_BASCINET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.GREAT_HELM.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.GREAT_HELM_2.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.SALLET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.BURGONET_FALLING_BUFFE.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.CLOSE_HELM.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.BLACK_SALLET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.VISORED_MORION.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.SALLET_BEVOR.get()),
                    itemStack(KHItems.BLACK_SALLET_BEVOR.get()),
                    itemStack(KHItems.SALLET_BEVOR.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.BLACK_SALLET_BEVOR.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.SALLET_BEVOR.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.BLACK_SALLET_BEVOR.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.FROGMOUTH.get()),
                    itemStack(KHItems.GREAT_ARMET.get()),
                    itemStack(KHItems.GREAT_ARMET_2.get()),
                    itemStack(KHItems.GREAT_BASCINET.get()),
                    itemStack(KHItems.GREAT_HOUNDSKUL_BASCINET.get()),
                    itemStack(KHItems.MAXIMILLIAN_HELMET.get()),
                    itemStack(KHItems.SAVOYARD.get()),
                    itemStack(KHItems.ARAGONESE_SALLET.get()),

                    itemStack(KHItems.FROGMOUTH.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.GREAT_ARMET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.GREAT_ARMET_2.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.GREAT_BASCINET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.GREAT_HOUNDSKUL_BASCINET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.MAXIMILLIAN_HELMET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.SAVOYARD.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.ARAGONESE_SALLET.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.FROGMOUTH.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.GREAT_ARMET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.GREAT_ARMET_2.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.GREAT_BASCINET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.GREAT_HOUNDSKUL_BASCINET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.MAXIMILLIAN_HELMET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.SAVOYARD.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.ARAGONESE_SALLET.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.CHAPERON.get()),
                    itemStack(KHItems.GILDED_CHAPERON.get()),

                    itemStack(KHItems.AVENTAIL.get()),

                    itemStack(KHItems.BRIGANDINE.get()),
                    itemStack(KHItems.PLATE_CUIRASS.get()),
                    itemStack(KHItems.MAXIMILLIAN_CUIRASS.get()),
                    itemStack(KHItems.XIIII_PLATE_CUIRASS.get()),
                    itemStack(KHItems.XIIII_PLATE_BREASTPLATE.get()),

                    itemStack(KHItems.BRIGANDINE.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.PLATE_CUIRASS.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.MAXIMILLIAN_CUIRASS.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.XIIII_PLATE_CUIRASS.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.XIIII_PLATE_BREASTPLATE.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.BRIGANDINE.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.PLATE_CUIRASS.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.MAXIMILLIAN_CUIRASS.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.XIIII_PLATE_CUIRASS.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.XIIII_PLATE_BREASTPLATE.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.PLACKART.get()),
                    itemStack(KHItems.PLACKART.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.PLACKART.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.TASSETS.get()),
                    itemStack(KHItems.TASSETS.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.TASSETS.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.SURCOAT.get()),
                    itemStack(KHItems.SURCOAT_SLEEVELESS.get()),
                    itemStack(KHItems.CIVILIAN_SURCOAT.get()),
                    itemStack(KHItems.GIORNEA.get()),

                    itemStack(KHItems.RIM_GUARDS.get()),
                    itemStack(KHItems.BESAGEWS.get()),

                    itemStack(KHItems.MAIL_SPAULDERS.get()),
                    itemStack(KHItems.BRIGANDINE_SPAULDERS.get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get()),
                    itemStack(KHItems.MAIL_SPAULDERS.get(ArmorVariant.PLAIN, ArmorVariant.BESAGEWS).get()),
                    itemStack(KHItems.BRIGANDINE_SPAULDERS.get(ArmorVariant.PLAIN, ArmorVariant.BESAGEWS).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.PLAIN, ArmorVariant.BESAGEWS).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.PLAIN, ArmorVariant.RIMMED).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.PLAIN, ArmorVariant.BESAGEWS, ArmorVariant.RIMMED).get()),

                    itemStack(KHItems.BRIGANDINE_SPAULDERS.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.BRIGANDINE_SPAULDERS.get(ArmorVariant.DARK, ArmorVariant.BESAGEWS).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.DARK, ArmorVariant.BESAGEWS).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.DARK, ArmorVariant.RIMMED).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.DARK, ArmorVariant.BESAGEWS, ArmorVariant.RIMMED).get()),

                    itemStack(KHItems.MAIL_SPAULDERS.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.BRIGANDINE_SPAULDERS.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.MAIL_SPAULDERS.get(ArmorVariant.GOLDEN, ArmorVariant.BESAGEWS).get()),
                    itemStack(KHItems.BRIGANDINE_SPAULDERS.get(ArmorVariant.GOLDEN, ArmorVariant.BESAGEWS).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.GOLDEN, ArmorVariant.BESAGEWS).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.GOLDEN, ArmorVariant.RIMMED).get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get(ArmorVariant.GOLDEN, ArmorVariant.BESAGEWS, ArmorVariant.RIMMED).get()),

                    itemStack(KHItems.LEATHER_GLOVES.get()),
                    itemStack(KHItems.MAIL_GLOVES.get()),

                    itemStack(KHItems.GAUNTLET.get()),
                    itemStack(KHItems.BRIGANDINE_HARNESS.get()),
                    itemStack(KHItems.PLATE_HARNESS.get()),

                    itemStack(KHItems.GAUNTLET.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.BRIGANDINE_HARNESS.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.PLATE_HARNESS.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.GAUNTLET.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.BRIGANDINE_HARNESS.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.PLATE_HARNESS.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.BRIGANDINE_CUISSES.get()),
                    itemStack(KHItems.PLATE_CUISSES.get()),

                    itemStack(KHItems.BRIGANDINE_CUISSES.get(ArmorVariant.DARK).get()),
                    itemStack(KHItems.PLATE_CUISSES.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.BRIGANDINE_CUISSES.get(ArmorVariant.GOLDEN).get()),
                    itemStack(KHItems.PLATE_CUISSES.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.GREAVES.get()),

                    itemStack(KHItems.GREAVES.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.GREAVES.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.SABATONS.get()),

                    itemStack(KHItems.SABATONS.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.SABATONS.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.HOOD.get()),
                    itemStack(KHItems.TORN_HOOD.get()),
                    itemStack(KHItems.JESTER_HOOD.get()),
                    itemStack(KHItems.HELMET_HOOD.get()),
                    itemStack(KHItems.HELMET_TORN_HOOD.get()),

                    itemStack(KHItems.CLOAK.get()),
                    itemStack(KHItems.TORN_CLOAK.get()),

                    itemStack(KHItems.HORSE_BARDING.get()),

                    itemStack(KHItems.HORSE_BARDING.get(ArmorVariant.DARK).get()),

                    itemStack(KHItems.HORSE_BARDING.get(ArmorVariant.GOLDEN).get()),

                    itemStack(KHItems.PLUME.get()),
                    itemStack(KHItems.TRI_PLUME.get()),
                    itemStack(KHItems.FLUFFY_PLUME.get()),
                    itemStack(KHItems.TORSE.get()),
                    itemStack(KHItems.TEUTONIC_SNAKES.get()),
                    itemStack(KHItems.TEUTONIC_BLACK_SNAKES.get()),
                    itemStack(KHItems.GOLD_HORNS.get()),
                    itemStack(KHItems.BLACK_HORNS.get()),
                    itemStack(KHItems.TEUTONIC_GOLD_WINGS.get()),
                    itemStack(KHItems.TEUTONIC_BLACK_WINGS.get()),
                    itemStack(KHItems.TEUTONIC_WINGS_BALL_ENDS.get()),
                    itemStack(KHItems.TEUTONIC_WINGS_SHARP_ENDS.get()),
                    itemStack(KHItems.DRAGON.get()),
                    itemStack(KHItems.LION.get()),
                    itemStack(KHItems.SNAKE.get()),
                    itemStack(KHItems.UNICORN.get()),
                    itemStack(KHItems.STAG.get()),
                    itemStack(KHItems.BOAR.get()),
                    itemStack(KHItems.EAGLE.get()),
                    itemStack(KHItems.PEGASUS.get())
            )))
            .build());

    Supplier<CreativeModeTab> KH_INGREDIENT_TAB = register("kh_ingredient", () -> SCItemGroup.create(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "kh_deco"))
            .icon(() -> new ItemStack(KHItems.HALBERD_HEAD.get()))
            .backgroundTexture(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/gui/container/creative_inventory/tab_items.png"))
            .scrollerSprites(SCROLLER_SPRITE, SCROLLER_DISABLED_SPRITE)
            .topTabSprites(UNSELECTED_TOP_TABS, SELECTED_TOP_TABS)
            .bottomTabSprites(UNSELECTED_BOTTOM_TABS, SELECTED_BOTTOM_TABS)
            .title(Component.translatable("component.itemgroup.knightsheraldry.tab.kh_ingredient"))
            .appendItems((output) -> {
                output.acceptAll(List.of(
                        itemStack(KHItems.MANUSCRIPT_DAGGER.get()),
                        itemStack(KHItems.MANUSCRIPT_SWORD.get()),
                        itemStack(KHItems.MANUSCRIPT_AXE.get()),
                        itemStack(KHItems.MANUSCRIPT_HAMMER.get()),
                        itemStack(KHItems.MANUSCRIPT_MACE.get()),
                        itemStack(KHItems.MANUSCRIPT_HALBERD.get()),
                        itemStack(KHItems.MANUSCRIPT_LONGSWORD.get()),
                        itemStack(KHItems.MANUSCRIPT_GREATSWORD.get()),
                        itemStack(KHItems.MANUSCRIPT_SPEAR.get()),
                        itemStack(KHItems.MANUSCRIPT_PITCHFORK.get()),

                        itemStack(KHItems.MANUSCRIPT_BARBUTE.get()),
                        itemStack(KHItems.MANUSCRIPT_BASCINET.get()),
                        itemStack(KHItems.MANUSCRIPT_KETTLE.get()),
                        itemStack(KHItems.MANUSCRIPT_NASAL.get()),
                        itemStack(KHItems.MANUSCRIPT_BURGONET.get()),
                        itemStack(KHItems.MANUSCRIPT_SALLET.get()),
                        itemStack(KHItems.MANUSCRIPT_MORION.get()),
                        itemStack(KHItems.MANUSCRIPT_ARMET.get()),
                        itemStack(KHItems.MANUSCRIPT_CAGE.get()),
                        itemStack(KHItems.MANUSCRIPT_GREAT_HELMET.get()),
                        itemStack(KHItems.MANUSCRIPT_CLOSE_HELMET.get()),
                        itemStack(KHItems.MANUSCRIPT_FROGMOUTH.get()),
                        itemStack(KHItems.MANUSCRIPT_MAXIMILIAN.get()),

                        itemStack(KHItems.MANUSCRIPT_VISOR.get()),
                        itemStack(KHItems.MANUSCRIPT_FALLING_BUFFE.get()),
                        itemStack(KHItems.MANUSCRIPT_BEVOR.get()),

                        itemStack(KHItems.MANUSCRIPT_AVENTAIL.get()),

                        itemStack(KHItems.MANUSCRIPT_CUIRASS.get()),

                        itemStack(KHItems.MANUSCRIPT_PLACKART.get()),

                        itemStack(KHItems.MANUSCRIPT_TASSETS.get()),

                        itemStack(KHItems.MANUSCRIPT_RIM_GUARDS.get()),

                        itemStack(KHItems.MANUSCRIPT_BESAGEWS.get()),

                        itemStack(KHItems.MANUSCRIPT_SPAULDERS.get()),

                        itemStack(KHItems.MANUSCRIPT_HARNESS.get()),

                        itemStack(KHItems.MANUSCRIPT_CUISSES.get()),

                        itemStack(KHItems.MANUSCRIPT_GREAVES.get()),

                        itemStack(KHItems.MANUSCRIPT_SABATONS.get()),

                        itemStack(KHItems.MANUSCRIPT_BARDING.get()),

                        itemStack(KHItems.MANUSCRIPT_SWALLOWTAIL.get()),
                        itemStack(KHItems.MANUSCRIPT_BODKIN.get()),
                        itemStack(KHItems.MANUSCRIPT_BROADHEAD.get()),
                        itemStack(KHItems.MANUSCRIPT_CLOTH.get())
                ));

                List.of(
                        KHItems.DAGGER_HEAD, KHItems.STILETTO_HEAD, KHItems.SWORD_HEAD,
                        KHItems.FALCHION_HEAD, KHItems.RAPIER_HEAD, KHItems.AXE_HEAD,
                        KHItems.HAMMER_HEAD, KHItems.MACE_HEAD, KHItems.HALBERD_HEAD,
                        KHItems.BILLHOOK_HEAD, KHItems.SPEAR_HEAD, KHItems.PITCHFORK_HEAD
                ).forEach(item -> {
                    ItemStack finishedStack = ignitedItemStack(item.get());
                    output.accept(new ItemStack(item.get()));
                    output.accept(finishedStack);
                });


                output.acceptAll(List.of(
                        itemStack(KHItems.BARBUTE_PIECE.get()),
                        itemStack(KHItems.BASCINET_PIECE.get()),
                        itemStack(KHItems.KETTLE_PIECE.get()),
                        itemStack(KHItems.NASAL_PIECE.get()),
                        itemStack(KHItems.BURGONET_PIECE.get()),
                        itemStack(KHItems.SALLET_PIECE.get()),
                        itemStack(KHItems.MORION_PIECE.get()),
                        itemStack(KHItems.ARMET_PIECE.get()),
                        itemStack(KHItems.CAGE_PIECE.get()),
                        itemStack(KHItems.GREAT_HELMET_PIECE.get()),
                        itemStack(KHItems.CLOSE_HELMET_PIECE.get()),
                        itemStack(KHItems.FROGMOUTH_PIECE.get()),
                        itemStack(KHItems.MAXIMILIAN_PIECE.get()),
                        itemStack(KHItems.VISOR.get()),
                        itemStack(KHItems.FALLING_BUFFE.get()),
                        itemStack(KHItems.BEVOR.get()),
                        itemStack(KHItems.CUIRASS_PIECE.get()),
                        itemStack(KHItems.SPAULDERS_PIECE.get()),
                        itemStack(KHItems.HARNESS_PIECE.get())
                ));
            })
            .build());

    private static Supplier<CreativeModeTab> register(String name, Supplier<CreativeModeTab> itemSupplier) {
        return Services.PLATFORM.register(BuiltInRegistries.CREATIVE_MODE_TAB, name, itemSupplier);
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering ItemGroups for " + KnightsHeraldry.MOD_ID);
    }
}