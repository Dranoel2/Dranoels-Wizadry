package net.dranoel.wizadry.entrypoints;

import net.dranoel.wizadry.renderers.CrudeStaffRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DranoelsWizadryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        GeoItemRenderer.registerItemRenderer(DranoelsWizadry.CRUDE_STAFF_ITEM, new CrudeStaffRenderer());

        KeyBinding NEXT_SPELL = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.dranoels_wizadry.next_spell",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.dranoels_wizadry.keybind_category"
        ));

        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            while(NEXT_SPELL.wasPressed()) {
                ClientPlayNetworking.send(DranoelsWizadry.identifier("next_spell"), PacketByteBufs.empty());
            }
        });
    }
}