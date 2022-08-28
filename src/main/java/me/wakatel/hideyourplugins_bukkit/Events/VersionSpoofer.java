package me.wakatel.hideyourplugins_bukkit.Events;

import me.wakatel.hideyourplugins_bukkit.HideYourPlugins_Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class VersionSpoofer implements Listener {
    @EventHandler
    public void execute(PlayerCommandPreprocessEvent e) {
        Plugin plugin = HideYourPlugins_Bukkit.getPlugin(HideYourPlugins_Bukkit.class);
        Player player = e.getPlayer();
        String command = e.getMessage().split(" ")[0];
        if (e.isCancelled())
            return;
        if (!player.hasPermission("hyp.bypass.version") && command.matches("(?i)/ver|/version|/bukkit:ver|/bukkit:version") && plugin.getConfig().getBoolean("version-spoofer.enabled")) {
            e.setCancelled(true);
            String string = Objects.<String>requireNonNull(plugin.getConfig().getString("version-spoofer.message"));
            if (string.equalsIgnoreCase("none"))
                return;
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', string));
        }
    }
}
