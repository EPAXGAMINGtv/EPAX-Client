package de.epax.mixin;

import de.epax.client.Utils.RGBUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
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
public class CoordinatesMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    private final float speed = 0.001f;

    @Inject(method = "render", at = @At("HEAD"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;

        int playerX = (int) client.player.getX();
        int playerY = (int) client.player.getY();
        int playerZ = (int) client.player.getZ();

        String textX = "X: " + playerX;
        String textY = "Y: " + playerY;
        String textZ = "Z: " + playerZ;

        int baseX = 10;
        int baseY = 15;

        RGBUtil.renderTextWithRainbowEffect(context, textRenderer, textX, baseX, baseY, speed);
        RGBUtil.renderTextWithRainbowEffect(context, textRenderer, textY, baseX, baseY + 12, speed);
        RGBUtil.renderTextWithRainbowEffect(context, textRenderer, textZ, baseX, baseY + 24, speed);
    }
}
