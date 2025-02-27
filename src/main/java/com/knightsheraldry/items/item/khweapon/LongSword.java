package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.item.SwordItem;

public class LongSword extends SwordItem implements SCWeapon {
    public LongSword(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public int getAnimation() {
        return 3;
    }

    @Override
    public SCDamageCalculator.DamageType getOnlyDamageType() {
        return null;
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                1.0d, //1st Distance
                2.3d, //2nd Distance
                2.8d, //3rd Distance
                3.1d, //4th Distance
                4.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                KnightsHeraldry.getConfig().getLongSwordDamageSlashing(),
                KnightsHeraldry.getConfig().getLongSwordDamagePiercing(),
                KnightsHeraldry.getConfig().getLongSwordDamageBludgeoning()
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                2
        };
    }
}