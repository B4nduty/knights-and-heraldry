package banduty.knightsheraldry.client.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.Craftman;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CraftmanRenderer extends MobRenderer<Craftman, VillagerModel<Craftman>> {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String DEFAULT_TEXTURE = "default";

    private static final ResourceLocation DEFAULT_TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(
            KnightsHeraldry.MOD_ID, "textures/entity/craftman/" + DEFAULT_TEXTURE + ".png");

    private static final Map<String, Boolean> EXISTS_CACHE = new ConcurrentHashMap<>();
    private static final Map<String, ResourceLocation> TEXTURE_CACHE = new ConcurrentHashMap<>();

    public CraftmanRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel<>(context.bakeLayer(ModelLayers.VILLAGER)), 0.5F);
        this.addLayer(new BiomeOverlayLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Craftman entity) {
        return DEFAULT_TEXTURE_LOCATION;
    }

    private static String biomeKeyOf(Craftman entity) {
        ResourceLocation loc = entity.getCraftmanData().biomeKey();
        return loc.getNamespace().equals("minecraft")
                ? loc.getPath()
                : loc.getNamespace() + "_" + loc.getPath();
    }

    private static ResourceLocation resolveBiomeTexture(String biomeKey) {
        Boolean exists = EXISTS_CACHE.get(biomeKey);

        if (exists == null) {
            ResourceLocation candidate = ResourceLocation.fromNamespaceAndPath(
                    KnightsHeraldry.MOD_ID, "textures/entity/craftman/" + biomeKey + ".png");

            exists = Minecraft.getInstance().getResourceManager().getResource(candidate).isPresent();
            EXISTS_CACHE.put(biomeKey, exists);

            if (exists) {
                TEXTURE_CACHE.put(biomeKey, candidate);
            } else {
                LOGGER.error("Missing craftman biome texture '{}' for biome key '{}'; skipping overlay render.",
                        candidate, biomeKey);
            }
        }

        return exists ? TEXTURE_CACHE.get(biomeKey) : null;
    }

    private static class BiomeOverlayLayer extends RenderLayer<Craftman, VillagerModel<Craftman>> {

        BiomeOverlayLayer(CraftmanRenderer renderer) {
            super(renderer);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Craftman entity,
                           float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                           float netHeadYaw, float headPitch) {
            String biomeKey = biomeKeyOf(entity);
            ResourceLocation biomeTexture = resolveBiomeTexture(biomeKey);

            if (biomeTexture == null) {
                return;
            }

            VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(biomeTexture));
            int overlay = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);

            getParentModel().renderToBuffer(poseStack, consumer, packedLight, overlay, 0xFFFFFFFF);
        }
    }
}