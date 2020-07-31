package pl.workonfire.banhammer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import pl.workonfire.banhammer.commands.MainCommand;
import pl.workonfire.banhammer.listeners.EntityDamage;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public final class BanHammer extends JavaPlugin {
    private static BanHammer instance;
    public static final String PREFIX = "§f[§r§4§lBanHammer§f]§r ";

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new EntityDamage(), getInstance());
        getCommand("banhammer").setExecutor(new MainCommand());
        getInstance().saveDefaultConfig();
    }

    public static BanHammer getInstance() {
        return instance;
    }

    public static ItemStack getBanHammer() {
        Material material = Material.getMaterial(ConfigManager.getValue("item.material"));
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        List<String> lore = ConfigManager.getConfig().getStringList("item.lore");
        String itemName = ConfigManager.getValue("item.name");
        if (lore != null) {
            for (String loreLine : lore) itemLore.add(ChatColor.translateAlternateColorCodes('&', loreLine));
            itemMeta.setLore(itemLore);
        }
        if (itemName != null) itemMeta.setDisplayName(itemName);
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }
}
