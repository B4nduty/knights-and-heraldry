package com.knightsheraldry.items.custom.item.khweapon;

import com.knightsheraldry.items.ModToolMaterials;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.item.SwordItem;

public class Sword extends SwordItem implements KHWeapon {
    public Sword(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public int getAnimation() {
        return 3;
    }

    @Override
    public KHDamageCalculator.DamageType getOnlyDamageType() {
        return null;
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                1.0d, //1st Distance
                2.2d, //2nd Distance
                2.7d, //3rd Distance
                3.2d, //4th Distance
                3.5d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Slashing
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Piercing
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F //Bludgeoning
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                3
        };
    }
}