package com.example.vetoverse;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class AntiSpeed  extends JavaPlugin implements Listener{

    @Override
    public void onEnable(){
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        double speed = player.getWalkSpeed();

        if(speed > 0.5){
            KickAndBan(player,"Speed");
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
