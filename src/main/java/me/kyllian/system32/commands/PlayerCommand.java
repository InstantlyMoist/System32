package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import me.kyllian.system32.utils.Group;
import me.kyllian.system32.utils.System32Player;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand implements CommandExecutor{

    private System32Plugin plugin;

    public PlayerCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (sender.hasPermission("permission.test")) sender.sendMessage("Hi!");
        // player NAME set group rank
        // player NAME remove group
        // player NAME add permission permission
        // player NAME remove permission permission
        if (args.length == 3) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                return true;
            }
            System32Player system32Player = plugin.getPlayerHandler().getPlayerData(player.getUniqueId());
            if (args[2].equalsIgnoreCase("group")) {
                if (args[1].equalsIgnoreCase("remove")) {
                    system32Player.setGroup(plugin.getGroupHandler().getDefaultGroup());
                    sender.sendMessage(plugin.getMessageHandler().getRemovedRankMessage(args[0]));
                    return true;
                }
            }
        }
        if (args.length == 4) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                return true;
            }
            System32Player system32Player = plugin.getPlayerHandler().getPlayerData(player.getUniqueId());
            if (args[2].equalsIgnoreCase("permission")) {
                if (args[1].equalsIgnoreCase("add")) {
                    Bukkit.broadcastMessage(system32Player.getPermissionAttachment().getPermissions().keySet() + "");
                    sender.sendMessage(system32Player.addPermission(args[3]) ?
                            plugin.getMessageHandler().getAddedPermissionMessage(args[0], args[3]) :
                            plugin.getMessageHandler().getAlreadyHasPermissionMessage(args[0], args[3]));
                    if (player.hasPermission(args[3])) system32Player.addPlayerPermission(args[3]);
                    system32Player.savePlayerData();
                    Bukkit.broadcastMessage(system32Player.getPermissionAttachment().getPermissions().keySet() + "");
                    return true;
                }
                if (args[1].equalsIgnoreCase("remove")) {
                    Bukkit.broadcastMessage(system32Player.getPermissionAttachment().getPermissions().keySet() + "");
                    sender.sendMessage(system32Player.removePermission(args[3]) ?
                            plugin.getMessageHandler().getRemovedPermissionMessage(args[0], args[3]) :
                            plugin.getMessageHandler().getDoesntHavePermissionMessage(args[0], args[3]));
                    system32Player.savePlayerData();
                    Bukkit.broadcastMessage(system32Player.getPermissionAttachment().getPermissions().keySet() + "");
                    return true;
                }
            }
            if (args[2].equalsIgnoreCase("group")) {
                Group group = plugin.getGroupHandler().getByName(args[3]);
                if (group == null) {
                    sender.sendMessage(plugin.getMessageHandler().getGroupDoesNotExistMessage(args[3]));
                    return true;
                }
                if (args[1].equalsIgnoreCase("set")) {
                    system32Player.setGroup(group);
                    sender.sendMessage(plugin.getMessageHandler().getGroupSetMessage(args[0], args[3]));
                    return true;
                }
            }
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage(commandLabel));
        return true;
    }
}
