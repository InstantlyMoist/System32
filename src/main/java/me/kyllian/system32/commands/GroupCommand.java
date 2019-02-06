package me.kyllian.system32.commands;

import me.kyllian.system32.System32Plugin;
import me.kyllian.system32.utils.Group;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GroupCommand implements CommandExecutor {

    private System32Plugin plugin;

    public GroupCommand(System32Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("create")) {
                if (!sender.hasPermission("group.create")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("group.create"));
                    return true;
                }
                Group group = plugin.getGroupHandler().getByName(args[1]);
                if (group == null) {
                    plugin.getGroupHandler().addGroup(args[1]);
                    plugin.getGroupHandler().saveGroups();
                    sender.sendMessage(plugin.getMessageHandler().getGroupCreatedMessage(args[1]));
                    return true;
                }
                sender.sendMessage(plugin.getMessageHandler().getGroupAlreadyExistsMessage(args[1]));
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if (!sender.hasPermission("group.remove")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("group.remove"));
                    return true;
                }
                Group group = plugin.getGroupHandler().getByName(args[1]);
                if (group == null) {
                    sender.sendMessage(plugin.getMessageHandler().getGroupDoesNotExistMessage(args[1]));
                    return true;
                }
                plugin.getGroupHandler().removeGroup(args[1]);
                sender.sendMessage(plugin.getMessageHandler().getGroupRemovedMessage(args[1]));
                return true;
            }
        }
        if (args.length == 3) {
            Group group = plugin.getGroupHandler().getByName(args[0]);
            if (group == null) {
                sender.sendMessage(plugin.getMessageHandler().getGroupDoesNotExistMessage(args[1]));
                return true;
            }
            if (args[1].equalsIgnoreCase("show")) {
                if (!sender.hasPermission("group.show")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("group.show"));
                    return true;
                }
                if (args[2].equalsIgnoreCase("permissions")) {
                    sender.sendMessage(plugin.getMessageHandler().getGroupShowPermissionsMessage(args[0], String.join(" ", group.getPermissions())));
                    return true;
                }
                if (args[2].equalsIgnoreCase("inheritance")) {
                    sender.sendMessage(plugin.getMessageHandler().getGroupShowInheritanceMessage(args[0], String.join(" ", group.getInheritance())));
                    return true;
                }
                if (args[2].equalsIgnoreCase("prefix")) {
                    sender.sendMessage(plugin.getMessageHandler().getGroupShowPrefixMessage(args[0], group.getPrefix()));
                    return true;
                }
                if (args[2].equalsIgnoreCase("suffix")) {
                    sender.sendMessage(plugin.getMessageHandler().getGroupShowSuffixMessage(args[0], group.getSuffix()));
                    return true;
                }
            }
            if (args[1].equalsIgnoreCase("remove")) {
                if (!sender.hasPermission("group.remove")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("group.remove"));
                    return true;
                }
                if (args[2].equalsIgnoreCase("prefix")) {
                    group.setPrefix("");
                    plugin.getGroupHandler().saveGroups();
                    sender.sendMessage(plugin.getMessageHandler().getRemovedPrefixmessage(args[0]));
                    return true;
                }
                if (args[2].equalsIgnoreCase("suffix")) {
                    group.setSuffix("");
                    plugin.getGroupHandler().saveGroups();
                    sender.sendMessage(plugin.getMessageHandler().getRemovedSuffixMessage(args[0]));
                    return true;
                }
            }
        }
        if (args.length == 4) {
            Group group = plugin.getGroupHandler().getByName(args[0]);
            if (group == null) {
                sender.sendMessage(plugin.getMessageHandler().getGroupDoesNotExistMessage(args[0]));
                return true;
            }
            if (args[2].equalsIgnoreCase("inheritage")) {
                if (!sender.hasPermission("group.inheritage")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("group.inheritage"));
                    return true;
                }
                Group groupTarget = plugin.getGroupHandler().getByName(args[3]);
                if (groupTarget == null) {
                    sender.sendMessage(plugin.getMessageHandler().getGroupDoesNotExistMessage(args[3]));
                    return true;
                }
                if (args[1].equalsIgnoreCase("add")) {
                    sender.sendMessage(group.addInherit(args[3]) ?
                            plugin.getMessageHandler().getAddedInheritageMessage(args[3], args[0]) :
                            plugin.getMessageHandler().getAlreadyInheritsMessage(args[3], args[0]));
                    group.updatePermissions();
                    plugin.getGroupHandler().saveGroups();
                    return true;
                }
                if (args[1].equalsIgnoreCase("remove")) {
                    sender.sendMessage(group.removeInherit(args[3]) ?
                            plugin.getMessageHandler().getRemovedInheritageMessage(args[3], args[0]) :
                            plugin.getMessageHandler().getDoesntInheritMessage(args[3], args[0]));
                    group.updatePermissions();
                    plugin.getGroupHandler().saveGroups();
                    return true;
                }
            }
            if (args[2].equalsIgnoreCase("permission")) {
                if (!sender.hasPermission("group.permission")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("group.permission"));
                    return true;
                }
                if (args[1].equalsIgnoreCase("add")) {
                    sender.sendMessage(group.addPermission(args[3]) ?
                            plugin.getMessageHandler().getAddedPermissionMessageGroup(args[3], args[0]) :
                            plugin.getMessageHandler().getAlreadyHasPermissionMessageGroup(args[3], args[0]));
                    group.updatePermissions();
                    plugin.getGroupHandler().saveGroups();
                    return true;
                }
                if (args[1].equalsIgnoreCase("remove")) {
                    sender.sendMessage(group.removePermission(args[3]) ?
                            plugin.getMessageHandler().getRemovedPermissionMessageGroup(args[3], args[0]) :
                            plugin.getMessageHandler().getDoesntHavePermissionMessageGroup(args[3], args[0]));
                    group.updatePermissions();
                    plugin.getGroupHandler().saveGroups();
                    return true;
                }
            }
        }
        if (args.length >= 4) {
            Group group = plugin.getGroupHandler().getByName(args[0]);
            if (group == null) {
                sender.sendMessage(plugin.getMessageHandler().getGroupDoesNotExistMessage(args[0]));
                return true;
            }
            if (args[2].equalsIgnoreCase("suffix")) {
                if (!sender.hasPermission("group.suffix")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("group.create"));
                    return true;
                }
                if (args[1].equalsIgnoreCase("set")) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 3; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }
                    String finalString = builder.toString().trim();
                    group.setSuffix(finalString);
                    plugin.getGroupHandler().saveGroups();
                    sender.sendMessage(plugin.getMessageHandler().getSetSuffixMessage(args[0], finalString));
                    return true;
                }
            }
            if (args[2].equalsIgnoreCase("prefix")) {
                if (!sender.hasPermission("group.suffix")) {
                    sender.sendMessage(plugin.getMessageHandler().getNeedPermissionMessage("group.create"));
                    return true;
                }
                if (args[1].equalsIgnoreCase("set")) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 3; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }
                    String finalString = builder.toString().trim();
                    group.setPrefix(finalString);
                    plugin.getGroupHandler().saveGroups();
                    sender.sendMessage(plugin.getMessageHandler().getSetPrefixMessage(args[0], finalString));
                    return true;
                }
            }
        }
        sender.sendMessage(plugin.getMessageHandler().getUnknownArgumentMessage(commandLabel));
        return true;
    }
}
