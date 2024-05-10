package dev.rotator.commands;

import dev.rotator.Main;
import dev.rotator.kitlogic.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player p)) {
            return false;
        }

        if (strings.length != 1) {
            StringBuilder allKits = new StringBuilder();
            for (String kit : KitManager.getKitManager().getAvailableKits()) allKits.append(kit).append(" ");
            p.sendMessage("§7Available kits: " + allKits.toString());
            return false;
        }

        Main.getPl().getKitManager().assignKit(p, strings[0]);

        return true;
    }
}
