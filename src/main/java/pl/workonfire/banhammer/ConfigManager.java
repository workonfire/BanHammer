package pl.workonfire.banhammer;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("ConstantConditions")
public abstract class ConfigManager {
    private static final FileConfiguration config = BanHammer.getInstance().getConfig();

    public static FileConfiguration getConfig() {
        return config;
    }

    public static String getValue(String path) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString(path));
    }
}
