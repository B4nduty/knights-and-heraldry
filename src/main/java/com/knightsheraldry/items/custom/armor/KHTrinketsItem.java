
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
import net.minecraft.block.entity.BannerPattern;
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
import net.minecraft.item.BannerItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
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
        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(getPath()));
        float r = 1, g = 1, b = 1;
        if (isDyeable()) {
            int color = getColor(stack);
            r = (color >> 16 & 255) / 255.0F;
            g = (color >> 8 & 255) / 255.0F;
            b = (color & 255) / 255.0F;
        }
        model.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
        if (isDyeable()) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, getOverlayIdentifier(this));
        if (stack.getOrCreateNbt().getBoolean("aventail")) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel()), getAventailIdentifier(this));
        if (stack.getOrCreateNbt().getBoolean("rimmed")) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png"));
        if (stack.getOrCreateNbt().getBoolean("besagews")) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/besagews.png"));
        NbtCompound nbt = stack.getOrCreateNbt();
        NbtList patterns = nbt.getList("Patterns", 10);

        if (stack.getOrCreateNbt().contains("Pattern") && !patterns.isEmpty()) {
            for (int i = 0; i < patterns.size(); i++) {
                NbtCompound patternNbt = patterns.getCompound(i);
                String patternId = patternNbt.getString("Pattern");

                BannerPattern pattern = (BannerPattern) BannerPattern.byId(patternId);
                if (pattern != null) {
                    Identifier patternTexture = new Identifier("minecraft", "textures/entity/banner/" + pattern.getId() + ".png");

                    VertexConsumer patternConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(patternTexture));
                    model.render(matrices, patternConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1.0F);
                }
            }
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        var text = stack.getTranslationKey();
        if (stack.getOrCreateNbt().getBoolean("aventail")) {
            text += "_aventail";
        }
        if (stack.getOrCreateNbt().getBoolean("rimmed")) {
            text += "_rimmed";
        }
        if (stack.getOrCreateNbt().getBoolean("besagews")) {
            text += "_besagews";
        }
        return Text.translatable(text);
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

        textureOverlayString += "_overlay.png";

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }

    private @NotNull Identifier getAventailIdentifier(KHTrinketsItem khTrinketsItem) {
        Identifier originalIdentifier = khTrinketsItem.getPath();

        String textureOverlayString = null;
        if (originalIdentifier != null) {
            textureOverlayString = originalIdentifier.getPath();
        }

        if (textureOverlayString != null && textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        textureOverlayString += "_aventail.png";

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
        boolean foundBesagews = false;
        if (player.currentScreenHandler instanceof CraftingScreenHandler craftingInventory) {
            for (int i = 0; i < craftingInventory.getCraftingSlotCount(); i++) {
                ItemStack ingredient = craftingInventory.getSlot(i).getStack();
                if (ingredient.getItem() == ModItems.AVENTAIL) {
                    foundAventail = true;
                    break;
                }
                if (ingredient.getItem() instanceof BannerItem) {
                    NbtCompound bannerNbt = ingredient.getSubNbt("Pattern");
                    if (bannerNbt != null) {
                        stack.getOrCreateNbt().put("Pattern", bannerNbt.copy());
                    }
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
                if (ingredient.getItem() == ModItems.BESAGEWS) {
                    foundBesagews = true;
                    break;
                }
            }
        }

        if (foundAventail) {
            stack.getOrCreateNbt().putBoolean("aventail", true);
        }

        if (foundRimGuards) {
            stack.getOrCreateNbt().putBoolean("rimmed", true);
        }

        if (foundBesagews) {
            stack.getOrCreateNbt().putBoolean("besagews", true);
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