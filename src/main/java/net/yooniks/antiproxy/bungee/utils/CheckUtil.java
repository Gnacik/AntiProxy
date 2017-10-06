package net.yooniks.antiproxy.bungee.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.yooniks.antiproxy.bungee.AntiProxyPlugin;
import net.yooniks.antiproxy.enums.PluginType;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class CheckUtil {

    private List<String> blocked_ips = new LinkedList<>();
    private List<String> allowed_players = new LinkedList<>();

    private PluginType pluginType;

    public void setPluginType(PluginType pluginType){
        this.pluginType = pluginType;
    }

    public void loadBlockedIps() {
        final AntiProxyPlugin main = AntiProxyPlugin.getPlugin();
        this.blocked_ips.addAll(main.getConfig().getStringList("blocked-ips"));
    }

    public void loadAllowedPlayers(){
        final AntiProxyPlugin main = AntiProxyPlugin.getPlugin();
        for (String playerName : main.getConfig().getStringList("allowed-players")){
            this.allowed_players.add(playerName.toLowerCase());
        }
    }

    public boolean hasProxy(String address, String playerName){
        if (this.allowed_players.contains(playerName.toLowerCase())) return false;
        if (this.blocked_ips.contains(address)) return true;
        ObjectMapper objectMapper = new ObjectMapper();
        boolean hasProxy = false;
        try {
            if (objectMapper.readTree(new URL("http://proxycheck.io/v1/"+address+"&key=111111-222222-333333-444444&vpn=1&asn=1&node=1&time=1&tag=forum%20signup%20page"))
                    .get("proxy").toString().contains("yes")){
                hasProxy = true;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return hasProxy;
    }
}
