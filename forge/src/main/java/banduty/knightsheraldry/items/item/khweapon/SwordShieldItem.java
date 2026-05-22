package banduty.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.util.data.itemdata.SCTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class SwordShieldItem extends SwordItem {
    public SwordShieldItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (stack.is(SCTags.WEAPONS_SHIELD.getTag())) return ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
        return super.canPerformAction(stack, toolAction);
    }
}
