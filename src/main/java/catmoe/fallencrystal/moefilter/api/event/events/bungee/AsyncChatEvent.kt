package catmoe.fallencrystal.moefilter.api.event.events.bungee

import net.md_5.bungee.api.connection.ProxiedPlayer

class AsyncChatEvent(val sender: ProxiedPlayer, val isProxyCommand: Boolean, val isBackendCommand: Boolean, val isCancelled: Boolean)