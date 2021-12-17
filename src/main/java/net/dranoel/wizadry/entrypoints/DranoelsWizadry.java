package net.dranoel.wizadry.entrypoints;

import net.dranoel.wizadry.items.CrudeStaffItem;
import net.dranoel.wizadry.spells.AbsorbMagicSpell;
import net.dranoel.wizadry.spells.ReleaseMagicSpell;
import net.dranoel.wizadry.spells.Spell;
import net.dranoel.wizadry.util.Registries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DranoelsWizadry implements ModInitializer {

    private Item registerItem(String id, Item item) {
        return Registry.register(Registry.ITEM, identifier(id), item);
    }
    private Block registerBlock(String id, Block block) {
        return Registry.register(Registry.BLOCK, identifier(id), block);
    }
    private Item registerBlockItem(String id, Block block) {
        return Registry.register(Registry.ITEM, identifier(id), new BlockItem(block, new FabricItemSettings().group(GROUP)));
    }
    private Spell registerSpell(String id, Spell spell) {
        return Registry.register(Registries.SPELL, identifier(id), spell);
    }    public static final String MOD_ID = "dranoels_wizadry";

    public static Spell ABSORB_MAGIC_SPELL;
    public static Spell RELEASE_MAGIC_SPELL;

    public static Item CRUDE_STAFF_ITEM;

    public static ItemGroup GROUP = FabricItemGroupBuilder.build(DranoelsWizadry.identifier("item_group"), () -> new ItemStack(CRUDE_STAFF_ITEM));

    public static Identifier identifier(String path) {
        return new Identifier(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        ABSORB_MAGIC_SPELL = registerSpell("absorb_magic", new AbsorbMagicSpell());
        RELEASE_MAGIC_SPELL = registerSpell("release_magic", new ReleaseMagicSpell());

        CRUDE_STAFF_ITEM = registerItem("crude_staff", new CrudeStaffItem());

        ServerPlayNetworking.registerGlobalReceiver(identifier("next_spell"), (server, player, handler, buf, responseSender) -> {
            DranoelsWizadryComponents.SELECTED_SPELL.get(player).nextSpell();
        });
    }
}