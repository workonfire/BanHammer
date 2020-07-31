package pl.workonfire.banhammer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.workonfire.banhammer.BanHammer;
import pl.workonfire.banhammer.ConfigManager;

import static pl.workonfire.banhammer.BanHammer.PREFIX;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String permission = "banhammer.get";
            if (player.hasPermission(permission)) {
                player.getInventory().addItem(BanHammer.getBanHammer());
                player.sendMessage(PREFIX + ConfigManager.getValue("messages.get-message"));
            }
            else player.sendMessage(PREFIX + ConfigManager.getValue("messages.missing-permission")
                    .replaceAll("\\{PERMISSION}", permission));
        }
        else sender.sendMessage(PREFIX + ConfigManager.getValue("messages.cannot-execute-from-console"));
        return true;
    }
}
