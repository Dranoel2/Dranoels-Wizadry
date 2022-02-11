package net.dranoel.wizadry.spells;

import net.dranoel.wizadry.components.ManaComponent;
import net.dranoel.wizadry.entrypoints.DranoelsWizadryComponents;
import net.minecraft.entity.player.PlayerEntity;

public class AbsorbMagicSpell extends Spell {

    public AbsorbMagicSpell() {
        super(1, 0);
    }

    @Override
    public void cast(PlayerEntity player) {
        ManaComponent component = DranoelsWizadryComponents.MANA.get(player);
        int mana = component.getMana();
        component.setMana(mana + 10);
    }
}