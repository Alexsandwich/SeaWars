package com.seawars.commands;

import com.seawars.Seawars;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class setRegion implements CommandExecutor {

    public Location loc1;
    public Location loc2;

    static Seawars plugin;

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
                            player.sendMessage(ChatColor.GREEN + "Location 1 set at: " + loc1.getBlockX() + "," + loc1.getBlockY() + "," + loc1.getBlockZ());
                        }
                        if (args[0].equalsIgnoreCase("2")) {
                            loc2 = player.getLocation();
                            plugin.getConfig().set("region2", loc2);
                            player.sendMessage(ChatColor.GREEN + "Location 2 set at: " + loc2.getBlockX() + "," + loc2.getBlockY() + "," + loc2.getBlockZ());
                        }
                        if (args[0].equalsIgnoreCase("filltest")) {
                            // Save default config into plugins/<your-plugin>/config.yml if not exists
                            plugin.saveDefaultConfig();
                            // Get config from config file
                            plugin.config = plugin.getConfig();
                            // Put default values into it (from your jar's config.yml file)
                            plugin.config.options().copyDefaults(true);
                            // Add missing / new parameters into plugins/<your-plugin>/config.yml
                            plugin.saveConfig();

                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have permission");
                        return true;
                    }
                }
            }
        return true;
    }

    public static void startGame(Player player){
        List<Block> blocks = new ArrayList<>();

        //TODO work on getting region data from config.yml

        //int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int topBlockX = (plugin.getConfig().getInt("region1.x") < plugin.getConfig().getInt("region2.x") ? plugin.getConfig().getInt("region2.x") : plugin.getConfig().getInt("region1.x"));
        //int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int bottomBlockX = (plugin.getConfig().getInt("region1.x") > plugin.getConfig().getInt("region2.x") ? plugin.getConfig().getInt("region2.x") : plugin.getConfig().getInt("region1.x"));
        //int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
        int topBlockY = (plugin.getConfig().getInt("region1.y") < plugin.getConfig().getInt("region2.y") ? plugin.getConfig().getInt("region2.y") : plugin.getConfig().getInt("region1.y"));
        //int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
        int bottomBlockY = (plugin.getConfig().getInt("region1.y") > plugin.getConfig().getInt("region2.y") ? plugin.getConfig().getInt("region2.y") : plugin.getConfig().getInt("region1.y"));

        //int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int topBlockZ = (plugin.getConfig().getInt("region1.z") < plugin.getConfig().getInt("region2.z") ? plugin.getConfig().getInt("region2.z") : plugin.getConfig().getInt("region1.z"));
        //int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int bottomBlockZ = (plugin.getConfig().getInt("region1.z") > plugin.getConfig().getInt("region2.z") ? plugin.getConfig().getInt("region2.z") : plugin.getConfig().getInt("region1.z"));

        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                for(int y = bottomBlockY; y <= topBlockY; y++)
                {
                    Block block = player.getWorld().getBlockAt(x, y, z);
                    player.sendMessage(String.valueOf(x + "" + y +"" + ""+ z));

                    blocks.add(block);
                    block.setType(Material.WATER);
                }
            }
        }
    }
}