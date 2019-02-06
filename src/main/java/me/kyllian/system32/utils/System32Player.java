package me.kyllian.system32.utils;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class System32Player {

    private UUID uuid;
    private System32Plugin plugin;
    private File file;
    private FileConfiguration fileConfiguration;
    private PermissionAttachment permissionAttachment;

    // Data
    private Group group;
    private List<String> permissions;

    public System32Player(System32Plugin plugin, UUID uuid) {
        this.uuid = uuid;
        this.plugin = plugin;

        permissionAttachment = Bukkit.getPlayer(uuid).addAttachment(plugin);
        permissions = new ArrayList<>();

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
            setGroup(plugin.getGroupHandler().getDefaultGroup());
        }
        permissions = fileConfiguration.getStringList("permissions");
        updatePermissions();

        //TODO Save the data after eveyrthing has been edited.
            //TODO: Load the file into the local object.
    }

    public void savePlayerData() {
        fileConfiguration.set("group", group.getName());
        fileConfiguration.set("permissions", permissions);
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

    public boolean addPermission(String permission) {
        if (permissions.contains(permission)) return false;
        if (!permission.startsWith("-")) permissionAttachment.setPermission(permission, true);
        else permissionAttachment.unsetPermission(permission.replace("-", ""));
        return true;
    }

    public void addInheritPermission(String permission) {
        permissionAttachment.setPermission(permission, true);
    }

    public void addPlayerPermission(String permission) {
        permissions.add(permission);
    }

    public boolean removePermission(String permission) {
        if (!permissions.contains(permission)) return false;
        permissionAttachment.unsetPermission(permission.replace("-", ""));
        permissions.remove(permission);
        return true;
    }

    public void updatePermissions() {
        //TODO: Make sure the players' own permission will be overriding the rank ones.
        if (permissionAttachment != null) Bukkit.getPlayer(uuid).removeAttachment(permissionAttachment);
        permissionAttachment = Bukkit.getPlayer(uuid).addAttachment(plugin);
        group.getPermissions().forEach(permission -> {
            addPermission(permission);
        });
        group.getInheritance().forEach(name -> {
            Group group = plugin.getGroupHandler().getByName(name);
            group.getPermissions().forEach(permission -> {
                addInheritPermission(permission);
            });
        });
        permissions.forEach(permission -> {
            addPermission(permission);
        });
    }

    public void setGroup(Group group) {
        this.group = group;
        savePlayerData();
        updatePermissions();
    }

    public Group getGroup() {
        return group;
    }

    public PermissionAttachment getPermissionAttachment() {
        return permissionAttachment;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
