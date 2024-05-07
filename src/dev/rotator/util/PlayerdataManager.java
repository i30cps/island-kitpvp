package dev.rotator.util;

import dev.rotator.kitlogic.Kit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.UUID;

public class PlayerdataManager {
    // No kit levels. Each kit just has experience that needs to be gained.
    // Certain kit perks or cosmetics can be unlocked at a certain amount of XP.

    private YamlManager yamlManager;

    public PlayerdataManager() {
        yamlManager = new YamlManager("playerdata.yml");
    }

    private ConfigurationSection addPlayer(UUID uuid) {
        return yamlManager.getConfiguration().createSection(uuid.toString());
    }
    private ConfigurationSection addKit(UUID uuid, Kit kit) {
        return ofUUID(uuid).createSection(kit.getID());
    }

    public YamlManager getYamlManager() { return yamlManager; }

    private ConfigurationSection ofUUID(UUID uuid) {
        ConfigurationSection result = yamlManager.getConfiguration().getConfigurationSection(uuid.toString());
        if (result == null) return addPlayer(uuid);
        return result;
    }
    private ConfigurationSection ofKit(UUID uuid, Kit kit) {
        ConfigurationSection result = ofUUID(uuid).getConfigurationSection(kit.getID());
        if (result == null) return addKit(uuid, kit);
        return result;
    }

    public void setKills(UUID uuid, int kills) { ofUUID(uuid).set("kills", kills); }
    public int getKills(UUID uuid) { return ofUUID(uuid).getInt("kills"); }
    public int incrementKills(UUID uuid) {
        setKills(uuid, getKills(uuid) + 1);
        return getKills(uuid);
    }

    public int getGeneralExperience(UUID uuid) { return ofUUID(uuid).getInt("xp"); }
    public void setGeneralExperience(UUID uuid, int xp) { ofUUID(uuid).set("xp", xp); }
    public int addGeneralExperience(UUID uuid, int xp) {
        setGeneralExperience(uuid, getGeneralExperience(uuid) + xp);
        return getGeneralExperience(uuid);
    }

    public int getKitExperience(UUID uuid, Kit kit) { return ofKit(uuid, kit).getInt("xp"); }
    public void setKitExperience(UUID uuid, Kit kit, int xp) { ofKit(uuid, kit).set("xp", xp); }
    public int addKitExperience(UUID uuid, Kit kit, int xp) {
        setKitExperience(uuid, kit, getKitExperience(uuid, kit) + xp);
        return getKitExperience(uuid, kit);
    }
}
