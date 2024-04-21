package mavdotkt.firebinds

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config

@Config(name = "firebinds")
class ModConfig: ConfigData {
    var ips: Array<String> = emptyArray()
}