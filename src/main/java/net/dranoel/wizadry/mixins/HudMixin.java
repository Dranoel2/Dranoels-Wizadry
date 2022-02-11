package net.dranoel.wizadry.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dranoel.wizadry.entrypoints.DranoelsWizadry;
import net.dranoel.wizadry.entrypoints.DranoelsWizadryComponents;
import net.dranoel.wizadry.items.StaffItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HudMixin extends DrawableHelper {
    @Shadow @Final private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "renderHotbar(FLnet/minecraft/client/util/math/MatrixStack;)V")
    private void renderManaBar(float tickDelta, MatrixStack matrices, CallbackInfo ci) {
        PlayerEntity player = this.client.player;
        if(player != null && player.getMainHandStack().getItem() instanceof StaffItem) {
            int mana = DranoelsWizadryComponents.MANA.get(player).getMana();
            int fullness = (int) Math.floor((float) mana / 100f * 128f);
            RenderSystem.setShaderTexture(0, DranoelsWizadry.identifier("textures/gui/mana_bar.png"));
            drawTexture(matrices, 1, 1, 0, 0, 128, 16, 128, 48);
            drawTexture(matrices, 1, 1, 0, 16, fullness, 16, 128, 48);
            drawTexture(matrices, 1, 17, 0, 32, 128, 16, 128, 48);
            Identifier spellID = DranoelsWizadryComponents.SELECTED_SPELL.get(player).getSpell();
            Text text = new TranslatableText(String.format("spell.%s.%s", spellID.getNamespace(), spellID.getPath()));
            this.client.textRenderer.draw(matrices, text, 17, 20, 0x0077ff);
            RenderSystem.enableBlend();
        }
    }
}
