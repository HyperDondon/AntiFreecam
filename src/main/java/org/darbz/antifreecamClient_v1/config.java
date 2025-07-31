package org.darbz.antifreecamClient_v1;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class config {

    private final File configFile;
    private final YamlConfiguration yamlConfig;
    private final JavaPlugin plugin;

    public config(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "ATF_Config.yml");

        if (!configFile.getParentFile().exists()) {
            configFile.getParentFile().mkdirs();
        }

        if (!configFile.exists()) {
            try {
                boolean justCreated = configFile.createNewFile();
                if (justCreated) {
                    plugin.getLogger().info("ATF_Config.yml has been successfully created.");
                } else {
                    plugin.getLogger().warning("ATF_Config.yml could not be created.");
                }
            } catch (IOException e) {
                plugin.getLogger().severe("ATF_Config.yml failed to be created.");
                throw new RuntimeException("Could not create ATF_Config.yml", e);
            }
        }

        this.yamlConfig = YamlConfiguration.loadConfiguration(configFile);
        initializeDefaults();
    }

    private void initializeDefaults() {
        if (!yamlConfig.contains("toggle")) {
            yamlConfig.set("toggle", true); // Default: system ON
            saveConfig();
        }
    }

    public boolean isAntifreecamEnabled() {
        return yamlConfig.getBoolean("toggle", true);
    }

    public void setAntifreecamEnabled(boolean enabled) {
        yamlConfig.set("toggle", enabled);
        saveConfig();
    }

    public void saveConfig() {
        try {
            yamlConfig.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to save ATF_Config.yml.");
            e.printStackTrace();
        }
    }

    public YamlConfiguration getRawConfig() {
        return yamlConfig;
    }
}
