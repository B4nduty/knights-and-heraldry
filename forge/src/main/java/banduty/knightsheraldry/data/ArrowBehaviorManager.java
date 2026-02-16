package banduty.knightsheraldry.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "knightsheraldry", bus = Mod.EventBusSubscriber.Bus.FORGE)
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
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(INSTANCE);
    }
}