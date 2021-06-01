package com.seawars.commands;

import com.seawars.Seawars;
import com.seawars.currency.CurrencyManager;
import com.seawars.currency.CurrencyUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class giveCommand implements CommandExecutor {

    public Seawars plugin;

    public giveCommand(Seawars plugin) {
        this.plugin = plugin;

    }

    CurrencyManager manager = new CurrencyManager(plugin);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("currencyManager.use")) {
            if (args.length == 0) {

                sender.sendMessage(CurrencyUtils.chat("/give <playername> <amount>"));
                return true;

            } else if (args.length == 1) {
                sender.sendMessage("specify amount");
            }
        } else if (args.length == 2) {
            @SuppressWarnings("deprecation")
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
            if (p != null) {

                int amount = Integer.parseInt(args[2]);
                if(manager.getPlayerCurrency(p) >= amount) {
                    manager.addCurrencyToPlayer(args[1], amount);
                    manager.removeCurrencyFromPlayer(p, amount);
                }  else{
                    sender.sendMessage("no money!");
                }
            } else {
                sender.sendMessage(CurrencyUtils.chat("Player" + args[1] + "Could not be found"));

            }
        } else {
            sender.sendMessage(CurrencyUtils.chat("You do not have permission to execute this command"));
        }

        return true;

    }
}
}
