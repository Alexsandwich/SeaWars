package com.seawars.commands;

import com.seawars.Seawars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getBalance implements CommandExecutor {

    private Seawars plugin;
    public getBalance(Seawars plugin) {
        this.plugin = plugin;
    }

    public int balance = 0;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are not a player!");
            return true;
        } else {
            Player player = (Player) commandSender;

            //This method bellow obtains the players money
            if (plugin.data.getConfig().contains("players." + player.getUniqueId().toString() + ".balance"))
                balance = plugin.data.getConfig().getInt("players." + player.getUniqueId().toString() + ".balance");
            //plugin.data.getConfig().set("players." + player.getUniqueId().toString() + ".balance", (balance + 100));
            plugin.data.saveConfig();

            player.sendMessage("Your Balance is: " + balance);

        }

        return true;
    }
}
