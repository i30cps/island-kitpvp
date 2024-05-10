package dev.rotator.kitlogic;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;

public abstract class Kit {
    protected int killExperience = 20;

    private String id;
    public abstract String getID();
    public abstract void apply(Player p);

    public void cleanUp(Player p) {}
    public void onPlayerKill(Player p) {}
    public void onProjectileLaunch(ProjectileLaunchEvent e) {}
    public boolean isNullKit() { return false; }
    public void preApply(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        // TODO: Teleport player to the map.
        p.getInventory().clear();
        p.setSaturation(20);
        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());
    }
    public void onPlayerInteract(PlayerInteractEvent e) {}
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {}
    public void onBlockPlace(BlockPlaceEvent e) {}

}