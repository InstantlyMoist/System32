package me.kyllian.system32.handlers;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
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

    public String translateColors(Player player, String message) {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) message = PlaceholderAPI.setPlaceholders(player, message);
        return translateColors(message);
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

    public String getAddedPermissionMessagePlayer(String playerName, String permission) {
        return translateColors(configuration.getString("AddedPermissionPlayer").replace("%player_name%", playerName).replace("%permission%", permission));
    }

    public String getAlreadyHasPermissionMessagePlayer(String playerName, String permission) {
        return translateColors(configuration.getString("AlreadyHasPermissionPlayer").replace("%player_name%", playerName).replace("%permission%", permission));
    }

    public String getRemovedPermissionMessagePlayer(String playerName, String permission) {
        return translateColors(configuration.getString("RemovedPermissionPlayer").replace("%player_name%", playerName).replace("%permission%", permission));
    }

    public String getDoesntHavePermissionMessagePlayer(String playerName, String permission) {
        return translateColors(configuration.getString("DoesntHavePermissionPlayer").replace("%player_name%", playerName).replace("%permission%", permission));
    }

    public String getAddedPermissionMessageGroup(String permission, String groupName) {
        return translateColors(configuration.getString("AddedPermissionGroup").replace("%permission%", permission).replace("%group%", groupName));
    }

    public String getAlreadyHasPermissionMessageGroup(String permission, String groupName) {
        return translateColors(configuration.getString("AlreadyHasPermissionGroup").replace("%permission%", permission).replace("%group%", groupName));
    }

    public String getRemovedPermissionMessageGroup(String permission, String groupName) {
        return translateColors(configuration.getString("RemovedPermissionGroup").replace("%permission%", permission).replace("%group%", groupName));
    }

    public String getDoesntHavePermissionMessageGroup(String permission, String groupName) {
        return translateColors(configuration.getString("DoesntHavePermissionGroup").replace("%permission%", permission).replace("%group%", groupName));
    }

    public String getHasPermissionsMessage(String playerName, String permissions, String groupName) {
        return translateColors(configuration.getString("HasPermission").replace("%player_name%", playerName).replace("%permissions%", permissions).replace("%group%", groupName));
    }

    public String getIsGroupMessage(String playerName, String groupName) {
        return translateColors(configuration.getString("IsGroup").replace("%group%", groupName).replace("%player_name%", playerName));
    }

    public String getNeedPermissionMessage(String permission) {
        return translateColors(configuration.getString("NeedPermission").replace("%permission%", permission));
    }

    public String getGroupAlreadyExistsMessage(String groupName) {
        return translateColors(configuration.getString("GroupAlreadyExists").replace("%group%", groupName));
    }

    public String getGroupCreatedMessage(String groupName) {
        return translateColors(configuration.getString("GroupCreated").replace("%group%", groupName));
    }

    public String getGroupRemovedMessage(String groupName) {
        return translateColors(configuration.getString("GroupRemoved").replace("%group%", groupName));
    }

    public String getRemovedSuffixMessage(String groupName) {
        return translateColors(configuration.getString("RemovedSuffix").replace("%group%", groupName));
    }

    public String getRemovedPrefixmessage(String groupName) {
        return translateColors(configuration.getString("RemovedPrefix").replace("%group%", groupName));
    }

    public String getSetSuffixMessage(String groupName, String suffix) {
        return translateColors(configuration.getString("SetSuffix").replace("%group%", groupName).replace("%suffix%", suffix));
    }

    public String getSetPrefixMessage(String groupName, String prefix) {
        return translateColors(configuration.getString("SetPrefix").replace("%group%", groupName).replace("%prefix%", prefix));
    }

    public String getGroupShowInheritanceMessage(String groupName, String groups) {
        return translateColors(configuration.getString("GroupShowInheritance").replace("%group%", groupName).replace("%groups%", groups));
    }

    public String getGroupShowPermissionsMessage(String groupName, String permissions) {
        return translateColors(configuration.getString("GroupShowPermissions").replace("%group%", groupName).replace("%permissions%", permissions));
    }

    public String getGroupShowPrefixMessage(String groupName, String prefix) {
        return translateColors(configuration.getString("GroupShowPrefix").replace("%group%", groupName).replace("%prefix%", prefix));
    }
    public String getGroupShowSuffixMessage(String groupName, String suffix) {
        return translateColors(configuration.getString("GroupShowSuffix").replace("%group%", groupName).replace("%suffix%", suffix));
    }

    public String getAddedInheritageMessage(String groupName, String inherit) {
        return translateColors(configuration.getString("AddedInheritage").replace("%group&", groupName).replace("%inherit%", inherit));
    }

    public String getAlreadyInheritsMessage(String groupName, String inherit) {
        return translateColors(configuration.getString("AlreadyInherits").replace("%group&", groupName).replace("%inherit%", inherit));
    }

    public String getRemovedInheritageMessage(String groupName, String inherit) {
        return translateColors(configuration.getString("RemovedInheritage").replace("%group&", groupName).replace("%inherit%", inherit));
    }

    public String getDoesntInheritMessage(String groupName, String inherit) {
        return translateColors(configuration.getString("DoesntInherit").replace("%group&", groupName).replace("%inherit%", inherit));
    }

    public String getCheckingUpdateMessage() {
        return translateColors(configuration.getString("CheckingUpdate"));
    }

    public String getUpdateNotFoundMessage() {
        return translateColors(configuration.getString("UpdateNotFound"));
    }

    public String getUpdateFoundMessage(String oldVersion, String newVersion) {
        return translateColors(configuration.getString("UpdateFound").replace("%oldversion%", oldVersion).replace("%newversion%", newVersion).replace("%url%", plugin.getUpdateChecker().getResourceURL()));
    }

    public String getMustBeAPlayerMessage() {
        return translateColors(configuration.getString("MustBeAPlayer"));
    }

    public String getSpawnNotSetMessage() {
        return translateColors(configuration.getString("SpawnNotSet"));
    }

    public String getTeleportedToSpawnMessage() {
        return translateColors(configuration.getString("TeleportedToSpawn"));
    }

    public String getTeleportedOtherToSpawnMessage(String playerName) {
        return translateColors(configuration.getString("TeleportedOtherToSpawn").replace("%player_name%", playerName));
    }

    public String getSpawnSetMessage() {
        return translateColors(configuration.getString("SpawnSet"));
    }

    public String getSpawnRemovedMessage() {
        return translateColors(configuration.getString("SpawnRemoved"));
    }

    public String getReloadedMessage() {
        return translateColors(configuration.getString("Reloaded"));
    }

    public String getHealedMessage() {
        return translateColors(configuration.getString("Healed"));
    }

    public String getHealedOtherMessage(String playerName) {
        return translateColors(configuration.getString("HealedOther").replace("%player_name%", playerName));
    }

    public String getFeedMessage() {
        return translateColors(configuration.getString("Feed"));
    }

    public String getFeedOtherMessage(String playerName) {
        return translateColors(configuration.getString("FeedOther").replace("%player_name%", playerName));
    }

    public String getGamemodeChangedMessage(String gamemode) {
        return translateColors(configuration.getString("GamemodeChanged").replace("%gamemode%", gamemode));
    }

    public String getGamemodeChangedOtherMessage(String playerName, String gamemode) {
        return translateColors(configuration.getString("GamemodeChangedOther").replace("%gamemode%", gamemode).replace("%player_name%", playerName));
    }

    public String getWarpDoesNotExistMessage(String warpName) {
        return translateColors(configuration.getString("WarpDoesNotExist").replace("%warp%", warpName));
    }

    public String getWarpedMessage(String warpName) {
        return translateColors(configuration.getString("Warped").replace("%warp%", warpName));
    }

    public String getWarpedOtherMessage(String playerName, String warpName) {
        return translateColors(configuration.getString("WarpedOther").replace("%warp%", warpName).replace("%player_name%", playerName));
    }

    public String getWarpsMessage(String warps) {
        return translateColors(configuration.getString("Warps").replace("%warps%", warps));
    }

    public String getWarpExistsMessage(String warpName) {
        return translateColors(configuration.getString("WarpExists").replace("%warp%", warpName));
    }

    public String  getWarpCreatedMessage(String warpName) {
        return translateColors(configuration.getString("WarpCreated").replace("%warp%", warpName));
    }

    public String getWarpRemovedMessage(String warpName) {
        return translateColors(configuration.getString("WarpRemoved").replace("%warp%", warpName));
    }
}
