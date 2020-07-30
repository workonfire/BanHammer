package pl.workonfire.banhammer;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import pl.workonfire.banhammer.commands.MainCommand;
import pl.workonfire.banhammer.listeners.EntityDamage;

import java.util.ArrayList;

public final class BanHammer extends JavaPlugin {
    private BanHammer instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new EntityDamage(), getInstance());
        getCommand("banhammer").setExecutor(new MainCommand());
    }

    public BanHammer getInstance() {
        return instance;
    }

    public static ItemStack getBanHammer() {
        ItemStack item = new ItemStack(Material.GOLDEN_AXE);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§f§oBans every touched player.");
        itemMeta.setLore(lore);
        itemMeta.setDisplayName("§4§lBan Hammer");
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
        return item;
    }
}
