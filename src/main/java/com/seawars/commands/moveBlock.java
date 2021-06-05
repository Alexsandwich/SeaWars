package com.seawars.commands;

import com.seawars.Seawars;
import com.seawars.events.blockPlace;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class moveBlock implements CommandExecutor {

    private Seawars plugin;
    public moveBlock(Seawars plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        Location location1 = (Location) plugin.getConfig().get("region1");
        Location location2 = (Location) plugin.getConfig().get("region2");

        int y = (location1.getBlockY() < location2.getBlockY() ? location2.getBlockY() : location1.getBlockY());

        for (Location location : blockPlace.redblockloc) {
            int x = location.getBlockX();
            int z = location.getBlockZ();
            new Location(player.getWorld(), x, y + 2, z).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
            player.sendMessage(String.valueOf(location));

        }
        for (Location location : blockPlace.blueblockloc) {
            int xb = location.getBlockX();
            int zb = location.getBlockZ();
            new Location(player.getWorld(), xb, y + 2, zb).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
            player.sendMessage(String.valueOf(location));
        }

        for (Location location : blockPlace.greenblockloc) {
            int xg = location.getBlockX();
            int zg = location.getBlockZ();
            new Location(player.getWorld(), xg, y + 2, zg).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
            player.sendMessage(String.valueOf(location));
        }
            for (Location location : blockPlace.yellowblockloc) {
                int xy = location.getBlockX();
                int zy = location.getBlockZ();
                new Location(player.getWorld(), xy, y + 2, zy).getBlock().setType(location.getBlock().getType());
                location.getBlock().setType(Material.AIR);
                player.sendMessage(String.valueOf(location));
        }
        return true;
    }
}
