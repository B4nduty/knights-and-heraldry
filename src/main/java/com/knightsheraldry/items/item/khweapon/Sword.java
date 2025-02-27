package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.item.SwordItem;

public class Sword extends SwordItem implements SCWeapon {
    public Sword(float attackSpeed, Settings settings) {
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
                2.2d, //2nd Distance
                2.7d, //3rd Distance
                3.2d, //4th Distance
                3.5d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                KnightsHeraldry.getConfig().getSwordDamageSlashing(),
                KnightsHeraldry.getConfig().getSwordDamagePiercing(),
                KnightsHeraldry.getConfig().getSwordDamageBludgeoning()
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                3
        };
    }
}