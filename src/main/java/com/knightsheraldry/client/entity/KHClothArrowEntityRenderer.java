package com.knightsheraldry.client.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.KHArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class KHClothArrowEntityRenderer extends ProjectileEntityRenderer<KHArrowEntity> {
    public KHClothArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(KHArrowEntity arrowEntity) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/arrow/cloth_arrow.png");
    }
}