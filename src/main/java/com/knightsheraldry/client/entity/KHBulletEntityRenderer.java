package com.knightsheraldry.client.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.KHArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class KHBulletEntityRenderer extends ProjectileEntityRenderer<KHArrowEntity> {
    public KHBulletEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(KHArrowEntity arrowEntity) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/a_layer_1.png");
    }
}