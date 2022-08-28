package me.wakatel.hideyourplugins_bukkit;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import me.wakatel.hideyourplugins_bukkit.Commands.ReloadCommand;
import me.wakatel.hideyourplugins_bukkit.Events.PluginHider;
import me.wakatel.hideyourplugins_bukkit.Events.SyntaxBlocker;
import me.wakatel.hideyourplugins_bukkit.Events.VersionSpoofer;
import me.wakatel.hideyourplugins_bukkit.Packets.TabComplete;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideYourPlugins_Bukkit extends JavaPlugin {

    @Override
    public void onEnable() {
        int pluginId = 16276;
        Metrics metrics = new Metrics(this, pluginId);
        getConfig().options().copyDefaults();
        saveConfig();
        getCommand("hypreload").setExecutor(new ReloadCommand());
        getServer().getPluginManager().registerEvents(new PluginHider(), this);
        getServer().getPluginManager().registerEvents(new SyntaxBlocker(), this);
        getServer().getPluginManager().registerEvents(new VersionSpoofer(), this);
        if (getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
            getLogger().info("ProtocolLib detected.");
            getLogger().info("Registering packet listener...");
            ProtocolLibrary.getProtocolManager().addPacketListener(new TabComplete(this, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE }));
            getLogger().info("Register packet complete!");
            getLogger().info(ChatColor.GREEN + "Thank you for using this plugin!");
            getLogger().info(ChatColor.GREEN + "Please leave a rating :)");
            getLogger().info(ChatColor.GREEN + "https://www.spigotmc.org/resources/hideyourplugins-advanced-plugins-hider.104859/");
            getLogger().info(ChatColor.GREEN + "by. wakatel");
        }else {
            getLogger().warning("Could not find ProtocolLib! This plugin is required.");
            getLogger().warning("Download ProtocolLib here https://github.com/dmulloy2/ProtocolLib/releases");
        }
    }
}
