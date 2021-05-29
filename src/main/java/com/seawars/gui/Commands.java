package com.seawars.gui;

import com.seawars.Seawars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private Seawars plugin;
    public Commands(Seawars plugin) {
        this.plugin = plugin;

        plugin.getCommand("test").setExecutor(this);


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
        return false;
    }
}
