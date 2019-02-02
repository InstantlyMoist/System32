package me.kyllian.system32.handlers;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigurationHandler {

    private System32Plugin plugin;

    private File configurationFile;
    private FileConfiguration configuration;

    public ConfigurationHandler(System32Plugin plugin) {
        this.plugin = plugin;

        configurationFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configurationFile.exists()) plugin.saveResource("config.yml", false);
        configuration = YamlConfiguration.loadConfiguration(configurationFile);
    }

    public void saveConfig() {
        try {
            configuration.save(configurationFile);
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: Configuration file could not be saved!");
        }
    }

    public void reloadConfig() {
        configuration = YamlConfiguration.loadConfiguration(configurationFile);
    }

    // Settings

    public int getSaveDelay() {
        return configuration.getInt("Settings.SaveDelay");
    }

    // Chat

    public String getFormat() {
        return configuration.getString("Chat.Format");
    }
}
