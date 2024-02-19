package com.example.vetoverse;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class AntiReach implements Listener{

    @EventHandler
    public void AntiReachHack(EntityDamageEvent e){
        if(e instanceof EntityDamageByEntityEvent){
            EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) e;
            if(damageEvent.getEntity() instanceof Player){
                Player victim = (Player) damageEvent.getEntity();
                if(damageEvent.getDamager() instanceof Player){
                    Player attacker = (Player) damageEvent.getDamager();
                    if(attacker.getGameMode() == GameMode.CREATIVE || isBow(attacker.getInventory().getItemInMainHand()) || isCrossBow(attacker.getInventory().getItemInMainHand()) || isTrident(attacker.getInventory().getItemInMainHand())){
                        return;
                    }
                    if(attacker.getLocation().distance(victim.getLocation()) >= 4){
                        e.setCancelled(true);
                        attacker.sendMessage("§l§4[チート検出システム]\n"+"§l§6リーチハックが検出されました。\n");
                    }
                }
            }
        }
    }
    private boolean isBow(ItemStack item) {
        return item != null && item.getType() == Material.BOW;
    }

    private boolean isCrossBow(ItemStack item){
        return item != null && item.getType() == Material.CROSSBOW;
    }

    private boolean isTrident(ItemStack item){
        return item != null && item.getType() == Material.TRIDENT;
    }
}
