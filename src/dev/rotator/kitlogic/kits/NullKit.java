package dev.rotator.kitlogic.kits;

import dev.rotator.kitlogic.Kit;
import org.bukkit.entity.Player;

public class NullKit implements Kit {

    @Override
    public void apply(Player p) {

    }

    @Override
    public boolean isNullKit() {
        return true;
    }
}
