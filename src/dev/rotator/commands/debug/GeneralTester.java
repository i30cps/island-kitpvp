package dev.rotator.commands.debug;

import dev.rotator.Main;
import dev.rotator.kitlogic.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GeneralTester implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        commandSender.sendMessage("you are smelly stinky poopoo");
        if (!commandSender.isOp()) return true;

        if (!(commandSender instanceof Player)) return true;
        Player p = (Player) commandSender;

        if (strings.length == 0) {
            return true;
        }

        if (strings[0].equals("basickit")) {
            Main.getPl().getKitManager().assignKit(p, "basic");
        } else if (strings[0].equals("huh")) {

        } else {
            p.sendMessage("idk");
        }

        return true;
    }
}