package banduty.knightsheraldry.items.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class KHExtendedArrowItem extends ArrowItem {

    @FunctionalInterface
    public interface ArrowFactory {
        AbstractArrow create(
                LivingEntity shooter,
                Level level,
                ItemStack stack,
                @Nullable ItemStack weapon
        );
    }

    private final ArrowFactory arrowEntityFactory;

    public KHExtendedArrowItem(Properties properties, ArrowFactory arrowEntityFactory) {
        super(properties);
        this.arrowEntityFactory = arrowEntityFactory;
    }

    @Override
    public AbstractArrow createArrow(
            Level level,
            ItemStack ammo,
            LivingEntity shooter,
            @Nullable ItemStack weapon
    ) {
        return arrowEntityFactory.create(shooter, level, ammo, weapon);
    }
}