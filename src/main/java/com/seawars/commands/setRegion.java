package com.seawars.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setRegion implements CommandExecutor {

    public static Location loc1;
    public static Location loc2;

    int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
    int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
    int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
    int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
    int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
    int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("set")) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage("Please do this command in game");
                return true;
            } else {
                Player player = (Player) commandSender;
                if (player.hasPermission("seawars.admin")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("1")) {
                            loc1 = player.getLocation();
                        }
                        if (args[0].equalsIgnoreCase("2")) {
                            loc2 = player.getLocation();
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have permission");
                        return true;
                    }
                }
            }
        }
        return true;
    }
}