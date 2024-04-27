package mavdotkt.firebinds

import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import org.slf4j.LoggerFactory


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
		println("Initializing FireBinds")
		Registry.init()
//		val spawnBinding: KeyBinding = KeyBindingHelper.registerKeyBinding(
//			KeyBinding("key.mcdiamondfire.spawn", InputUtil.Type.KEYSYM, GLFW.GLFW_RELEASE_BEHAVIOR_NONE, "category.mcdiamondfire.keybinds")
//		)
//		val voteBinding: KeyBinding = KeyBindingHelper.registerKeyBinding(
//			KeyBinding("key.mcdiamondfire.vote", InputUtil.Type.KEYSYM, GLFW.GLFW_RELEASE_BEHAVIOR_NONE+1, "category.mcdiamondfire.keybinds")
//		)

		ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client: MinecraftClient ->
//			while (spawnBinding.wasPressed()) {
//				if(isOnDiamondFire(client)) {
//					client.player?.networkHandler?.sendChatCommand("spawn")
//				}
//			}
//			while (voteBinding.wasPressed()) {
//				if(isOnDiamondFire(client)) {
//					client.player?.networkHandler?.sendChatCommand("vote")
//				}
//			}
//			check(client, this.getConfig())
			Registry.executePressed(client, this.getConfig())
		})
	}

	fun getConfig(): ModConfig {
		return AutoConfig.getConfigHolder(ModConfig::class.java).config
	}
}