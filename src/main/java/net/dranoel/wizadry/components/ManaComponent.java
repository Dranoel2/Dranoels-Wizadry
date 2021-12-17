package net.dranoel.wizadry.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.dranoel.wizadry.entrypoints.DranoelsWizadryComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class ManaComponent implements ComponentV3, AutoSyncedComponent {

    private final PlayerEntity provider;

    public ManaComponent(PlayerEntity provider) {
        this.provider = provider;
    }

    private int value = 0;

    public int getMana() {
        return this.value;
    }

    public void setMana(int value) {
        this.value = Math.min(value, 100);
        DranoelsWizadryComponents.MANA.sync(provider);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.value = tag.getInt("value");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt("value", this.value);
    }
}