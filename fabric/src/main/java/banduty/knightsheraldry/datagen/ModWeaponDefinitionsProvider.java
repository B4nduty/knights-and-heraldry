package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.sounds.ModSounds;
import banduty.stoneycore.datagen.DefinitionsProvider;
import banduty.stoneycore.items.SCItems;
import banduty.stoneycore.util.SCDamageCalculator;
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
        consumer.accept(ModItems.DAGGER,
                WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(7.0f, 0.0f, 3.5f)
                                .levelRadii(0.0, 0.7, 1.2, 1.7, 2.0)
                                .animation(3)
                                .piercingAnimation(3)
                                .build()
                ).build()
        );
        consumer.accept(ModItems.STILETTO,
                WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 6.5f)
                                .levelRadii(0.0, 0.7, 1.2, 1.7, 2.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build()
        );
        consumer.accept(ModItems.RAPIER,
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
                ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(10.0f, 0.0f, 0.0f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)
                                .build()
                ).build(),
                ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 10.0f, 0.0f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.BLUDGEONING)
                                .build()
                ).build(),
                ModItems.MACE, ModItems.SPIKED_MACE
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 8.0f, 0.0f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.BLUDGEONING)
                                .build()
                ).build(),
                ModItems.FLAIL, ModItems.BALL_FLAIL
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 10.0f, 8.0f)
                                .levelRadii(2.0, 2.4, 2.9, 3.4, 4.0)
                                .build()
                ).build(),
                ModItems.HAMMER, ModItems.WAR_HAMMER
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(10.5f, 7.5f, 6.0f)
                                .levelRadii(1.0, 2.3, 2.8, 3.1, 4.0)
                                .animation(3)
                                .piercingAnimation(2)
                                .build()
                ).build(),
                ModItems.LONGSWORD, ModItems.V_LONGSWORD
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(9.0f, 0.0f, 0.0f)
                                .levelRadii(1.0, 1.9, 2.6, 3.3, 4.0)
                                .build()
                ).build(),
                ModItems.FALCHION, ModItems.SCIMITAR
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 7.5f)
                                .levelRadii(3.3, 4.0, 4.7, 5.5, 6.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build(),
                ModItems.PITCHFORK
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 11.0f)
                                .levelRadii(4.4, 5.1, 5.9, 6.7, 7.5)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build(),
                ModItems.SPEAR
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 11.0f)
                                .levelRadii(5.5, 6.2, 7.0, 7.8, 8.5)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build(),
                ModItems.PIKE
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(8.0f, 0.0f, 10.0f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .animation(2)
                                .piercingAnimation(2)
                                .build()
                ).build(),
                ModItems.BILLHOOK
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(10.0f, 0.0f, 8.0f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .animation(3)
                                .piercingAnimation(1, 2)
                                .build()
                ).build(),
                ModItems.GLAIVE, ModItems.CURVED_GLAIVE
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(9.0f, 3.0f, 10.5f)
                                .levelRadii(4.0, 4.7, 5.5, 6.3, 7.0)
                                .animation(2)
                                .piercingAnimation(2)
                                .build()
                ).build(),
                ModItems.HALBERD
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(10.0f, 7.5f, 6.0f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .animation(2)
                                .piercingAnimation(2)
                                .build()
                ).build(),
                ModItems.POLEAXE
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 10.5f, 7.5f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .build()
                ).build(),
                ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 12.0f, 0.0f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.BLUDGEONING)
                                .build()
                ).build(),
                ModItems.MORNING_STAR
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(12.0f, 0.0f, 0.0f)
                                .levelRadii(3.0, 3.7, 4.5, 5.3, 6.0)
                                .build()
                ).build(),
                ModItems.BARDICHE
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
                ModItems.GREATSWORD, ModItems.CLAYMORE, ModItems.FLAMBERGE, ModItems.ZWEIHANDER
        );
        consumer.accept(WeaponBuilder.melee(
                        MeleeBuilder.create()
                                .damage(0.0f, 0.0f, 13.5f)
                                .levelRadii(3.4, 3.8, 4.3, 4.6, 5.0)
                                .onlyDamageType(SCDamageCalculator.DamageType.PIERCING)
                                .build()
                ).build(),
                ModItems.WARDART
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
                ModItems.LONGBOW
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
                ModItems.HEAVY_CROSSBOW
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
                                .soundEvent(ModSounds.ARQUEBUS_CLOSE)
                                .ammoRequirement("item1", SCItems.BLACK_POWDER, 1)
                                .ammoRequirement("item2", 1, Items.IRON_NUGGET, Items.GRAVEL)
                                .ammoRequirement("item3", 1, Items.PAPER, Items.GRASS)
                                .build()
                ).build(),
                ModItems.ARQUEBUS
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
                                .soundEvent(ModSounds.ARQUEBUS_CLOSE)
                                .ammoRequirement("item1", SCItems.BLACK_POWDER, 2)
                                .ammoRequirement("item2", 1, Items.IRON_NUGGET, Items.GRAVEL)
                                .ammoRequirement("item3", 1, Items.PAPER, Items.GRASS)
                                .build()
                ).build(),
                ModItems.HANDGONNE
        );

        // Weapons
        consumer.accept(WeaponBuilder.ammo(
                        AmmoBuilder.create()
                                .deflectChance(-0.2)
                                .build()
                ).build(),
                ModItems.BODKIN_ARROW
        );
        consumer.accept(WeaponBuilder.ammo(
                        AmmoBuilder.create()
                                .deflectChance(0.05)
                                .build()
                ).build(),
                ModItems.SWALLOWTAIL_ARROW
        );
    }
}