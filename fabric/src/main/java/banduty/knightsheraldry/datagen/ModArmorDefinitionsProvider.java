package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.DefinitionsProvider;
import net.minecraft.data.PackOutput;

public class ModArmorDefinitionsProvider extends DefinitionsProvider.Armor {
    public ModArmorDefinitionsProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void generateDefinitions(ArmorConsumer biConsumer) {
        // Gambeson Set
        biConsumer.accept(KHItems.QUILTED_COIF.get(), Builder.create()
                        .weight(0.5)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.005)
                        .build()
        );
        biConsumer.accept(KHItems.GAMBESON.get(), Builder.create()
                        .weight(5.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.025)
                        .build()
        );
        biConsumer.accept(KHItems.GAMBESON_BREECHES.get(), Builder.create()
                        .weight(2.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.01)
                        .build()
        );
        biConsumer.accept(KHItems.GAMBESON_BOOTS.get(), Builder.create()
                        .weight(1.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.002)
                        .build()
        );

        // Mail Set
        biConsumer.accept(KHItems.MAIL_COIF.get(), Builder.create()
                        .weight(2.5)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.04)
                        .build()
        );
        biConsumer.accept(KHItems.HAUBERK.get(), Builder.create()
                        .weight(10.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.07)
                        .build()
        );
        biConsumer.accept(KHItems.MAIL_BREECHES.get(), Builder.create()
                        .weight(8.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.05)
                        .build()
        );
        biConsumer.accept(KHItems.MAIL_BOOTS.get(), Builder.create()
                        .weight(3.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.02)
                        .build()
        );

        // Horse Armor
        biConsumer.accept(KHItems.HORSE_BARDING.get(), Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6)
                        .build()
        );
        biConsumer.accept(KHItems.DARK_HORSE_BARDING.get(), Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6)
                        .build()
        );
        biConsumer.accept(KHItems.GOLDEN_HORSE_BARDING.get(), Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6)
                        .build()
        );
    }
}
