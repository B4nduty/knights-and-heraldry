package banduty.knightsheraldry.data;

import banduty.knightsheraldry.KnightsHeraldry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID)
public class ArrowBehaviorManager extends SimpleJsonResourceReloadListener {
    public static final ArrowBehaviorManager INSTANCE = new ArrowBehaviorManager();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Map<ResourceLocation, ArrowBehavior> behaviors = new HashMap<>();

    public ArrowBehaviorManager() {
        super(GSON, "arrow_behaviors");
    }

    public ArrowBehavior getBehavior(ResourceLocation itemId) {
        return behaviors.get(itemId);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        behaviors.clear();
        object.forEach((location, jsonElement) -> {
            try {
                ArrowBehavior behavior = GSON.fromJson(jsonElement, ArrowBehavior.class);
                if (behavior != null && behavior.targetItemId != null) {
                    behaviors.put(behavior.targetItemId, behavior);
                }
            } catch (Exception e) {
                System.err.println("Failed to parse arrow behavior: " + location);
            }
        });
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(INSTANCE);
    }
}