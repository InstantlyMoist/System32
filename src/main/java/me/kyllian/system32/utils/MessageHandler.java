package me.kyllian.system32.utils;

import me.kyllian.system32.System32Plugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageHandler {

    private System32Plugin plugin;
    private File file;
    private FileConfiguration fileConfiguration;

    public MessageHandler(System32Plugin plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "messages.yml");
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void reloadMessages() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void saveMessages() {
        try {
            fileConfiguration.save(file);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public String getInvalidArgumentMessage() {
        return MessageUtils.colorTranslate(fileConfiguration.getString("InvalidArgument"))
    }

    public String getInvalidPlayerMessage() {
        return MessageUtils.colorTranslate(fileConfiguration.getString("InvalidPlayer"));
    }

    public String getPlayerMessage() {
        return MessageUtils.colorTranslate(fileConfiguration.getString("MustBeAPlayer"));
    }

    public String getNoPermissionMessage() {
        return MessageUtils.colorTranslate(fileConfiguration.getString("NoPermission"));
    }

    public String getNoTargetBlockMessgae(boolean others) {
        return MessageUtils.colorTranslate(fileConfiguration.getString(others ? "NoTargetBlockOther" : "NoTargetBlock"));
    }
}
