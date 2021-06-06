package com.seawars.commands;

import com.seawars.Seawars;
import com.seawars.currency.CurrencyManager;
import com.seawars.currency.CurrencyUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveCommand implements CommandExecutor {

    public Seawars plugin;

    public giveCommand(Seawars plugin) {
        this.plugin = plugin;

    }

    CurrencyManager manager = new CurrencyManager(plugin);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
            if (args.length == 0) {

                sender.sendMessage(CurrencyUtils.chat("/give <playername> <amount>"));
                return true;
            }
            if (args.length == 1) {
                sender.sendMessage("specify amount");
            }
            if (args.length == 2) {
                @SuppressWarnings("deprecation")
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {

                    int amount = Integer.parseInt(args[1]);
                    if(amount <0) {
                        player.sendMessage("Please enter a correct value!");
                    }
                    if(amount >0) {
                        if (manager.getPlayerCurrency(player) >= amount) {
                            manager.addCurrencyToPlayer(target, amount);
                            manager.removeCurrencyFromPlayer(player, amount);
                            player.sendMessage(String.valueOf(amount));
                            target.sendMessage("You got $" + amount + " from " + player.getDisplayName());
                        } else {
                            sender.sendMessage("no money!");
                        }
                    }

                } else {
                    sender.sendMessage(CurrencyUtils.chat("Player" + target + "Could not be found"));

                }

            return true;
        }
        return false;
    }
}

