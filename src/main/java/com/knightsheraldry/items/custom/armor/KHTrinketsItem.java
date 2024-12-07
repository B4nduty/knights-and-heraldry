
package com.knightsheraldry.items.custom.armor;

import com.google.common.collect.Multimap;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.model.*;
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
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.UUID;

public class KHTrinketsItem extends TrinketItem implements TrinketRenderer, DyeableItem {
    public final TrinketAttributes attributes;
    public final Type type;
    @Environment(EnvType.CLIENT) private BipedEntityModel<LivingEntity> model;

    public KHTrinketsItem(Settings settings, Type type, TrinketAttributes attributes) {
        super(settings);
        this.type = type;
        this.attributes = attributes;
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (!this.getDefaultStack().isIn(KHTags.ALWAYS_WEARABLE.getTag()) && isArmorSlot(equipmentSlot)) {
                if (!(entity.getEquippedStack(equipmentSlot).getItem() instanceof KHUnderArmorItem)) {
                    return false;
                }
            }
        }
        return true;
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

        if (attributes.armor() == 0 && toughness == 0) return modifiers;

        modifiers.put(EntityAttributes.GENERIC_ARMOR,
                new EntityAttributeModifier(uuid, KnightsHeraldry.MOD_ID + ":protection", attributes.armor(), EntityAttributeModifier.Operation.ADDITION));
        modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                new EntityAttributeModifier(uuid, KnightsHeraldry.MOD_ID + ":toughness", toughness, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        BipedEntityModel<LivingEntity> model = this.getModel();
        TrinketRenderer.followBodyRotations(entity, model);

        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(getPath()));
        float[] color = getDyeColor(stack);

        model.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, color[0], color[1], color[2], 1.0F);
        renderOverlayAndAdditions(stack, matrices, vertexConsumers, light, model);
    }

    @Environment(EnvType.CLIENT)
    private void renderOverlayAndAdditions(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, BipedEntityModel<LivingEntity> model) {
        if (attributes.dyeable() != null)
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, getIdentifierWithSuffix("_overlay"));
        if (stack.getOrCreateNbt().getBoolean("aventail"))
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel()), getIdentifierWithSuffix("_aventail"));
        if (stack.getOrCreateNbt().getBoolean("rimmed"))
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png"));
        if (stack.getOrCreateNbt().getBoolean("besagews"))
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/besagews.png"));
    }

    @Override
    public Text getName(ItemStack stack) {
        String text = stack.getTranslationKey();
        if (stack.getOrCreateNbt().getBoolean("aventail")) text += "_aventail";
        if (stack.getOrCreateNbt().getBoolean("rimmed")) text += "_rimmed";
        if (stack.getOrCreateNbt().getBoolean("besagews")) text += "_besagews";
        return Text.translatable(text);
    }

    public final boolean isDyeable() { return attributes.dyeable() != null; }

    public final Identifier getPath() { return attributes.texturePath(); }

    private Identifier getIdentifierWithSuffix(String suffix) {
        String texturePath = attributes.texturePath().getPath().replace(".png", "") + suffix + ".png";
        return new Identifier(attributes.texturePath().getNamespace(), texturePath);
    }

    @Environment(EnvType.CLIENT)
    public final BipedEntityModel<LivingEntity> getModel() {
        if (this.model == null && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            switch (this.type) {
                case HELMET ->
                        this.model = new TrinketsHelmetModel(TrinketsHelmetModel.getTexturedModelData().createModel());
                case CHESTPLATE ->
                        this.model = new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel());
                case CLOAK -> this.model = new CloakHoodModel(CloakHoodModel.getTexturedModelData().createModel());
                case LEGGINGS ->
                        this.model = new TrinketsLeggingsModel(TrinketsLeggingsModel.getTexturedModelData().createModel());
                case BOOTS ->
                        this.model = new TrinketsBootsModel(TrinketsBootsModel.getTexturedModelData().createModel());
                default -> KnightsHeraldry.LOGGER.error("Don't specified Model or not a Valid Model");
            }
        }
        return this.model;
    }

    private float[] getDyeColor(ItemStack stack) {
        int color = getColor(stack);
        return new float[]{(color >> 16 & 255) / 255.0F, (color >> 8 & 255) / 255.0F, (color & 255) / 255.0F};
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : attributes.dyeable().defaultColor();
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
        HELMET("helmet"),
        CHESTPLATE("chestplate"),
        LEGGINGS("leggings"),
        BOOTS("boots"),
        CLOAK("cloak");

        private final String model;

        Type(String model) {
            this.model = model;
        }

        public String getName() {
            return this.model;
        }
    }

    public record TrinketAttributes(double armor, double toughness, double hungerDrainAddition, Identifier texturePath, Dyeable dyeable) {
        public record Dyeable(int defaultColor, boolean overlay) {}
    }
}