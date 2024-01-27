package dev.rotator.kitlogic.kits;

import dev.rotator.kitlogic.Kit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class BasicKit implements Kit {
    public String name = "&eBasic Kit";
    public String id = "basic";
    public String description = "§8A very simple kit, just some armor and a sword. That's it!";

    public void apply(Player p) {
        PlayerInventory inv = p.getInventory();
        ItemStack diaHelm = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta diaHelmMeta = diaHelm.getItemMeta();
        assert diaHelmMeta != null;
        diaHelmMeta.setUnbreakable(true);
        inv.setHelmet(diaHelm);
    }


}