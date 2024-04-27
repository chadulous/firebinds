package mavdotkt.firebinds.keybinds

import mavdotkt.firebinds.KeybindDeclaration
import mavdotkt.firebinds.KeybindDeclarationManger
import mavdotkt.firebinds.Registry

object Misc: KeybindDeclarationManger {
    override fun attach(registry: Registry) {
        registry.register(KeybindDeclaration("spawn") {
            it.player?.networkHandler?.sendChatCommand("spawn")
        })
        registry.register(KeybindDeclaration("vote") {
            it.player?.networkHandler?.sendChatCommand("vote")
        })
    }
}