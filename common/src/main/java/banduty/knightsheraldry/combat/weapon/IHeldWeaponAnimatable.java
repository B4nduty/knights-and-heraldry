package banduty.knightsheraldry.combat.weapon;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IHeldWeaponAnimatable {
    void trackHolder(ItemStack stack, ServerLevel serverLevel, LivingEntity holder);
}
