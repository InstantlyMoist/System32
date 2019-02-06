package me.kyllian.system32.handlers;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnHandler {

    private System32Plugin plugin;

    private File spawnFile;
    private FileConfiguration configuration;

    private Location spawn;

    public SpawnHandler(System32Plugin plugin) {
        this.plugin = plugin;

        spawnFile = new File(plugin.getDataFolder(), "spawn.yml");
        if (!spawnFile.exists()) plugin.saveResource("spawn.yml", false);
        configuration = YamlConfiguration.loadConfiguration(spawnFile);

        spawn = (Location) configuration.get("spawn");
    }

    public void saveSpawn() {
        configuration.set("spawn", spawn);
        try {
            configuration.save(spawnFile);
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: SpawnCommand file could not be saved!");
        }
    }

    public void reloadSpawn() {
        configuration = YamlConfiguration.loadConfiguration(spawnFile);
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public boolean spawnExists() {
        return spawn != null;
    }
}
