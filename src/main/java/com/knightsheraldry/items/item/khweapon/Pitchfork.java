package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.item.SwordItem;

public class Pitchfork extends SwordItem implements SCWeapon {
    public Pitchfork(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                3.3d, //1st Distance
                4.0d, //2nd Distance
                4.7d, //3rd Distance
                5.5d, //4th Distance
                6.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                0.0F, //Slashing
                KnightsHeraldry.getConfig().getPitchforkDamagePiercing(),
                0.0F //Bludgeoning
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
    public SCDamageCalculator.DamageType getOnlyDamageType() {
        return SCDamageCalculator.DamageType.PIERCING;
    }
}