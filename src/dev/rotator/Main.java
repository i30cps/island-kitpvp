package dev.rotator;

import dev.rotator.eventhandlers.KitEventsManager;
import dev.rotator.kitlogic.KitManager;
import dev.rotator.util.PlayerdataManager;
import dev.rotator.util.YamlManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import dev.rotator.commands.*;

public class Main extends JavaPlugin {
    private static Main pl;
    public static Main getPl() {
        return pl;
    }

    public KitManager kitManager = new KitManager();
    public KitEventsManager kitEventsManager = new KitEventsManager();
    public PlayerdataManager playerdataManager;

    public KitManager getKitManager() { return kitManager; }
    public KitEventsManager getKitEventsManager() { return kitEventsManager; }
    public PlayerdataManager getPlayerdataManager() { return playerdataManager; }
    public static PlayerdataManager getDefaultPlayerdataManager() { return getPl().getPlayerdataManager(); }

    @Override
    public void onEnable() {

        pl = this;

        playerdataManager = new PlayerdataManager();

        configureGameRules();

        Bukkit.getPluginManager().registerEvents(kitManager, this);
        Bukkit.getPluginManager().registerEvents(kitEventsManager, this);

        getCommand("tester").setExecutor(new dev.rotator.commands.debug.GeneralTester());
        getCommand("kit").setExecutor(new KitCommand());
    }

    private void configureGameRules() {
        World world = getServer().getWorlds().get(0);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
    }

    @Override
    public void onDisable() {
        YamlManager.saveAll();
    }

}