package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import me.kyllian.system32.utils.Warp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private System32Plugin plugin;

    public WarpCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("warp")) {
            if (args.length == 1) {
                if (!sender.hasPermission("system32.warp")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.warp"));
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                Player player = (Player) sender;
                Warp warp = plugin.getWarpHandler().getByName(args[0]);
                if (warp == null) {
                    player.sendMessage(plugin.getMessageHandler().getWarpDoesNotExistMessage(args[0]));
                    return true;
                }
                player.teleport(warp.getLocation());
                player.sendMessage(plugin.getMessageHandler().getWarpedMessage(args[0]));
                return true;
            }
            if (args.length == 2) {
                if (!sender.hasPermission("system32.warp.other")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.warp.other"));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[1]));
                    return true;
                }
                Warp warp = plugin.getWarpHandler().getByName(args[0]);
                if (warp == null) {
                    sender.sendMessage(plugin.getMessageHandler().getWarpDoesNotExistMessage(args[0]));
                    return true;
                }
                target.teleport(warp.getLocation());
                target.sendMessage(plugin.getMessageHandler().getWarpedMessage(args[0]));
                sender.sendMessage(plugin.getMessageHandler().getWarpedOtherMessage(args[1], args[0]));
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("warps")) {
            if (!sender.hasPermission("system32.warp.list")) {
                sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.warp.list"));
                return true;
            }
            StringBuilder builder = new StringBuilder();
            plugin.getWarpHandler().getWarpList().forEach(warp -> {
                builder.append(warp.getName()).append(" ");
            });
            sender.sendMessage(plugin.getMessageHandler().getWarpsMessage(builder.toString().trim()));
            return true;
        }
        if (command.getName().equalsIgnoreCase("setwarp")) {
            if (args.length == 1) {
                if (!sender.hasPermission("system32.warp.add")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.warp.add"));
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                Player player = (Player) sender;
                Warp warp = plugin.getWarpHandler().getByName(args[0]);
                if (warp == null) {
                    plugin.getWarpHandler().addWarp(args[0], player.getLocation());
                    plugin.getWarpHandler().saveWarps();
                    player.sendMessage(plugin.getMessageHandler().getWarpCreatedMessage(args[0]));
                    return true;
                }
                player.sendMessage(plugin.getMessageHandler().getWarpExistsMessage(args[0]));
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("delwarp")) {
            if (args.length == 1) {
                if (!sender.hasPermission("system32.warp.remove")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.warp.remove"));
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                Player player = (Player) sender;
                Warp warp = plugin.getWarpHandler().getByName(args[0]);
                if (warp != null) {
                    plugin.getWarpHandler().removeWarp(warp);
                    plugin.getWarpHandler().saveWarps();
                    player.sendMessage(plugin.getMessageHandler().getWarpRemovedMessage(args[0]));
                    return true;
                }
                player.sendMessage(plugin.getMessageHandler().getWarpDoesNotExistMessage(args[0]));
                return true;
            }
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage(commandLabel));
        return true;
    }
}
