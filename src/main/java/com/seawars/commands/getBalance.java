package com.seawars.commands;

import com.seawars.Seawars;
import com.seawars.currency.CurrencyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getBalance implements CommandExecutor {

    private Seawars plugin;
    public getBalance(Seawars plugin) {
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        CurrencyManager manager = new CurrencyManager(plugin);
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are not a player!");
            return true;
        } else {
            Player player = (Player) commandSender;
            if (plugin.data.getConfig().contains("players." + player.getUniqueId().toString() + ".balance"))
                plugin.data.saveConfig();
            player.sendMessage("Your Balance is: " + manager.getPlayerCurrency(player) + " $ ");

        }

        return true;
    }
}
