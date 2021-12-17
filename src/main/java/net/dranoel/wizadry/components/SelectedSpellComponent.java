package net.dranoel.wizadry.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.dranoel.wizadry.entrypoints.DranoelsWizadry;
import net.dranoel.wizadry.entrypoints.DranoelsWizadryComponents;
import net.dranoel.wizadry.items.StaffItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.List;

public class SelectedSpellComponent implements ComponentV3, AutoSyncedComponent {

    private final PlayerEntity provider;

    public SelectedSpellComponent(PlayerEntity provider) {
        this.provider = provider;
    }

    private Identifier value = DranoelsWizadry.identifier("absorb_magic");

    public Identifier nextSpell() {
        Item item = provider.getMainHandStack().getItem();
        if(item instanceof StaffItem) {
            int level = ((StaffItem) item).getLevel();
            List<Identifier> spellList = DranoelsWizadryComponents.UNLOCKED_SPELLS.get(provider).getUsableSpells(level);
            int index = spellList.indexOf(this.value) + 1;
            if(index == spellList.size()) {
                index = 0;
            }
            Identifier next = spellList.get(index);
            this.value = next;
            DranoelsWizadryComponents.SELECTED_SPELL.sync(provider);
            return next;
        }
        DranoelsWizadryComponents.SELECTED_SPELL.sync(provider);
        return DranoelsWizadry.identifier("absorb_magic");
    }

    public void setSpell(Identifier value) {
        this.value = value;
        DranoelsWizadryComponents.SELECTED_SPELL.sync(provider);
    }

    public Identifier getSpell() {
        return this.value;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.value = new Identifier(tag.getString("value"));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("value", this.value.toString());
    }
}