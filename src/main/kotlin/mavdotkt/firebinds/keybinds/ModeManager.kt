package mavdotkt.firebinds.keybinds

import mavdotkt.firebinds.KeybindDeclaration
import mavdotkt.firebinds.KeybindDeclarationManger
import mavdotkt.firebinds.Registry

object ModeManager: KeybindDeclarationManger {
    override fun attach(registry: Registry) {
        registry.register(KeybindDeclaration("dev") {
            it.player?.networkHandler?.sendChatCommand("dev")
        })
        registry.register(KeybindDeclaration("play") {
            it.player?.networkHandler?.sendChatCommand("play")
        })
        registry.register(KeybindDeclaration("build") {
            it.player?.networkHandler?.sendChatCommand("build")
        })
    }
}