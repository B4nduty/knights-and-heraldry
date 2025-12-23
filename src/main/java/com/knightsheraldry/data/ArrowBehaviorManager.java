package com.knightsheraldry.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ArrowBehaviorManager implements SimpleSynchronousResourceReloadListener {
    public static final ArrowBehaviorManager INSTANCE = new ArrowBehaviorManager();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Map<ResourceLocation, ArrowBehavior> behaviors = new HashMap<>();

    public ArrowBehavior getBehavior(ResourceLocation itemId) {
        return behaviors.get(itemId);
    }

    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation("knightsheraldry", "arrow_behavior_loader");
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        behaviors.clear();
        String basePath = "arrow_behaviors";

        manager.listResources(basePath, path -> path.getPath().endsWith(".json"))
                .forEach((id, resource) -> {
                    try (var in = resource.open(); var reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                        JsonElement json = GSON.fromJson(reader, JsonElement.class);
                        ArrowBehavior behavior = GSON.fromJson(json, ArrowBehavior.class);
                        if (behavior != null && behavior.targetItemId != null) {
                            behaviors.put(behavior.targetItemId, behavior);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void register() {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(INSTANCE);
    }
}