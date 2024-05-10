package dev.rotator.commands.admin;

import dev.rotator.Main;
import dev.rotator.kitlogic.Kit;
import dev.rotator.kitlogic.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetExperienceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.isOp()) return false;

        if (strings.length < 1) {
            commandSender.sendMessage("wrong usage (ask rotator)");
            return false;
        }

        if (strings.length == 1) {
            Player p = Bukkit.getPlayer(strings[0]);
            if (p == null) {
                commandSender.sendMessage("player does not exist");
                return false;
            }

            commandSender.sendMessage("§aPlayer's xp is §e"
                    + Main.getDefaultPlayerdataManager().getGeneralExperience(p.getUniqueId()));
        } else if (strings.length == 2) {
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

            commandSender.sendMessage("§aPlayer's kit xp is §e"
                    + Main.getDefaultPlayerdataManager().getKitExperience(p.getUniqueId(), kit));
        } else return false;

        return true;
    }
}
