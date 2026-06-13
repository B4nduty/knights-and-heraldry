package banduty.knightsheraldry.entity.custom;

import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.entity.custom.SCArrowEntity;
import banduty.stoneycore.util.data.entitydata.IEntityDataSaver;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class KHSwallowTailArrowEntity extends SCArrowEntity {

    private Player stuckPlayer;

    public KHSwallowTailArrowEntity(EntityType<? extends KHSwallowTailArrowEntity> type, Level level) {
        super(type, level);
    }

    public KHSwallowTailArrowEntity(LivingEntity shooter, Level level, ItemStack stack, @Nullable ItemStack firedFromWeapon) {
        super(KHEntities.SWALLOWTAIL_ARROW.get(), shooter, level, stack, firedFromWeapon);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(KHItems.SWALLOWTAIL_ARROW.get());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(KHItems.SWALLOWTAIL_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);

        if (!(entityHitResult.getEntity() instanceof LivingEntity target)) {
            return;
        }

        if (target instanceof Player player && stuckPlayer == null) {
            if (player.isCreative()) return;

            stuckPlayer = player;
            updateSwallowTailArrowCount(player);
        }

        scHitEntity(
                target,
                new ItemStack(KHItems.SWALLOWTAIL_ARROW.get()),
                getBaseDamage()
        );
    }

    private void updateSwallowTailArrowCount(Player player) {
        int currentCount =
                ((IEntityDataSaver) player)
                        .stoneycore$getPersistentData()
                        .getInt("swallowtailArrowCount");

        ((IEntityDataSaver) player)
                .stoneycore$getPersistentData()
                .putInt("swallowtailArrowCount", currentCount + 1);
    }
}