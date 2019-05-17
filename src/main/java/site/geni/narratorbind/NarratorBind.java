package site.geni.narratorbind;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.gui.menu.AccessibilityScreen;
import net.minecraft.client.gui.menu.options.ChatOptionsScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.options.Option;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings("unused")
public class NarratorBind implements ClientModInitializer {
	private static int KEY = 0;
	private static final CtrlKeyBinding NARRATOR_BINDING = CtrlKeyBinding.Builder.create(new Identifier("narratorbind", "narrator"), GLFW.GLFW_KEY_B).build();

	@Override
	public void onInitializeClient() {
		KeyBindingRegistry.INSTANCE.addCategory("NarratorBind");
		KeyBindingRegistry.INSTANCE.register(NARRATOR_BINDING);

		ClientTickCallback.EVENT.register(client -> {
			if (NARRATOR_BINDING.isPressed() && (client.currentScreen == null || (client.currentScreen.getFocused() instanceof TextFieldWidget) || !((TextFieldWidget) client.currentScreen.getFocused()).method_20315())) {
				Option.NARRATOR.cycle(client.options, 1);
				if (client.currentScreen instanceof ChatOptionsScreen) {
					((ChatOptionsScreen) client.currentScreen).method_2096();
				}

				if (client.currentScreen instanceof AccessibilityScreen) {
					((AccessibilityScreen) client.currentScreen).method_19366();
				}
			}
		});
	}
}