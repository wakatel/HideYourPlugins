package me.wakatel.hideyourplugins.Commands;

import me.wakatel.hideyourplugins.HideYourPlugins;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ReloadCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Plugin plugin = HideYourPlugins.getPlugin(HideYourPlugins.class);
        if (sender.hasPermission("hyp.reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.prefix") + plugin.getConfig().getString("plugin.reload-message")));
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.prefix") + plugin.getConfig().getString("plugin.no-perm")));
        }
        return true;
    }
}
