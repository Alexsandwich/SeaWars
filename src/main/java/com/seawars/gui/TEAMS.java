package com.seawars.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;





public class TEAMS implements Listener {

        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent e) {
            createScoreboard(e.getPlayer());
            updateScoreboard();
        }

        @EventHandler
        public void onPlayerQuit(PlayerQuitEvent e) {
            updateScoreboard();
        }


        @SuppressWarnings("deprecation")
        public void createScoreboard(Player p) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();


            Team red = board.registerNewTeam("Red");
            Team blue = board.registerNewTeam("Blue");
            Team yellow = board.registerNewTeam("Yellow");

            red.setPrefix(ChatColor.RED + "[RED] " + ChatColor.WHITE);
            blue.setPrefix(ChatColor.BLUE + "[BLUE] " + ChatColor.WHITE);
            yellow.setPrefix(ChatColor.YELLOW + "[YELLOW] " + ChatColor.WHITE);

            red.setAllowFriendlyFire(false);
            red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
            red.setCanSeeFriendlyInvisibles(false);
            blue.setAllowFriendlyFire(false);
            blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
            blue.setCanSeeFriendlyInvisibles(false);
            yellow.setAllowFriendlyFire(false);
            yellow.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
            yellow.setCanSeeFriendlyInvisibles(false);


        }

        public void updateScoreboard() {
            for (Player online : Bukkit.getOnlinePlayers()) {
            }
        }

    }


