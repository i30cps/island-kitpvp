package dev.rotator.kitlogic.kits;

import dev.rotator.Main;
import dev.rotator.kitlogic.Kit;
import dev.rotator.util.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ChickenKit implements Kit {
    @Override
    public void apply(Player p) {

        PlayerInventory inv = p.getInventory();
        inv.setItemInOffHand(new ItemStack(Material.MOSS_BLOCK, 32));
        inv.addItem(new ItemBuilder(Material.IRON_SWORD).unbreakable(true).build());
        inv.addItem(new ItemBuilder(Material.EGG).displayname("§bBridge Egg").amount(2).build());
        inv.setHelmet(new ItemBuilder(Material.LEATHER_HELMET).displayname("§eChicken Hat").enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).color(Color.fromRGB(255, 35, 0)).build());
        inv.setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).displayname("§eChicken Shirt").enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).color(Color.fromRGB(255, 255, 255)).build());
        inv.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).displayname("§eChicken Pants").enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).color(Color.fromRGB(255, 255, 0)).build());
        inv.setBoots(new ItemBuilder(Material.LEATHER_BOOTS).displayname("§eChicken Feet").enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).color(Color.fromRGB(255, 255, 0)).build());
        inv.setItemInOffHand(new ItemStack(Material.MOSS_BLOCK, 32));
    }

    @Override
    public void onPlayerKill(Player p) {
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
        p.getInventory().addItem(new ItemStack(Material.MOSS_BLOCK, 16));
        p.getInventory().addItem(new ItemBuilder(Material.EGG).displayname("§bBridge Egg").build());
    }

    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        Player p = (Player) e.getEntity().getShooter();
        Projectile projectile = e.getEntity();
        if (!(projectile instanceof Egg egg)) return;

        new BukkitRunnable() {
            int iterations = 0;
            final int maxIterations = 50;

            @Override
            public void run() {
                if (iterations++ >= maxIterations) this.cancel();
                if (egg.isDead()) {
                    this.cancel();
                    return;
                }

                double[][] toAddArr = {{0.3, 0}, {0, 0.3}, {-0.3, 0}, {0, -0.3}, {0, 0}};
                Location location = egg.getLocation().subtract(0, 2, 0);

                Vector<Block> toRemove = new Vector<>();

                for (double[] toAdd : toAddArr) {
                    Block b = location.add(toAdd[0], 0, toAdd[1]).getBlock();
                    if (b.getType().equals(Material.AIR)) {
                        b.setType(Material.MOSS_BLOCK);
                        toRemove.add(b);
                    }
                }

                // start block disappear timer
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Block b : toRemove) {
                            b.setType(Material.AIR);
                        }
                    }
                }.runTaskLater(Main.getPl(), 240L); // blocks disappear after 12 seconds
            }

        }.runTaskTimer(Main.getPl(), 1L, 1L);
    }
}
