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

public class onJoin implements Listener {

    public static int players = 0;

    static Seawars plugin;

    static int count = 20;


    public onJoin(Seawars instance) {
        plugin = instance;
    }

    //TODO fix player joining
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.RED + "Waiting for players!");
        players = +1;
        player.sendMessage(String.valueOf(players));

        if(players == 1) {
            countdown(player);
        }
    }

    //TODO Fix Countdown not restarting!
    public void countdown(Player player) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + count + " seconds..");
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (count == 0) {
                        // Start game method
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Teleporting players to arena");
                        setRegion.startGame(player);
                        cancel(); // Cancels the timer
                    } else {
                        count--;
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + count + " seconds..");
                    }
                }
            }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Seawars"), 20L, 20L);
        }

}
