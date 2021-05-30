package com.seawars.events;

import com.seawars.Seawars;
import com.seawars.commands.setRegion;
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

    String prefix = plugin.prefix;

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
    public void countdown(Player player) {
            Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Game starting in " + count + " seconds..");
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (count == 0) {
                        // Start game method
                        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Teleporting players to arena");
                        setRegion.startGame(player);
                        cancel(); // Cancels the timer
                    } else {
                        count--;
                        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Game starting in " + count + " seconds..");
                    }
                }
            }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Seawars"), 20L, 20L);
        }

}
