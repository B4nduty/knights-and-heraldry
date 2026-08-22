package banduty.knightsheraldry.client.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.Craftman;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CraftmanRenderer extends MobRenderer<Craftman, VillagerModel<Craftman>> {

    private static final String DEFAULT_TEXTURE = "default";

    private static final Map<String, ResourceLocation> TEXTURE_CACHE = new ConcurrentHashMap<>();

    public CraftmanRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel<>(context.bakeLayer(ModelLayers.VILLAGER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Craftman entity) {
        ResourceLocation loc = entity.getCraftmanData().biomeKey();
        String biomeKey = loc.getNamespace().equals("minecraft")
                ? loc.getPath()
                : loc.getNamespace() + "_" + loc.getPath();

        return TEXTURE_CACHE.computeIfAbsent(biomeKey, this::resolveTexture);
    }

    private ResourceLocation resolveTexture(String biomeKey) {
        ResourceLocation candidate = ResourceLocation.fromNamespaceAndPath(
                KnightsHeraldry.MOD_ID, "textures/entity/craftman/" + biomeKey + ".png");

        if (Minecraft.getInstance().getResourceManager().getResource(candidate).isPresent()) {
            return candidate;
        }

        return ResourceLocation.fromNamespaceAndPath(
                KnightsHeraldry.MOD_ID, "textures/entity/craftman/" + DEFAULT_TEXTURE + ".png");
    }
}