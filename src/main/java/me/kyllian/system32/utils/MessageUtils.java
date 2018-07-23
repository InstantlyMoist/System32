package me.kyllian.system32.utils;

import org.bukkit.ChatColor;

public class MessageUtils {

    public static String colorTranslate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
