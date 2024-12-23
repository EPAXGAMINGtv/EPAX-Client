package de.epax.client.Utils;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class RGBUtil {
    private static float hue = 0.0f;

    /**
     * Rendert einen Text mit einem Regenbogeneffekt.
     *
     * @param context       Der DrawContext zum Rendern.
     * @param textRenderer  Der TextRenderer von Minecraft.
     * @param text          Der Text, der gerendert werden soll.
     * @param x             Die X-Position.
     * @param y             Die Y-Position.
     * @param speed         Die Geschwindigkeit des Farbwechsels.
     */
    public static void renderTextWithRainbowEffect(DrawContext context, TextRenderer textRenderer, String text, int x, int y, float speed) {
        int offsetX = x;
        for (int i = 0; i < text.length(); i++) {
            speed =0.0008f;
            char c = text.charAt(i);
            float letterHue = (hue + (i * 0.1f)) % 1.0f;
            int rgbColor = java.awt.Color.HSBtoRGB(letterHue, 1.0f, 1.0f);
            context.drawText(textRenderer, String.valueOf(c), offsetX, y, rgbColor, false);
            offsetX += textRenderer.getWidth(String.valueOf(c));
        }

        // update the colors
        hue += speed;
        if (hue > 5.0f) {
            hue -= 5.0f;
        }
    }
}
