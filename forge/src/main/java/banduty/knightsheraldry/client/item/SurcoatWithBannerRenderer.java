package banduty.knightsheraldry.client.item;

import banduty.knightsheraldry.items.ModItems;
import banduty.stoneycore.util.patterns.PatternHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import java.util.List;

public class SurcoatWithBannerRenderer {
    public static void render(ItemStack stack, ItemDisplayContext displayContext,
                              PoseStack poseStack, MultiBufferSource bufferSource,
                              int combinedLight, int combinedOverlay) {

        Minecraft minecraft = Minecraft.getInstance();
        ItemRenderer itemRenderer = minecraft.getItemRenderer();

        // Render base model
        BakedModel baseModel = itemRenderer.getModel(stack, null, null, 0);
        itemRenderer.render(stack, displayContext, false, poseStack, bufferSource,
                combinedLight, combinedOverlay, baseModel);

        // Render banner patterns on top
        if (stack.is(ModItems.SURCOAT.get()) || stack.is(ModItems.SURCOAT_SLEEVELESS.get())) {
            List<Tuple<ResourceLocation, DyeColor>> patterns = PatternHelper.getBannerPatterns(stack);

            // FIXED: Use < instead of == in the loop condition
            for(int i = 0; i < patterns.size(); i++) {
                ResourceLocation patternLocation = patterns.get(i).getA();
                BakedModel patternModel = minecraft.getModelManager().getModel(patternLocation);

                // If pattern model doesn't exist, use missing model
                if (patternModel == minecraft.getModelManager().getMissingModel()) {
                    continue; // Skip if pattern model doesn't exist
                }

                poseStack.pushPose();

                // Add a small offset for each layer to prevent z-fighting
                float offset = 0.001f * (i + 1);
                poseStack.translate(0, 0, offset);

                // Apply color tint if needed (for banner pattern colors)
                // You might need to handle this differently based on your needs

                itemRenderer.render(stack, displayContext, false, poseStack,
                        bufferSource, combinedLight, combinedOverlay, patternModel);

                poseStack.popPose();
            }
        }
    }
}