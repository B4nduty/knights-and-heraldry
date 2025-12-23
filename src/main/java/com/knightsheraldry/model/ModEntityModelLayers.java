package com.knightsheraldry.model;

import com.google.common.collect.Sets;
import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class ModEntityModelLayers {
    private static final Set<ModelLayerLocation> LAYERS = Sets.newHashSet();
    public static final ModelLayerLocation HORSE_BARDING_MODEL_LAYER = registerMain("horse_barding");

    private static ModelLayerLocation registerMain(String id) {
        return register(id, "main");
    }

    private static ModelLayerLocation register(String id, String layer) {
        ModelLayerLocation modelLayerLocation = create(id, layer);
        if (!LAYERS.add(modelLayerLocation)) {
            throw new IllegalStateException("Duplicate registration for " + modelLayerLocation);
        } else {
            return modelLayerLocation;
        }
    }

    private static ModelLayerLocation create(String id, String layer) {
        return new ModelLayerLocation(new ResourceLocation(KnightsHeraldry.MOD_ID, id), layer);
    }
}
