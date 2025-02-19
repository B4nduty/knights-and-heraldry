package com.knightsheraldry.items.armor.trinkets;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.armor.KHTrinketsItem;
import com.knightsheraldry.model.TrinketsArmModel;
import com.knightsheraldry.model.TrinketsChestplateModel;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class KHChestplateTrinkets extends TrinketItem implements KHTrinketsItem {
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

    @Override
    public BipedEntityModel<LivingEntity> getModel() {
        return new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel());
    }

    @Override
    public BipedEntityModel<LivingEntity> getFirstPersonModel() {
        return new TrinketsArmModel(TrinketsArmModel.getTexturedModelData().createModel());
    }

    @Override
    public Identifier getTexturePath() {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/" + this + ".png");
    }
}
