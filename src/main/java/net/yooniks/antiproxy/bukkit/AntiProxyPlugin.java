package net.yooniks.antiproxy.bukkit;

import net.yooniks.antiproxy.bukkit.listeners.AsyncPlayerPreLoginListener;
import net.yooniks.antiproxy.bukkit.utils.CheckUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiProxyPlugin extends JavaPlugin {

    private CheckUtil checker;

    @Override
    public void onLoad(){
        checker = new CheckUtil();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.checker.loadBlockedIps();
        this.checker.loadAllowedPlayers();
        this.getServer().getPluginManager().registerEvents(
                new AsyncPlayerPreLoginListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public CheckUtil getChecker() {
        return checker;
    }
}
