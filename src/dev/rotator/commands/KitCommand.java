package dev.rotator.commands;

import dev.rotator.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender;

        if (strings.length != 1) return false;

        Main.getPl().getKitManager().assignKit(p, strings[0]);

        return true;
    }
}
