package net.dranoel.wizadry.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.dranoel.wizadry.spells.SpellUtil;
import net.dranoel.wizadry.util.Registries;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ScrollSpellComponent extends ItemComponent implements ComponentV3 {
    public ScrollSpellComponent(ItemStack stack) {
        super(stack);
    }

    public Identifier getValue() {
        Identifier defaultSpell = Registries.SPELL.getId(SpellUtil.getRandomSpell());
        if(!this.hasTag("spell", NbtType.STRING)) {
            this.putString("spell", defaultSpell.toString());
            return defaultSpell;
        }
        return new Identifier(this.getString("spell"));
    }
}
