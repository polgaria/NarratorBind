package site.geni.narratorbind;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class CtrlKeyBinding extends FabricKeyBinding {
	private CtrlKeyBinding(Identifier id, InputUtil.Type type, int code, String category) {
		super(id, type, code, category);
	}

	@Override
	public String getLocalizedName() {
		return super.isNotBound() ? super.getLocalizedName() : "CTRL + " + super.getLocalizedName();
	}

	@Override
	public boolean isPressed() {
		return super.isPressed() && Screen.hasControlDown();
	}

	static class Builder {
		final CtrlKeyBinding binding;

		Builder(CtrlKeyBinding binding) {
			this.binding = binding;
		}

		CtrlKeyBinding build() {
			return binding;
		}

		static CtrlKeyBinding.Builder create(Identifier id, int code) {
			return new CtrlKeyBinding.Builder(new CtrlKeyBinding(id, InputUtil.Type.KEYSYM, code, "NarratorBind"));
		}
	}
}
