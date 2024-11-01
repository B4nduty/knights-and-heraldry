package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.client.item.KHGeoRangeWeaponsModel;
import com.knightsheraldry.event.KeyInputHandler;
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

    /**
     * <p><b>Warning:</b>
     * This class is made for use of KnightsHeraldry, you can use it, but it isn't made to use by external people.
     * It is a class made only to reduce files and storage space.
     */
    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, int maxUseTime, float damage,
                             double blockRange, UseAction useAction, SoundEvent... soundEvents) {
        super(settings, damageType, maxUseTime, damage, blockRange, useAction, soundEvents);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, int maxUseTime, float damage,
                             double blockRange, UseAction useAction, int rechargeTime, SoundEvent... soundEvents) {
        super(settings, damageType, maxUseTime, damage, blockRange, useAction, rechargeTime, soundEvents);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, int maxUseTime, float damage,
                             double blockRange, UseAction useAction, int rechargeTime, int amountFirstItem, Item firstItem, Item firstItem2nOption, int amountSecondItem,
                             Item secondItem, Item secondItem2nOption, int amountThirdItem, Item thirdItem, Item thirdItem2nOption, boolean needsFlintAndSteel, int cooldown, SoundEvent... soundEvents) {
        super(settings, damageType, maxUseTime, damage, blockRange, useAction, rechargeTime, amountFirstItem, firstItem,
                firstItem2nOption, amountSecondItem, secondItem, secondItem2nOption, amountThirdItem, thirdItem,
                thirdItem2nOption, needsFlintAndSteel, cooldown, soundEvents);
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
        if (getFirstItem() != null && isShooting(itemStack) && animationState.getController().getAnimationState() == AnimationController.State.PAUSED) setShooting(itemStack, false);
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
        if (getFirstItem() != null)
            tooltip.add(Text.translatable("tooltip.knightsheraldry.need_to_hold", KeyInputHandler.reload.getBoundKeyLocalizedText()));
    }
}