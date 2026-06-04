package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.items.itemgroup.SCItemGroup;
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
        return new ItemStack(item);
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

                    itemStack(KHItems.DARK_BARBUTE.get()),
                    itemStack(KHItems.DARK_BASCINET.get()),
                    itemStack(KHItems.DARK_KETTLE_HELM.get()),
                    itemStack(KHItems.DARK_NASAL_HELM.get()),
                    itemStack(KHItems.DARK_VIKING_HELM.get()),
                    itemStack(KHItems.DARK_BURGONET.get()),

                    itemStack(KHItems.GOLDEN_BARBUTE.get()),
                    itemStack(KHItems.GOLDEN_BASCINET.get()),
                    itemStack(KHItems.GOLDEN_KETTLE_HELM.get()),
                    itemStack(KHItems.GOLDEN_NASAL_HELM.get()),
                    itemStack(KHItems.GOLDEN_VIKING_HELM.get()),
                    itemStack(KHItems.GOLDEN_BURGONET.get()),

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

                    itemStack(KHItems.DARK_ARMET.get()),
                    itemStack(KHItems.DARK_ARMET_2.get()),
                    itemStack(KHItems.DARK_VISORED_BARBUTE.get()),
                    itemStack(KHItems.DARK_HOUNDSKULL.get()),
                    itemStack(KHItems.DARK_CAGE.get()),
                    itemStack(KHItems.DARK_VISORED_BASCINET.get()),
                    itemStack(KHItems.DARK_GREAT_HELM.get()),
                    itemStack(KHItems.DARK_GREAT_HELM_2.get()),
                    itemStack(KHItems.DARK_SALLET.get()),
                    itemStack(KHItems.DARK_BURGONET_FALLING_BUFFE.get()),
                    itemStack(KHItems.DARK_CLOSE_HELM.get()),

                    itemStack(KHItems.GOLDEN_ARMET.get()),
                    itemStack(KHItems.GOLDEN_ARMET_2.get()),
                    itemStack(KHItems.GOLDEN_VISORED_BARBUTE.get()),
                    itemStack(KHItems.GOLDEN_HOUNDSKULL.get()),
                    itemStack(KHItems.GOLDEN_CAGE.get()),
                    itemStack(KHItems.GOLDEN_VISORED_BASCINET.get()),
                    itemStack(KHItems.GOLDEN_GREAT_HELM.get()),
                    itemStack(KHItems.GOLDEN_GREAT_HELM_2.get()),
                    itemStack(KHItems.GOLDEN_SALLET.get()),
                    itemStack(KHItems.GOLDEN_BURGONET_FALLING_BUFFE.get()),
                    itemStack(KHItems.GOLDEN_CLOSE_HELM.get()),

                    itemStack(KHItems.SALLET_BEVOR.get()),
                    itemStack(KHItems.DARK_SALLET_BEVOR.get()),
                    itemStack(KHItems.GOLDEN_SALLET_BEVOR.get()),

                    itemStack(KHItems.FROGMOUTH.get()),
                    itemStack(KHItems.GREAT_ARMET.get()),
                    itemStack(KHItems.GREAT_ARMET_2.get()),
                    itemStack(KHItems.GREAT_BASCINET.get()),
                    itemStack(KHItems.GREAT_HOUNDSKUL_BASCINET.get()),
                    itemStack(KHItems.MAXIMILLIAN_HELMET.get()),
                    itemStack(KHItems.SAVOYARD.get()),

                    itemStack(KHItems.AVENTAIL.get()),

                    itemStack(KHItems.DARK_FROGMOUTH.get()),
                    itemStack(KHItems.DARK_GREAT_ARMET.get()),
                    itemStack(KHItems.DARK_GREAT_ARMET_2.get()),
                    itemStack(KHItems.DARK_GREAT_BASCINET.get()),
                    itemStack(KHItems.DARK_GREAT_HOUNDSKUL_BASCINET.get()),
                    itemStack(KHItems.DARK_MAXIMILLIAN_HELMET.get()),
                    itemStack(KHItems.DARK_SAVOYARD.get()),

                    itemStack(KHItems.GOLDEN_FROGMOUTH.get()),
                    itemStack(KHItems.GOLDEN_GREAT_ARMET.get()),
                    itemStack(KHItems.GOLDEN_GREAT_ARMET_2.get()),
                    itemStack(KHItems.GOLDEN_GREAT_BASCINET.get()),
                    itemStack(KHItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get()),
                    itemStack(KHItems.GOLDEN_MAXIMILLIAN_HELMET.get()),
                    itemStack(KHItems.GOLDEN_SAVOYARD.get()),

                    itemStack(KHItems.CHAPERON.get()),
                    itemStack(KHItems.GILDED_CHAPERON.get()),

                    itemStack(KHItems.BRIGANDINE.get()),
                    itemStack(KHItems.BRIG_BREASTPLATE.get()),
                    itemStack(KHItems.BRIG_BREASTPLATE_TASSETS.get()),
                    itemStack(KHItems.PLATE_CUIRASS.get()),
                    itemStack(KHItems.PLATE_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.MAXIMILLIAN_CUIRASS.get()),
                    itemStack(KHItems.MAXIMILLIAN_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.XIIII_PLATE_CUIRASS.get()),
                    itemStack(KHItems.XIIII_PLATE_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.XIIII_PLATE_BREASTPLATE.get()),

                    itemStack(KHItems.DARK_BRIGANDINE.get()),
                    itemStack(KHItems.DARK_BRIG_BREASTPLATE.get()),
                    itemStack(KHItems.DARK_BRIG_BREASTPLATE_TASSETS.get()),
                    itemStack(KHItems.DARK_PLATE_CUIRASS.get()),
                    itemStack(KHItems.DARK_PLATE_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.DARK_MAXIMILLIAN_CUIRASS.get()),
                    itemStack(KHItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.DARK_XIIII_PLATE_CUIRASS.get()),
                    itemStack(KHItems.DARK_XIIII_PLATE_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.DARK_XIIII_PLATE_BREASTPLATE.get()),

                    itemStack(KHItems.GOLDEN_BRIGANDINE.get()),
                    itemStack(KHItems.GOLDEN_BRIG_BREASTPLATE.get()),
                    itemStack(KHItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get()),
                    itemStack(KHItems.GOLDEN_PLATE_CUIRASS.get()),
                    itemStack(KHItems.GOLDEN_PLATE_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.GOLDEN_MAXIMILLIAN_CUIRASS.get()),
                    itemStack(KHItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.GOLDEN_XIIII_PLATE_CUIRASS.get()),
                    itemStack(KHItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS.get()),
                    itemStack(KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get()),

                    itemStack(KHItems.SURCOAT.get()),
                    itemStack(KHItems.SURCOAT_SLEEVELESS.get()),
                    itemStack(KHItems.CIVILIAN_SURCOAT.get()),
                    itemStack(KHItems.GIORNEA.get()),

                    itemStack(KHItems.RIM_GUARDS.get()),
                    itemStack(KHItems.BESAGEWS.get()),

                    itemStack(KHItems.MAIL_SPAULDERS.get()),
                    itemStack(KHItems.BRIGANDINE_SPAULDERS.get()),
                    itemStack(KHItems.PLATE_SPAULDERS.get()),
                    itemStack(KHItems.MAIL_SPAULDERS_BESAGEWS.get()),
                    itemStack(KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get()),
                    itemStack(KHItems.PLATE_SPAULDERS_BESAGEWS.get()),
                    itemStack(KHItems.PLATE_SPAULDERS_RIMMED.get()),
                    itemStack(KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get()),

                    itemStack(KHItems.DARK_BRIGANDINE_SPAULDERS.get()),
                    itemStack(KHItems.DARK_PLATE_SPAULDERS.get()),
                    itemStack(KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get()),
                    itemStack(KHItems.DARK_PLATE_SPAULDERS_BESAGEWS.get()),
                    itemStack(KHItems.DARK_PLATE_SPAULDERS_RIMMED.get()),
                    itemStack(KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get()),

                    itemStack(KHItems.GOLDEN_MAIL_SPAULDERS.get()),
                    itemStack(KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get()),
                    itemStack(KHItems.GOLDEN_PLATE_SPAULDERS.get()),
                    itemStack(KHItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get()),
                    itemStack(KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get()),
                    itemStack(KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get()),
                    itemStack(KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get()),
                    itemStack(KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get()),

                    itemStack(KHItems.GAUNTLET.get()),
                    itemStack(KHItems.BRIGANDINE_HARNESS.get()),
                    itemStack(KHItems.PLATE_HARNESS.get()),

                    itemStack(KHItems.DARK_GAUNTLET.get()),
                    itemStack(KHItems.DARK_BRIGANDINE_HARNESS.get()),
                    itemStack(KHItems.DARK_PLATE_HARNESS.get()),

                    itemStack(KHItems.GOLDEN_GAUNTLET.get()),
                    itemStack(KHItems.GOLDEN_BRIGANDINE_HARNESS.get()),
                    itemStack(KHItems.GOLDEN_PLATE_HARNESS.get()),

                    itemStack(KHItems.BRIGANDINE_CUISSES.get()),
                    itemStack(KHItems.PLATE_CUISSES.get()),

                    itemStack(KHItems.DARK_BRIGANDINE_CUISSES.get()),
                    itemStack(KHItems.DARK_PLATE_CUISSES.get()),

                    itemStack(KHItems.GOLDEN_BRIGANDINE_CUISSES.get()),
                    itemStack(KHItems.GOLDEN_PLATE_CUISSES.get()),

                    itemStack(KHItems.GREAVES.get()),

                    itemStack(KHItems.DARK_GREAVES.get()),

                    itemStack(KHItems.GOLDEN_GREAVES.get()),

                    itemStack(KHItems.SABATONS.get()),

                    itemStack(KHItems.DARK_SABATONS.get()),

                    itemStack(KHItems.GOLDEN_SABATONS.get()),

                    itemStack(KHItems.HOOD.get()),
                    itemStack(KHItems.TORN_HOOD.get()),
                    itemStack(KHItems.JESTER_HOOD.get()),
                    itemStack(KHItems.HELMET_HOOD.get()),
                    itemStack(KHItems.HELMET_TORN_HOOD.get()),

                    itemStack(KHItems.CLOAK.get()),
                    itemStack(KHItems.TORN_CLOAK.get()),

                    itemStack(KHItems.HORSE_BARDING.get()),

                    itemStack(KHItems.DARK_HORSE_BARDING.get()),

                    itemStack(KHItems.GOLDEN_HORSE_BARDING.get()))))
            .build());

    Supplier<CreativeModeTab> KH_DECO_TAB = register("kh_deco", () -> SCItemGroup.create(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "kh_deco"))
            .icon(() -> new ItemStack(KHItems.PLUME.get()))
            .backgroundTexture(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/gui/container/creative_inventory/tab_items.png"))
            .scrollerSprites(SCROLLER_SPRITE, SCROLLER_DISABLED_SPRITE)
            .topTabSprites(UNSELECTED_TOP_TABS, SELECTED_TOP_TABS)
            .bottomTabSprites(UNSELECTED_BOTTOM_TABS, SELECTED_BOTTOM_TABS)
            .title(Component.translatable("component.itemgroup.knightsheraldry.tab.kh_deco"))
            .appendItems((output) -> output.acceptAll(List.of(
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

    private static Supplier<CreativeModeTab> register(String name, Supplier<CreativeModeTab> itemSupplier) {
        return Services.PLATFORM.register(BuiltInRegistries.CREATIVE_MODE_TAB, name, itemSupplier);
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering ItemGroups for " + KnightsHeraldry.MOD_ID);
    }
}