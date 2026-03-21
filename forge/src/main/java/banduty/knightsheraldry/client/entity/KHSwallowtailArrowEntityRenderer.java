package banduty.knightsheraldry.client.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.KHSwallowTailArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class KHSwallowtailArrowEntityRenderer extends ArrowRenderer<KHSwallowTailArrowEntity> {
    public KHSwallowtailArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull KHSwallowTailArrowEntity arrowEntity) {
        return new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/arrow/swallowtail_arrow.png");
    }
}