package me.kyllian.system32.utils;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class System32Player {

    private UUID uuid;
    private System32Plugin plugin;
    private File file;
    private FileConfiguration fileConfiguration;

    // Data
    private Group group;

    public System32Player(System32Plugin plugin, UUID uuid) {
        this.uuid = uuid;
        this.plugin = plugin;

        file = new File(plugin.getPlayerHandler().getPlayerFolder(), uuid.toString() + ".yml");
        try {
            if (!file.exists()) {
                Bukkit.getLogger().info("[System32]: Creating player data file for the UUID " + uuid.toString());
                file.createNewFile();
            }
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: Player data file from the UUID " + uuid.toString() + " could not be created.");
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);

        loadData();
    }

    public void loadData() {
        group = plugin.getGroupHandler().getByName(fileConfiguration.getString("group"));
        if (group == null) {
            Bukkit.getLogger().warning("[System32]: Invalid group found for the UUID " + uuid.toString() + " putting back to default group");
            group = plugin.getGroupHandler().getDefaultGroup();
            savePlayerData();
        }

        //TODO Save the data after eveyrthing has been edited.
            //TODO: Load the file into the local object.
    }

    public void savePlayerData() {
        fileConfiguration.set("group", group.getName());
        //TODO: Set the local data into the file
        try {
            fileConfiguration.save(file);
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: Player data file from the UUID " + uuid.toString() + " could not be saved.");
        }
    }

    public void reloadPlayerData() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public Group getGroup() {
        return group;
    }
}
