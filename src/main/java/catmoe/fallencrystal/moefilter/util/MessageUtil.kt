package catmoe.fallencrystal.moefilter.util

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer

object MessageUtil {
    fun colorize(text: String): String { return ChatColor.translateAlternateColorCodes('&', text) }

    fun sendMessage(sender: CommandSender, message: String) { if (sender !is ProxiedPlayer) { logInfo(message); return } else { sendMessage(sender, message)} }

    fun sendMessage(player: ProxiedPlayer, message: String) { player.sendMessage(ChatMessageType.CHAT, TextComponent(colorize(message))) }

    fun logInfo(text: String) { ProxyServer.getInstance().logger.info(colorize((text))) }
}