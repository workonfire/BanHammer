package pl.workonfire.banhammer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import pl.workonfire.banhammer.commands.MainCommand;
import pl.workonfire.banhammer.listeners.EntityDamage;

import java.util.ArrayList;

@SuppressWarnings("ConstantConditions")
public final class BanHammer extends JavaPlugin {
    private BanHammer instance;
    public static final String PREFIX = "§f§k[§r§4§lBanHammer§f§k]§r ";

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
        return item;
    }
}
