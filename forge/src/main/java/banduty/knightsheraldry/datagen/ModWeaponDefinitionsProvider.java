package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.sounds.ModSounds;
import banduty.stoneycore.combat.melee.SCDamageType;
import banduty.stoneycore.datagen.DefinitionsProvider;
import banduty.stoneycore.items.SCItems;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;

public class ModWeaponDefinitionsProvider extends DefinitionsProvider.Weapon {
    public ModWeaponDefinitionsProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void generateDefinitions(WeaponConsumer consumer) {
        // Melee
        consumer.accept(ModItems.DAGGER.get(),
                Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 4.5f, 7.0f, 4.5f, 2.5f)
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 2.5f, 3.5f, 2.5f, 1.0f)
                                .levelRadii(0.0, 0.7, 1.2, 1.7, 2.0)
                                .anim(3)
                                .pierceAnim(3)
                ).build()
        );
        consumer.accept(ModItems.STILETTO.get(),
                Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 4.0f, 6.5f, 4.0f, 2.0f)
                                .levelRadii(0.0, 0.7, 1.2, 1.7, 2.0)

                ).build()
        );
        consumer.accept(ModItems.RAPIER.get(),
                Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 6.0f, 9.0f, 6.0f, 3.0f)
                                .levelRadii(2.5, 2.9, 3.4, 4.0, 4.5)

                ).build()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 6.0f, 9.0f, 6.0f, 3.0f)
                                .bludgeoningDamage(0.0f, 4.0f, 6.0f, 4.0f, 2.0f)
                                .piercingDamage(0.0f, 4.0f, 6.0f, 4.0f, 2.0f)
                                .levelRadii(1.0, 2.2, 2.7, 3.2, 3.5)
                                .anim(3)
                                .pierceAnim(3)

                ).build(),
                ModItems.SWORD.get(), ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 6.5f, 10.0f, 6.5f, 3.0f)
                                .bludgeoningDamage()
                                .piercingDamage()
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)

                ).build(),
                ModItems.AXE.get(), ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage(0.0f, 6.5f, 10.0f, 6.5f, 3.0f)
                                .piercingDamage()
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)

                ).build(),
                ModItems.MACE.get(), ModItems.SPIKED_MACE.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage(0.0f, 5.0f, 8.0f, 5.0f, 2.5f)
                                .piercingDamage()
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)

                ).build(),
                ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage(0.0f, 6.5f, 10.0f, 6.5f, 3.0f)
                                .piercingDamage(0.0f, 5.0f, 8.0f, 5.0f, 2.5f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)

                ).build(),
                ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 7.0f, 10.5f, 7.0f, 3.5f)
                                .bludgeoningDamage(0.0f, 5.0f, 7.5f, 5.0f, 2.5f)
                                .piercingDamage(0.0f, 4.0f, 6.0f, 4.0f, 2.0f)
                                .levelRadii(1.0, 2.3, 2.8, 3.1, 4.0)
                                .anim(3)
                                .pierceAnim(2)

                ).build(),
                ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 6.0f, 9.0f, 6.0f, 3.0f)
                                .bludgeoningDamage()
                                .piercingDamage()
                                .levelRadii(1.0, 1.9, 2.6, 3.3, 4.0)

                ).build(),
                ModItems.FALCHION.get(), ModItems.SCIMITAR.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 5.0f, 7.5f, 5.0f, 2.5f)
                                .levelRadii(3.3, 4.0, 4.7, 5.5, 6.0)

                ).build(),
                ModItems.PITCHFORK.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 7.0f, 11.0f, 7.0f, 3.5f)
                                .levelRadii(4.4, 5.1, 5.9, 6.7, 7.5)

                ).build(),
                ModItems.SPEAR.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 7.0f, 11.0f, 7.0f, 3.5f)
                                .levelRadii(5.5, 6.2, 7.0, 7.8, 8.5)

                ).build(),
                ModItems.PIKE.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 5.0f, 8.0f, 5.0f, 2.5f)
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 6.5f, 10.0f, 6.5f, 3.0f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .anim(2)
                                .pierceAnim(2)

                ).build(),
                ModItems.BILLHOOK.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 6.5f, 10.0f, 6.5f, 3.0f)
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 5.0f, 8.0f, 5.0f, 2.5f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .anim(3)
                                .pierceAnim(1, 2)

                ).build(),
                ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 6.0f, 9.0f, 6.0f, 3.0f)
                                .bludgeoningDamage(0.0f, 2.0f, 3.0f, 2.0f, 1.0f)
                                .piercingDamage(0.0f, 7.0f, 10.5f, 7.0f, 3.5f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .anim(2)
                                .pierceAnim(2)

                ).build(),
                ModItems.HALBERD.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 6.5f, 10.0f, 6.5f, 3.0f)
                                .bludgeoningDamage(0.0f, 5.0f, 7.5f, 5.0f, 2.5f)
                                .piercingDamage(0.0f, 4.0f, 6.0f, 4.0f, 2.0f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .anim(2)
                                .pierceAnim(2)

                ).build(),
                ModItems.POLEAXE.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage(0.0f, 7.0f, 10.5f, 7.0f, 3.5f)
                                .piercingDamage(0.0f, 5.0f, 7.5f, 5.0f, 2.5f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)

                ).build(),
                ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage(0.0f, 8.0f, 12.0f, 8.0f, 4.0f)
                                .piercingDamage()
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)

                ).build(),
                ModItems.MORNING_STAR.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 8.0f, 12.0f, 8.0f, 4.0f)
                                .bludgeoningDamage()
                                .piercingDamage()
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)

                ).build(),
                ModItems.BARDICHE.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage(0.0f, 8.0f, 13.0f, 8.0f, 4.0f)
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 8.0f, 10.0f, 8.0f, 4.0f)
                                .levelRadii(1.0, 2.2, 2.9, 4.1, 5.0)
                                .anim(3)
                                .pierceAnim(2)
                                .knockback(0.3)

                ).build(),
                ModItems.GREATSWORD.get(), ModItems.CLAYMORE.get(), ModItems.FLAMBERGE.get(), ModItems.ZWEIHANDER.get()
        );
        consumer.accept(Weapon.Builder.create().melee(
                        Weapon.MeleeBuilder.create()
                                .slashingDamage()
                                .bludgeoningDamage()
                                .piercingDamage(0.0f, 9.0f, 13.5f, 9.0f, 4.5f)
                                .levelRadii(3.4, 3.8, 4.3, 4.6, 5.0)

                ).build(),
                ModItems.WARDART.get()
        );

        // Ranged Weapon
        consumer.accept(Weapon.Builder.create().ranged(
                        Weapon.RangedBuilder.create("bow")
                                .projectile(14, SCDamageType.PIERCING, 3.7f, 1)
                                .maxUseTime(72000)
                                .rechargeTime(3)
                                .needsFlintAndSteel(false)
                                .useAnim(UseAnim.BOW)
                                .soundEvent(SoundEvents.ARROW_SHOOT)

                ).build(),
                ModItems.LONGBOW.get()
        );
        consumer.accept(Weapon.Builder.create().ranged(
                        Weapon.RangedBuilder.create("crossbow")
                                .projectile(16, SCDamageType.PIERCING, 3f, 1)
                                .maxUseTime(72000)
                                .rechargeTime(5)
                                .needsFlintAndSteel(false)
                                .useAnim(UseAnim.CROSSBOW)
                                .soundEvent(SoundEvents.ARROW_SHOOT)

                ).build(),
                ModItems.HEAVY_CROSSBOW.get()
        );
        consumer.accept(Weapon.Builder.create().ranged(
                        Weapon.RangedBuilder.create("musket")
                                .projectile(21, SCDamageType.BLUDGEONING, 5.9f, 2)
                                .maxUseTime(1)
                                .rechargeTime(15)
                                .needsFlintAndSteel(false)
                                .useAnim(UseAnim.BOW)
                                .soundEvent(ModSounds.ARQUEBUS_CLOSE.get())
                                .ammoRequirement("item1", SCItems.BLACK_POWDER.get(), 1)
                                .ammoRequirement("item2", 1, Items.IRON_NUGGET, Items.GRAVEL)
                                .ammoRequirement("item3", 1, Items.PAPER, Items.GRASS)

                ).build(),
                ModItems.ARQUEBUS.get()
        );
        consumer.accept(Weapon.Builder.create().ranged(
                        RangedBuilder.create("musket")
                                .projectile(26, SCDamageType.BLUDGEONING, 4.3f, 2.5f)
                                .maxUseTime(1)
                                .rechargeTime(15)
                                .needsFlintAndSteel(true)
                                .useAnim(UseAnim.BOW)
                                .soundEvent(ModSounds.ARQUEBUS_CLOSE.get())
                                .ammoRequirement("item1", SCItems.BLACK_POWDER.get(), 2)
                                .ammoRequirement("item2", 1, Items.IRON_NUGGET, Items.GRAVEL)
                                .ammoRequirement("item3", 1, Items.PAPER, Items.GRASS)

                ).build(),
                ModItems.HANDGONNE.get()
        );

        // Weapons
        consumer.accept(Weapon.Builder.create().ammo(-0.2).build(), ModItems.BODKIN_ARROW.get());
        consumer.accept(Weapon.Builder.create().ammo(0.05).build(), ModItems.SWALLOWTAIL_ARROW.get());
    }
}