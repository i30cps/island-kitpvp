package dev.rotator;

import dev.rotator.eventhandlers.KitEventsManager;
import dev.rotator.kitlogic.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import dev.rotator.commands.*;

public class Main extends JavaPlugin {
    public static JavaPlugin pl;
    public static JavaPlugin getPl() {
        return pl;
    }

    public static KitManager kitManager = new KitManager();
    public static KitEventsManager kitEventsManager = new KitEventsManager();



    @Override
    public void onEnable() {
        pl = this;

        Bukkit.getPluginManager().registerEvents(kitManager, this);
        Bukkit.getPluginManager().registerEvents(kitEventsManager, this);

        getCommand("tester").setExecutor(new dev.rotator.commands.debug.GeneralTester());
        getCommand("kit").setExecutor(new KitCommand());
    }

    @Override
    public void onDisable() {

    }

    public static KitManager getKitManager() {
        return kitManager;
    }
}