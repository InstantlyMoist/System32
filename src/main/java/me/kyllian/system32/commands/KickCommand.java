package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    private System32Plugin plugin;

    public KickCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length >= 1) {
            if (!(sender.hasPermission("system32.kick"))) {
                sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.kick"));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                return true;
            }
            if (target.hasPermission("system32.kick.override")) {
                sender.sendMessage(plugin.getMessageHandler().getCantKickMessage(args[0]));
                return true;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                builder.append(args[i]).append(" ");
            }
            String reason = builder.toString().trim();
            target.kickPlayer(plugin.getMessageHandler().getKickMessage(sender.getName(), reason));
            return true;
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage("kick"));
        return true;
    }
}
