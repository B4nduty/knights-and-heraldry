package banduty.knightsheraldry.client.item;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;

import java.util.List;
import java.util.function.Function;

public class SurcoatWithBannerModel implements IUnbakedGeometry<SurcoatWithBannerModel> {
    public static final Loader INSTANCE = new Loader();

    @Override
    public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
                           Function<Material, TextureAtlasSprite> spriteGetter,
                           ModelState modelState, ItemOverrides overrides,
                           ResourceLocation modelLocation) {

        String modelPath = modelLocation.getPath();
        String baseModelPath = modelPath.replace("_with_banner", "");

        BakedModel baseModel = baker.bake(
                new ResourceLocation(modelLocation.getNamespace(), baseModelPath),
                modelState, spriteGetter
        );

        if (baseModel == null) {
            baseModel = baker.bake(ModelBakery.MISSING_MODEL_LOCATION, modelState, spriteGetter);
        }

        return new Baked(baseModel);
    }

    public static class Loader implements IGeometryLoader<SurcoatWithBannerModel> {
        @Override
        public SurcoatWithBannerModel read(JsonObject jsonObject, JsonDeserializationContext context) {
            return new SurcoatWithBannerModel();
        }
    }

    public static class Baked implements BakedModel {
        private final BakedModel baseModel;

        public Baked(BakedModel baseModel) {
            this.baseModel = baseModel;
        }

        @Override
        public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand) {
            return baseModel.getQuads(state, side, rand);
        }

        @Override
        public boolean useAmbientOcclusion() {
            return baseModel.useAmbientOcclusion();
        }

        @Override
        public boolean isGui3d() {
            return baseModel.isGui3d();
        }

        @Override
        public boolean usesBlockLight() {
            return baseModel.usesBlockLight();
        }

        @Override
        public boolean isCustomRenderer() {
            return true; // This is key - tells Minecraft to use custom renderer
        }

        @Override
        public TextureAtlasSprite getParticleIcon() {
            return baseModel.getParticleIcon();
        }

        @Override
        public ItemOverrides getOverrides() {
            return baseModel.getOverrides();
        }

        @Override
        public BakedModel applyTransform(ItemDisplayContext transformType, PoseStack poseStack,
                                         boolean applyLeftHandTransform) {
            return baseModel.applyTransform(transformType, poseStack, applyLeftHandTransform);
        }
    }
}