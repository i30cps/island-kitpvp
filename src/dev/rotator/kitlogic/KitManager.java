package dev.rotator.kitlogic;

import dev.rotator.kitlogic.kits.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

public class KitManager implements Listener {
    private Map<UUID, Kit> uuidKitMap;
    private Map<String, Kit> availableKits;

    public Vector<UUID> playersWithKits;

    public KitManager() {
        this.uuidKitMap = new HashMap<>();
        this.availableKits = new HashMap<>();

        this.playersWithKits = new Vector<>();

        this.availableKits.put("null", new NullKit());
        this.availableKits.put("basic", new BasicKit());
        this.availableKits.put("chicken", new ChickenKit());
        this.availableKits.put("scout", new ScoutKit());
        this.availableKits.put("frog", new FrogKit());
        this.availableKits.put("assassin", new AssassinKit());

    }

    private Kit unnullKit(Kit kit) {
        if (kit == null) return availableKits.get("null");
        return kit;
    }

    public boolean isNullKit(Kit kit) { return kit.isNullKit(); }

    public boolean playerHasKit(Player p) { return !isNullKit(getKitOfPlayer(p)); }

    public Kit getKitOfPlayer(UUID uuid) {
        return unnullKit(uuidKitMap.get(uuid));
    }

    public Kit getKitOfPlayer(Player p) {
        return unnullKit(uuidKitMap.get(p.getUniqueId()));
    }

    public boolean isValidKit(String kitName) {
        return availableKits.containsKey(kitName);
    }

    public Set<String> getAvailableKits() {
        return availableKits.keySet();
    }

    public void assignKit(Player p, Kit kit) {
        kit.preApply(p);
        kit.apply(p);
        uuidKitMap.put(p.getUniqueId(), kit);
        playersWithKits.add(p.getUniqueId());
    }

    public void assignKit(Player p, String kitName) {
        if (!isValidKit(kitName)) {
            p.sendMessage("§cInvalid kit name." +
                    "If you are not a playtester, please open a ticket (bug report) about this.");
            Bukkit.getLogger().warning("Invalid kit name from player " +
                    p.getName() + " with kit " + kitName);
            return;
        }

        assignKit(p, availableKits.get(kitName));
    }

    // stuff to automatically remove the kit from the player upon death.
    // this should not need any modifications beside potential bug fixes
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (playerHasKit(e.getEntity())) e.getEntity().getInventory().clear(); // keepInventory is on
        cleanUp(e.getEntity());
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e) {
        if (playerHasKit(e.getPlayer())) e.getPlayer().setHealth(0); // TODO: Reset the player instead of simply killing them.
    }

    public void cleanUp(UUID uuid) {
        uuidKitMap.remove(uuid);
        playersWithKits.remove(uuid);
        getKitOfPlayer(uuid).cleanUp(Bukkit.getPlayer(uuid));
    }
    public void cleanUp(Player p) {
        cleanUp(p.getUniqueId());
    }
}
