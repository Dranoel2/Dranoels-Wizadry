package net.dranoel.wizadry.spells;

import net.minecraft.entity.player.PlayerEntity;

public abstract class Spell {

    private final int level;
    private final int manaUsage;

    public int getManaUsage() {
        return manaUsage;
    }

    public int getLevel() {
        return level;
    }

    public Spell(int level, int manaUsage) {
        this.level = level;
        this.manaUsage = manaUsage;
    }

    public abstract void cast(PlayerEntity player);
}