package banduty.knightsheraldry.items.item.khweapon.flail;

import banduty.knightsheraldry.client.item.weapon.FlailModel;
import banduty.knightsheraldry.client.item.weapon.FlailRenderer;
import banduty.knightsheraldry.items.ModToolMaterials;
import banduty.knightsheraldry.platform.ClientServices;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class Flail extends SwordItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private LivingEntity fallbackLivingEntity = null;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (isSelected && entity instanceof LivingEntity livingEntity) {
            this.fallbackLivingEntity = livingEntity;
        } else fallbackLivingEntity = null;
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    public Flail(float attackSpeed, Properties properties) {
        super(ModToolMaterials.WEAPONS,
                properties.attributes(SwordItem.createAttributes(ModToolMaterials.WEAPONS, 1, attackSpeed)));
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<Flail> renderer;

            @Override
            public GeoItemRenderer<Flail> getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new FlailRenderer(new FlailModel());

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<Flail> animationState) {
        RawAnimation animation = RawAnimation.begin().then("standby", Animation.LoopType.LOOP);

        if (Services.PLATFORM.isModLoaded("bettercombat")) animation = ClientServices.getClientHelper().loadAnimation(fallbackLivingEntity);

        animationState.getController().setAnimation(animation);

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}