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
import pl.workonfire.banhammer.ConfigManager;

import static pl.workonfire.banhammer.BanHammer.PREFIX;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            Entity damager = event.getDamager();
            if (damager instanceof Player) {
                Player banner = (Player) damager;
                if (banner.getInventory().getItemInMainHand().isSimilar(BanHammer.getBanTool())
                        && event.getEntityType() == EntityType.PLAYER
                        && banner.hasPermission("banhammer.use")) {

                    Player victim = (Player) event.getEntity();

                    if (!victim.hasPermission("banhammer.exempt")) {
                        victim.getWorld().strikeLightning(victim.getLocation());
                        String banMessage = ConfigManager.getValue("messages.ban-message")
                                .replaceAll("\\{PLAYER}", victim.getDisplayName());
                        Bukkit.broadcast(banMessage, "banhammer.notify");
                        BanList banList = Bukkit.getBanList(BanList.Type.NAME);
                        String banReason = ConfigManager.getValue("messages.ban-reason")
                                .replaceAll("\\{PLAYER}", banner.getDisplayName());
                        banList.addBan(victim.getName(), banReason, null, banner.getName());
                        victim.kickPlayer(banReason);
                    }
                    else banner.sendMessage(PREFIX + ConfigManager.getValue("messages.cannot-ban"));
                }
            }
        }
    }
}
