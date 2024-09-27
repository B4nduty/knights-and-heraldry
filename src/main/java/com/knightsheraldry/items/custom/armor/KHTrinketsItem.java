
package com.knightsheraldry.items.custom.armor;

import com.google.common.collect.Multimap;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.model.TrinketsBootsModel;
import com.knightsheraldry.model.TrinketsChestplateModel;
import com.knightsheraldry.model.TrinketsHelmetModel;
import com.knightsheraldry.model.TrinketsLeggingsModel;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class KHTrinketsItem extends TrinketItem implements TrinketRenderer, DyeableItem {
    protected final Type type;
    protected final double armor;
    protected final double toughness;
    protected final double hungerDrainAddition;
    private final Identifier texturePath;
    private final boolean dyeable;

    public KHTrinketsItem(Settings settings, Type type, double armor, double toughness, double hungerDrainAddition, Identifier texturePath, boolean dyeable) {
        super(settings);
        this.type = type;
        this.armor = armor;
        this.toughness = toughness;
        this.hungerDrainAddition = hungerDrainAddition;
        this.texturePath = texturePath;
        this.dyeable = dyeable;
    }

    public double getHungerDrainAddition() {
        return this.hungerDrainAddition;
    }

    private BipedEntityModel<LivingEntity> model;

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, KnightsHeraldry.MOD_ID + ":" + "protection", this.armor, EntityAttributeModifier.Operation.ADDITION));
        modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, KnightsHeraldry.MOD_ID + ":" + "protection", this.toughness, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        BipedEntityModel<LivingEntity> model = this.getModel();
        TrinketRenderer.followBodyRotations(entity, model);
        if (stack.getItem() instanceof KHTrinketsItem khTrinketsItem) {
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                    RenderLayer.getArmorCutoutNoCull(khTrinketsItem.getPath()));
            if (isDyeable()) {
                int color = getColor(stack);

                float r = (color >> 16 & 255) / 255.0F;
                float g = (color >> 8 & 255) / 255.0F;
                float b = (color & 255) / 255.0F;

                Identifier textureOverlayPath = getIdentifier(khTrinketsItem);

                // Base armor render (tinted layer) - Render the armor with color tint
                model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);

                // Overlay render (untinted layer) - Render the overlay (no tint)
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, textureOverlayPath);
            } else model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        }
    }

    public boolean isDyeable() {return this.dyeable;}

    public Identifier getPath() {return this.texturePath;}

    private static @NotNull Identifier getIdentifier(KHTrinketsItem khTrinketsItem) {
        Identifier originalIdentifier = khTrinketsItem.getPath();

        String textureOverlayString = originalIdentifier.getPath();

        if (textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        textureOverlayString += "_overlay.png";

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getModel() {
        if (this.model == null) {
            switch (this.type) {
                case HELMET ->
                        this.model = new TrinketsHelmetModel(TrinketsHelmetModel.getTexturedModelData().createModel());
                case CHESTPLATE ->
                        this.model = new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel());
                case LEGGINGS ->
                        this.model = new TrinketsLeggingsModel(TrinketsLeggingsModel.getTexturedModelData().createModel());
                case BOOTS ->
                        this.model = new TrinketsBootsModel(TrinketsBootsModel.getTexturedModelData().createModel());
                default -> KnightsHeraldry.LOGGER.error("Don't specified Model or not a Valid Model");
            }
        }

        return this.model;
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 10511680;
    }

    public enum Type {
        HELMET("helmet"),
        CHESTPLATE("chestplate"),
        LEGGINGS("leggings"),
        BOOTS("boots");

        private final String model;

        Type(String model) {
            this.model = model;
        }

        public String getName() {
            return this.model;
        }
    }
}