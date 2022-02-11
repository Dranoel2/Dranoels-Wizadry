package net.dranoel.wizadry.models;

import net.dranoel.wizadry.entrypoints.DranoelsWizadry;
import net.dranoel.wizadry.items.CrudeStaffItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrudeStaffModel extends AnimatedGeoModel<CrudeStaffItem> {
    @Override
    public Identifier getModelLocation(CrudeStaffItem object) {
        return DranoelsWizadry.identifier("geo/crude_staff.geo.json");
    }

    @Override
    public Identifier getTextureLocation(CrudeStaffItem object) {
        return DranoelsWizadry.identifier("textures/item/amethyst_oak.png");
    }

    @Override
    public Identifier getAnimationFileLocation(CrudeStaffItem animatable) {
        return DranoelsWizadry.identifier("animations/crude_staff.animation.json");
    }
}