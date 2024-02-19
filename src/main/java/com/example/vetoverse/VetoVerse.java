package com.example.vetoverse;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

//設定

public final class VetoVerse extends JavaPlugin{

    @Override
    public void onEnable() {
        // Plugin startup logic
      getLogger().info("アンチチートプラグイン 'Veto Verse'　が起動しました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("アンチチートプラグイン 'Veto Verse' が停止しました。");
    }
}


