package com.seawars.commands;

import com.seawars.events.onJoin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class restartCount implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are not a player!");
            return true;
        } else if(commandSender.isOp()) {
            onJoin.countdown(player);
        } else {
            player.sendMessage("No Permission");
        }
        return true;
    }
}
