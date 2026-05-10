package banduty.knightsheraldry.items.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class KHExtendedArrowItem extends ArrowItem {
    private final BiFunction<LivingEntity, Level, AbstractArrow> arrowEntityFactory;

    public KHExtendedArrowItem(Properties properties,
                               BiFunction<LivingEntity, Level, AbstractArrow> arrowEntityFactory) {
        super(properties);
        this.arrowEntityFactory = arrowEntityFactory;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return arrowEntityFactory.apply(shooter, level);
    }
}