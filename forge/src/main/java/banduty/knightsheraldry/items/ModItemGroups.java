package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItemGroups {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KnightsHeraldry.MOD_ID);

    private static ItemStack itemStack(ItemLike item) {
        return new ItemStack(item);
    }

    public static final RegistryObject<CreativeModeTab> KH_WEAPONS_TAB = CREATIVE_MODE_TABS.register("kh_weapons",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ZWEIHANDER.get()))
                    .title(Component.translatable("component.itemgroup.knightsheraldry.tab.kh_weapons"))
                    .displayItems((parameters, output) -> {
                        output.acceptAll(List.of(
                                itemStack(ModItems.DAGGER.get()),
                                itemStack(ModItems.STILETTO.get()),
                                itemStack(ModItems.RAPIER.get()),
                                itemStack(ModItems.SWORD.get()),
                                itemStack(ModItems.V_SWORD.get()),
                                itemStack(ModItems.ARMING_SWORD.get()),
                                itemStack(ModItems.AXE.get()),
                                itemStack(ModItems.BROAD_AXE.get()),
                                itemStack(ModItems.CROOKED_AXE.get()),
                                itemStack(ModItems.STRAIGHT_CROOKED_AXE.get()),
                                itemStack(ModItems.MACE.get()),
                                itemStack(ModItems.SPIKED_MACE.get()),
                                itemStack(ModItems.FLAIL.get()),
                                itemStack(ModItems.BALL_FLAIL.get()),
                                itemStack(ModItems.HAMMER.get()),
                                itemStack(ModItems.WAR_HAMMER.get()),
                                itemStack(ModItems.LONGSWORD.get()),
                                itemStack(ModItems.V_LONGSWORD.get()),
                                itemStack(ModItems.FALCHION.get()),
                                itemStack(ModItems.SCIMITAR.get()),
                                itemStack(ModItems.PITCHFORK.get()),
                                itemStack(ModItems.SPEAR.get()),
                                itemStack(ModItems.PIKE.get()),
                                itemStack(ModItems.BILLHOOK.get()),
                                itemStack(ModItems.GLAIVE.get()),
                                itemStack(ModItems.CURVED_GLAIVE.get()),
                                itemStack(ModItems.HALBERD.get()),
                                itemStack(ModItems.LANCE.get()),
                                itemStack(ModItems.WOODEN_LANCE.get()),
                                itemStack(ModItems.POLEAXE.get()),
                                itemStack(ModItems.POLEHAMMER.get()),
                                itemStack(ModItems.BEC_DE_CORBIN.get()),
                                itemStack(ModItems.MORNING_STAR.get()),
                                itemStack(ModItems.BARDICHE.get()),
                                itemStack(ModItems.GREATSWORD.get()),
                                itemStack(ModItems.CLAYMORE.get()),
                                itemStack(ModItems.FLAMBERGE.get()),
                                itemStack(ModItems.ZWEIHANDER.get()),
                                itemStack(ModItems.WARDART.get()),
                                itemStack(ModItems.LONGBOW.get()),
                                itemStack(ModItems.HEAVY_CROSSBOW.get()),
                                itemStack(ModItems.ARQUEBUS.get()),
                                itemStack(ModItems.HANDGONNE.get()),
                                itemStack(ModItems.SWALLOWTAIL_ARROW.get()),
                                itemStack(ModItems.BODKIN_ARROW.get()),
                                itemStack(ModItems.BROADHEAD_ARROW.get()),
                                itemStack(ModItems.CLOTH_ARROW.get())
                        ));
                    }).build());

    public static final RegistryObject<CreativeModeTab> KH_ARMORS_TAB = CREATIVE_MODE_TABS.register("kh_armors",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.QUILTED_COIF.get()))
                    .title(Component.translatable("component.itemgroup.knightsheraldry.tab.kh_armors"))
                    .displayItems((parameters, output) -> {
                        output.acceptAll(List.of(
                                itemStack(ModItems.QUILTED_COIF.get()),
                                itemStack(ModItems.GAMBESON.get()),
                                itemStack(ModItems.GAMBESON_BREECHES.get()),
                                itemStack(ModItems.GAMBESON_BOOTS.get()),

                                itemStack(ModItems.MAIL_COIF.get()),
                                itemStack(ModItems.HAUBERK.get()),
                                itemStack(ModItems.MAIL_BREECHES.get()),
                                itemStack(ModItems.MAIL_BOOTS.get()),

                                itemStack(ModItems.BARBUTE.get()),
                                itemStack(ModItems.BASCINET.get()),
                                itemStack(ModItems.KETTLE_HELM.get()),
                                itemStack(ModItems.NASAL_HELM.get()),
                                itemStack(ModItems.VIKING_HELM.get()),
                                itemStack(ModItems.BURGONET.get()),

                                itemStack(ModItems.DARK_BARBUTE.get()),
                                itemStack(ModItems.DARK_BASCINET.get()),
                                itemStack(ModItems.DARK_KETTLE_HELM.get()),
                                itemStack(ModItems.DARK_NASAL_HELM.get()),
                                itemStack(ModItems.DARK_VIKING_HELM.get()),
                                itemStack(ModItems.DARK_BURGONET.get()),

                                itemStack(ModItems.GOLDEN_BARBUTE.get()),
                                itemStack(ModItems.GOLDEN_BASCINET.get()),
                                itemStack(ModItems.GOLDEN_KETTLE_HELM.get()),
                                itemStack(ModItems.GOLDEN_NASAL_HELM.get()),
                                itemStack(ModItems.GOLDEN_VIKING_HELM.get()),
                                itemStack(ModItems.GOLDEN_BURGONET.get()),

                                itemStack(ModItems.ARMET.get()),
                                itemStack(ModItems.ARMET_2.get()),
                                itemStack(ModItems.VISORED_BARBUTE.get()),
                                itemStack(ModItems.HOUNDSKULL.get()),
                                itemStack(ModItems.CAGE.get()),
                                itemStack(ModItems.CAGE_2.get()),
                                itemStack(ModItems.VISORED_BASCINET.get()),
                                itemStack(ModItems.GREAT_HELM.get()),
                                itemStack(ModItems.GREAT_HELM_2.get()),
                                itemStack(ModItems.SALLET.get()),
                                itemStack(ModItems.BURGONET_FALLING_BUFFE.get()),
                                itemStack(ModItems.CLOSE_HELM.get()),

                                itemStack(ModItems.DARK_ARMET.get()),
                                itemStack(ModItems.DARK_ARMET_2.get()),
                                itemStack(ModItems.DARK_VISORED_BARBUTE.get()),
                                itemStack(ModItems.DARK_HOUNDSKULL.get()),
                                itemStack(ModItems.DARK_CAGE.get()),
                                itemStack(ModItems.DARK_CAGE_2.get()),
                                itemStack(ModItems.DARK_VISORED_BASCINET.get()),
                                itemStack(ModItems.DARK_GREAT_HELM.get()),
                                itemStack(ModItems.DARK_GREAT_HELM_2.get()),
                                itemStack(ModItems.DARK_SALLET.get()),
                                itemStack(ModItems.DARK_BURGONET_FALLING_BUFFE.get()),
                                itemStack(ModItems.DARK_CLOSE_HELM.get()),

                                itemStack(ModItems.GOLDEN_ARMET.get()),
                                itemStack(ModItems.GOLDEN_ARMET_2.get()),
                                itemStack(ModItems.GOLDEN_VISORED_BARBUTE.get()),
                                itemStack(ModItems.GOLDEN_HOUNDSKULL.get()),
                                itemStack(ModItems.GOLDEN_CAGE.get()),
                                itemStack(ModItems.GOLDEN_CAGE_2.get()),
                                itemStack(ModItems.GOLDEN_VISORED_BASCINET.get()),
                                itemStack(ModItems.GOLDEN_GREAT_HELM.get()),
                                itemStack(ModItems.GOLDEN_GREAT_HELM_2.get()),
                                itemStack(ModItems.GOLDEN_SALLET.get()),
                                itemStack(ModItems.GOLDEN_BURGONET_FALLING_BUFFE.get()),
                                itemStack(ModItems.GOLDEN_CLOSE_HELM.get()),

                                itemStack(ModItems.FROGMOUTH.get()),
                                itemStack(ModItems.GREAT_ARMET.get()),
                                itemStack(ModItems.GREAT_ARMET_2.get()),
                                itemStack(ModItems.GREAT_BASCINET.get()),
                                itemStack(ModItems.GREAT_HOUNDSKUL_BASCINET.get()),
                                itemStack(ModItems.MAXIMILLIAN_HELMET.get()),
                                itemStack(ModItems.SAVOYARD.get()),

                                itemStack(ModItems.AVENTAIL.get()),
                                itemStack(ModItems.BEVOR.get()),
                                itemStack(ModItems.DARK_BEVOR.get()),
                                itemStack(ModItems.GOLDEN_BEVOR.get()),

                                itemStack(ModItems.DARK_FROGMOUTH.get()),
                                itemStack(ModItems.DARK_GREAT_ARMET.get()),
                                itemStack(ModItems.DARK_GREAT_ARMET_2.get()),
                                itemStack(ModItems.DARK_GREAT_BASCINET.get()),
                                itemStack(ModItems.DARK_GREAT_HOUNDSKUL_BASCINET.get()),
                                itemStack(ModItems.DARK_MAXIMILLIAN_HELMET.get()),
                                itemStack(ModItems.DARK_SAVOYARD.get()),

                                itemStack(ModItems.GOLDEN_FROGMOUTH.get()),
                                itemStack(ModItems.GOLDEN_GREAT_ARMET.get()),
                                itemStack(ModItems.GOLDEN_GREAT_ARMET_2.get()),
                                itemStack(ModItems.GOLDEN_GREAT_BASCINET.get()),
                                itemStack(ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get()),
                                itemStack(ModItems.GOLDEN_MAXIMILLIAN_HELMET.get()),
                                itemStack(ModItems.GOLDEN_SAVOYARD.get()),

                                itemStack(ModItems.CHAPERON.get()),
                                itemStack(ModItems.GILDED_CHAPERON.get()),

                                itemStack(ModItems.BRIGANDINE.get()),
                                itemStack(ModItems.BRIG_BREASTPLATE.get()),
                                itemStack(ModItems.BRIG_BREASTPLATE_TASSETS.get()),
                                itemStack(ModItems.PLATE_CUIRASS.get()),
                                itemStack(ModItems.PLATE_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.MAXIMILLIAN_CUIRASS.get()),
                                itemStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.XIIII_PLATE_CUIRASS.get()),
                                itemStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.XIIII_PLATE_BREASTPLATE.get()),

                                itemStack(ModItems.DARK_BRIGANDINE.get()),
                                itemStack(ModItems.DARK_BRIG_BREASTPLATE.get()),
                                itemStack(ModItems.DARK_BRIG_BREASTPLATE_TASSETS.get()),
                                itemStack(ModItems.DARK_PLATE_CUIRASS.get()),
                                itemStack(ModItems.DARK_PLATE_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.DARK_MAXIMILLIAN_CUIRASS.get()),
                                itemStack(ModItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.DARK_XIIII_PLATE_CUIRASS.get()),
                                itemStack(ModItems.DARK_XIIII_PLATE_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.DARK_XIIII_PLATE_BREASTPLATE.get()),

                                itemStack(ModItems.GOLDEN_BRIGANDINE.get()),
                                itemStack(ModItems.GOLDEN_BRIG_BREASTPLATE.get()),
                                itemStack(ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get()),
                                itemStack(ModItems.GOLDEN_PLATE_CUIRASS.get()),
                                itemStack(ModItems.GOLDEN_PLATE_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.GOLDEN_MAXIMILLIAN_CUIRASS.get()),
                                itemStack(ModItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.GOLDEN_XIIII_PLATE_CUIRASS.get()),
                                itemStack(ModItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS.get()),
                                itemStack(ModItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get()),

                                itemStack(ModItems.SURCOAT.get()),
                                itemStack(ModItems.SURCOAT_SLEEVELESS.get()),
                                itemStack(ModItems.CIVILIAN_SURCOAT.get()),
                                itemStack(ModItems.GIORNEA.get()),

                                itemStack(ModItems.RIM_GUARDS.get()),
                                itemStack(ModItems.BESAGEWS.get()),

                                itemStack(ModItems.MAIL_SPAULDERS.get()),
                                itemStack(ModItems.BRIGANDINE_SPAULDERS.get()),
                                itemStack(ModItems.PLATE_SPAULDERS.get()),
                                itemStack(ModItems.MAIL_SPAULDERS_BESAGEWS.get()),
                                itemStack(ModItems.BRIGANDINE_SPAULDERS_BESAGEWS.get()),
                                itemStack(ModItems.PLATE_SPAULDERS_BESAGEWS.get()),
                                itemStack(ModItems.PLATE_SPAULDERS_RIMMED.get()),
                                itemStack(ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get()),

                                itemStack(ModItems.DARK_BRIGANDINE_SPAULDERS.get()),
                                itemStack(ModItems.DARK_PLATE_SPAULDERS.get()),
                                itemStack(ModItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get()),
                                itemStack(ModItems.DARK_PLATE_SPAULDERS_BESAGEWS.get()),
                                itemStack(ModItems.DARK_PLATE_SPAULDERS_RIMMED.get()),
                                itemStack(ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get()),

                                itemStack(ModItems.GOLDEN_MAIL_SPAULDERS.get()),
                                itemStack(ModItems.GOLDEN_BRIGANDINE_SPAULDERS.get()),
                                itemStack(ModItems.GOLDEN_PLATE_SPAULDERS.get()),
                                itemStack(ModItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get()),
                                itemStack(ModItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get()),
                                itemStack(ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get()),
                                itemStack(ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get()),
                                itemStack(ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get()),

                                itemStack(ModItems.GAUNTLET.get()),
                                itemStack(ModItems.BRIGANDINE_HARNESS.get()),
                                itemStack(ModItems.PLATE_HARNESS.get()),

                                itemStack(ModItems.DARK_GAUNTLET.get()),
                                itemStack(ModItems.DARK_BRIGANDINE_HARNESS.get()),
                                itemStack(ModItems.DARK_PLATE_HARNESS.get()),

                                itemStack(ModItems.GOLDEN_GAUNTLET.get()),
                                itemStack(ModItems.GOLDEN_BRIGANDINE_HARNESS.get()),
                                itemStack(ModItems.GOLDEN_PLATE_HARNESS.get()),

                                itemStack(ModItems.BRIGANDINE_CUISSES.get()),
                                itemStack(ModItems.PLATE_CUISSES.get()),

                                itemStack(ModItems.DARK_BRIGANDINE_CUISSES.get()),
                                itemStack(ModItems.DARK_PLATE_CUISSES.get()),

                                itemStack(ModItems.GOLDEN_BRIGANDINE_CUISSES.get()),
                                itemStack(ModItems.GOLDEN_PLATE_CUISSES.get()),

                                itemStack(ModItems.GREAVES.get()),

                                itemStack(ModItems.DARK_GREAVES.get()),

                                itemStack(ModItems.GOLDEN_GREAVES.get()),

                                itemStack(ModItems.SABATONS.get()),

                                itemStack(ModItems.DARK_SABATONS.get()),

                                itemStack(ModItems.GOLDEN_SABATONS.get()),

                                itemStack(ModItems.HOOD.get()),
                                itemStack(ModItems.TORN_HOOD.get()),
                                itemStack(ModItems.JESTER_HOOD.get()),
                                itemStack(ModItems.HELMET_HOOD.get()),
                                itemStack(ModItems.HELMET_TORN_HOOD.get()),

                                itemStack(ModItems.CLOAK.get()),
                                itemStack(ModItems.TORN_CLOAK.get()),

                                itemStack(ModItems.HORSE_BARDING.get()),

                                itemStack(ModItems.DARK_HORSE_BARDING.get()),

                                itemStack(ModItems.GOLDEN_HORSE_BARDING.get())
                        ));
                    }).build());

    public static final RegistryObject<CreativeModeTab> KH_DECO_TAB = CREATIVE_MODE_TABS.register("kh_deco",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.PLUME.get()))
                    .title(Component.translatable("component.itemgroup.knightsheraldry.tab.kh_armors"))
                    .displayItems((parameters, output) -> {
                        output.acceptAll(List.of(
                                itemStack(ModItems.PLUME.get()),
                                itemStack(ModItems.TRI_PLUME.get()),
                                itemStack(ModItems.FLUFFY_PLUME.get()),
                                itemStack(ModItems.TORSE.get()),
                                itemStack(ModItems.TEUTONIC_SNAKES.get()),
                                itemStack(ModItems.TEUTONIC_BLACK_SNAKES.get()),
                                itemStack(ModItems.GOLD_HORNS.get()),
                                itemStack(ModItems.BLACK_HORNS.get()),
                                itemStack(ModItems.TEUTONIC_GOLD_WINGS.get()),
                                itemStack(ModItems.TEUTONIC_BLACK_WINGS.get()),
                                itemStack(ModItems.TEUTONIC_WINGS_BALL_ENDS.get()),
                                itemStack(ModItems.TEUTONIC_WINGS_SHARP_ENDS.get()),
                                itemStack(ModItems.DRAGON.get()),
                                itemStack(ModItems.LION.get()),
                                itemStack(ModItems.SNAKE.get()),
                                itemStack(ModItems.UNICORN.get()),
                                itemStack(ModItems.STAG.get()),
                                itemStack(ModItems.BOAR.get()),
                                itemStack(ModItems.EAGLE.get()),
                                itemStack(ModItems.PEGASUS.get())
                        ));
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}