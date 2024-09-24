package com.knightsheraldry.util;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class VillagerTradesModifier {
    public static void registerCustomTrades() {
        for (int level = 1; level <= 5; level++) {
            int finalLevel = level;
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, level,
                    factories -> {
                        if (finalLevel == 1) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 5),
                                    new ItemStack(ModItems.QUILTED_COIF, 1),
                                    12, 1, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 7),
                                    new ItemStack(ModItems.GAMBESON, 1),
                                    12, 1, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 5),
                                    new ItemStack(ModItems.GAMBESON_BREECHES, 1),
                                    12, 1, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 4),
                                    new ItemStack(ModItems.GAMBESON_BOOTS, 1),
                                    12, 1, 0.05f));
                        }

                        if (finalLevel == 2) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 6),
                                    new ItemStack(ModItems.MAIL_COIF, 1),
                                    12, 5, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 9),
                                    new ItemStack(ModItems.HAUBERK, 1),
                                    12, 5, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 7),
                                    new ItemStack(ModItems.MAIL_BREECHES, 1),
                                    12, 5, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 6),
                                    new ItemStack(ModItems.MAIL_BOOTS, 1),
                                    12, 5, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 5),
                                    new ItemStack(ModItems.MAIL_PAULDRON, 1),
                                    12, 5, 0.05f));
                        }

                        if (finalLevel == 3) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 15),
                                    new ItemStack(ModItems.BRIGANDINE, 1),
                                    12, 10, 0.05f));
                        }

                        if (finalLevel == 4) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 10),
                                    new ItemStack(ModItems.BRIGANDINE_PAULDRON, 1),
                                    12, 15, 0.05f));
                        }

                        if (finalLevel == 5) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 12),
                                    new ItemStack(ModItems.PLATE_PAULDRON, 1),
                                    12, 15, 0.05f));
                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(2);
                                if (randomNumber == 0) item = ModItems.BRIG_BREASTPLATE;
                                else item = ModItems.BRIG_BREASTPLATE_TASSETS;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 17),
                                        new ItemStack(item, 1),
                                        12, 15, 0.05f
                                );
                            });
                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(2);
                                if (randomNumber == 0) item = ModItems.PLATE_CUIRASS_TASSETS;
                                else if (randomNumber == 1) item = ModItems.MAXIMILIAN_CUIRASS;
                                else if (randomNumber == 2) item = ModItems.MAXIMILIAN_CUIRASS_TASSETS;
                                else if (randomNumber == 3) item = ModItems.XIIII_PLATE_CUIRASS;
                                else if (randomNumber == 4) item = ModItems.XIIII_PLATE_CUIRASS_TASSETS;
                                else if (randomNumber == 5) item = ModItems.XIIII_PLATE_BREASTPLATE;
                                else item = ModItems.PLATE_CUIRASS;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 18),
                                        new ItemStack(item, 1),
                                        12, 15, 0.05f
                                );
                            });
                        }
                    });

            TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, level,
                    factories -> {
                        if (finalLevel == 1) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 3),
                                    new ItemStack(ModItems.PITCHFORK, 1),
                                    12, 1, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 3),
                                    new ItemStack(ModItems.SPEAR, 1),
                                    12, 1, 0.05f));


                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(4);
                                if (randomNumber == 0) item = ModItems.AXE;
                                else if (randomNumber == 1) item = ModItems.BROAD_AXE;
                                else if (randomNumber == 2) item = ModItems.CROOKED_AXE;
                                else item = ModItems.STRAIGHT_CROOKED_AXE;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 5),
                                        new ItemStack(item, 1),
                                        12, 1, 0.05f
                                );
                            });

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 2),
                                    new ItemStack(ModItems.DAGGER, 1),
                                    12, 1, 0.05f));
                        }

                        if (finalLevel == 2) {
                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(3);
                                if (randomNumber == 0) item = ModItems.SWORD;
                                else if (randomNumber == 1) item = ModItems.V_SWORD;
                                else item = ModItems.ARMING_SWORD;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 7),
                                        new ItemStack(item, 1),
                                        12, 5, 0.05f
                                );
                            });

                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(2);
                                if (randomNumber == 0) item = ModItems.MACE;
                                else item = ModItems.SPIKED_MACE;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 5),
                                        new ItemStack(item, 1),
                                        12, 5, 0.05f
                                );
                            });

                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(2);
                                if (randomNumber == 0) item = ModItems.FLAIL;
                                else item = ModItems.BALL_FLAIL;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 6),
                                        new ItemStack(item, 1),
                                        12, 5, 0.05f
                                );
                            });

                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(2);
                                if (randomNumber == 0) item = ModItems.HAMMER;
                                else item = ModItems.WAR_HAMMER;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 5),
                                        new ItemStack(item, 1),
                                        12, 5, 0.05f
                                );
                            });

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 8),
                                    new ItemStack(ModItems.WARDART, 1),
                                    12, 5, 0.05f
                            ));
                        }

                        if (finalLevel == 3) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 6),
                                    new ItemStack(ModItems.BILLHOOK, 1),
                                    12, 10, 0.05f));

                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(2);
                                if (randomNumber == 0) item = ModItems.LONGSWORD;
                                else item = ModItems.V_LONGSWORD;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 12),
                                        new ItemStack(item, 1),
                                        12, 10, 0.05f
                                );
                            });

                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(2);
                                if (randomNumber == 0) item = ModItems.GLAIVE;
                                else item = ModItems.CURVED_GLAIVE;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 6),
                                        new ItemStack(item, 1),
                                        12, 10, 0.05f
                                );
                            });

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 7),
                                    new ItemStack(ModItems.KATANA, 1),
                                    12, 10, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 5),
                                    new ItemStack(ModItems.LANCE, 1),
                                    12, 10, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 2),
                                    new ItemStack(ModItems.STILETTO, 1),
                                    12, 10, 0.05f));
                        }

                        if (finalLevel == 4) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 9),
                                    new ItemStack(ModItems.POLEAXE, 1),
                                    12, 15, 0.05f));

                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(2);
                                if (randomNumber == 0) item = ModItems.POLEHAMMER;
                                else item = ModItems.BEC_DE_CORBIN;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 9),
                                        new ItemStack(item, 1),
                                        12, 15, 0.05f
                                );
                            });

                            factories.add((entity, random) -> {
                                Item item;
                                int randomNumber = random.nextInt(4);
                                if (randomNumber == 0) item = ModItems.WARSWORD;
                                else if (randomNumber == 1) item = ModItems.WARSWORD_CLAYMORE;
                                else if (randomNumber == 2) item = ModItems.WARSWORD_FLAMBERGE;
                                else item = ModItems.WARSWORD_ZWEIHANDER;

                                return new TradeOffer(
                                        new ItemStack(Items.EMERALD, 14),
                                        new ItemStack(item, 1),
                                        12, 15, 0.05f
                                );
                            });

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 6),
                                    new ItemStack(ModItems.RAPIER, 1),
                                    12, 15, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 8),
                                    new ItemStack(ModItems.MORNING_STAR, 1),
                                    12, 15, 0.05f));
                        }

                        if (finalLevel == 5) {
                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 7),
                                    new ItemStack(ModItems.HALBERD, 1),
                                    12, 15, 0.05f));

                            factories.add((entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 7),
                                    new ItemStack(ModItems.BARDICHE, 1),
                                    12, 15, 0.05f));
                        }
                    });
        }
    }
}