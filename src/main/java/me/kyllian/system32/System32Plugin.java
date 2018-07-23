package me.kyllian.system32;

import me.kyllian.system32.utils.MessageHandler;
import me.kyllian.system32.utils.PermissionsHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class System32Plugin extends JavaPlugin {

    private MessageHandler messageHandler;
    private PermissionsHandler permissionsHandler;

    public void onEnable() {
        messageHandler = new MessageHandler(this);
        permissionsHandler = new PermissionsHandler(this);
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public PermissionsHandler getPermissionsHandler() {
        return permissionsHandler;
    }
}
