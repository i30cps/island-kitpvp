package dev.rotator.kitlogic.unlockables;

import dev.rotator.Main;
import dev.rotator.kitlogic.Kit;
import dev.rotator.kitlogic.KitManager;
import dev.rotator.kitlogic.KitUnlockable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ArrowKillEffect extends KitUnlockable {
    public ArrowKillEffect(Kit kit) {
        super(kit);
    }

    @Override
    public String getID() {
        return "ArrowKillEffect";
    }

    @Override
    public boolean unlocked(Player p) {
        if (!(Main.getDefaultKitManager().getKitOfPlayer(p) == this.kit)) return false;
        return Main.getDefaultPlayerdataManager().getKitExperience(
                p.getUniqueId(), KitManager.getKitManager().getKitOfPlayer(p)) > 100;
    }

    public void onPlayerKill(Player p) {
        float[][] dirs = {{1, 0.2f, 0}, {0, 0.2f, 1}, {-1, 0.2f, 0}, {0, 0.2f, -1},
                {1, 0.2f, 1}, {-1, 0.2f, -1}, {1, 0.2f, -1}, {-1, 0.2f, 1}};
        for (float[] direction : dirs) {
            Vector d = new Vector(direction[0], direction[1], direction[2]);
            d.rotateAroundY(Math.random() * 45);
            Arrow arrow = p.getWorld().spawnArrow(p.getLocation().add(d).add(0, 1, 0), d, 1.3F, 0);
            arrow.setShooter(p);
        }

    }
}
