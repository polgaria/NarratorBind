package site.geni.narratorbind.mixin.client;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/screen/Screen;hasControlDown()Z",
			ordinal = 1
		),
		method = "onKey"
	)
	private boolean disableBind(boolean original) {
		return false;
	}

	// fabric-loom is not generating the refmap for onKey without this, :shrug:
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "onKey"
	)
	private void workaround(long long_1, int int_1, int int_2, int int_3, int int_4, CallbackInfo ci) {
		// NO-OP
	}
}
