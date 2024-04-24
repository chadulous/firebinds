package mavdotkt.firebinds.keybinds

import mavdotkt.firebinds.KeybindDeclaration
import net.minecraft.client.MinecraftClient

object Dev : KeybindDeclaration {
    override fun execute(client: MinecraftClient) {
        client.player?.networkHandler?.sendChatCommand("dev")
    }
}