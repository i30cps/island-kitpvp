package dev.rotator.eventhandlers;

import dev.rotator.Main;
import dev.rotator.kitlogic.Kit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

public class KitEventsManager implements Listener {
    public KitEventsManager() {

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        Player p = e.getEntity();
        if (killer != null) {
            Main.getKitManager().getKitOfPlayer(killer).onPlayerKill(killer);
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        ProjectileSource source = e.getEntity().getShooter();
        if (!(source instanceof Player)) return;

        Main.getKitManager().getKitOfPlayer((Player) source).onProjectileLaunch(e);
    }

    @EventHandler
    public void onPlayerPlace (BlockPlaceEvent e) {
        // check if the player has a kit. if so, remove the block later.
        if (Main.getKitManager().playerHasKit(e.getPlayer())) {
            // remove the block after 10 seconds:

            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.AIR);
                }
            }.runTaskLater(Main.getPl(), 200L);
        }

    }
}
