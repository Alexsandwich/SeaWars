package com.seawars.commands;

import com.seawars.events.blockPlace;
import com.seawars.util.teamSystem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Iterator;

public class moveBlock implements CommandExecutor {



    //Working block moving system!
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;


        //if(teamSystem.red.hasPlayer(player)) {
        for (Location location : blockPlace.redblockloc) {

            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();
            new Location(player.getWorld(), x, y + 2, z).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
            player.sendMessage(String.valueOf(location));

        }
        //}

        //if(teamSystem.blue.hasPlayer(player)) {
        for (Location location : blockPlace.blueblockloc) {

            int xb = location.getBlockX();
            int yb = location.getBlockY();
            int zb = location.getBlockZ();
            new Location(player.getWorld(), xb, yb + 2, zb).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
            player.sendMessage(String.valueOf(location));
        }

        for (Location location : blockPlace.greenblockloc) {

            int xg = location.getBlockX();
            int yg = location.getBlockY();
            int zg = location.getBlockZ();
            new Location(player.getWorld(), xg, yg + 2, zg).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
            player.sendMessage(String.valueOf(location));
        }
            for (Location location : blockPlace.yellowblockloc) {

                int xy = location.getBlockX();
                int yy = location.getBlockY();
                int zy = location.getBlockZ();
                new Location(player.getWorld(), xy, yy + 2, zy).getBlock().setType(location.getBlock().getType());
                location.getBlock().setType(Material.AIR);
                player.sendMessage(String.valueOf(location));


                //}

        }
        return true;
    }
}
