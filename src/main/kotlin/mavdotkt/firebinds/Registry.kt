package mavdotkt.firebinds

// Keybinds
import mavdotkt.firebinds.keybinds.Dev
import mavdotkt.firebinds.keybinds.Spawn
import mavdotkt.firebinds.keybinds.Vote


import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

val list = arrayOf<KeybindDeclaration>(
        Vote,
        Spawn,
        Dev
)

var registry = arrayOf<Pair<KeybindDeclaration, KeyBinding>>()
fun check(client: MinecraftClient, config: ModConfig) {
    registry.forEach {
        val (d, kb) = it
        if(kb.wasPressed()) {
            if(isOnDiamondFire(client, config)) {
                d.execute(client)
            }
        }
    }
}

fun register() {
    var key = GLFW.GLFW_RELEASE_BEHAVIOR_NONE
    list.forEach {
        println(it.javaClass.name)
        registry += Pair(it, KeyBindingHelper.registerKeyBinding(
            KeyBinding("key.mcdiamondfire.${it.javaClass.name.split(".").last().lowercase()}", InputUtil.Type.KEYSYM, key, "category.mcdiamondfire.keybinds")
        ))
        key++
    }
}


fun isOnDiamondFire(client: MinecraftClient, config: ModConfig): Boolean {
    if(client.isInSingleplayer) return false
    val serverEntry = client.currentServerEntry ?: return false
    return config.ips.any { ip -> ip.endsWith(serverEntry.address) }
}