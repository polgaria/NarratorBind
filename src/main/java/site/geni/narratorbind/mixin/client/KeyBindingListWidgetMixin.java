package site.geni.narratorbind.mixin.client;

import net.minecraft.client.gui.widget.KeyBindingListWidget;
import net.minecraft.client.options.KeyBinding;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import site.geni.narratorbind.CtrlKeyBinding;


@Mixin(KeyBindingListWidget.KeyBindingEntry.class)
public abstract class KeyBindingListWidgetMixin {
	private static KeyBinding forBinding = null;

	@Shadow
	@Final
	private KeyBinding binding;

	@Inject(
		at = @At(
			value = "JUMP",
			opcode = Opcodes.GOTO,
			ordinal = 2,
			shift = At.Shift.BEFORE
		),
		method = "render",
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private void checkKeyBindingProperly(int int_1, int int_2, int int_3, int int_4, int int_5, int int_6, int int_7, boolean boolean_1, float float_1, CallbackInfo ci, boolean a, boolean b, KeyBinding[] c, int d, int e, KeyBinding f) {
		forBinding = f;
	}

	@ModifyVariable(
		at = @At(
			value = "JUMP",
			opcode = Opcodes.GOTO,
			ordinal = 2
		),
		method = "render",
		index = 11
	)
	private boolean checkKeyBindingProperly(boolean original) {
		if (forBinding != null &&
			this.binding instanceof CtrlKeyBinding && !(forBinding instanceof CtrlKeyBinding) ||
			!(this.binding instanceof CtrlKeyBinding) && forBinding instanceof CtrlKeyBinding
		) {
			return false;
		}

		return original;
	}
}
