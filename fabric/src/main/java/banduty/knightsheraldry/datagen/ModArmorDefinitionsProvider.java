package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.entity.ModEntities;
import banduty.knightsheraldry.items.ModItems;
import banduty.stoneycore.datagen.DefinitionsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class ModArmorDefinitionsProvider extends DefinitionsProvider.Armor {
    public ModArmorDefinitionsProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void generateDefinitions(BiConsumer<Item, DefinitionEntry> biConsumer) {
        // Gambeson Set
        biConsumer.accept(ModItems.QUILTED_COIF, Builder.create()
                        .weight(0.5)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.005, getAllArrows())
                        .build()
        );
        biConsumer.accept(ModItems.GAMBESON, Builder.create()
                        .weight(5.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.025, getAllArrows())
                        .build()
        );
        biConsumer.accept(ModItems.GAMBESON_BREECHES, Builder.create()
                        .weight(2.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.01, getAllArrows())
                        .build()
        );
        biConsumer.accept(ModItems.GAMBESON_BOOTS, Builder.create()
                        .weight(1.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.002, getAllArrows())
                        .build()
        );

        // Mail Set
        biConsumer.accept(ModItems.MAIL_COIF, Builder.create()
                        .weight(2.5)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.04, getAllArrows())
                        .build()
        );
        biConsumer.accept(ModItems.HAUBERK, Builder.create()
                        .weight(10.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.07, getAllArrows())
                        .build()
        );
        biConsumer.accept(ModItems.MAIL_BREECHES, Builder.create()
                        .weight(8.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.05, getAllArrows())
                        .build()
        );
        biConsumer.accept(ModItems.MAIL_BOOTS, Builder.create()
                        .weight(3.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.02, getAllArrows())
                        .build()
        );

        // Horse Armor
        biConsumer.accept(ModItems.HORSE_BARDING, Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6, getAllArrows())
                        .build()
        );
        biConsumer.accept(ModItems.DARK_HORSE_BARDING, Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6, getAllArrows())
                        .build()
        );
        biConsumer.accept(ModItems.GOLDEN_HORSE_BARDING, Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6, getAllArrows())
                        .build()
        );
    }

    protected EntityType<?>[] getAllArrows() {
        return new EntityType<?>[] {
                EntityType.ARROW,
                ModEntities.BODKIN_ARROW,
                ModEntities.BROADHEAD_ARROW,
                ModEntities.CLOTH_ARROW,
                ModEntities.SWALLOWTAIL_ARROW
        };
    }
}
