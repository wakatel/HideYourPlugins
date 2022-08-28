package me.wakatel.hideyourplugins_bukkit.Events;

import java.util.Objects;
import me.wakatel.hideyourplugins_bukkit.HideYourPlugins_Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class PluginHider implements Listener {
    @EventHandler
    public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent e) {
        Plugin plugin = HideYourPlugins_Bukkit.getPlugin(HideYourPlugins_Bukkit.class);
        for (String command : plugin.getConfig().getStringList("plugin-hider.commands")) {
            if ((e.getMessage().toLowerCase().equals("/" + command) || e.getMessage().toLowerCase().startsWith("/" + command + " ")) && !e.getPlayer().hasPermission("hyp.bypass.command") && plugin.getConfig().getBoolean("plugin-hider.enabled")) {
                e.setCancelled(true);
                String string = Objects.<String>requireNonNull(plugin.getConfig().getString("plugin-hider.message"));
                if (string.equalsIgnoreCase("none"))
                    return;
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', string));
            }
        }
    }
}
