package com.knightsheraldry.items.custom.item.khweapon;

import com.knightsheraldry.items.ModToolMaterials;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.item.SwordItem;

public class Falchion extends SwordItem implements KHWeapon {
    public Falchion(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                1.0d, //1st Distance
                1.9d, //2nd Distance
                2.6d, //3rd Distance
                3.3d, //4th Distance
                4.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                0.0F, 8.0F, 12.0F, 8.0F, 4.0F, //Slashing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[0];
    }

    @Override
    public int getAnimation() {
        return 0;
    }

    @Override
    public KHDamageCalculator.DamageType getOnlyDamageType() {
        return null;
    }
}
