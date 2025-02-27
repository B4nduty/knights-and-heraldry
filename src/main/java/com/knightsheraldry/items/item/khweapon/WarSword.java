package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.item.SwordItem;

public class WarSword extends SwordItem implements SCWeapon {
    public WarSword(float attackSpeed, Settings settings) {
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
                2.9d, //3rd Distance
                4.1d, //4th Distance
                5.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                KnightsHeraldry.getConfig().getWarSwordDamageSlashing(),
                KnightsHeraldry.getConfig().getWarSwordDamagePiercing(),
                KnightsHeraldry.getConfig().getWarSwordDamageBludgeoning()
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                2
        };
    }
}