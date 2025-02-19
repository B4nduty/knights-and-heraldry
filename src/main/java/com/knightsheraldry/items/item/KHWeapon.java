package com.knightsheraldry.items.item;

import com.knightsheraldry.util.KHDamageCalculator;

public interface KHWeapon {
    double[] getRadiusValues();

    float[] getAttackDamageValues();

    int[] getPiercingAnimation();

    int getAnimation();

    KHDamageCalculator.DamageType getOnlyDamageType();
}