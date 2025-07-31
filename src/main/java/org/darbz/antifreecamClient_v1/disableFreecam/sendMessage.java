package org.darbz.antifreecamClient_v1.disableFreecam;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.darbz.antifreecamClient_v1.AntifreecamClient_v1;

public class sendMessage {

    public static void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("ATF_DISABLE_F");
                }
            }
        }.runTaskLater(AntifreecamClient_v1.getInstance(), 40L);
    }
}
