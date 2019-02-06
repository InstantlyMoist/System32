package me.kyllian.system32.utils;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Group(System32Plugin plugin, String name, boolean newGroup) {
        this.plugin = plugin;
        this.name = name;
        if (newGroup) {
            defaultGroup = false;
            permissions = new ArrayList<>(Arrays.asList());
            inheritance = new ArrayList<>(Arrays.asList());
            prefix = "&7";
            suffix = "";
        } else {
            defaultGroup = plugin.getGroupHandler().getDefault(name);
            permissions = plugin.getGroupHandler().getPermissions(name);
            inheritance = plugin.getGroupHandler().getInheritance(name);
            prefix = plugin.getGroupHandler().getPrefix(name);
            suffix = plugin.getGroupHandler().getSuffix(name);
        }
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

    public List<String> getInheritance() {
        return inheritance;
    }

    public boolean isBuild() {
        return build;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean addPermission(String permission) {
        if (permissions.contains(permission)) return false;
        permissions.add(permission);
        return true;
    }

    public boolean removePermission(String permission) {
        if (!permissions.contains(permission)) return false;
        permissions.remove(permission);
        return true;
    }

    public boolean addInherit(String group) {
        if (inheritance.contains(group)) return false;
        inheritance.add(group);
        return true;
    }

    public boolean removeInherit(String group) {
        if (!inheritance.contains(group)) return false;
        inheritance.remove(group);
        return true;
    }

    public void updatePermissions() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            System32Player system32Player = plugin.getPlayerHandler().getPlayerData(player.getUniqueId());
            if (system32Player.getGroup() == this) system32Player.updatePermissions();
        });
    }
}

