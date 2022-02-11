package net.dranoel.wizadry.spells;

import net.dranoel.wizadry.util.Registries;
import net.minecraft.util.Identifier;

import java.util.Random;

public class SpellUtil {
    public static Spell getRandomSpell() {
        return Registries.SPELL.getRandom(new Random());
    }
    public static String getTranslatable(Identifier id) {
        return String.format("spell.%s.%s", id.getNamespace(), id.getPath());
    }
}
