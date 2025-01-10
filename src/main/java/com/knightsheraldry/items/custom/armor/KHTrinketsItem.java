
package com.knightsheraldry.items.custom.armor;

import com.google.common.collect.Multimap;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.model.*;
import com.knightsheraldry.util.DyeUtil;
import com.knightsheraldry.util.itemdata.KHTags;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.UUID;

public class KHTrinketsItem extends TrinketItem implements TrinketRenderer {
    public final TrinketAttributes attributes;
    public final Type type;
    private BipedEntityModel<LivingEntity> model;

    public KHTrinketsItem(Settings settings, Type type, double armor, double toughness, double hungerDrainAddition, Identifier texturePath) {
        super(settings);
        this.type = type;
        this.attributes = new TrinketAttributes(armor, toughness, hungerDrainAddition, texturePath);
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return Arrays.stream(EquipmentSlot.values())
                .filter(this::isArmorSlot)
                .allMatch(slotType -> stack.isIn(KHTags.ALWAYS_WEARABLE.getTag()) ||
                        entity.getEquippedStack(slotType).getItem() instanceof KHUnderArmorItem);
    }

    private boolean isArmorSlot(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD, CHEST, LEGS, FEET -> true;
            default -> false;
        };
    }

    public double getHungerDrainAddition() {
        return this.attributes.hungerDrainAddition();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        double toughness = this.attributes.toughness() + (stack.getOrCreateNbt().getBoolean("aventail") ? 2 : 0);

        if (attributes.armor() > 0 || toughness > 0) {
            modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid,
                    KnightsHeraldry.MOD_ID + ":protection", attributes.armor(), EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid,
                    KnightsHeraldry.MOD_ID + ":toughness", toughness, EntityAttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        BipedEntityModel<LivingEntity> model = getModel();
        TrinketRenderer.followBodyRotations(entity, model);

        if (model instanceof CloakHoodModel cloakHoodModel) {
            cloakHoodModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        }

        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(getPath()));
        float[] color = DyeUtil.getDyeColor(stack);

        model.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, color[0], color[1], color[2], 1.0F);
        renderOverlayAndAdditions(stack, matrices, vertexConsumers, light, model);
    }

    @Environment(EnvType.CLIENT)
    private void renderOverlayAndAdditions(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, BipedEntityModel<LivingEntity> model) {
        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "aventail", getIdentifierWithSuffix("_aventail"));
        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "rimmed", new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png"));
        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "besagews", new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/besagews.png"));

        if (stack.getItem() instanceof KHDyeableTrinketsItem khDyeableTrinketsItem && khDyeableTrinketsItem.getOverlay()) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, getIdentifierWithSuffix("_overlay"));
        }
    }

    private void renderPartIfNeeded(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, BipedEntityModel<LivingEntity> model, String key, Identifier identifier) {
        if (stack.getOrCreateNbt().getBoolean(key)) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, identifier);
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        StringBuilder translationKey = new StringBuilder(stack.getTranslationKey());
        if (stack.getOrCreateNbt().getBoolean("aventail")) translationKey.append("_aventail");
        if (stack.getOrCreateNbt().getBoolean("rimmed")) translationKey.append("_rimmed");
        if (stack.getOrCreateNbt().getBoolean("besagews")) translationKey.append("_besagews");
        return Text.translatable(translationKey.toString());
    }

    public Identifier getPath() {
        return attributes.texturePath();
    }

    private Identifier getIdentifierWithSuffix(String suffix) {
        String texturePath = attributes.texturePath().getPath().replace(".png", "") + suffix + ".png";
        return new Identifier(attributes.texturePath().getNamespace(), texturePath);
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getModel() {
        if (this.model == null && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            this.model = switch (this.type) {
                case HELMET -> new TrinketsHelmetModel(TrinketsHelmetModel.getTexturedModelData().createModel());
                case CHESTPLATE -> new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel());
                case CLOAK -> new CloakHoodModel(CloakHoodModel.getTexturedModelData().createModel());
                case LEGGINGS -> new TrinketsLeggingsModel(TrinketsLeggingsModel.getTexturedModelData().createModel());
                case BOOTS -> new TrinketsBootsModel(TrinketsBootsModel.getTexturedModelData().createModel());
            };
        }
        return this.model;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        if (player.currentScreenHandler instanceof CraftingScreenHandler craftingInventory) {
            applyCraftingModifiers(stack, craftingInventory.getCraftingSlotCount(), craftingInventory::getSlot);
        } else if (player.currentScreenHandler instanceof PlayerScreenHandler playerInventory) {
            applyCraftingModifiers(stack, 4, playerInventory::getSlot);
        }
    }

    private void applyCraftingModifiers(ItemStack stack, int slotCount, java.util.function.IntFunction<Slot> slotSupplier) {
        for (int i = 0; i < slotCount; i++) {
            ItemStack ingredient = slotSupplier.apply(i).getStack();
            if (ingredient.getItem() == ModItems.AVENTAIL) stack.getOrCreateNbt().putBoolean("aventail", true);
            if (ingredient.getItem() == ModItems.RIM_GUARDS) stack.getOrCreateNbt().putBoolean("rimmed", true);
            if (ingredient.getItem() == ModItems.BESAGEWS) stack.getOrCreateNbt().putBoolean("besagews", true);
        }
    }

    public enum Type {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS, CLOAK
    }

    public record TrinketAttributes(double armor, double toughness, double hungerDrainAddition, Identifier texturePath) { }
}
