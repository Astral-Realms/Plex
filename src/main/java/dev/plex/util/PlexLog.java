package dev.plex.util;

import dev.plex.Plex;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class PlexLog
{
    public static void log(String message, Object... strings)
    {
        for (int i = 0; i < strings.length; i++)
        {
            if (message.contains("{" + i + "}"))
            {
                message = message.replace("{" + i + "}", strings[i].toString());
            }
        }
        Bukkit.getConsoleSender().sendMessage(String.format(ChatColor.YELLOW + "[Plex] " + ChatColor.GRAY + "%s", message));
    }

    public static void error(String message, Object... strings)
    {
        for (int i = 0; i < strings.length; i++)
        {
            if (message.contains("{" + i + "}"))
            {
                message = message.replace("{" + i + "}", strings[i].toString());
            }
        }
        Bukkit.getConsoleSender().sendMessage(String.format(ChatColor.RED + "[Plex Error] " + ChatColor.GOLD + "%s", message));
    }

    public static void debug(String message, Object... strings)
    {
        for (int i = 0; i < strings.length; i++)
        {
            if (message.contains("{" + i + "}"))
            {
                message = message.replace("{" + i + "}", strings[i].toString());
            }
        }
        if (Plex.get().config.getBoolean("debug"))
        {
            Bukkit.getConsoleSender().sendMessage(String.format(ChatColor.DARK_PURPLE + "[Plex Debug] " + ChatColor.GOLD + "%s", message));
        }
    }
}
