package me.kyllian.system32;

import me.kyllian.system32.handlers.*;
import org.bukkit.plugin.java.JavaPlugin;

public class System32Plugin extends JavaPlugin {

    // Handlers
    private ConfigurationHandler configurationHandler;
    private DataHandler dataHandler;
    private MessageHandler messageHandler;
    private PlayerDataHandler playerHandler;
    private GroupHandler groupHandler;

    public void onEnable() {
        initializeHandlers();
    }

    public void initializeHandlers() {
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        configurationHandler = new ConfigurationHandler(this);
        dataHandler = new DataHandler(this);
        messageHandler = new MessageHandler(this);
        playerHandler = new PlayerDataHandler(this);
        groupHandler = new GroupHandler(this);
        groupHandler.loadGroups();
        //TODO: Initialize handlers
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
}
