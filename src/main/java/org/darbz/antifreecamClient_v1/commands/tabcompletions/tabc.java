package org.darbz.antifreecamClient_v1.commands.tabcompletions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class tabc implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            results.add("options");
        }
        if (args[0].equalsIgnoreCase("options")) {
            if (args.length == 2) {
                results.add("enable");

                results.add("disable");

                results.add("reload-config");

            }


        }


        return results;
    }
}
