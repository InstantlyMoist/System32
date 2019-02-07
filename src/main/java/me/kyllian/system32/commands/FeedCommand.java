package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor{

    private System32Plugin plugin;

    public FeedCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("system32.feed")) {
                sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.feed"));
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                return true;
            }
            Player player = (Player) sender;
            player.setFoodLevel(20);
            player.sendMessage(plugin.getMessageHandler().getFeedMessage());
            return true;
        }
        if (args.length == 1) {
            if (!sender.hasPermission("system32.feed.others")) {
                sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.feed.others"));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                return true;
            }
            target.setFoodLevel(20);
            target.sendMessage(plugin.getMessageHandler().getFeedMessage());
            sender.sendMessage(plugin.getMessageHandler().getFeedOtherMessage(args[0]));
            return true;
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage(commandLabel));
        return true;
    }
}
