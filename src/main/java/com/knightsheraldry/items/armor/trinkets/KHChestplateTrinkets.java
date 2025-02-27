package com.knightsheraldry.items.armor.trinkets;

import banduty.stoneycore.items.armor.SCTrinketsItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.TrinketsArmModel;
import com.knightsheraldry.model.TrinketsChestplateModel;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class KHChestplateTrinkets extends TrinketItem implements SCTrinketsItem {
    double armor;
    double toughness;
    double hungerDrainAddition;

    public KHChestplateTrinkets(Settings settings, double armor, double toughness, double hungerDrainAddition) {
        super(settings);
        this.armor = armor;
        this.toughness = toughness;
        this.hungerDrainAddition = hungerDrainAddition;
    }

    @Override
    public double armor() {
        return armor;
    }

    @Override
    public double toughness() {
        return toughness;
    }

    @Override
    public double hungerDrainAddition() {
        return hungerDrainAddition;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<LivingEntity> getModel() {
        return new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel());
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<LivingEntity> getFirstPersonModel() {
        return new TrinketsArmModel(TrinketsArmModel.getTexturedModelData().createModel());
    }

    @Override
    public Identifier getTexturePath() {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/" + this + ".png");
    }
}
