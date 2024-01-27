package dev.rotator.kitlogic;

import dev.rotator.kitlogic.kits.BasicKit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class KitManager implements Listener {
    private Map<UUID, Kit> uuidKitMap;
    private Map<String, Kit> availableKits;

    public KitManager() {
        this.uuidKitMap = new HashMap<>();
        this.availableKits = new HashMap<>();

        this.availableKits.put("basic", new BasicKit());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        cleanUp(e.getEntity());
    }

    public void cleanUp(UUID uuid) {
        uuidKitMap.remove(uuid);
    }
    public void cleanUp(Player p) {
        cleanUp(p.getUniqueId());
    }

    @Nullable
    public Kit getKit(UUID uuid) {
        return uuidKitMap.get(uuid);
    }

    @Nullable
    public Kit getKit(Player p) {
        return uuidKitMap.get(p.getUniqueId());
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

}
