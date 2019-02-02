package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GroupCommand implements CommandExecutor {

    private System32Plugin plugin;

    public GroupCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        //group
        return true;
    }
}
