package net.yooniks.antiproxy.bungee.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.yooniks.antiproxy.bungee.AntiProxyPlugin;

public class PlayerPreLoginListener implements Listener {

    @EventHandler
    public void onConnect(PreLoginEvent e){
        final String address = e.getConnection().getAddress().toString();
        final String playerName = e.getConnection().getName();
        final AntiProxyPlugin main = AntiProxyPlugin.getPlugin();
        if (main.getChecker().hasProxy(address, playerName)){
            final String text = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("message-kick"));
            e.setCancelled(true);
            e.setCancelReason(text);
        }
    }
}
