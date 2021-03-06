package com.seawars.commands;

import com.seawars.Seawars;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class setRegion implements CommandExecutor {

    public Location loc1;
    public Location loc2;
    public Location red;
    public Location yellow;
    public Location green;
    public Location blue;

    static Seawars plugin;

    String prefix = plugin.prefix;

    public setRegion(Seawars instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage("Please do this command in game");
                return true;
            } else {
                Player player = (Player) commandSender;

                if (player.hasPermission("seawars.admin")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("1")) {
                            loc1 = player.getLocation();
                            plugin.getConfig().set("region1", loc1);
                            player.sendMessage(prefix + ChatColor.GREEN + "Location 1 set at: " + loc1.getBlockX() + "," + loc1.getBlockY() + "," + loc1.getBlockZ());
                            reloadConfig();
                        }
                        if (args[0].equalsIgnoreCase("2")) {
                            loc2 = player.getLocation();
                            plugin.getConfig().set("region2", loc2);
                            plugin.saveConfig();
                            player.sendMessage(prefix + ChatColor.GREEN + "Location 2 set at: " + loc2.getBlockX() + "," + loc2.getBlockY() + "," + loc2.getBlockZ());
                            reloadConfig();
                        }
                        if (args[0].equalsIgnoreCase("red")) {
                            red = player.getLocation();
                            plugin.getConfig().set("red", red);
                            plugin.saveConfig();
                            player.sendMessage(prefix + ChatColor.GREEN + "Location red set at: " + red.getBlockX() + "," + red.getBlockY() + "," + red.getBlockZ());
                            reloadConfig();
                        }
                        if (args[0].equalsIgnoreCase("blue")) {
                            blue = player.getLocation();
                            plugin.getConfig().set("blue", blue);
                            plugin.saveConfig();
                            player.sendMessage(prefix + ChatColor.GREEN + "Location blue set at: " + blue.getBlockX() + "," + blue.getBlockY() + "," + blue.getBlockZ());
                            reloadConfig();
                        }
                        if (args[0].equalsIgnoreCase("yellow")) {
                            yellow = player.getLocation();
                            plugin.getConfig().set("yellow", yellow);
                            plugin.saveConfig();
                            player.sendMessage(prefix + ChatColor.GREEN + "Location yellow set at: " + yellow.getBlockX() + "," + yellow.getBlockY() + "," + yellow.getBlockZ());
                            reloadConfig();
                        }
                        if (args[0].equalsIgnoreCase("green")) {
                            green = player.getLocation();
                            plugin.getConfig().set("green", green);
                            plugin.saveConfig();
                            player.sendMessage(prefix + ChatColor.GREEN + "Location green set at: " + green.getBlockX() + "," + green.getBlockY() + "," + green.getBlockZ());
                            reloadConfig();
                        }
                    } else {
                        player.sendMessage(prefix + ChatColor.RED + "You do not have permission");
                        return true;
                    }
                }
            }
        return true;
    }

    public static void startGame(Player player){
        List<Block> blocks = new ArrayList<>();

        Location location1 = (Location) plugin.getConfig().get("region1");
        Location location2 = (Location) plugin.getConfig().get("region2");

        int topBlockX = (location1.getBlockX() < location2.getBlockX() ? location2.getBlockX() : location1.getBlockX());
        int bottomBlockX = (location1.getBlockX() > location2.getBlockX() ? location2.getBlockX() : location1.getBlockX());
        int topBlockY = (location1.getBlockY() < location2.getBlockY() ? location2.getBlockY() : location1.getBlockY());
        int bottomBlockY = (location1.getBlockY() > location2.getBlockY() ? location2.getBlockY() : location1.getBlockY());
        int topBlockZ = (location1.getBlockZ() < location2.getBlockZ() ? location2.getBlockZ() : location1.getBlockZ());
        int bottomBlockZ = (location1.getBlockZ() > location2.getBlockZ() ? location2.getBlockZ() : location1.getBlockZ());

        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                for(int y = bottomBlockY; y <= topBlockY; y++)
                {
                    Block block = player.getWorld().getBlockAt(x, y, z);

                    blocks.add(block);
                    block.setType(Material.WATER);
                }
            }
        }
    }
    public void reloadConfig() {
        plugin.saveDefaultConfig();
        plugin.config = plugin.getConfig();
        plugin.config.options().copyDefaults(true);
        plugin.saveConfig();
    }
}