package pl.workonfire.banhammer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.workonfire.banhammer.BanHammer;

public class MainCommand implements CommandExecutor {
    private static final String PREFIX = "§7[§4§lBanHammer§7] ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String permission = "banhammer.get";
            if (player.hasPermission(permission)) {
                player.getInventory().addItem(BanHammer.getBanHammer());
                player.sendMessage(PREFIX + "§aYou were given a ban hammer. Use it with caution!");
            }
            else player.sendMessage(PREFIX + "§cYou need the following permission in order to execute this command: §7§o" + permission);
        }
        else sender.sendMessage(PREFIX + "§cYou have to be a player in order to execute this command.");
        return true;
    }
}
