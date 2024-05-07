package dev.rotator.util;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.YamlConfiguration;

import dev.rotator.Main;

public class YamlManager {
    private static final File dataFolder = Main.getPl().getDataFolder();
    private static final Set<YamlManager> allManagers = new HashSet<YamlManager>();

    private final File file;
    private YamlConfiguration yamlConfig;

    public YamlManager(String path) {
        file = new File(dataFolder, path);

        allManagers.add(this);

        reload();
    }

    public YamlConfiguration getConfiguration() { return yamlConfig; }

    public void save() {
        try {
            this.yamlConfig.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.yamlConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveAll() {
        for (YamlManager ym : allManagers) {
            ym.save();
        }
    }
}