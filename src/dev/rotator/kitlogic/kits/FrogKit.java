package dev.rotator.kitlogic.kits;

import dev.rotator.kitlogic.Kit;
import dev.rotator.util.items.ItemBuilder;
import dev.rotator.util.items.ItemUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FrogKit implements Kit {
    public void apply(Player p) {
        PlayerInventory inv = p.getInventory();

        ItemUtil.smartGive(p, new ItemBuilder(Material.DIAMOND_HELMET).unbreakable(true).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        ItemUtil.smartGive(p, new ItemBuilder(Material.LEATHER_CHESTPLATE).unbreakable(true).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).color(Color.fromRGB(0, 255, 0)).build());
        ItemUtil.smartGive(p, new ItemBuilder(Material.LEATHER_LEGGINGS).unbreakable(true).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).color(Color.fromRGB(0, 255, 0)).build());
        ItemUtil.smartGive(p, new ItemBuilder(Material.LEATHER_BOOTS).unbreakable(true).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).color(Color.fromRGB(0, 255, 0)).build());
        ItemUtil.smartGive(p, new ItemBuilder(Material.STONE_SWORD).unbreakable(true).enchant(Enchantment.DAMAGE_ALL, 2).build());


        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, -1, 4));

        inv.setItemInOffHand(new ItemStack(Material.MOSS_BLOCK, 32));
    }

    @Override
    public void onPlayerKill(Player p) {
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
        p.getInventory().addItem(new ItemStack(Material.MOSS_BLOCK, 16));
    }
}