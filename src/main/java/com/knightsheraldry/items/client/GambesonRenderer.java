package com.knightsheraldry.items.client;

import com.knightsheraldry.items.custom.armor.GambesonItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.renderer.DyeableGeoArmorRenderer;

public class GambesonRenderer extends DyeableGeoArmorRenderer<GambesonItem> {
    public GambesonRenderer() {
        super(new GambesonModel());

        addRenderLayer(new GambesonOverlay(this));
    }

    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    protected boolean isBoneDyeable(GeoBone bone) {
        return false;
    }

    @Override
    protected @NotNull Color getColorForBone(GeoBone bone) {
        Item item = this.getCurrentStack().getItem();
        int color = ((DyeableItem) item).getColor(this.getCurrentStack());
        return new Color((0xFF << 24) | color);
    }
}
