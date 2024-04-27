package mavdotkt.firebinds
import mavdotkt.firebinds.keybinds.Misc
import mavdotkt.firebinds.keybinds.ModeManager
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

typealias Executor = (client: MinecraftClient) -> Unit

class KeybindDeclaration(val localization: String, val execute: Executor)
interface KeybindDeclarationManger {
    fun attach(registry: Registry)
}

object Registry {
    private var stack = emptyArray<Pair<KeybindDeclaration, KeyBinding>>()
    private var key = GLFW.GLFW_RELEASE_BEHAVIOR_NONE
    fun register(keybindDeclaration: KeybindDeclaration) {
        stack += Pair(keybindDeclaration, KeyBindingHelper.registerKeyBinding(
            KeyBinding("key.mcdiamondfire.${keybindDeclaration.localization}", InputUtil.Type.KEYSYM, key, "category.mcdiamondfire.keybinds")
        ))
        key++
    }

    fun init() {
        arrayOf(ModeManager, Misc).forEach {
            it.attach(this)
        }
    }

    fun executePressed(client: MinecraftClient, config: ModConfig) {
        stack.forEach {
            val (d, kb) = it
            while(kb.wasPressed()) {
                if(isOnDiamondFire(client, config)) {
                    d.execute(client)
                }
            }
        }
    }
}

private fun isOnDiamondFire(client: MinecraftClient, config: ModConfig): Boolean {
    if(client.isInSingleplayer) return false
    val serverEntry = client.currentServerEntry ?: return false
    return config.ips.any { ip -> ip.endsWith(serverEntry.address) }
}