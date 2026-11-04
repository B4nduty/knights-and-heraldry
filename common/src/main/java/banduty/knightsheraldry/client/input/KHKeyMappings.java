package banduty.knightsheraldry.client.input;

import banduty.knightsheraldry.platform.ClientServices;
import banduty.knightsheraldry.util.GripUtil;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

/** Key mappings shared by both loaders; each loader registers them and calls {@link #onClientTick}. */
public final class KHKeyMappings {
    private KHKeyMappings() {}

    public static final String CATEGORY = "key.categories.knightsheraldry";

    public static final KeyMapping TOGGLE_GRIP = new KeyMapping(
            "key.knightsheraldry.toggle_grip", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, CATEGORY);

    public static void onClientTick(Minecraft minecraft) {
        while (TOGGLE_GRIP.consumeClick()) {
            if (minecraft.player != null && GripUtil.isVersatile(minecraft.player.getMainHandItem())) {
                ClientServices.getClientHelper().sendToggleGrip();
            }
        }
    }
}
