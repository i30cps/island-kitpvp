package dev.rotator.kitlogic;

import org.bukkit.entity.Player;

public interface Kit {

    public static String name = "";
    public static String description = "";
    public static String id = "";

    void apply(Player p);

}