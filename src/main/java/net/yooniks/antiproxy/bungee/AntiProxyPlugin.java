package net.yooniks.antiproxy.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.yooniks.antiproxy.bungee.listeners.PlayerPreLoginListener;
import net.yooniks.antiproxy.bungee.utils.CheckUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class AntiProxyPlugin extends Plugin {

    private CheckUtil checker;
    private static AntiProxyPlugin plugin;

    private Configuration configuration;

    @Override
    public void onLoad(){
        plugin = this;
        checker = new CheckUtil();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.checker.loadBlockedIps();
        this.checker.loadAllowedPlayers();
        this.getProxy().getPluginManager().registerListener(
                this, new PlayerPreLoginListener());
    }

    @Override
    public void onDisable() {
    }

    private void saveDefaultConfig(){
        if (!this.getDataFolder().exists())
            this.getDataFolder().mkdir();

        final File file = new File(getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static AntiProxyPlugin getPlugin() {
        return plugin;
    }

    public Configuration getConfig() {
        return configuration;
    }

    public CheckUtil getChecker() {
        return checker;
    }
}
