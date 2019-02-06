package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private System32Plugin plugin;

    public SpawnCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("spawn")) {
            if (args.length == 0) {
                if (!sender.hasPermission("system32.spawn")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.spawn"));
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                if (!plugin.getSpawnHandler().spawnExists()) {
                    sender.sendMessage(plugin.getMessageHandler().getSpawnNotSetMessage());
                    return true;
                }
                Player player = (Player) sender;
                player.teleport(plugin.getSpawnHandler().getSpawn());
                player.sendMessage(plugin.getMessageHandler().getTeleportedToSpawnMessage());
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (!sender.hasPermission("system32.spawn.set")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.spawn.set"));
                        return true;
                    }
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                        return true;
                    }
                    Player player = (Player) sender;
                    plugin.getSpawnHandler().setSpawn(player.getLocation());
                    plugin.getSpawnHandler().saveSpawn();
                    player.sendMessage(plugin.getMessageHandler().getSpawnSetMessage());
                    return true;
                }
                if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) {
                    if (!sender.hasPermission("system32.spawn.remove")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.spawn.remove"));
                        return true;
                    }
                    plugin.getSpawnHandler().setSpawn(null);
                    plugin.getSpawnHandler().saveSpawn();
                    sender.sendMessage(plugin.getMessageHandler().getSpawnRemovedMessage());
                    return true;
                }
                if (!sender.hasPermission("system32.spawn.other")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.spawn.other"));
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                    return true;
                }
                if (!plugin.getSpawnHandler().spawnExists()) {
                    sender.sendMessage(plugin.getMessageHandler().getSpawnNotSetMessage());
                    return true;
                }
                target.teleport(plugin.getSpawnHandler().getSpawn());
                target.sendMessage(plugin.getMessageHandler().getTeleportedToSpawnMessage());
                sender.sendMessage(plugin.getMessageHandler().getTeleportedOtherToSpawnMessage(target.getName()));
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("setspawn")) {
            if (!sender.hasPermission("system32.spawn.set")) {
                sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.spawn.set"));
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                return true;
            }
            Player player = (Player) sender;
            plugin.getSpawnHandler().setSpawn(player.getLocation());
            plugin.getSpawnHandler().saveSpawn();
            player.sendMessage(plugin.getMessageHandler().getSpawnSetMessage());
            return true;
        }
        if (command.getName().equalsIgnoreCase("delspawn")) {
            if (!sender.hasPermission("system32.spawn.remove")) {
                sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.spawn.remove"));
                return true;
            }
            plugin.getSpawnHandler().setSpawn(null);
            plugin.getSpawnHandler().saveSpawn();
            sender.sendMessage(plugin.getMessageHandler().getSpawnRemovedMessage());
            return true;
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage(commandLabel));
        return true;
    }
}
