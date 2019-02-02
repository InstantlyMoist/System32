package me.kyllian.system32.handlers;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataHandler {

    private System32Plugin plugin;

    private File dataFile;
    private FileConfiguration configuration;

    public DataHandler(System32Plugin plugin) {
        this.plugin = plugin;

        dataFile = new File(plugin.getDataFolder(), "data.yml");
        if (!dataFile.exists()) plugin.saveResource("data.yml", false);
        configuration = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void saveData() {
        try {
            configuration.save(dataFile);
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: Data file could not be saved!");
        }
    }

    public void reloadData() {
        configuration = YamlConfiguration.loadConfiguration(dataFile);
    }
}
