package com.seawars.commands;

import com.seawars.Seawars;
import com.seawars.gui.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class shopCommand implements CommandExecutor {

    private Seawars plugin;
    public shopCommand(Seawars plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p =(Player) sender;

        if (p.hasPermission("User")){
            p.openInventory(GUI.GUI(p));
        }
        return true;
    }
}
