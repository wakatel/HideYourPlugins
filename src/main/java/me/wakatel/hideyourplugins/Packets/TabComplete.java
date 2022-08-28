package me.wakatel.hideyourplugins.Packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.Plugin;

public class TabComplete extends PacketAdapter {
    public TabComplete(Plugin plugin, PacketType[] types) {
        super(plugin, types);
    }

    public void onPacketReceiving(PacketEvent e) {
        PacketType packetType = e.getPacketType();
        if (packetType.equals(PacketType.Play.Client.TAB_COMPLETE))
            e.setCancelled(true);
    }
}
