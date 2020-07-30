package pl.workonfire.banhammer.listeners;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import pl.workonfire.banhammer.BanHammer;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            Entity damager = event.getDamager();
            if (damager instanceof Player) {
                Player banner = (Player) damager;
                if (banner.getInventory().getItemInMainHand().isSimilar(BanHammer.getBanHammer())
                        && event.getEntityType() == EntityType.PLAYER) {

                    Player victim = (Player) event.getEntity();
                    victim.getWorld().strikeLightning(victim.getLocation());

                    Bukkit.broadcast(victim.getDisplayName() + " §cwas banned from the server using a §4§lBAN HAMMER§c!",
                            "banhammer.notify");

                    BanList banList = Bukkit.getBanList(BanList.Type.NAME);
                    String banReason = "§cYou were §4§lBANNED §cusing a §4§lBAN HAMMER§c! By: §r" + banner.getDisplayName();
                    banList.addBan(victim.getName(), banReason, null, banner.getName());
                    victim.kickPlayer(banReason);
                }
            }
        }
    }
}
