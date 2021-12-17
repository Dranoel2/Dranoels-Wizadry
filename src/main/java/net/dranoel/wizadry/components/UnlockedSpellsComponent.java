package net.dranoel.wizadry.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.dranoel.wizadry.entrypoints.DranoelsWizadry;
import net.dranoel.wizadry.entrypoints.DranoelsWizadryComponents;
import net.dranoel.wizadry.util.Registries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UnlockedSpellsComponent implements ComponentV3, AutoSyncedComponent {

    private final PlayerEntity provider;

    public UnlockedSpellsComponent(PlayerEntity provider) {
        this.provider = provider;
        value.add(DranoelsWizadry.identifier("absorb_magic"));
        value.add(DranoelsWizadry.identifier("release_magic"));
    }

    private List<Identifier> value = new LinkedList<>();

    public List<Identifier> getUsableSpells(int level) {
        return this.value.stream().filter(identifier -> { return (Registries.SPELL.get(identifier).getLevel()) <= level; } ).toList();
    }

    public List<Identifier> getAllSpells() {
        return this.value;
    }

    public void setAllSpells(List<Identifier> value) {
        this.value = value;
        DranoelsWizadryComponents.UNLOCKED_SPELLS.sync(provider);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        String string = tag.getString("value");
        this.value = Arrays.stream(string.split(",")).map(Identifier::new).toList();
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        StringBuilder string = new StringBuilder();
        for (Identifier current : this.value) {
            string.append(current.toString()).append(",");
        }
        tag.putString("value", string.toString());
    }

}