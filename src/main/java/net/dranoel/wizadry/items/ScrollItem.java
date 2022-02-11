package net.dranoel.wizadry.items;

import net.dranoel.wizadry.components.UnlockedSpellsComponent;
import net.dranoel.wizadry.entrypoints.DranoelsWizadryComponents;
import net.dranoel.wizadry.spells.SpellUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScrollItem extends Item {
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient()) {
        Identifier spell = DranoelsWizadryComponents.SCROLL_SPELL.get(stack).getValue();
        UnlockedSpellsComponent component = DranoelsWizadryComponents.UNLOCKED_SPELLS.get(user);
        if(component.getAllSpells().contains(spell)) {
            user.sendMessage(new TranslatableText("message.dranoels_wizadry.spell_already_unlocked"), false);
            return TypedActionResult.fail(stack);
        } else {
            component.addSpell(spell);
            user.sendMessage(new TranslatableText("message.dranoels_wizadry.new_spell_unlocked", SpellUtil.getTranslatable(spell)), false);
            return TypedActionResult.consume(stack);
        }
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Identifier spell = DranoelsWizadryComponents.SCROLL_SPELL.get(stack).getValue();
        String translatable = SpellUtil.getTranslatable(spell);
        Text text = new TranslatableText(translatable);
        tooltip.add( new TranslatableText("tooltip.dranoels_wizadry.scroll_spell", text));
    }

    public ScrollItem() {
        super(new FabricItemSettings());
    }

}
