package com.knightsheraldry.items.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.MailItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MailModel extends GeoModel<MailItem> {

    @Override
    public Identifier getModelResource(MailItem animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "geo/under_armour.geo.json");
    }

    @Override
    public Identifier getTextureResource(MailItem animatable) {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/armor/mail.png");
    }

    @Override
    public Identifier getAnimationResource(MailItem animatable) {
        return null;
    }
}
