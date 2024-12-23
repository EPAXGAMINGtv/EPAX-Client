package de.epax.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class CrosshairMixin {

    @Shadow @Final
    private MinecraftClient client;

    private float hue = 0.0f;
    private final float speed = 0.001f;

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    public void renderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        int crosshairX = screenWidth / 2;
        int crosshairY = screenHeight / 2;

        // Kleinere Größe, aber schlanker (klassisches Aussehen)
        float letterHue = hue % 1.0f;
        int rgbColor = java.awt.Color.HSBtoRGB(letterHue, 1.0f, 1.0f);

        // Oben und Unten (schlanker)
        context.fill(crosshairX - 1, crosshairY - 2, crosshairX + 1, crosshairY - 1, rgbColor);  // Oben
        context.fill(crosshairX - 1, crosshairY + 1, crosshairX + 1, crosshairY + 2, rgbColor);  // Unten

        // Links und Rechts (schlanker)
        context.fill(crosshairX - 2, crosshairY - 1, crosshairX - 1, crosshairY + 1, rgbColor);  // Links
        context.fill(crosshairX + 1, crosshairY - 1, crosshairX + 2, crosshairY + 1, rgbColor);  // Rechts

        hue += speed;
        if (hue > 1.0f) hue -= 1.0f;

        ci.cancel(); // Überschreibt das Standard-Crosshair
    }
}
