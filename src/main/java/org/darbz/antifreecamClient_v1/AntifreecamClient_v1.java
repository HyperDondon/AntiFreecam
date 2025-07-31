package org.darbz.antifreecamClient_v1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.darbz.antifreecamClient_v1.commands.AntiFreeCamMain;
import org.darbz.antifreecamClient_v1.commands.hasMod;
import org.darbz.antifreecamClient_v1.commands.tabcompletions.tabc;



import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class AntifreecamClient_v1 extends JavaPlugin implements Listener {

    private static AntifreecamClient_v1 instance;
    private config cfg;
    public static Set<UUID> HasMod = new HashSet<>();

    public static AntifreecamClient_v1 getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getLogger().info("ATF Has Loaded");

        cfg = new config(this);
        cfg.saveConfig();

        getCommand("atf").setExecutor(new AntiFreeCamMain());
        getCommand("atf").setTabCompleter(new tabc());
        getCommand("verify").setExecutor(new hasMod());

        registerEvents();
    }

    @Override
    public void onDisable() {
        if (cfg != null) {
            cfg.saveConfig();
        }
    }

    public config getPluginConfig() {
        return cfg;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (cfg == null) {
            getServer().getLogger().info("§cATF_Config not available.");
            return;
        }

        if (cfg.isAntifreecamEnabled()) {
            player.sendMessage("ATF_SECRET_REQUEST");
        } else {
            return;
        }

        Bukkit.getScheduler().runTaskLater(this, () -> {
            if (!HasMod.contains(player.getUniqueId())) {
                if (cfg.isAntifreecamEnabled()) {
                    player.kickPlayer(ChatColor.RED + "You must install the AntiFreeCam Mod to play on this server.");
                }
            }
        }, 28L);
    }


    @EventHandler
    public void Disconnect(PlayerQuitEvent e) {
        HasMod.remove(e.getPlayer().getUniqueId());
    }
}
