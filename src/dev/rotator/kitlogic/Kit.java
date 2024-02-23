package dev.rotator.kitlogic;

import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;

public interface Kit {
    void apply(Player p);
    default void cleanUp(Player p) {}
    default void onPlayerKill(Player p) {}
    default void onProjectileLaunch(ProjectileLaunchEvent e) {}
    default boolean isNullKit() { return false; }
}