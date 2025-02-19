package com.knightsheraldry.items.item;

import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.UseAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface KHRangeWeapon {
    @NotNull KHDamageCalculator.DamageType getDamageType();

    int maxUseTime();

    float baseDamage();

    float speed();

    @Nullable AmmoRequirement ammoRequirement();

    int rechargeTime();

    boolean needsFlintAndSteel();

    @Nullable SoundEvent[] soundEvents();

    UseAction useAction();

    record AmmoRequirement(
            int amountFirstItem, Item firstItem, Item firstItem2nOption,
            int amountSecondItem, Item secondItem, Item secondItem2nOption,
            int amountThirdItem, Item thirdItem, Item thirdItem2nOption
    ) {}
}
