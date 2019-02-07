package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class System32Command implements CommandExecutor {

    private System32Plugin plugin;

    public System32Command(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("system32.reload")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("system32.reload"));
                    return true;
                }
                plugin.getConfigurationHandler().reloadConfig();
                plugin.getMessageHandler().reloadMessages();
                plugin.getSpawnHandler().reloadSpawn();
                plugin.getGroupHandler().reloadGroups();
                plugin.getWarpHandler().reloadWarps();
                sender.sendMessage(plugin.getMessageHandler().getReloadedMessage());
                return true;
            }
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage(commandLabel));
        return true;
    }
}
