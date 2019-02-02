package me.kyllian.system32.handlers;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MessageHandler {

    private System32Plugin plugin;

    private File messageFile;
    private FileConfiguration configuration;

    public MessageHandler(System32Plugin plugin) {
        this.plugin = plugin;

        messageFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messageFile.exists()) plugin.saveResource("messages.yml", false);
        configuration = YamlConfiguration.loadConfiguration(messageFile);
    }

    public void saveMessages() {
        try {
            configuration.save(messageFile);
        } catch (IOException exception) {
            Bukkit.getLogger().warning("[System32]: Messages file could not be saved!");
        }
    }

    public void reloadMessages() {
        configuration = YamlConfiguration.loadConfiguration(messageFile);
    }

    public String translateColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String translateColors(Player player, String permission, String message) {
        return player.hasPermission(permission) ? ChatColor.translateAlternateColorCodes('&', message) : message;
    }

    // Messages

    public String getGroupDoesNotExistMessage(String groupName) {
        return translateColors(configuration.getString("GroupDoesNotExist").replace("%group%", groupName));
    }

    public String getGroupSetMessage(String playerName, String groupName) {
        return translateColors(configuration.getString("GroupSet").replace("%group%", groupName).replace("%player_name%", playerName));
    }

    public String getPlayerNotOnlineMessage(String playerName) {
        return translateColors(configuration.getString("PlayerNotOnline").replace("%player_name%", playerName));
    }

    public String getRemovedRankMessage(String playerName) {
        return translateColors(configuration.getString("RemovedGroup").replace("%player_name%", playerName));
    }

    public String getUnknownArgumentMessage(String command) {
        return translateColors(configuration.getString("UnknownArgument").replace("%command%", command));
    }

    // to sort

    public String getAddedPermissionMessage(String playerName, String permission) {
        return translateColors(configuration.getString("AddedPermission").replace("%player_name%", playerName).replace("%permission%", permission));
    }

    public String getAlreadyHasPermissionMessage(String playerName, String permission) {
        return translateColors(configuration.getString("AlreadyHasPermission").replace("%player_name%", playerName).replace("%permission%", permission));
    }

    public String getRemovedPermissionMessage(String playerName, String permission) {
        return translateColors(configuration.getString("RemovedPermission").replace("%player_name%", playerName).replace("%permission%", permission));
    }

    public String getDoesntHavePermissionMessage(String playerName, String permission) {
        return translateColors(configuration.getString("DoesntHavePermission").replace("%player_name%", playerName).replace("%permission%", permission));
    }
}
