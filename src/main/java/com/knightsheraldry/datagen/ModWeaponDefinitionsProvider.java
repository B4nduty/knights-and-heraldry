package com.knightsheraldry.datagen;

import banduty.stoneycore.datagen.DefinitionsProvider;
import banduty.stoneycore.items.SCItems;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.sounds.ModSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;

public class ModWeaponDefinitionsProvider extends DefinitionsProvider.Weapon {
    public ModWeaponDefinitionsProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void generateDefinitions(ItemDefinitionConsumer consumer) {
        // Melee
        consumer.accept(ModItems.DAGGER.get(),
                WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(7.0f, 0.0f, 3.5f)
                                .levelRadii(0.0, 0.7, 1.2, 1.7, 2.0)
                                .animation(3)
                                .piercingAnimation(3)
                                .build()
                ).build()
        );
        consumer.accept(ModItems.STILETTO.get(),
                WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 6.5f)
                                .levelRadii(0.0, 0.7, 1.2, 1.7, 2.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build()
        );
        consumer.accept(ModItems.RAPIER.get(),
                WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 9.0f)
                                .levelRadii(2.5, 2.9, 3.4, 4.0, 4.5)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(9.0f, 6.0f, 6.0f)
                                .levelRadii(1.0, 2.2, 2.7, 3.2, 3.5)
                                .animation(3)
                                .piercingAnimation(3)
                                .build()
                ).build(),
                ModItems.SWORD.get(), ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(10.0f, 0.0f, 0.0f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)
                                .build()
                ).build(),
                ModItems.AXE.get(), ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 10.0f, 0.0f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.BLUDGEONING)
                                .build()
                ).build(),
                ModItems.MACE.get(), ModItems.SPIKED_MACE.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 8.0f, 0.0f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.BLUDGEONING)
                                .build()
                ).build(),
                ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 10.0f, 8.0f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)
                                .build()
                ).build(),
                ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(10.5f, 7.5f, 6.0f)
                                .levelRadii(1.0, 2.3, 2.8, 3.1, 4.0)
                                .animation(3)
                                .piercingAnimation(2)
                                .build()
                ).build(),
                ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(9.0f, 0.0f, 0.0f)
                                .levelRadii(1.0, 1.9, 2.6, 3.3, 4.0)
                                .build()
                ).build(),
                ModItems.FALCHION.get(), ModItems.SCIMITAR.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 7.5f)
                                .levelRadii(3.3, 4.0, 4.7, 5.5, 6.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build(),
                ModItems.PITCHFORK.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 11.0f)
                                .levelRadii(4.4, 5.1, 5.9, 6.7, 7.5)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build(),
                ModItems.SPEAR.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 11.0f)
                                .levelRadii(5.5, 6.2, 7.0, 7.8, 8.5)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build(),
                ModItems.PIKE.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(8.0f, 0.0f, 10.0f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .animation(2)
                                .piercingAnimation(2)
                                .build()
                ).build(),
                ModItems.BILLHOOK.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(10.0f, 0.0f, 8.0f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .animation(3)
                                .piercingAnimation(1, 2)
                                .build()
                ).build(),
                ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(9.0f, 3.0f, 10.5f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .animation(2)
                                .piercingAnimation(2)
                                .build()
                ).build(),
                ModItems.HALBERD.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(10.0f, 7.5f, 6.0f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .animation(2)
                                .piercingAnimation(2)
                                .build()
                ).build(),
                ModItems.POLEAXE.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 10.5f, 7.5f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .build()
                ).build(),
                ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 12.0f, 0.0f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.BLUDGEONING)
                                .build()
                ).build(),
                ModItems.MORNING_STAR.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(12.0f, 0.0f, 0.0f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .build()
                ).build(),
                ModItems.BARDICHE.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(13.0f, 0.0f, 10.0f)
                                .levelRadii(1.0, 2.2, 2.9, 4.1, 5.0)
                                .animation(3)
                                .piercingAnimation(2)
                                .bonusKnockback(0.3)
                                .build()
                ).build(),
                ModItems.GREATSWORD.get(), ModItems.CLAYMORE.get(), ModItems.FLAMBERGE.get(), ModItems.ZWEIHANDER.get()
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 13.5f)
                                .levelRadii(3.4, 3.8, 4.3, 4.6, 5.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build(),
                ModItems.WARDART.get()
        );

        // Ranged Weapon
        consumer.accept(WeaponBuilder.ranged(
                        RangedBuilder.create()
                                .id("bow")
                                .baseDamage(14)
                                .speed(3.7f)
                                .divergence(1)
                                .maxUseTime(72000)
                                .rechargeTime(0)
                                .needsFlintAndSteel(false)
                                .damageType(SCDamageCalculator.DamageType.PIERCING)
                                .useAnim(UseAnim.BOW)
                                .soundEvent(SoundEvents.ARROW_SHOOT)
                                .build()
                ).build(),
                ModItems.LONGBOW.get()
        );
        consumer.accept(WeaponBuilder.ranged(
                        RangedBuilder.create()
                                .id("crossbow")
                                .baseDamage(16)
                                .speed(3f)
                                .divergence(1)
                                .maxUseTime(72000)
                                .rechargeTime(5)
                                .needsFlintAndSteel(false)
                                .damageType(SCDamageCalculator.DamageType.PIERCING)
                                .useAnim(UseAnim.CROSSBOW)
                                .soundEvent(SoundEvents.ARROW_SHOOT)
                                .build()
                ).build(),
                ModItems.HEAVY_CROSSBOW.get()
        );
        consumer.accept(WeaponBuilder.ranged(
                        RangedBuilder.create()
                                .id("musket")
                                .baseDamage(21)
                                .speed(5.9f)
                                .divergence(2)
                                .maxUseTime(1)
                                .rechargeTime(15)
                                .needsFlintAndSteel(false)
                                .damageType(SCDamageCalculator.DamageType.BLUDGEONING)
                                .useAnim(UseAnim.BOW)
                                .soundEvent(ModSounds.ARQUEBUS_CLOSE.get())
                                .ammoRequirement("item1", SCItems.BLACK_POWDER.get(), 1)
                                .ammoRequirement("item2", 1, Items.IRON_NUGGET, Items.GRAVEL)
                                .ammoRequirement("item3", 1, Items.PAPER, Items.GRASS)
                                .build()
                ).build(),
                ModItems.ARQUEBUS.get()
        );
        consumer.accept(WeaponBuilder.ranged(
                        RangedBuilder.create()
                                .id("musket")
                                .baseDamage(26)
                                .speed(4.3f)
                                .divergence(2.5f)
                                .maxUseTime(1)
                                .rechargeTime(15)
                                .needsFlintAndSteel(true)
                                .damageType(SCDamageCalculator.DamageType.BLUDGEONING)
                                .useAnim(UseAnim.BOW)
                                .soundEvent(ModSounds.ARQUEBUS_CLOSE.get())
                                .ammoRequirement("item1", SCItems.BLACK_POWDER.get(), 2)
                                .ammoRequirement("item2", 1, Items.IRON_NUGGET, Items.GRAVEL)
                                .ammoRequirement("item3", 1, Items.PAPER, Items.GRASS)
                                .build()
                ).build(),
                ModItems.HANDGONNE.get()
        );

        // Weapons
        consumer.accept(WeaponBuilder.ammo(
                        AmmoBuilder.create()
                                .deflectChance(-0.2)
                                .build()
                ).build(),
                ModItems.BODKIN_ARROW.get()
        );
        consumer.accept(WeaponBuilder.ammo(
                        AmmoBuilder.create()
                                .deflectChance(0.05)
                                .build()
                ).build(),
                ModItems.SWALLOWTAIL_ARROW.get()
        );
    }
}