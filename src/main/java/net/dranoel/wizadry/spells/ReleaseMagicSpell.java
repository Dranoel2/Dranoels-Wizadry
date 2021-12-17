package net.dranoel.wizadry.spells;

import net.dranoel.wizadry.components.ManaComponent;
import net.dranoel.wizadry.entrypoints.DranoelsWizadryComponents;
import net.minecraft.entity.player.PlayerEntity;

public class ReleaseMagicSpell extends Spell {

    public ReleaseMagicSpell() {
        super(1, 10);
    }

    @Override
    public void cast(PlayerEntity player) {
        ManaComponent component = DranoelsWizadryComponents.MANA.get(player);
        int mana = component.getMana();
        component.setMana(Math.max(mana - 10, 0));
    }
}