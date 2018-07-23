package me.kyllian.system32.utils;

import me.kyllian.system32.System32Plugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PermissionsHandler {

    private System32Plugin plugin;
    private File file;
    private FileConfiguration fileConfiguration;

    public PermissionsHandler(System32Plugin plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "permission.yml");
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void reloadPermissions() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void savePermissions() {
        try {
            fileConfiguration.save(file);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public String getBreakPermission(boolean others) {
        return fileConfiguration.getString(others ? "BreakOtherPermission" : "BreakPermission");
    }
}
