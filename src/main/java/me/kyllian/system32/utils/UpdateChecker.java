package me.kyllian.system32.utils;

import me.kyllian.system32.System32Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker {

    private System32Plugin plugin;
    private int project;
    private URL url;
    private String version;
    private boolean setup = true;

    private boolean update;
    private String newVersion;

    public UpdateChecker(System32Plugin plugin, int project) {
        this.plugin = plugin;
        this.project = project;
        version = plugin.getDescription().getVersion();

        try {
            url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + project);
            check();
        } catch (MalformedURLException exception) {
            setup = false;
            exception.printStackTrace();
        }
    }

    public String getResourceURL() {
        return "https://spigotmc.org/recources/" + project;
    }

    public void check() {
        if (!setup) return;
        new BukkitRunnable() {
            public void run() {
                try {
                    URLConnection connection = url.openConnection();
                    newVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                    update = !version.equals(newVersion);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    setup = false;
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    public String getUpdateMessage() {
        if (update) return plugin.getMessageHandler().getUpdateFoundMessage(version, newVersion);
        else return plugin.getMessageHandler().getUpdateNotFoundMessage();
    }
}
