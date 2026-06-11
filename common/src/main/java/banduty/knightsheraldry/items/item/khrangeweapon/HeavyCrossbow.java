package banduty.knightsheraldry.items.item.khrangeweapon;

import banduty.knightsheraldry.client.item.weapon.HeavyCrossbowRenderer;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.util.definitionsloader.ArmorAttachmentDefinitionsStorage;
import banduty.stoneycore.util.definitionsloader.WeaponDefinitionsStorage;
import banduty.stoneycore.util.weaponutil.SCRangeWeaponUtil;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class HeavyCrossbow extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private LivingEntity fallbackLivingEntity = null;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (isSelected && entity instanceof LivingEntity livingEntity) {
            this.fallbackLivingEntity = livingEntity;
        } else fallbackLivingEntity = null;
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    public HeavyCrossbow(Properties properties) {
        super(properties);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private HeavyCrossbowRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new HeavyCrossbowRenderer();
                }
                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<HeavyCrossbow> animationState) {
        ItemStack itemStack = animationState.getData(DataTickets.ITEMSTACK);
        AnimationController<HeavyCrossbow> controller = animationState.getController();
        if (fallbackLivingEntity == null) return PlayState.STOP;
        if (SCRangeWeaponUtil.getWeaponState(itemStack).isShooting()) {
            controller.setAnimationSpeed(1.0);
            animationState.getController().setAnimation(RawAnimation.begin().then("shoot", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        else if (SCRangeWeaponUtil.getWeaponState(itemStack).isReloading()) {
            int rechargeTime = WeaponDefinitionsStorage.getData(itemStack).ranged().rechargeTime();
            for (ItemStack armorStack : fallbackLivingEntity.getArmorSlots()) {
                for (ItemStack attachment : SCUnderArmor.getArmorAttachments(armorStack)) {
                    rechargeTime += ArmorAttachmentDefinitionsStorage.getData(attachment).rechargeTime();
                }
            }
            controller.setAnimationSpeed((double) WeaponDefinitionsStorage.getData(itemStack).ranged().rechargeTime() / rechargeTime);
            animationState.getController().setAnimation(RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        else if (SCRangeWeaponUtil.getWeaponState(itemStack).isCharged()) {
            controller.setAnimationSpeed(1.0);
            animationState.getController().setAnimation(RawAnimation.begin().then("charged", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        else {
            controller.setAnimationSpeed(1.0);
            animationState.getController().setAnimation(RawAnimation.begin().then("unloaded", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
