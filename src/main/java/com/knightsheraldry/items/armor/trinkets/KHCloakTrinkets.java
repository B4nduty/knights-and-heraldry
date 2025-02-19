package com.knightsheraldry.items.armor.trinkets;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.armor.KHTrinketsItem;
import com.knightsheraldry.model.CloakHoodModel;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;

public class KHCloakTrinkets extends TrinketItem implements KHTrinketsItem, DyeableItem {
    double armor;
    double toughness;
    double hungerDrainAddition;
    boolean overlay;
    int defaultColor;

    public KHCloakTrinkets(Settings settings, double armor, double toughness, double hungerDrainAddition,
                           boolean overlay, int defaultColor) {
        super(settings);
        this.armor = armor;
        this.toughness = toughness;
        this.hungerDrainAddition = hungerDrainAddition;
        this.overlay = overlay;
        this.defaultColor = defaultColor;
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
        return new CloakHoodModel(CloakHoodModel.getTexturedModelData().createModel());
    }

    @Override
    public Identifier getTexturePath() {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/" + this + ".png");
    }

    public boolean isDyeable() {
        return true;
    }

    public boolean isDyeableWithOverlay() {
        return overlay;
    }

    public int getDefaultColor() {
        return defaultColor;
    }
}
