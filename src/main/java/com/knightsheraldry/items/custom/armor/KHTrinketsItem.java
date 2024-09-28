
package com.knightsheraldry.items.custom.armor;

import com.google.common.collect.Multimap;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
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
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class KHTrinketsItem extends TrinketItem implements TrinketRenderer, DyeableItem {
    public final Type type;
    protected final double armor;
    protected final double toughness;
    protected final double hungerDrainAddition;
    private final Identifier texturePath;
    private final boolean dyeable;

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return isWearingFullKHArmorSet(entity);
    }

    private boolean isWearingFullKHArmorSet(LivingEntity entity) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (isArmorSlot(slot)) {
                ItemStack armorPiece = entity.getEquippedStack(slot);
                if (!(armorPiece.getItem() instanceof KHArmorItem)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isArmorSlot(EquipmentSlot slot) {
        return slot == EquipmentSlot.HEAD || slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
    }

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
        double toughness = this.toughness;
        if (stack.getOrCreateNbt().getBoolean("aventail")){
            toughness += 2;
        }
        modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, KnightsHeraldry.MOD_ID + ":" + "protection", this.armor, EntityAttributeModifier.Operation.ADDITION));
        modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, KnightsHeraldry.MOD_ID + ":" + "toughness", toughness, EntityAttributeModifier.Operation.ADDITION));
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
            float r = 1;
            float g = 1;
            float b = 1;
            if (isDyeable()) {
                int color = getColor(stack);
                r = (color >> 16 & 255) / 255.0F;
                g = (color >> 8 & 255) / 255.0F;
                b = (color & 255) / 255.0F;
            }

            Identifier textureOverlayPath = getOverlayIdentifier(khTrinketsItem);
            Identifier textureAventailPath = getAventailIdentifier(stack, khTrinketsItem);

            // Base armor render (tinted layer) - Render the armor with color tint
            if (!stack.getOrCreateNbt().getBoolean("aventail"))
                model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);

            // Overlay render (untinted layer) - Render the overlay (no tint)
            if (!textureOverlayPath.equals(new Identifier(""))) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, textureOverlayPath);
            if (!textureAventailPath.equals(new Identifier(""))) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, textureAventailPath);
            if (stack.getOrCreateNbt().getBoolean("rimGuards")) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png"));
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        if (stack.getOrCreateNbt().getBoolean("aventail")) {
            return Text.translatable(stack.getTranslationKey() + "_aventail");
        }
        if (stack.getOrCreateNbt().getBoolean("rimGuards")) {
            return Text.translatable(stack.getTranslationKey() + "_rimGuards");
        }
        return super.getName(stack);
    }

    public boolean isDyeable() {return this.dyeable;}

    public Identifier getPath() {return this.texturePath;}

    private @NotNull Identifier getOverlayIdentifier(KHTrinketsItem khTrinketsItem) {
        Identifier originalIdentifier = khTrinketsItem.getPath();

        String textureOverlayString = null;
        if (originalIdentifier != null) {
            textureOverlayString = originalIdentifier.getPath();
        }

        if (textureOverlayString != null && textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        if (isDyeable()) textureOverlayString += "_overlay.png";

        else return new Identifier("");

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }

    private @NotNull Identifier getAventailIdentifier(ItemStack stack, KHTrinketsItem khTrinketsItem) {
        Identifier originalIdentifier = khTrinketsItem.getPath();

        String textureOverlayString = null;
        if (originalIdentifier != null) {
            textureOverlayString = originalIdentifier.getPath();
        }

        if (textureOverlayString != null && textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        if (stack.getOrCreateNbt().getBoolean("aventail")) {
            textureOverlayString += "_aventail.png";
        }

        else return new Identifier("");

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

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        boolean foundAventail = false;
        boolean foundRimGuards = false;
        if (player.currentScreenHandler instanceof CraftingScreenHandler craftingInventory) {
            for (int i = 0; i < craftingInventory.getCraftingSlotCount(); i++) {
                ItemStack ingredient = craftingInventory.getSlot(i).getStack();
                if (ingredient.getItem() == ModItems.AVENTAIL) {
                    foundAventail = true;
                    break;
                }
            }
        } else if (player.currentScreenHandler instanceof PlayerScreenHandler playerInventoryCrafting) {
            for (int i = 1; i <= 4; i++) {
                Slot slot = playerInventoryCrafting.getSlot(i);
                ItemStack ingredient = slot.getStack();
                if (ingredient.getItem() == ModItems.AVENTAIL) {
                    foundAventail = true;
                    break;
                }
            }
        } else if (player.currentScreenHandler instanceof SmithingScreenHandler smithingInventory) {
            for (int i = 0; i < 2; i++) {
                ItemStack ingredient = smithingInventory.getSlot(i).getStack();
                if (ingredient.getItem() == ModItems.RIM_GUARDS) {
                    foundRimGuards = true;
                    break;
                }
            }
        }

        if (foundAventail && stack.getItem() != ModItems.AVENTAIL) {
            stack.getOrCreateNbt().putBoolean("aventail", true);
        }

        if (foundRimGuards && stack.getItem() != ModItems.RIM_GUARDS) {
            stack.getOrCreateNbt().putBoolean("rimGuards", true);
        }
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