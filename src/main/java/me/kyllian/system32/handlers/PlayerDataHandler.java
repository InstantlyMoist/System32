package me.kyllian.system32.handlers;

import me.kyllian.system32.System32Plugin;
import me.kyllian.system32.utils.SystemPermissible;
import me.kyllian.system32.utils.PermissibleInjector;
import me.kyllian.system32.utils.System32Player;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.Permissible;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class PlayerDataHandler extends BukkitRunnable implements Listener {

    private System32Plugin plugin;

    private HashMap<UUID, System32Player> players;
    private int saveDelay;
    private File playerFolder;

    public PlayerDataHandler(System32Plugin plugin) {
        this.plugin = plugin;
        saveDelay = plugin.getConfigurationHandler().getSaveDelay();

        Bukkit.getPluginManager().registerEvents(this, plugin);

        players = new HashMap<>();

        playerFolder = new File(plugin.getDataFolder(), "players");
        if (!playerFolder.exists()) playerFolder.mkdir();
        initializePlayers();

        this.runTaskTimer(plugin, saveDelay, saveDelay);
    }

    public void initializePlayers() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            players.put(player.getUniqueId(), new System32Player(plugin, player.getUniqueId()));
        });
    }
    public void run() {
        players.values().forEach(player -> {
            player.savePlayerData();
        });
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("system32.update")) player.sendMessage(plugin.getUpdateChecker().getUpdateMessage());
        if (!players.containsKey(player))
            players.put(player.getUniqueId(), new System32Player(plugin, player.getUniqueId()));
        System32Player system32Player = getPlayerData(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (players.containsKey(player)) {
            System32Player system32Player = players.get(player);
            system32Player.savePlayerData();
            players.remove(player);
        }
    }

    public System32Player getPlayerData(UUID uuid) {
        return players.computeIfAbsent(uuid, f -> new System32Player(plugin, uuid));
    }

    public File getPlayerFolder() {
        return playerFolder;
    }
}
