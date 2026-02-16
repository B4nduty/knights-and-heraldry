package banduty.knightsheraldry.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class SurcoatItemRenderer extends BlockEntityWithoutLevelRenderer {
    private static SurcoatItemRenderer INSTANCE;

    public SurcoatItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels());
    }

    public static SurcoatItemRenderer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SurcoatItemRenderer();
        }
        return INSTANCE;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext,
                             PoseStack poseStack, MultiBufferSource bufferSource,
                             int combinedLight, int combinedOverlay) {
        SurcoatWithBannerRenderer.render(stack, displayContext, poseStack,
                bufferSource, combinedLight, combinedOverlay);
    }

    public static void register(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return getInstance();
            }
        });
    }
}