package com.seawars.events;

import com.seawars.Seawars;
import com.seawars.currency.CurrencyManager;
import com.seawars.gui.Utils;
import com.seawars.util.teamSystem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class blockPlace implements Listener {

    public static List<Block> redblock = new ArrayList<>();
    public static List<Block> blueblock = new ArrayList<>();
    public static List<Block> yellowblock = new ArrayList<>();
    public static List<Block> greenblock = new ArrayList<>();

    public static List<Location> redblockloc = new ArrayList<>();
    public static List<Location> blueblockloc = new ArrayList<>();
    public static List<Location> yellowblockloc = new ArrayList<>();
    public static List<Location> greenblockloc = new ArrayList<>();

    private Seawars plugin;

    public blockPlace(Seawars plugin) {
        this.plugin = plugin;

    }
    CurrencyManager manager = new CurrencyManager(plugin);


    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (teamSystem.red.hasPlayer(player)) {
            redblock.add(event.getBlockPlaced());
            redblockloc.add(event.getBlockPlaced().getLocation());
        }
        if (teamSystem.blue.hasPlayer(player)) {
            blueblock.add(event.getBlockPlaced());
            blueblockloc.add(event.getBlockPlaced().getLocation());
        }
        if (teamSystem.yellow.hasPlayer(player)) {
            yellowblock.add(event.getBlockPlaced());
            yellowblockloc.add(event.getBlockPlaced().getLocation());
        }
        if (teamSystem.green.hasPlayer(player)) {
            greenblock.add(event.getBlockPlaced());
            greenblockloc.add(event.getBlockPlaced().getLocation());
        }
    }


    public static HashMap<String, Integer> redobbymap = new HashMap<String, Integer>();
    public static HashMap<String, Integer> greenobbymap = new HashMap<String, Integer>();
    public static HashMap<String, Integer> blueobbymap = new HashMap<String, Integer>();
    public static HashMap<String, Integer> yellowobbymap = new HashMap<String, Integer>();

    public static HashMap<String, Integer> redwoodmap = new HashMap<String, Integer>();
    public static HashMap<String, Integer> greenwoodmap = new HashMap<String, Integer>();
    public static HashMap<String, Integer> bluewoodmap = new HashMap<String, Integer>();
    public static HashMap<String, Integer> yellowwoodmap = new HashMap<String, Integer>();

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        Player player = (Player) event.getEntity().getShooter();


        if (entity instanceof Arrow) {
            Location loc = entity.getLocation();
            Vector vec = entity.getVelocity();
            Location loc2 = new Location(loc.getWorld(), loc.getX() + vec.getX(), loc.getY() + vec.getY(), loc.getZ() + vec.getZ());
            event.getEntity().remove();

            int ir = redobbymap.get(String.valueOf(player));
            int ig = greenobbymap.get(String.valueOf(player));
            int ib = blueobbymap.get(String.valueOf(player));
            int iy = yellowobbymap.get(String.valueOf(player));

            int irw = redwoodmap.get(String.valueOf(player));
            int igw = greenwoodmap.get(String.valueOf(player));
            int ibw = bluewoodmap.get(String.valueOf(player));
            int iyw = yellowwoodmap.get(String.valueOf(player));

            //if(redblock.size() <= 5) {
            //if(teamSystem.red.hasPlayer(player)) {
            //player.setGameMode(GameMode.SPECTATOR);
            //}

            if (redblock.contains(loc2.getBlock())) {
                if (teamSystem.red.hasPlayer(player)) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can not damage your own boat"));
                } else if (loc2.getBlock().getType() == Material.IRON_BLOCK) {
                    redobbymap.put(String.valueOf(player), ir + 1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You've hit Iron Block " + ir + "/5"));
                    manager.addCurrencyToPlayer(player, 20);
                    player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 20$")));

                } else if (loc2.getBlock().getType() == Material.OAK_LOG){
                    redwoodmap.put(String.valueOf(player), irw +1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You've hit Oak Log " + irw + "/2"));
                    manager.addCurrencyToPlayer(player, 10);
                    player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 10$")));
                }
                if (ir == 5) {
                    if(loc2.getBlock().getType() == Material.IRON_BLOCK) {
                        loc2.getBlock().setType(Material.AIR);
                        redblock.remove(loc2);
                        redobbymap.replace(String.valueOf(player), 1);
                        manager.addCurrencyToPlayer(player, 50);
                        player.sendMessage(Utils.chat((ChatColor.GOLD + "You received 50$")));
                    }
                }
                if (irw == 2) {
                    if(loc2.getBlock().getType() == Material.OAK_LOG) {
                        loc2.getBlock().setType(Material.AIR);
                        redblock.remove(loc2);
                        redwoodmap.replace(String.valueOf(player), 1);
                        manager.addCurrencyToPlayer(player, 20);
                        player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 20$")));
                    }
                }
            }
            //}


            if (blueblock.contains(loc2.getBlock())) {
                if (teamSystem.blue.hasPlayer(player)) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can not damage your own boat"));
                } else if (loc2.getBlock().getType() == Material.IRON_BLOCK) {
                    blueobbymap.put(String.valueOf(player), ib + 1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.BLUE + "You've hit Iron Block " + ib + "/5"));
                    manager.addCurrencyToPlayer(player, 20);
                    player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 20$")));

                } else if (loc2.getBlock().getType() == Material.OAK_LOG){
                    bluewoodmap.put(String.valueOf(player), ibw +1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.BLUE + "You've hit Oak Log " + ibw + "/2"));
                    manager.addCurrencyToPlayer(player, 10);
                    player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 10$")));
                }
                if (ib == 5) {
                    if(loc2.getBlock().getType() == Material.IRON_BLOCK) {
                        loc2.getBlock().setType(Material.AIR);
                        blueblock.remove(loc2);
                        manager.addCurrencyToPlayer(player, 50);
                        player.sendMessage(Utils.chat((ChatColor.GOLD + "You received 50$")));
                    }
                }
                if (ibw == 2) {
                    if(loc2.getBlock().getType() == Material.OAK_LOG) {
                        loc2.getBlock().setType(Material.AIR);
                        redblock.remove(loc2);
                        bluewoodmap.replace(String.valueOf(player), 1);
                        manager.addCurrencyToPlayer(player, 20);
                        player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 20$")));
                    }
                }
            }


            if (greenblock.contains(loc2.getBlock())) {
                if (teamSystem.green.hasPlayer(player)) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can not damage your own boat"));
                } else if (loc2.getBlock().getType() == Material.IRON_BLOCK) {
                    greenobbymap.put(String.valueOf(player), ig + 1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "You've hit obsidian " + ig + "/5"));
                    manager.addCurrencyToPlayer(player, 20);
                    player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 20$")));

                } else if (loc2.getBlock().getType() == Material.OAK_LOG){
                    greenwoodmap.put(String.valueOf(player), igw +1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "You've hit Oak Log " + igw + "/2"));
                    manager.addCurrencyToPlayer(player, 10);
                    player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 10$")));
                }
                if (ig == 5) {
                    if(loc2.getBlock().getType() == Material.IRON_BLOCK) {
                        loc2.getBlock().setType(Material.AIR);
                        greenblock.remove(loc2);
                        greenobbymap.replace(String.valueOf(player), 1);
                        manager.addCurrencyToPlayer(player, 50);
                        player.sendMessage(Utils.chat((ChatColor.GOLD + "You received 50$")));
                    }
                }
                if (igw == 2) {
                    if(loc2.getBlock().getType() == Material.OAK_LOG) {
                        loc2.getBlock().setType(Material.AIR);
                        redblock.remove(loc2);
                        greenwoodmap.replace(String.valueOf(player), 1);
                        manager.addCurrencyToPlayer(player, 20);
                        player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 20$")));
                    }
                }
            }

            if (yellowblock.contains(loc2.getBlock())) {
                if (teamSystem.yellow.hasPlayer(player)) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can not damage your own boat"));
                } else if (loc2.getBlock().getType() == Material.IRON_BLOCK) {
                    yellowobbymap.put(String.valueOf(player), iy + 1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.YELLOW + "You've hit Iron Block " + iy + "/5"));
                    manager.addCurrencyToPlayer(player, 20);
                    player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 20$")));

                } else if (loc2.getBlock().getType() == Material.OAK_LOG){
                    yellowwoodmap.put(String.valueOf(player), iyw +1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.YELLOW + "You've hit Oak Log " + iyw + "/2"));
                    manager.addCurrencyToPlayer(player, 10);
                    player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 10$")));

                }
                if (iy == 5) {
                    if(loc2.getBlock().getType() == Material.IRON_BLOCK) {
                        loc2.getBlock().setType(Material.AIR);
                        yellowblock.remove(loc2);
                        yellowobbymap.replace(String.valueOf(player), 1);
                        manager.addCurrencyToPlayer(player, 50);
                        player.sendMessage(Utils.chat((ChatColor.GOLD + "You received 50$")));
                    }
                }
                if (iyw == 2) {
                    if(loc2.getBlock().getType() == Material.OAK_LOG) {
                        loc2.getBlock().setType(Material.AIR);
                        yellowblock.remove(loc2);
                        yellowwoodmap.replace(String.valueOf(player), 1);
                        manager.addCurrencyToPlayer(player, 20);
                        player.sendMessage(Utils.chat((ChatColor.GOLD + "Hit! You received 20$")));
                    }
                }
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
