package dev.alta.example.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import dev.alta.commons.annotations.AutoRegister
import dev.alta.commons.utils.Chat
import dev.alta.commons.gui.GuiBuilder
import dev.alta.commons.input.InputManager
import dev.alta.commons.gui.openGui
import org.bukkit.Material
import org.bukkit.entity.Player

@AutoRegister
@CommandAlias("example|ex")
class ExampleCommand : BaseCommand() {

    @Default
    @Description("Shows the main example command")
    fun onDefault(player: Player) {
        Chat.sendMessage(player, """
            ${Chat.PRI}Example Plugin Commands:
            ${Chat.SEC}/example gui ${Chat.PRI}- Opens example GUI
            ${Chat.SEC}/example input ${Chat.PRI}- Tests input methods
        """.trimIndent())
    }

    @Subcommand("gui")
    @Description("Opens an example GUI")
    fun onGui(player: Player) {
        player.openGui(3, "Example GUI") {
            setItem(13, GuiBuilder.createItem(Material.DIAMOND, "<aqua>Click me!")) { event ->
                event.isCancelled = true
                Chat.sendMessage(player, "${Chat.PRI}You clicked the diamond!")
            }
            fillBorder(GuiBuilder.createItem(Material.BLACK_STAINED_GLASS_PANE, " "))
        }
    }

    @Subcommand("input")
    @Description("Tests input methods")
    fun onInput(player: Player) {
        Chat.sendMessage(player, "${Chat.PRI}Please type something in chat:")
        InputManager.requestChatInput(player, "${Chat.PRI}Type your message:").thenAccept { input ->
            Chat.sendMessage(player, "${Chat.PRI}You typed: ${Chat.SEC}$input")
        }
    }
}
