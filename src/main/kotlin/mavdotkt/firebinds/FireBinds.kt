package mavdotkt.firebinds

import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW
import org.slf4j.LoggerFactory
import java.util.*


object FireBinds : ModInitializer {
    private val logger = LoggerFactory.getLogger("firebinds")

	override fun onInitialize() {
		AutoConfig.register(
			ModConfig::class.java
		) { definition: Config?, configClass: Class<ModConfig?>? ->
			Toml4jConfigSerializer(
				definition,
				configClass
			)
		}
		val keyBinding: KeyBinding = KeyBindingHelper.registerKeyBinding(
			KeyBinding("key.mcdiamondfire.spawn", InputUtil.Type.KEYSYM, GLFW.GLFW_RELEASE_BEHAVIOR_NONE, "category.mcdiamondfire.keybinds")
		)

		ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client: MinecraftClient ->
			while (keyBinding.wasPressed()) {
				logger.info(Objects.requireNonNull(client.currentServerEntry)?.address)
				logger.info(this.getConfig().ips.contains(Objects.requireNonNull(client.currentServerEntry)?.address).toString())
				client.player?.networkHandler?.sendChatCommand("spawn")
			}
		})
	}

	fun getConfig(): ModConfig {
		val config = AutoConfig.getConfigHolder(ModConfig::class.java).config
		return config;
	}
}