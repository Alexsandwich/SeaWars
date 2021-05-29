package com.seawars.commands;

import com.seawars.Seawars;
import com.seawars.gui.teamGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class teamJoin implements CommandExecutor {
    static Seawars plugin;

    public teamJoin(Seawars instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are not a player!");
            return true;
        } else {
            Player player = (Player) commandSender;
            player.openInventory(teamGUI.GUI(player));
        }
        return true;
    }
}
