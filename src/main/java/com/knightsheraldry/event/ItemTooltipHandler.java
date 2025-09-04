package com.knightsheraldry.event;

import com.knightsheraldry.items.item.khammo.ClothArrow;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class ItemTooltipHandler implements ItemTooltipCallback {
    @Override
    public void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (stack.getItem() instanceof ClothArrow) {
            if (stack.getNbt() != null && stack.getNbt().getBoolean("ignited")) {
                lines.add(Text.translatable("text.tooltip.knightsheraldry.extinguish"));
            } else if (stack.getNbt() == null || !stack.getNbt().getBoolean("extinguished"))
                lines.add(Text.translatable("text.tooltip.knightsheraldry.ignite"));
        }

        if (stack.getItem() instanceof Handgonne && stack.getNbt() != null && stack.getNbt().getBoolean("charged"))
                lines.add(Text.translatable("text.tooltip.knightsheraldry.right_click-flint_steel-fire"));

        for (HelmetDeco helmetDeco : HelmetDeco.HELMET_DECO.values()) {
            if (stack.isOf(helmetDeco.item())) continue;
            if (!(stack.getNbt() != null && stack.getNbt().getBoolean(helmetDeco.getNbtKey()))) return;

            ItemStack decoStack = new ItemStack(helmetDeco.item());

            // Get Minecraft's ItemRenderer
            MinecraftClient client = MinecraftClient.getInstance();
            ItemRenderer renderer = client.getItemRenderer();

            // Dummy MatrixStack and VertexConsumerProvider for rendering
            MatrixStack matrices = new MatrixStack();
            VertexConsumerProvider.Immediate vertexConsumers = client.getBufferBuilders().getEntityVertexConsumers();

            // Render the item in GUI mode at x=10, y=10 (adjust as needed)
            renderer.renderItem(
                    player,                          // entity = null
                    decoStack,                     // the item to render
                    ModelTransformationMode.GUI,   // render mode
                    false,                         // leftHanded
                    matrices,
                    vertexConsumers,
                    player.getWorld(),                          // world = null
                    0,                             // light
                    0,                             // overlay
                    42                             // seed (any int)
            );

            // Flush the buffers so it actually draws
            vertexConsumers.draw();
        }
    }
}
