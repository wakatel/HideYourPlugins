package me.wakatel.hideyourplugins.Events;

import me.wakatel.hideyourplugins.HideYourPlugins;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class SyntaxBlocker implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Plugin plugin = HideYourPlugins.getPlugin(HideYourPlugins.class);
        if (e.getMessage().split(" ")[0].contains(":") && !e.getPlayer().hasPermission("hyp.bypass.syntax") && plugin.getConfig().getBoolean("syntax-blocker.enabled")) {
            e.setCancelled(true);
            String string = Objects.<String>requireNonNull(plugin.getConfig().getString("syntax-blocker.message"));
            if (string.equalsIgnoreCase("none"))
                return;
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', string));
        }
    }
}
