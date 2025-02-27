package com.knightsheraldry.client.entity;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.KnightsHeraldry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class KHSwallowtailArrowEntityRenderer extends ProjectileEntityRenderer<SCArrowEntity> {
    public KHSwallowtailArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(SCArrowEntity arrowEntity) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/arrow/swallowtail_arrow.png");
    }
}