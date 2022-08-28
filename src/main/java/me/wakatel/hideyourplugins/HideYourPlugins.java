package me.wakatel.hideyourplugins;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import me.wakatel.hideyourplugins.Commands.ReloadCommand;
import me.wakatel.hideyourplugins.Events.PluginHider;
import me.wakatel.hideyourplugins.Events.SyntaxBlocker;
import me.wakatel.hideyourplugins.Events.VersionSpoofer;
import me.wakatel.hideyourplugins.Packets.TabComplete;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class HideYourPlugins extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger logger = Logger.getLogger("HYP");
        int pluginId = 16276;
        Metrics metrics = new Metrics(this, pluginId);
        if (!(new File(getDataFolder(), "config.yml")).exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
            getCommand("hypreload").setExecutor(new ReloadCommand());
        }
        getServer().getPluginManager().registerEvents(new PluginHider(), this);
        getServer().getPluginManager().registerEvents(new SyntaxBlocker(), this);
        getServer().getPluginManager().registerEvents(new VersionSpoofer(), this);
        if (getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
            logger.info("ProtocolLib detected.");
            logger.info("Registering packet listener...");
            ProtocolLibrary.getProtocolManager().addPacketListener(new TabComplete(this, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE }));
            logger.info("Register packet complete!");
            logger.info("+-----------------------------+");
            logger.info("HideYourPlugins 1.2");
            logger.info("Thank you for using this plugin!");
            logger.info("Please leave a rating :)");
            logger.info("https://www.spigotmc.org/resources/hideyourplugins-advanced-plugins-hider.104859/");
            logger.info("by. wakatel");
            logger.info("+-----------------------------+");
        }else {
            logger.warning("Could not find ProtocolLib! This plugin is required.");
            logger.warning("Download ProtocolLib here https://github.com/dmulloy2/ProtocolLib/releases");
        }
    }
}
