package net.yooniks.antiproxy.bukkit.listeners;

import net.yooniks.antiproxy.bukkit.AntiProxyPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncPlayerPreLoginListener implements Listener{

    @EventHandler
    public void onConnect(AsyncPlayerPreLoginEvent e){
        final String address = e.getAddress().toString();
        final String playerName = e.getName();
        final AntiProxyPlugin main = AntiProxyPlugin.getPlugin(AntiProxyPlugin.class);
        if (main.getChecker().hasProxy(address, playerName)){
            final String text = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("message-kick"));
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, text);
        }
    }
}
