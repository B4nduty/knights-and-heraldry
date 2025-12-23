package com.knightsheraldry.client.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.KHClothArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class KHClothArrowEntityRenderer extends ArrowRenderer<KHClothArrowEntity> {
    public KHClothArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull KHClothArrowEntity arrowEntity) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/arrow/cloth_arrow.png");
    }
}