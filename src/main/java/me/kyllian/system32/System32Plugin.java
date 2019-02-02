package me.kyllian.system32;

import me.kyllian.system32.commands.PlayerCommand;
import me.kyllian.system32.handlers.*;
import me.kyllian.system32.listeners.PlayerChatListener;
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
        groupHandler.loadGroups();
        //TODO: Initialize handlers
    }

    public void initializeCommands() {
        getCommand("player").setExecutor(new PlayerCommand(this));
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
}
