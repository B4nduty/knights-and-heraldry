package com.knightsheraldry.items.armor.trinkets;

import banduty.stoneycore.items.armor.SCTrinketsItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.CloakHoodModel;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;

public class KHCloakTrinkets extends TrinketItem implements SCTrinketsItem, DyeableItem {
    double armor;
    double toughness;
    double hungerDrainAddition;
    boolean overlay;

    public KHCloakTrinkets(Settings settings, double armor, double toughness, double hungerDrainAddition,
                           boolean overlay) {
        super(settings);
        this.armor = armor;
        this.toughness = toughness;
        this.hungerDrainAddition = hungerDrainAddition;
        this.overlay = overlay;
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
        return new CloakHoodModel(CloakHoodModel.getTexturedModelData().createModel());
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Identifier getTexturePath() {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/" + this + ".png");
    }

    @Override
    public boolean hasOverlay() {
        return overlay;
    }

    @Override
    public boolean hasCustomAngles() {
        return true;
    }
}
