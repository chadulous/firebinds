package mavdotkt.firebinds

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry

@Config(name = "firebinds")
class ModConfig: ConfigData {
    @ConfigEntry.Gui.Tooltip
    var ips: Array<String> = arrayOf("mcdiamondfire.com")
}