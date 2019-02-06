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
        if (args.length == 3) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                return true;
            }
            System32Player system32Player = plugin.getPlayerHandler().getPlayerData(player.getUniqueId());
            if (args[2].equalsIgnoreCase("permission")) {
                if (args[1].equalsIgnoreCase("show")) {
                    if (!sender.hasPermission("player.permission.show")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("player.permission.show"));
                        return true;
                    }
                    sender.sendMessage(plugin.getMessageHandler().getHasPermissionsMessage(args[0], String.join(", ",system32Player.getPermissions()), system32Player.getGroup().getName()));
                    return true;
                }
            }
            if (args[2].equalsIgnoreCase("group")) {
                if (args[1].equalsIgnoreCase("show")) {
                    if (!sender.hasPermission("player.group.show")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("player.group.show"));
                        return true;
                    }
                    sender.sendMessage(plugin.getMessageHandler().getIsGroupMessage(args[0], system32Player.getGroup().getName()));
                    return true;
                }
                if (args[1].equalsIgnoreCase("remove")) {
                    if (!sender.hasPermission("player.group.remove")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("player.group.remove"));
                        return true;
                    }
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
                    if (!sender.hasPermission("player.permission.add")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("player.permission.add"));
                        return true;
                    }
                    boolean added = system32Player.addPermission(args[3]);
                    sender.sendMessage(added ?
                            plugin.getMessageHandler().getAddedPermissionMessagePlayer(args[0], args[3]) :
                            plugin.getMessageHandler().getAlreadyHasPermissionMessagePlayer(args[0], args[3]));
                    if (added) {
                        system32Player.addPlayerPermission(args[3]);
                        system32Player.savePlayerData();
                    }
                    return true;
                }
                if (args[1].equalsIgnoreCase("remove")) {
                    if (!sender.hasPermission("player.permission.remove")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("player.permission.remove"));
                        return true;
                    }
                    sender.sendMessage(system32Player.removePermission(args[3]) ?
                            plugin.getMessageHandler().getRemovedPermissionMessagePlayer(args[0], args[3]) :
                            plugin.getMessageHandler().getDoesntHavePermissionMessagePlayer(args[0], args[3]));
                    system32Player.savePlayerData();
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
                    if (!sender.hasPermission("player.group.set")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("player.group.set"));
                        return true;
                    }
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
