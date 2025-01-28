package com.knightsheraldry.items.custom.item.khrangeweapon;

import com.knightsheraldry.client.item.HandgonneModel;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.item.KHRangeWeapon;
import com.knightsheraldry.sounds.ModSounds;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.weaponutil.KHRangeWeaponUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.UseAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Handgonne extends Item implements KHRangeWeapon, GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public Handgonne(Settings settings) {
        super(settings);
    }

    @Override
    public @NotNull KHDamageCalculator.DamageType getDamageType() {
        return KHDamageCalculator.DamageType.BLUDGEONING;
    }

    @Override
    public int maxUseTime() {
        return 1;
    }

    @Override
    public float baseDamage() {
        return 26f;
    }

    @Override
    public float speed() {
        return 1.9f;
    }

    @Override
    public @Nullable AmmoRequirement ammoRequirement() {
        return new AmmoRequirement(
                2, ModItems.BLACK_POWDER, null,
                1, Items.IRON_NUGGET, Items.GRAVEL,
                1, Items.PAPER, Items.GRASS
        );
    }

    @Override
    public int rechargeTime() {
        return 15 * 20;
    }

    @Override
    public boolean needsFlintAndSteel() {
        return true;
    }

    @Override
    public @Nullable SoundEvent[] soundEvents() {
        return new SoundEvent[]{
                ModSounds.ARQUEBUS_CLOSE_1, ModSounds.ARQUEBUS_CLOSE_2, ModSounds.ARQUEBUS_CLOSE_3
        };
    }

    @Override
    public UseAction useAction() {
        return UseAction.BOW;
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoItemRenderer<Handgonne> renderer;

            @Override
            public GeoItemRenderer<Handgonne> getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new HandgonneModel());

                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<Handgonne> animationState) {
        ItemStack itemStack = animationState.getData(DataTickets.ITEMSTACK);
        if (KHRangeWeaponUtil.getWeaponState(itemStack).isShooting()) animationState.getController().setAnimation(RawAnimation.begin().then("shoot", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (KHRangeWeaponUtil.getWeaponState(itemStack).isReloading()) animationState.getController().setAnimation(RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (KHRangeWeaponUtil.getWeaponState(itemStack).isCharged()) animationState.getController().setAnimation(RawAnimation.begin().then("charged", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else animationState.getController().setAnimation(RawAnimation.begin().then("unloaded", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
