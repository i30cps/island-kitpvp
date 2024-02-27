package dev.rotator.kitlogic.kits;

import dev.rotator.Main;
import dev.rotator.kitlogic.Kit;
import dev.rotator.util.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.UUID;

public class AssassinKit implements Kit {
    public void apply(Player p) {
        PlayerInventory inv = p.getInventory();

        inv.setHelmet(new ItemBuilder(Material.LEATHER_HELMET).color(Color.fromRGB(0, 0, 0)).unbreakable(true).build());
        inv.setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).color(Color.fromRGB(0, 0, 0)).unbreakable(true).build());
        inv.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).color(Color.fromRGB(0, 0, 0)).unbreakable(true).build());
        inv.setBoots(new ItemBuilder(Material.LEATHER_BOOTS).color(Color.fromRGB(0, 0, 0)).unbreakable(true).build());

        inv.addItem(new ItemBuilder(Material.STONE_SWORD).unbreakable(true).enchant(Enchantment.DAMAGE_ALL, 2).build());
        inv.addItem(new ItemBuilder(Material.WITHER_SKELETON_SKULL).displayname("§8Shadowstep (Right-click)").lore("§7Teleports to the nearest player.").build());


        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, -1, 1, false, false));


        inv.setItemInOffHand(new ItemStack(Material.MOSS_BLOCK, 32));
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent e) {
        Material type = e.getBlock().getType();
        if (type.equals(Material.WITHER_SKELETON_SKULL) || type.equals(Material.WITHER_SKELETON_WALL_SKULL))
            e.setCancelled(true);
    }

    @Override
    public void onPlayerKill(Player p) {
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
        p.getInventory().addItem(new ItemStack(Material.MOSS_BLOCK, 16));

        p.getInventory().addItem(new ItemBuilder(Material.WITHER_SKELETON_SKULL)
                .displayname("§8Shadowstep (Right-click)")
                .lore("§7Teleports to the nearest player.").build());

    }

    @Override
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        shadowStepAbility(p, p.getInventory().getItem(p.getInventory().getHeldItemSlot()));
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            shadowStepAbility(e.getPlayer(), e.getItem());
        }
    }

    private void shadowStepAbility(Player p, ItemStack item) {
        if (item == null) return;
        if (!item.getType().equals(Material.WITHER_SKELETON_SKULL)) return;

        // now, teleport the player

        UUID closestPlayer = null;
        double lowestDist = -1;

        for (UUID uuid : Main.getKitManager().playersWithKits) {
            if (uuid.equals(p.getUniqueId())) continue;

            Player candidate = Bukkit.getPlayer(uuid);

            double newDist = candidate.getLocation().distanceSquared(p.getLocation());
            if (lowestDist == -1d || newDist < lowestDist) {
                lowestDist = newDist;
                closestPlayer = uuid;
            }
        }

        if (closestPlayer == null) {
            p.sendMessage("§cThere are no other players to teleport to!");
            return;
        }

        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1, 0, false, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1, 0, false, false));

        p.teleport(Objects.requireNonNull(Bukkit.getPlayer(closestPlayer)));

        PlayerInventory inv = p.getInventory();

        ItemStack is = inv.getItem(inv.getHeldItemSlot());
        if (is != null && is.getType().equals(Material.WITHER_SKELETON_SKULL)) {
            is.setAmount(is.getAmount() - 1);
            inv.setItem(inv.getHeldItemSlot(), is);
        } else { // item may be in offhand or main hand
            is = inv.getItemInOffHand();
            is.setAmount(is.getAmount() - 1);
            inv.setItemInOffHand(is);
        }


    }
}