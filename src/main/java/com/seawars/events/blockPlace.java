package com.seawars.events;

import com.seawars.Seawars;
import com.seawars.util.teamSystem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class blockPlace implements Listener {

    List<Block> redblock = new ArrayList<>();
    List<Block> blueblock = new ArrayList<>();
    List<Block> yellowblock = new ArrayList<>();
    List<Block> greenblock = new ArrayList<>();

    private Seawars plugin;

    public blockPlace(Seawars plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(teamSystem.red.hasPlayer(player)) {
            redblock.add(event.getBlockPlaced());
        }
        if(teamSystem.blue.hasPlayer(player)) {
            blueblock.add(event.getBlockPlaced());
        }
        if(teamSystem.yellow.hasPlayer(player)) {
            yellowblock.add(event.getBlockPlaced());
        }
        if(teamSystem.green.hasPlayer(player)) {
            greenblock.add(event.getBlockPlaced());
        }
    }

    int hit = 0;

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        Player player = (Player) event.getEntity().getShooter();


        if (entity instanceof Arrow) {
            Location loc = entity.getLocation();
            Vector vec = entity.getVelocity();
            Location loc2 = new Location(loc.getWorld(), loc.getX() + vec.getX(), loc.getY() + vec.getY(), loc.getZ() + vec.getZ());
            event.getEntity().remove();

            if (redblock.contains(loc2.getBlock())) {

                if (loc2.getBlock().getType() == Material.OBSIDIAN) {
                        player.sendMessage(String.valueOf(hit));
                        hit++;

                }
                if (hit == 5) {
                    loc2.getBlock().setType(Material.AIR);
                    hit =0;
                }
            }


            if(blueblock.contains(loc2.getBlock())) {
                player.sendMessage("blue block");
            }
            if(greenblock.contains(loc2.getBlock())) {
                player.sendMessage("green block");
            }
            if(yellowblock.contains(loc2.getBlock())) {
                player.sendMessage("yellow block");
            }

        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (teamSystem.red.hasPlayer(player)) {
            if(redblock.contains(block)) {
                redblock.remove(block);
            }
            if (blueblock.contains(block)) {
                event.setCancelled(true);
            }
            if (greenblock.contains(block)) {
                event.setCancelled(true);
            }
            if (yellowblock.contains(block)) {
                event.setCancelled(true);
            }
        }
        if (teamSystem.blue.hasPlayer(player)) {
            if (redblock.contains(block)) {
                event.setCancelled(true);
            }
            if (greenblock.contains(block)) {
                event.setCancelled(true);
            }
            if (yellowblock.contains(block)) {
                event.setCancelled(true);
            }
            if(blueblock.contains(block)) {
                blueblock.remove(block);
            }
        }
        if (teamSystem.yellow.hasPlayer(player)) {
            if (blueblock.contains(block)) {
                event.setCancelled(true);
            }
            if (greenblock.contains(block)) {
                event.setCancelled(true);
            }
            if (redblock.contains(block)) {
                event.setCancelled(true);
            }
            if(yellowblock.contains(block)) {
                yellowblock.remove(block);
            }
        }
        if (teamSystem.green.hasPlayer(player)) {
            if (blueblock.contains(block)) {
                event.setCancelled(true);
            }
            if (redblock.contains(block)) {
                event.setCancelled(true);
            }
            if (yellowblock.contains(block)) {
                event.setCancelled(true);
            }
            if(greenblock.contains(block)) {
                greenblock.remove(block);
            }
        }
    }
}
