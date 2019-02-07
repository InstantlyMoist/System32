package me.kyllian.system32.handlers;

import me.kyllian.system32.System32Plugin;
import me.kyllian.system32.utils.Group;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupHandler {

    private System32Plugin plugin;

    private File groupFile;
    private FileConfiguration configuration;

    private List<Group> groups;

    public GroupHandler(System32Plugin plugin) {
        this.plugin = plugin;

        groupFile = new File(plugin.getDataFolder(), "groups.yml");
        if (!groupFile.exists()) plugin.saveResource("groups.yml", false);
        configuration = YamlConfiguration.loadConfiguration(groupFile);

        initialize(false);
    }

    public void initialize(boolean load) {
        groups = new ArrayList<>();
        if (load) loadGroups();
    }

    public void loadGroups() {
        for (String group : configuration.getConfigurationSection("groups").getKeys(false)) {
            groups.add(new Group(plugin, group, false));
        }
    }

    public void addGroup(String group) {
        groups.add(new Group(plugin, group, true));
    }

    public void removeGroup(String group) {
        configuration.set("groups." +  group, null);
        groups.remove(getByName(group));
        saveGroups();
    }

    public void saveGroups() {
        for (Group group : groups) {
            configuration.set("groups." + group.getName() + ".default", group.isDefaultGroup());
            configuration.set("groups." + group.getName() + ".permissions", group.getPermissions());
            configuration.set("groups." + group.getName() + ".inheritance", group.getInheritance());
            configuration.set("groups." + group.getName() + ".info.prefix", group.getPrefix());
            configuration.set("groups." + group.getName() + ".info.build", group.isBuild());
            configuration.set("groups." + group.getName() + ".info.suffix", group.getSuffix());
        }
        // TODO: Add command to add permissions for a group and load them directly
        try {
            configuration.save(groupFile);
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: Groups file could not be saved!");
        }
    }

    public void reloadGroups() {
        configuration = YamlConfiguration.loadConfiguration(groupFile);
        initialize(true);
    }

    public boolean getDefault(String groupName) {
        return configuration.getBoolean("groups." + groupName + ".default");
    }

    public List<String> getPermissions(String groupName) {
        return configuration.getStringList("groups." + groupName + ".permissions");
    }

    public List<String> getInheritance(String groupName) {
        return configuration.getStringList("groups." + groupName + ".inheritance");
    }

    public String getPrefix(String groupName) {
        return configuration.getString("groups." + groupName + ".info.prefix");
    }

    public boolean getBuild(String groupName) {
        return configuration.getBoolean("groups." + groupName + ".info.build");
    }

    public String getSuffix(String groupName) {
        return configuration.getString("groups." + groupName + ".info.suffix");
    }

    public Group getByName(String name) {
        for (Group group : groups) {
            if (group.getName().equals(name)) return group;
        }
        return null;
    }

    public Group getDefaultGroup() {
        for (Group group : groups) {
            if (group.isDefaultGroup()) return group;
        }
        Bukkit.getLogger().warning("[System32]: No default group found, terminating plugin. Please set a default group in the groups.yml");
        Bukkit.getPluginManager().disablePlugin(plugin);
        return null;
    }
}
