package com.seawars.events;

import com.seawars.Seawars;
import com.seawars.commands.setRegion;
import com.seawars.util.teamSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class onJoin implements Listener {

    Collection<? extends Player> players = Bukkit.getOnlinePlayers();

    static Seawars plugin;

    static int count = 20;
    static int gameCount = 90;

    static String prefix = plugin.prefix;

    public onJoin(Seawars instance) {
        plugin = instance;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(prefix + ChatColor.RED + "Waiting for players!");


        if(players.toArray().length == 2) {
            countdown(player);
        }
    }

    //TODO Fix Countdown not restarting!
    public static void countdown(Player player) {
            Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Game starting in " + count + " seconds..");
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (count == 0) {
                        // Start game method
                        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Teleporting players to arena");
                        //setRegion.startGame(player);
                        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {

                            String name = onlinePlayer.getName();
                            teleport(onlinePlayer);
                        }
                        gameStart(player);
                        cancel(); // Cancels the timer
                    } else {
                        count--;
                        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Teleporting players in " + count + " seconds..");
                    }
                }
            }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Seawars"), 20L, 20L);
        }

        public static void teleport(Player player) {
            if(teamSystem.red.hasPlayer(player)) {
                player.teleport(plugin.getConfig().getLocation("red"));
            }
            if(teamSystem.yellow.hasPlayer(player)) {
                player.teleport(plugin.getConfig().getLocation("yellow"));
            }
            if(teamSystem.green.hasPlayer(player)) {
                player.teleport(plugin.getConfig().getLocation("green"));
            }
            if(teamSystem.blue.hasPlayer(player)) {
                player.teleport(plugin.getConfig().getLocation("blue"));
            }
        }

    public static void gameStart(Player player) {
        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Water Rises in " + gameCount + " seconds..");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (gameCount == 0) {
                    // Start game method
                    Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Water has risen! Game on!");
                    setRegion.startGame(player);
                    cancel(); // Cancels the timer
                } else {
                    gameCount--;
                    if (gameCount <= 10) {
                        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Water Rising in " + gameCount + " seconds..");
                    }
                }
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Seawars"), 20L, 20L);
    }
}
