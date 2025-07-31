package org.darbz.antifreecamClient_v1.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.darbz.antifreecamClient_v1.AntifreecamClient_v1;

import static org.darbz.antifreecamClient_v1.AntifreecamClient_v1.HasMod;

public class hasMod implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        if (HasMod.contains(((Player) sender).getUniqueId())) return false;

        HasMod.add(((Player) sender).getUniqueId());
        sender.sendMessage("Client Verified");
        
        return false;
    }
}
