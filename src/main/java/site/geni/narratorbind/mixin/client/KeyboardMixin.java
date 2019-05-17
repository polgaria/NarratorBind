package site.geni.narratorbind.mixin.client;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/Screen;hasControlDown()Z",
			ordinal = 1
		),
		method = "onKey"
	)
	private boolean disableBind(boolean original) {
		return false;
	}
}
