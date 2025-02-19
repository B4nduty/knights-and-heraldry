
package com.knightsheraldry.items.armor;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public interface KHTrinketsItem {
    double armor();

    double toughness();

    double hungerDrainAddition();

    BipedEntityModel<LivingEntity> getModel();

    default BipedEntityModel<LivingEntity> getFirstPersonModel() {
        return null;
    }

    Identifier getTexturePath();

    default boolean isDyeable() {
        return false;
    }

    default boolean isDyeableWithOverlay() {
        return false;
    }

    default int getDefaultColor() {
        return 0;
    }
}
