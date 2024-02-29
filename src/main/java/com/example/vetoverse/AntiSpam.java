package com.example.vetoverse;

import java.util.HashMap;
import java.util.Objects;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiSpam extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
    }
    private static final HashMap<String,Integer> lc = new HashMap<>();
    private static final HashMap<String,Integer> spam = new HashMap<>();
    private static int ctime = (int)(System.currentTimeMillis() / 1000);

    @EventHandler
    public void AntiSpamCommand(PlayerCommandPreprocessEvent e){
        Player player = e.getPlayer();
        String name = player.getName();
        ctime = (int)(System.currentTimeMillis() / 1000);

        if(lc.containsKey(name)){
            if (!spam.containsKey(name)) {
                spam.put(name, 0);
                lc.put(name, ctime);
            } else if (ctime - lc.get(name) > 5) {
                spam.remove(name);
                lc.put(name, ctime);
            } else if (ctime - lc.get(name) <= 3) {
                int i = spam.get(name) + 1;
                spam.put(name, i);
                lc.put(name, ctime);
            }

            if(spam.containsKey(name) && spam.get(name) > 15){
               KickAndBan(player,"spam");
            }
        }
        else{
            lc.put(name,ctime);
        }
    }

    @EventHandler
    public void onAntiSpamChat(PlayerChatEvent e){
        Player player = e.getPlayer();
        String name = player.getName();
        ctime = (int)(System.currentTimeMillis() / 1000);

        if(lc.containsKey(name)){
            if (!spam.containsKey(name)) {
                spam.put(name, 0);
                lc.put(name, ctime);
            } else if (ctime - lc.get(name) > 5) {
                spam.remove(name);
                lc.put(name, ctime);
            } else if (ctime - lc.get(name) <= 3) {
                int i = spam.get(name) + 1;
                spam.put(name, i);
                lc.put(name, ctime);
            }

            if(spam.containsKey(name) && spam.get(name) > 15){
                KickAndBan(player,"spam");
            }
        }
        else{
            lc.put(name,ctime);
        }
    }

    private void KickAndBan(Player player,String reason){
        //プレイヤーをサーバーから蹴った後、接続禁止処理を実行
        player.kickPlayer(getBannedMessage(reason));
        Bukkit.getBanList(BanList.Type.PROFILE).addBan(player.getName(),getBannedMessage(reason),null,null);
        Bukkit.getBanList(BanList.Type.IP).addBan(Objects.requireNonNull(player.getAddress()).getHostString(), getBannedMessage(reason),null,null);
    }

    public String getBannedMessage(String reason){
        //チート検出システムによるBANメッセージの作成
        return "§l§4[チート検出システム]\n"+"§l§6あなたは自動システムに接続禁止処理が施されました。\n"
                +"§l§e[理由]"+reason+"\n"+"§l§8 もしこれが間違いであれば報告して下さい。\n"+ "§l§8Discode : URL ";
    }
}
