package me.kyllian.system32.listeners;

import me.kyllian.system32.System32Plugin;
import me.kyllian.system32.utils.System32Player;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private System32Plugin plugin;

    public PlayerChatListener(System32Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //TODO Add placeholderapi support
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        System32Player system32Player = plugin.getPlayerHandler().getPlayerData(player.getUniqueId());
        String format = plugin.getConfigurationHandler().getFormat();
        event.setFormat(handleFormat(player, system32Player, event.getMessage(), format));
    }

    public String handleFormat(Player player, System32Player system32Player, String message, String format) {
        String finalMessage = format.replace("%prefix%", plugin.getMessageHandler().translateColors(system32Player.getGroup().getPrefix()));
        finalMessage = finalMessage.replace("%suffix%", plugin.getMessageHandler().translateColors(system32Player.getGroup().getSuffix()));
        finalMessage = finalMessage.replace("%player_name%", player.getName());
        finalMessage = plugin.getMessageHandler().translateColors(player, finalMessage);
        return plugin.getMessageHandler().translateColors(player, "essentials.chat", finalMessage + message);
    }
}
