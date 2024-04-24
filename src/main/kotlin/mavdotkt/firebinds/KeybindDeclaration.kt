package mavdotkt.firebinds

import net.minecraft.client.MinecraftClient

interface KeybindDeclaration {
    fun execute(client: MinecraftClient)
}