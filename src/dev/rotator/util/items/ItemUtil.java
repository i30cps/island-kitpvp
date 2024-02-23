package dev.rotator.util.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
    public static void smartGive(Player p, ItemStack itemStack) {
        Material m = itemStack.getType();
        PlayerInventory inv = p.getInventory();
        if (m.toString().toLowerCase().contains("boots")) {
            inv.setBoots(itemStack);
            return;
        }
        if (m.toString().toLowerCase().contains("chestplate")) {
            inv.setChestplate(itemStack);
            return;
        }
        if (m.toString().toLowerCase().contains("leggings")) {
            inv.setLeggings(itemStack);
            return;
        }
        if (m.toString().toLowerCase().contains("helmet")) {
            inv.setHelmet(itemStack);
            return;
        }

        inv.addItem(itemStack);
    }

    public static void setUnbreakable(ItemStack itemStack, boolean breakable) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setUnbreakable(breakable);
        itemStack.setItemMeta(itemMeta);
    }
}
