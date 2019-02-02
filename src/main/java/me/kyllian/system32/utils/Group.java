package me.kyllian.system32.utils;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;

import java.util.List;

public class Group {

    private System32Plugin plugin;

    private String name;
    private boolean defaultGroup;
    private List<String> permissions;
    private List<String> inheritance;
    private String prefix;
    private String suffix;
    private boolean build;

    public Group(System32Plugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        defaultGroup = plugin.getGroupHandler().getDefault(name);
        permissions = plugin.getGroupHandler().getPermissions(name);
        inheritance = plugin.getGroupHandler().getInheritance(name);
        prefix = plugin.getGroupHandler().getPrefix(name);
        suffix = plugin.getGroupHandler().getSuffix(name);
        build = plugin.getGroupHandler().getBuild(name);
        //TODO: Load group data
    }

    public String getName() {
        return name;
    }

    public boolean isDefaultGroup() {
        return defaultGroup;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}

