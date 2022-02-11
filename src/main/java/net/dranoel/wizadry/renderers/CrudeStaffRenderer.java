package net.dranoel.wizadry.renderers;

import net.dranoel.wizadry.items.CrudeStaffItem;
import net.dranoel.wizadry.models.CrudeStaffModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CrudeStaffRenderer extends GeoItemRenderer<CrudeStaffItem> {

    public CrudeStaffRenderer() {
        super(new CrudeStaffModel());
    }

    @Override
    public Integer getUniqueID(CrudeStaffItem animatable) {
        return 0;
    }

}