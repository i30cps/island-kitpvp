package dev.rotator.kitlogic.kits;

import dev.rotator.kitlogic.Kit;
import dev.rotator.util.items.ItemBuilder;
import dev.rotator.util.items.ItemUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HyperaggressiveKit implements Kit {
    public void apply(Player p) {
        PlayerInventory inv = p.getInventory();
        inv.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).unbreakable(true).build());
        inv.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
                .color(Color.fromRGB(0, 0, 70)).unbreakable(true).build());
        inv.addItem(new ItemBuilder(Material.END_STONE).
                displayname("End Smacker").enchant(Enchantment.DAMAGE_ALL, 4).build());
        inv.addItem(new ItemStack(Material.ENDER_PEARL, 1));
    }

    @Override
    public void onPlayerKill(Player p) {
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
        p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
    }
}