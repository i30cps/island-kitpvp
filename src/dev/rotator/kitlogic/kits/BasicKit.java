package dev.rotator.kitlogic.kits;

import dev.rotator.kitlogic.Kit;
import dev.rotator.util.items.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class BasicKit implements Kit {
    public String name = "&eBasic Kit";
    public String description = "§8A very simple kit, just some armor and a sword. That's it!";

    public void apply(Player p) {
        PlayerInventory inv = p.getInventory();
        Material[] mats = {Material.DIAMOND_HELMET, Material.IRON_CHESTPLATE,
                Material.IRON_LEGGINGS, Material.DIAMOND_BOOTS, Material.IRON_SWORD};

        for (Material m : mats) {
            ItemStack is = new ItemStack(m);
            ItemUtil.setUnbreakable(is, true);
            ItemUtil.smartGive(p, is);
        }

        inv.setItemInOffHand(new ItemStack(Material.MOSS_BLOCK, 32));
    }

    @Override
    public void onPlayerKill(Player p) {
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
        p.getInventory().addItem(new ItemStack(Material.MOSS_BLOCK, 16));
    }
}