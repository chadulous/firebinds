package mavdotkt.firebinds.keybinds

import mavdotkt.firebinds.KeybindDeclaration
import net.minecraft.client.MinecraftClient

object Vote : KeybindDeclaration {
    override fun execute(client: MinecraftClient) {
        client.player?.networkHandler?.sendChatCommand("vote")
    }
}