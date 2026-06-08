package banduty.knightsheraldry.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum ModToolMaterials implements Tier {
    WEAPONS(BlockTags.INCORRECT_FOR_IRON_TOOL, 100, 0.0F, -1.0F, 14, () ->
            Ingredient.of(Items.IRON_INGOT));

    private final TagKey<Block> incorrectBlocksForDrops;
    private int itemDurability;
    private final float speed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(
            TagKey<Block> incorrectBlocksForDrops,
            int itemDurability,
            float miningSpeed,
            float attackDamage,
            int enchantability,
            Supplier<Ingredient> repairIngredient
    ) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.itemDurability = itemDurability;
        this.speed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.itemDurability;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    public Tier setUses(int itemDurability) {
        this.itemDurability = itemDurability;
        return new Tier() {
            @Override
            public int getUses() {
                return itemDurability;
            }

            @Override
            public float getSpeed() {
                return speed;
            }

            @Override
            public float getAttackDamageBonus() {
                return attackDamage;
            }

            @Override
            public TagKey<Block> getIncorrectBlocksForDrops() {
                return incorrectBlocksForDrops;
            }

            @Override
            public int getEnchantmentValue() {
                return enchantability;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return repairIngredient.get();
            }
        };
    }
}