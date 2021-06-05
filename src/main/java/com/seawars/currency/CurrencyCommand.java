package com.seawars.currency;

import com.seawars.Seawars;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CurrencyCommand implements CommandExecutor {

    public Seawars plugin;

    public CurrencyCommand(Seawars plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("currencyManager.use")) {
            if (args.length == 0) {

                sender.sendMessage(CurrencyUtils.chat("/currency <add:remove:set> <player> <amount>"));
                return true;

            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("add")) {
                    sender.sendMessage(CurrencyUtils.chat("/currency <add> <player> <amount>"));

                } else if (args[0].equalsIgnoreCase("remove")) {
                    sender.sendMessage(CurrencyUtils.chat("/currency <remove> <player> <amount>"));

                } else if (args[1].equalsIgnoreCase("set")) {
                    sender.sendMessage(CurrencyUtils.chat("/currency <set> <player> <amount>"));

                } else if (args[0].equalsIgnoreCase("balance")) {
                    sender.sendMessage(CurrencyUtils.chat("/currency <balance> <player>"));



                }
            } else if (args.length == 2) {
                CurrencyManager manager = new CurrencyManager(plugin);
                @SuppressWarnings("deprecation")
                OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
                if (args[0].equalsIgnoreCase("balance")) {
                    if (p.isOnline()) {
                        sender.sendMessage(CurrencyUtils.chat(args[1] + " Balance's is " + manager.getPlayerCurrency(p) + " $"));

                        return true;
                    }

                } else {
                    sender.sendMessage(CurrencyUtils.chat(" Player " + args[1] + " Could not be found "));

                }
            } else if (args.length == 3) {
                @SuppressWarnings("deprecation")
                int amount = Integer.parseInt(args[2]);
                CurrencyManager manager = new CurrencyManager(plugin);
                OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
                if (args[0].equalsIgnoreCase("add")) {
                    if (p.isOnline()) {
                        manager.addCurrencyToPlayer(p, amount);
                        sender.sendMessage(CurrencyUtils.chat(" You have successfully added " + args[2] + " $" + " to the player " + p.getName()));


                    } else {
                        sender.sendMessage(CurrencyUtils.chat(" Player " + args[1] + " Could not be found "));

                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (p.isOnline()) {
                        manager.removeCurrencyFromPlayer(p, amount);
                        sender.sendMessage(CurrencyUtils.chat(" You have successfully removed " + args[2] + " $" + " from the player " + p.getName()));


                    } else {
                        sender.sendMessage(CurrencyUtils.chat(" Player " + args[1] + " Could not be found "));

                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (p.isOnline()) {
                        manager.setPlayerCurrency(p, amount);
                        sender.sendMessage(CurrencyUtils.chat(" You have successfully set the player " + p.getName() + " " + args[2] + " $"));
                    } else {
                        sender.sendMessage(CurrencyUtils.chat(" Player " + args[1] + " Could not be found "));
                    }
                }
            }
        } else {
            sender.sendMessage(CurrencyUtils.chat(" You do not have permission to execute this command "));
        }

        return true;

    }
}