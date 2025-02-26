package terramine.mixin.item.accessories.compass.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terramine.common.init.ModItems;
import terramine.common.trinkets.TrinketsHelper;

@Mixin(Gui.class)
public abstract class GuiMixin {

	@Shadow private int screenHeight;
	@Shadow private int screenWidth;

	@Shadow protected abstract Player getCameraPlayer();
	@Shadow public abstract Font getFont();

	@Inject(method = "renderPlayerHealth", require = 0, at = @At(value = "TAIL"))
	private void renderGuiCompass(PoseStack matrices, CallbackInfo ci) {
		Player player = this.getCameraPlayer();

		if (player == null || !getEquippedTrinkets(player)) {
			return;
		}

		int left = this.screenWidth - 22 - this.getFont().width(getHorizontal());
		int top = this.screenHeight - 33;

		matrices.pushPose();
		Gui.drawString(matrices, Minecraft.getInstance().font, getHorizontal(), left, top, 0xffffff);
	}

	@Unique
	private boolean getEquippedTrinkets(Player player) {
		return TrinketsHelper.isInInventory(ModItems.COMPASS, player) || TrinketsHelper.isInInventory(ModItems.GPS, player) || TrinketsHelper.isInInventory(ModItems.PDA, player)
				|| TrinketsHelper.isInInventory(ModItems.CELL_PHONE, player);
	}

	@Unique
	private String getHorizontal() {
		Minecraft mc = Minecraft.getInstance();
		StringBuilder sb = new StringBuilder();
		if (mc.player != null) {
			int x = (int) mc.player.position().x();
			int z = (int) mc.player.position().z();
			sb.append("X: ");
			sb.append(x);
			sb.append("  ");
			sb.append("Z: ");
			sb.append(z);
		}
		return sb.toString();
	}
}
