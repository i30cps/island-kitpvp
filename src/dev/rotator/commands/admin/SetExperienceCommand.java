package dev.rotator.commands.admin;

import dev.rotator.Main;
import dev.rotator.kitlogic.Kit;
import dev.rotator.kitlogic.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetExperienceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.isOp()) return false;

        if (strings.length < 2) {
            commandSender.sendMessage("wrong usage (ask rotator)");
            return false;
        }

        if (strings.length == 2) {
            Player p = Bukkit.getPlayer(strings[0]);
            if (p == null) {
                commandSender.sendMessage("player does not exist");
                return false;
            }
            int xp = Main.getDefaultPlayerdataManager().getGeneralExperience(p.getUniqueId());
            try {
                xp = Integer.parseInt(strings[1]);
            } catch (Exception e) {
                commandSender.sendMessage("Bad xp number");
                return false;
            }

            Main.getDefaultPlayerdataManager().setGeneralExperience(p.getUniqueId(), xp);
            commandSender.sendMessage("§aXp successfully set!");
        } else if (strings.length == 3) {
            Player p = Bukkit.getPlayer(strings[0]);
            if (p == null) {
                commandSender.sendMessage("player does not exist");
                return false;
            }
            Kit kit = KitManager.getKitByID(strings[1]);
            if (kit.isNullKit()) {
                commandSender.sendMessage("kit does not exist");
                return false;
            }

            int xp = Main.getDefaultPlayerdataManager().getKitExperience(p.getUniqueId(), kit);
            try {
                xp = Integer.parseInt(strings[2]);
            } catch (Exception e) {
                commandSender.sendMessage("Bad xp number");
                return false;
            }

            Main.getDefaultPlayerdataManager().setKitExperience(p.getUniqueId(), kit, xp);
            commandSender.sendMessage("§aXp successfully set!");
        } else return false;

        return true;
    }
}
