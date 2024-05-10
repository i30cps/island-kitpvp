package dev.rotator.kitlogic;

import org.bukkit.entity.Player;

public abstract class KitUnlockable {
    protected Kit kit = KitManager.getKitManager().getNullKit();
    public KitUnlockable(Kit kit) {
        this.kit = kit;
    }

    public Kit getKit() { return this.kit; }

    abstract public boolean unlocked(Player p);
    abstract public String getID();

}
