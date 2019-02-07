package me.kyllian.system32.utils;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Location;

public class Warp {

    private System32Plugin plugin;

    private String name;
    private Location location;

    public Warp(System32Plugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        location = plugin.getWarpHandler().getLocation(name);
    }

    public Warp(System32Plugin plugin, String name, Location location) {
        this.plugin = plugin;
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
