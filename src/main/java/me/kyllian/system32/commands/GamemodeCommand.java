package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    private System32Plugin plugin;

    public GamemodeCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("gamemode")) {
            if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
                    if (!sender.hasPermission("system32.gamemode.creative")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.creative"));
                        return true;
                    }
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("creative"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
                    if (!sender.hasPermission("system32.gamemode.survival")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.survival"));
                        return true;
                    }
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("survival"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
                    if (!sender.hasPermission("system32.gamemode.adventure")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.adventure"));
                        return true;
                    }
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("adventure"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("4") || args[0].equalsIgnoreCase("sp")) {
                    if (!sender.hasPermission("system32.gamemode.spectator")) {
                        sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.spectator"));
                        return true;
                    }
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("spectator"));
                    return true;
                }
            }
            if (args.length == 2) {
                if (!sender.hasPermission("system32.gamemode.others")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.others"));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[1]));
                    return true;
                }
                if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
                    target.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(plugin.getMessageHandler().getGamemodeChangedOtherMessage(target.getName(), "creative"));
                    target.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("creative"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
                    target.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(plugin.getMessageHandler().getGamemodeChangedOtherMessage(target.getName(), "survival"));
                    target.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("survival"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
                    target.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(plugin.getMessageHandler().getGamemodeChangedOtherMessage(target.getName(), "adventure"));
                    target.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("adventure"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("4") || args[0].equalsIgnoreCase("sp")) {
                    target.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(plugin.getMessageHandler().getGamemodeChangedOtherMessage(target.getName(), "spectator"));
                    target.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("spectator"));
                    return true;
                }
            }
        }
        if (command.getName().equalsIgnoreCase("gmc")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                if (!sender.hasPermission("system32.gamemode.creative")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.creative"));
                    return true;
                }
                Player player = (Player) sender;
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("creative"));
                return true;
            }
            if (args.length == 1) {
                if (!sender.hasPermission("system32.gamemode.others")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.others"));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                    return true;
                }
                target.setGameMode(GameMode.CREATIVE);
                sender.sendMessage(plugin.getMessageHandler().getGamemodeChangedOtherMessage(target.getName(), "creative"));
                target.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("creative"));
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("gms")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                if (!sender.hasPermission("system32.gamemode.survival")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.survival"));
                    return true;
                }
                Player player = (Player) sender;
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("survival"));
                return true;
            }
            if (args.length == 1) {
                if (!sender.hasPermission("system32.gamemode.others")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.others"));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                    return true;
                }
                target.setGameMode(GameMode.SURVIVAL);
                sender.sendMessage(plugin.getMessageHandler().getGamemodeChangedOtherMessage(target.getName(), "survival"));
                target.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("survival"));
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("gma")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                if (!sender.hasPermission("system32.gamemode.adventure")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.adventure"));
                    return true;
                }
                Player player = (Player) sender;
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("adventure"));
                return true;
            }
            if (args.length == 1) {
                if (!sender.hasPermission("system32.gamemode.others")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.others"));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                    return true;
                }
                target.setGameMode(GameMode.ADVENTURE);
                sender.sendMessage(plugin.getMessageHandler().getGamemodeChangedOtherMessage(target.getName(), "adventure"));
                target.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("adventure"));
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("gmsp")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                    return true;
                }
                if (!sender.hasPermission("system32.gamemode.spectator")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.spectator"));
                    return true;
                }
                Player player = (Player) sender;
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("spectator"));
                return true;
            }
            if (args.length == 1) {
                if (!sender.hasPermission("system32.gamemode.others")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.gamemode.others"));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                    return true;
                }
                target.setGameMode(GameMode.SPECTATOR);
                sender.sendMessage(plugin.getMessageHandler().getGamemodeChangedOtherMessage(target.getName(), "spectator"));
                target.sendMessage(plugin.getMessageHandler().getGamemodeChangedMessage("spectator"));
                return true;
            }
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage(commandLabel));
        return true;
    }
}
