package com.example.vetoverse;

import org.bukkit.entity.Player;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.Objects;

public class AntiFly implements Listener {
    @EventHandler
    public void Flying(PlayerToggleFlightEvent e){
        Player player = e.getPlayer();
        VetoVerse vetoverse = (VetoVerse) player.getServer().getPluginManager().getPlugin("VetoVerse");

        if(player.isOp()){
            //オペレーター権限をもっているプレイヤーのイベント
            return;
        }
        if(e.isFlying()){
            //オペレーター権限がないプレイヤーのイベント
            KickAndBan(player,"Fly");
        }
        else{
            KickAndBan(player,"Fly");
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
