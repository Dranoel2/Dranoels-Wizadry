package net.dranoel.wizadry.entrypoints;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.dranoel.wizadry.components.ManaComponent;
import net.dranoel.wizadry.components.SelectedSpellComponent;
import net.dranoel.wizadry.components.UnlockedSpellsComponent;

public class DranoelsWizadryComponents implements EntityComponentInitializer {

    public static ComponentKey<ManaComponent> MANA = ComponentRegistryV3.INSTANCE.getOrCreate(DranoelsWizadry.identifier("mana"), ManaComponent.class);
    public static ComponentKey<UnlockedSpellsComponent> UNLOCKED_SPELLS = ComponentRegistryV3.INSTANCE.getOrCreate(DranoelsWizadry.identifier("unlocked_spells"), UnlockedSpellsComponent.class);
    public static ComponentKey<SelectedSpellComponent> SELECTED_SPELL = ComponentRegistryV3.INSTANCE.getOrCreate(DranoelsWizadry.identifier("selected_spell"), SelectedSpellComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(MANA, ManaComponent::new, RespawnCopyStrategy.INVENTORY);
        registry.registerForPlayers(UNLOCKED_SPELLS, UnlockedSpellsComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(SELECTED_SPELL, SelectedSpellComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}