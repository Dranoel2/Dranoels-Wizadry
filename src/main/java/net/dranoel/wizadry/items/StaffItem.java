package net.dranoel.wizadry.items;

import net.dranoel.wizadry.components.ManaComponent;
import net.dranoel.wizadry.entrypoints.DranoelsWizadry;
import net.dranoel.wizadry.entrypoints.DranoelsWizadryComponents;
import net.dranoel.wizadry.spells.Spell;
import net.dranoel.wizadry.util.Registries;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class StaffItem extends Item {

    private int level;

    public int getLevel() {
        return level;
    }

    public StaffItem(int level) {
        super(new FabricItemSettings().group(DranoelsWizadry.GROUP).maxCount(1));
        this.level = level;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient) {
            ItemStack item = user.getStackInHand(Hand.MAIN_HAND);
            Identifier spellIdentifier = DranoelsWizadryComponents.SELECTED_SPELL.get(user).getSpell();
            Spell spell = Registries.SPELL.get(spellIdentifier);
            assert spell != null;
            int manaCost = spell.getManaUsage();
            ManaComponent component = DranoelsWizadryComponents.MANA.get(user);
            int mana = component.getMana();
            if (item.getItem() instanceof StaffItem && manaCost <= mana) {
                component.setMana(component.getMana() - manaCost);
                spell.cast(user);
                return TypedActionResult.success(item);
            } else return TypedActionResult.fail(user.getStackInHand(hand));
        } else return TypedActionResult.fail(user.getStackInHand(hand));
    }
}