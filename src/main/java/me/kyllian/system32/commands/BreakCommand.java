package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import me.kyllian.system32.utils.MessageHandler;
import me.kyllian.system32.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BreakCommand implements CommandExecutor {

    private System32Plugin plugin;

    public BreakCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getMessageHandler().getPlayerMessage());
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission(plugin.getPermissionsHandler().getBreakPermission(false))) {
                player.sendMessage(plugin.getMessageHandler().getNoPermissionMessage());
                return true;
            }
            // TODO: Break the block the player is looking at
            Block targetBlock = player.getTargetBlock(null, 20);
            if (targetBlock == null) {
                player.sendMessage(plugin.getMessageHandler().getNoTargetBlockMessgae(false));
                return true;
            }
            targetBlock.setType(Material.AIR);
            return true;
        }
        if (args.length == 1) {
            if (!sender.hasPermission(plugin.getPermissionsHandler().getBreakPermission(true))) {
                sender.sendMessage(plugin.getMessageHandler().getNoPermissionMessage());
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(plugin.getMessageHandler().getInvalidPlayerMessage());
                return true;
            }
            Block targetBlock = target.getTargetBlock(null, 20);
            if (targetBlock == null) {
                sender.sendMessage(plugin.getMessageHandler().getNoTargetBlockMessgae(true));
                return true;
            }
            targetBlock.setType(Material.AIR);
            return true;
        }
        return true;
    }
}
