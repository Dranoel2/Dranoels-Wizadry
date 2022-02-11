package net.dranoel.wizadry.items;

import net.minecraft.item.Item;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class CrudeStaffItem extends StaffItem implements IAnimatable {

    public CrudeStaffItem() {
        super(1);
    }

    private final AnimationFactory animationFactory = new AnimationFactory(this);

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crude_staff.crystal_bounce", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 20f, this::predicate));
    }

    @Override
    public AnimationFactory getFactory(){
        return this.animationFactory;
    }
}