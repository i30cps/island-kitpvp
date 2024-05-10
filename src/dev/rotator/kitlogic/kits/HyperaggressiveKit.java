package dev.rotator.kitlogic.kits;

import dev.rotator.Main;
import dev.rotator.kitlogic.Kit;
import dev.rotator.kitlogic.unlockables.ArrowKillEffect;
import dev.rotator.util.items.ItemBuilder;
import dev.rotator.util.items.ItemUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HyperaggressiveKit extends Kit {
    private ArrowKillEffect arrowKillEffect = new ArrowKillEffect(this);

    public HyperaggressiveKit() {
        arrowKillEffect = new ArrowKillEffect(this);
    }

    @Override
    public String getID() { return "hyperaggressivekit"; }

    public void apply(Player p) {
        PlayerInventory inv = p.getInventory();

        inv.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .unbreakable(true).build());
        inv.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .color(Color.fromRGB(0, 0, 70)).unbreakable(true).build());
        inv.addItem(new ItemBuilder(Material.END_STONE)
                .displayname("End Smacker").enchant(Enchantment.DAMAGE_ALL, 6).build());
        inv.addItem(new ItemStack(Material.ENDER_PEARL, 1));
        inv.setItemInOffHand(new ItemStack(Material.MOSS_BLOCK, 16));

        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 0, true, false));
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType().equals(Material.END_STONE)) e.setCancelled(true);
    }

    @Override
    public void onPlayerKill(Player p) {
        Main.getPl().getPlayerdataManager().addKitExperience(p.getUniqueId(), this, this.killExperience);

        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50, 1, true, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, true, false));


        ItemUtil.smartGive(p, new ItemStack(Material.GOLDEN_APPLE, 1));
        ItemUtil.smartGive(p, new ItemStack(Material.ENDER_PEARL, 1));
        ItemUtil.smartGive(p, new ItemStack(Material.MOSS_BLOCK, 8));

        if (arrowKillEffect.unlocked(p)) arrowKillEffect.onPlayerKill(p);
    }
}