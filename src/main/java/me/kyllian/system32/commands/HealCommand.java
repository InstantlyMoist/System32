package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    private System32Plugin plugin;

    public HealCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("system32.heal")) {
                sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.heal"));
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getMessageHandler().getMustBeAPlayerMessage());
                return true;
            }
            Player player = (Player) sender;
            player.setHealth(player.getMaxHealth());
            player.sendMessage(plugin.getMessageHandler().getHealedMessage());
            return true;
        }
        if (args.length == 1) {
            if (!sender.hasPermission("system32.heal.others")) {
                sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.heal.other"));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(plugin.getMessageHandler().getPlayerNotOnlineMessage(args[0]));
                return true;
            }
            target.setHealth(target.getMaxHealth());
            target.sendMessage(plugin.getMessageHandler().getHealedMessage());
            sender.sendMessage(plugin.getMessageHandler().getHealedOtherMessage(args[0]));
            return true;
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage(commandLabel));
        return true;
    }
}
