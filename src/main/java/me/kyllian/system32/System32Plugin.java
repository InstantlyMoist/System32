package me.kyllian.system32;

import me.kyllian.system32.commands.GroupCommand;
import me.kyllian.system32.commands.PlayerCommand;
import me.kyllian.system32.commands.SpawnCommand;
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
        groupHandler.loadGroups();

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
}
