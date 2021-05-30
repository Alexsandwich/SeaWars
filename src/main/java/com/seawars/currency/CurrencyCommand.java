package me.drunkcactus;

import net.md_5.bungee.chat.SelectorComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CurrencyCommand implements CommandExecutor {

    public Main plugin;

    public CurrencyCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("currency").setExecutor(this);
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

                } else if (args[1].equalsIgnoreCase("balance")) {
                    sender.sendMessage(CurrencyUtils.chat("/currency <balance> <player>"));



                }
            } else if (args.length == 2) {
                CurrencyManager manager = new CurrencyManager(plugin);
                @SuppressWarnings("deprecation")
                OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
                if (p != null) {
                    if (args[0].equalsIgnoreCase("balance")) {
                        sender.sendMessage(CurrencyUtils.chat(" The player " + p.getName() +" currently has Balance " + manager.getPlayerCurrency(p)));
                        return true;

                    } else {
                        sender.sendMessage(CurrencyUtils.chat("/currency <add>" + p.getName() + "<amount>"));
                    }
                } else {
                    sender.sendMessage(CurrencyUtils.chat("Player" + args[1] + "Could not be found"));

                }
            } else if (args.length == 3) {
                @SuppressWarnings("deprecation")
                int amount = Integer.parseInt(args[2]);
                CurrencyManager manager = new CurrencyManager(plugin);
                OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
                if (args[0].equalsIgnoreCase("add")) {
                    if (p != null) {
                        manager.addCurrencyToPlayer(p, amount);
                        sender.sendMessage(CurrencyUtils.chat("You have succesfully added $" + args[2] + "to the player" + p.getName()));


                    } else {
                        sender.sendMessage(CurrencyUtils.chat("Player" + args[1] + "Could not be found"));

                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (p != null) {
                        manager.removeCurrencyFromPlayer(p, amount);
                        sender.sendMessage(CurrencyUtils.chat("You have succesfully removed $" + args[2] + "to the player" + p.getName()));


                    } else {
                        sender.sendMessage(CurrencyUtils.chat("Player" + args[1] + "Could not be found"));

                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (p != null) {
                        manager.setPlayerCurrency(p, amount);
                        sender.sendMessage(CurrencyUtils.chat("You have succesfully set the player" + p.getName() + "to the player" + args[2]));
                    } else {
                        sender.sendMessage(CurrencyUtils.chat("Player" + args[1] + "Could not be found"));
                    }
                }
            }
        } else {
            sender.sendMessage(CurrencyUtils.chat("You do not have permission to execute this command"));
        }

        return false;

    }
}