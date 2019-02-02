package me.kyllian.system32.handlers;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageHandler {

    private System32Plugin plugin;

    private File messageFile;
    private FileConfiguration configuration;

    public MessageHandler(System32Plugin plugin) {
        this.plugin = plugin;

        messageFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messageFile.exists()) plugin.saveResource("messages.yml", false);
        configuration = YamlConfiguration.loadConfiguration(messageFile);
    }

    public void saveMessages() {
        try {
            configuration.save(messageFile);
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: Messages file could not be saved!");
        }
    }

    public void reloadMessages() {
        configuration = YamlConfiguration.loadConfiguration(messageFile);
    }

    public String translateColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
