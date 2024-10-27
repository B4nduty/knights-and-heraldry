package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.client.item.KHGeoRangeWeaponsModel;
import com.knightsheraldry.event.KeyInputHandler;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class KHGeoRangeWeapons extends KHRangeWeapons implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, float damage, double blockRange,
                             UseAction useAction, SoundEvent... soundEvents) {
        super(settings, damageType, damage, blockRange, useAction, soundEvents);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, float damage, double blockRange,
                             UseAction useAction, int rechargeTime, SoundEvent... soundEvents) {
        super(settings, damageType, damage, blockRange, useAction, rechargeTime, soundEvents);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, float damage, double blockRange,
                             UseAction useAction, int rechargeTime, Item firstItem, Item firstItem2nOption, Item secondItem,
                             Item secondItem2nOption, Item thirdItem, Item thirdItem2nOption, SoundEvent... soundEvents) {
        super(settings, damageType, damage, blockRange, useAction, rechargeTime, firstItem, firstItem2nOption, secondItem,
                secondItem2nOption, thirdItem, thirdItem2nOption, soundEvents);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoItemRenderer<KHGeoRangeWeapons> renderer;

            @Override
            public GeoItemRenderer<KHGeoRangeWeapons> getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new KHGeoRangeWeaponsModel());

                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<KHGeoRangeWeapons> animationState) {
        ItemStack itemStack = animationState.getData(DataTickets.ITEMSTACK);
        if (isShooting(itemStack) && animationState.getController().getAnimationState() == AnimationController.State.PAUSED) {
            setShooting(itemStack, false);
        }
        if (isReloading(itemStack)) animationState.getController().setAnimation(RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (isCharged(itemStack)) animationState.getController().setAnimation(RawAnimation.begin().then("charged", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (isShooting(itemStack)) animationState.getController().setAnimation(RawAnimation.begin().then("shoot", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else animationState.getController().setAnimation(RawAnimation.begin().then("unloaded", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (stack.getItem() == ModItems.ARQUEBUS)
            tooltip.add(Text.translatable("tooltip.knightsheraldry.need_to_hold", KeyInputHandler.reload.getBoundKeyLocalizedText()));
    }
}