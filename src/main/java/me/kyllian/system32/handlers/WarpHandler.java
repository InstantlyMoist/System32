package me.kyllian.system32.handlers;

import me.kyllian.system32.System32Plugin;
import me.kyllian.system32.utils.Group;
import me.kyllian.system32.utils.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarpHandler {

    private System32Plugin plugin;

    private File warpFile;
    private FileConfiguration configuration;

    private List<Warp> warpList;


    public WarpHandler(System32Plugin plugin) {
        this.plugin = plugin;

        warpFile = new File(plugin.getDataFolder(), "warps.yml");
        if (!warpFile.exists()) plugin.saveResource("warps.yml", false);
        configuration = YamlConfiguration.loadConfiguration(warpFile);

        initialize(false);
    }

    public void initialize(boolean loadWarps) {
        warpList = new ArrayList<>();
        if (loadWarps) loadWarps();
    }

    public void loadWarps() {
        if (configuration.getConfigurationSection("warps") == null) return;
        for (String key : configuration.getConfigurationSection("warps").getKeys( false)) {
            warpList.add(new Warp(plugin, key));
        }
    }

    public void saveWarps() {
        warpList.forEach(warp -> {
            configuration.set("warps." + warp.getName(), warp.getLocation());
        });
        try {
            configuration.save(warpFile);
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: Warp file could not be saved!");
        }
    }

    public void addWarp(String name, Location location) {
        warpList.add(new Warp(plugin, name, location));
    }

    public void removeWarp(Warp warp) {
        configuration.set("warps." + warp.getName(), null);
        warpList.remove(warp);
    }

    public void reloadWarps() {
        configuration = YamlConfiguration.loadConfiguration(warpFile);
    }

    public Location getLocation(String warpName) {
        return (Location) configuration.get("warps." + warpName);

    }

    public Warp getByName(String name) {
        for (Warp warp: warpList) {
            if (warp.getName().equals(name)) return warp;
        }
        return null;
    }

    public List<Warp> getWarpList() {
        return warpList;
    }
}
