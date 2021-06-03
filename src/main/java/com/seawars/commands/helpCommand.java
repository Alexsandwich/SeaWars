package com.seawars.commands;

import com.seawars.util.RainbowText;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are not a player!");
            return true;
        } else {
            Player player = (Player) commandSender;
            player.sendMessage(ChatColor.GOLD + "------------------ Sea Wars ------------------");
            player.sendMessage(ChatColor.GOLD + "/shop : To buy cosmetics and upgrades, do this command!");
            player.sendMessage(ChatColor.GOLD + "/team : Do this command to select/switch teams!");
            player.sendMessage(ChatColor.GOLD + "----------------------------------------------");
            return true;
        }
    }
}
