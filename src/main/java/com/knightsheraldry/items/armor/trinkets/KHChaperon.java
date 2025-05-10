package com.knightsheraldry.items.armor.trinkets;

import banduty.stoneycore.items.armor.SCTrinketsItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.ChaperonModel;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;

public class KHChaperon extends TrinketItem implements SCTrinketsItem, DyeableItem {
    boolean hasOverlay;

    public KHChaperon(Settings settings, boolean hasOverlay) {
        super(settings);
        this.hasOverlay = hasOverlay;
    }

    @Override
    public double armor() {
        return 0;
    }

    @Override
    public double toughness() {
        return 0;
    }

    @Override
    public double hungerDrainAddition() {
        return 0;
    }

    @Override
    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<LivingEntity> getModel() {
        return new ChaperonModel(ChaperonModel.getTexturedModelData().createModel());
    }

    @Override
    public Identifier getTexturePath() {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/chaperon.png");
    }
}
