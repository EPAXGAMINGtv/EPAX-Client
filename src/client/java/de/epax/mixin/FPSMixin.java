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
public class FPSMixin {

	@Shadow @Final
	private MinecraftClient client;
	private final float speed = 0.001f;

	@Inject(method = "render", at = @At("HEAD"))
	public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
		String text = "FPS: " + MinecraftClient.getInstance().getCurrentFps();
		TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
		RGBUtil.renderTextWithRainbowEffect(context, textRenderer, text, 4, 4, speed);
	}
}
