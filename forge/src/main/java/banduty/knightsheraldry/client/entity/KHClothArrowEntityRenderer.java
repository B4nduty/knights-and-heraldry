package banduty.knightsheraldry.client.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.KHClothArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class KHClothArrowEntityRenderer extends ArrowRenderer<KHClothArrowEntity> {
    public KHClothArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(KHClothArrowEntity arrowEntity) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/arrow/cloth_arrow.png");
    }
}