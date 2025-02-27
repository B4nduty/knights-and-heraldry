package com.knightsheraldry.items.item.khrangeweapon;

import banduty.stoneycore.items.item.SCRangeWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Longbow extends Item implements SCRangeWeapon {
    public Longbow(Settings settings) {
        super(settings);
    }

    @Override
    public @NotNull SCDamageCalculator.DamageType getDamageType() {
        return SCDamageCalculator.DamageType.PIERCING;
    }

    @Override
    public int maxUseTime() {
        return 72000;
    }

    @Override
    public float baseDamage() {
        return 14f;
    }

    @Override
    public float speed() {
        return 3.7f;
    }

    @Override
    public @Nullable AmmoRequirement ammoRequirement() {
        return null;
    }

    @Override
    public int rechargeTime() {
        return 0;
    }

    @Override
    public boolean needsFlintAndSteel() {
        return false;
    }

    @Override
    public @Nullable SoundEvent[] soundEvents() {
        return new SoundEvent[]{SoundEvents.ENTITY_ARROW_SHOOT};
    }

    @Override
    public UseAction useAction() {
        return UseAction.BOW;
    }
}
