package me.kyllian.system32;

import me.kyllian.system32.commands.*;
import me.kyllian.system32.handlers.*;
import me.kyllian.system32.listeners.PlayerChatListener;
import me.kyllian.system32.utils.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class System32Plugin extends JavaPlugin {

    // Handlers
    private ConfigurationHandler configurationHandler;
    private DataHandler dataHandler;
    private MessageHandler messageHandler;
    private PlayerDataHandler playerHandler;
    private GroupHandler groupHandler;
    private SpawnHandler spawnHandler;
    private WarpHandler warpHandler;

    private UpdateChecker updateChecker;

    public void onEnable() {
        Metrics metrics = new Metrics(this);
        initializeHandlers();
        initializeCommands();
        initializeListeners();
    }

    public void initializeHandlers() {
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        configurationHandler = new ConfigurationHandler(this);
        dataHandler = new DataHandler(this);
        messageHandler = new MessageHandler(this);
        groupHandler = new GroupHandler(this);
        playerHandler = new PlayerDataHandler(this);
        spawnHandler = new SpawnHandler(this);
        warpHandler = new WarpHandler(this);
        groupHandler.loadGroups();
        warpHandler.loadWarps();

        updateChecker = new UpdateChecker(this,37249 );
        //TODO: Initialize handlers
    }

    public void initializeCommands() {
        getCommand("group").setExecutor(new GroupCommand(this));
        getCommand("player").setExecutor(new PlayerCommand(this));
        SpawnCommand spawnCommand = new SpawnCommand(this);
        getCommand("spawn").setExecutor(spawnCommand);
        getCommand("delspawn").setExecutor(spawnCommand);
        getCommand("setspawn").setExecutor(spawnCommand);
        getCommand("system32").setExecutor(new System32Command(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("kick").setExecutor(new KickCommand(this));
        getCommand("feed").setExecutor(new FeedCommand(this));
        GamemodeCommand gamemodeCommand = new GamemodeCommand(this);
        getCommand("gamemode").setExecutor(gamemodeCommand);
        getCommand("gms").setExecutor(gamemodeCommand);
        getCommand("gmc").setExecutor(gamemodeCommand);
        getCommand("gma").setExecutor(gamemodeCommand);
        getCommand("gmsp").setExecutor(gamemodeCommand);
        WarpCommand warpCommand = new WarpCommand(this);
        getCommand("warp").setExecutor(warpCommand);
        getCommand("warps").setExecutor(warpCommand);
        getCommand("setwarp").setExecutor(warpCommand);
        getCommand("delwarp").setExecutor(warpCommand);
    }

    public void initializeListeners() {
        new PlayerChatListener(this);
    }

    public ConfigurationHandler getConfigurationHandler() {
        return configurationHandler;
    }

    public PlayerDataHandler getPlayerHandler() {
        return playerHandler;
    }

    public GroupHandler getGroupHandler() {
        return groupHandler;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }

    public SpawnHandler getSpawnHandler() {
        return spawnHandler;
    }

    public WarpHandler getWarpHandler() {
        return warpHandler;
    }
}
