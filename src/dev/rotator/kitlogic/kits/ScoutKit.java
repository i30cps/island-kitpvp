package dev.rotator.kitlogic.kits;

import dev.rotator.Main;
import dev.rotator.kitlogic.Kit;
import dev.rotator.util.items.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ScoutKit extends Kit {
    @Override
    public String getID() { return "ScoutKit"; }

    public void apply(Player p) {
        PlayerInventory inv = p.getInventory();
        Material[] mats = {Material.IRON_HELMET, Material.IRON_CHESTPLATE,
                Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.DIAMOND_SWORD};

        for (Material m : mats) {
            ItemStack is = new ItemStack(m);
            ItemUtil.setUnbreakable(is, true);
            ItemUtil.smartGive(p, is);
        }

        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));

        inv.setItemInOffHand(new ItemStack(Material.MOSS_BLOCK, 32));
    }

    @Override
    public void onPlayerKill(Player p) {
        Main.getPl().getPlayerdataManager().addKitExperience(p.getUniqueId(), this, this.killExperience);

        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
        p.getInventory().addItem(new ItemStack(Material.MOSS_BLOCK, 16));
    }
}