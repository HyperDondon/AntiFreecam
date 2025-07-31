package org.darbz.antifreecamClient_v1.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.darbz.antifreecamClient_v1.AntifreecamClient_v1;
import org.darbz.antifreecamClient_v1.commands.tabcompletions.tabc;
import org.darbz.antifreecamClient_v1.config;
import org.darbz.antifreecamClient_v1.disableFreecam.sendMessage;

public class AntiFreeCamMain implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (!player.isOp()) return false;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Please define arguments.");
            return true;
        }

        config cfg = AntifreecamClient_v1.getInstance().getPluginConfig();

        if (args[0].equalsIgnoreCase("options")) {
            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Please specify 'enable', 'disable', or 'reload-config'.");
                return true;
            }

            switch (args[1].toLowerCase()) {
                case "enable":
                    cfg.setAntifreecamEnabled(true);
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "Anti Freecam Mod set to " +
                            ChatColor.RED + "IS" + ChatColor.GREEN + ChatColor.ITALIC + " Required");
                    break;

                case "disable":
                    cfg.setAntifreecamEnabled(false);
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "Anti Freecam Mod set to " +
                            ChatColor.RED + "NOT" + ChatColor.GREEN + ChatColor.ITALIC + " Required");
                    break;

                case "reload-config":
                    cfg.saveConfig();
                    sendMessage.start();
                    AntifreecamClient_v1.getInstance().getCommand("atf").setTabCompleter(new tabc());
                    player.sendMessage(ChatColor.YELLOW + "Config reloaded.");
                    break;

                default:
                    player.sendMessage(ChatColor.RED + "Unknown args. Try 'enable', 'disable', or 'reload-config'.");
                    break;
            }

            return true;
        }

        player.sendMessage(ChatColor.RED + "Unknown command usage. Use /atf options <args>.");
        return true;
    }
}
