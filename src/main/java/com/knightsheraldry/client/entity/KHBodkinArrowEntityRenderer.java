package com.knightsheraldry.client.entity;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.KnightsHeraldry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class KHBodkinArrowEntityRenderer extends ProjectileEntityRenderer<SCArrowEntity> {
    public KHBodkinArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(SCArrowEntity arrowEntity) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/arrow/bodkin_arrow.png");
    }
}