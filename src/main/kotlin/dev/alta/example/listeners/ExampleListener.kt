package dev.alta.example.listeners

import dev.alta.commons.annotations.Listeners
import dev.alta.commons.utils.Chat
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

@Listeners
class ExampleListener : Listener {
    
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        Chat.sendMessage(player, """
            ${Chat.PRI}Welcome to the example server!
            ${Chat.SEC}Type /example to see available commands.
        """.trimIndent())
    }
}
