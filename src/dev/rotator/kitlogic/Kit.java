package dev.rotator.kitlogic;

import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Kit {
    void apply(Player p);
    default void cleanUp(Player p) {}
    default void onPlayerKill(Player p) {}
    default void onProjectileLaunch(ProjectileLaunchEvent e) {}
    default boolean isNullKit() { return false; }
    default void preApply(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        // TODO: Teleport player to the map.
        p.getInventory().clear();
        p.setSaturation(20);

    }
    default void onPlayerInteract(PlayerInteractEvent e) {}
    default void onPlayerInteractEntity(PlayerInteractEntityEvent e) {}
    default void onBlockPlace(BlockPlaceEvent e) {}
}