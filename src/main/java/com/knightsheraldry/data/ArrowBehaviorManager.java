package com.knightsheraldry.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ArrowBehaviorManager implements SimpleSynchronousResourceReloadListener {
    public static final ArrowBehaviorManager INSTANCE = new ArrowBehaviorManager();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Map<Identifier, ArrowBehavior> behaviors = new HashMap<>();

    public ArrowBehavior getBehavior(Identifier itemId) {
        return behaviors.get(itemId);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("knightsheraldry", "arrow_behavior_loader");
    }

    @Override
    public void reload(ResourceManager manager) {
        behaviors.clear();
        // Load JSONs from data/*/arrow_behaviors/*.json across all namespaces
        String basePath = "arrow_behaviors";

        manager.findResources(basePath, path -> path.getPath().endsWith(".json"))
                .forEach((id, resource) -> {
                    try (var in = resource.getInputStream(); var reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
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
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(INSTANCE);
    }
}