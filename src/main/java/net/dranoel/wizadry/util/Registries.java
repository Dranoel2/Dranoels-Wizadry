package net.dranoel.wizadry.util;

import net.dranoel.wizadry.entrypoints.DranoelsWizadry;
import net.dranoel.wizadry.spells.Spell;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.registry.SimpleRegistry;

public class Registries {
    public static SimpleRegistry<Spell> SPELL = FabricRegistryBuilder.createSimple(Spell.class, DranoelsWizadry.identifier("spell_registry")).buildAndRegister();
}