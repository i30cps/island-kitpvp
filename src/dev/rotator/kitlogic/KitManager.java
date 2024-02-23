package dev.rotator.kitlogic;

import dev.rotator.kitlogic.kits.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.*;

public class KitManager implements Listener {
    private Map<UUID, Kit> uuidKitMap;
    private Map<String, Kit> availableKits;

    public KitManager() {
        this.uuidKitMap = new HashMap<>();
        this.availableKits = new HashMap<>();

        this.availableKits.put("null", new NullKit());
        this.availableKits.put("basic", new BasicKit());
        this.availableKits.put("chicken", new ChickenKit());
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
        kit.apply(p);
        uuidKitMap.put(p.getUniqueId(), kit);
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
        cleanUp(e.getEntity());
    }

    public void cleanUp(UUID uuid) {
        uuidKitMap.remove(uuid);
    }
    public void cleanUp(Player p) {
        cleanUp(p.getUniqueId());
        Objects.requireNonNull(getKitOfPlayer(p)).cleanUp(p);
    }
}
