package dev.alta.example

import dev.alta.commons.Commons
import dev.alta.commons.config.ConfigurationManager
import dev.alta.commons.database.MongoManager
import dev.alta.commons.utils.Chat
import dev.alta.commons.redis.RedisManager
import org.bukkit.plugin.java.JavaPlugin

class ExamplePlugin : JavaPlugin() {
    companion object {
        lateinit var instance: ExamplePlugin
            private set
    }

    override fun onEnable() {
        instance = this
        
        // Save default config
        saveDefaultConfig()
        
        // Initialize configuration
        ConfigurationManager.initialize(this, "config.yml")
        
        // Initialize MongoDB
        val mongoUrl = config.getString("mongodb.url") ?: "mongodb://localhost:27017"
        val mongoDatabase = config.getString("mongodb.database") ?: "example"
        MongoManager.initialize(this, mongoUrl, mongoDatabase)
        
        // Initialize Redis
        val redisHost = config.getString("redis.host") ?: "localhost"
        val redisPort = config.getInt("redis.port", 6379)
        val redisPassword = config.getString("redis.password")
        RedisManager.initialize(redisHost, redisPort, redisPassword)
        
        // Register commands and listeners
        registerCommands()
        registerListeners()
        
        Chat.sendToConsole(Chat.component("<green>ExamplePlugin has been enabled!"))
    }

    override fun onDisable() {
        RedisManager.close()
        MongoManager.close()
        Chat.sendToConsole(Chat.component("<red>ExamplePlugin has been disabled!"))
    }

    private fun registerCommands() {
        // Commands will be registered automatically via @AutoRegister
    }

    private fun registerListeners() {
        // Listeners will be registered automatically via @Listeners
    }
}

