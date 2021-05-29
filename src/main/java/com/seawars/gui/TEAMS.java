package com.seawars.gui;

import com.seawars.Seawars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;


public class TEAMS implements Listener {

    private Seawars plugin;


    public TEAMS(Seawars plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        createScoreboard(e.getPlayer());
        updateScoreboard();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        updateScoreboard();
    }


    @SuppressWarnings("deprecation")
    public void createScoreboard(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("Stats", "dummy");
        objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "--=Sea Wars=--");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team red = board.registerNewTeam("Red");
        Team blue = board.registerNewTeam("Blue");
        Team yellow = board.registerNewTeam("Yellow");

        red.setPrefix(ChatColor.RED + "[RED] " + ChatColor.WHITE);
        blue.setPrefix(ChatColor.BLUE + "[BLUE] " + ChatColor.WHITE);
        yellow.setPrefix(ChatColor.YELLOW + "[YELLOW] " + ChatColor.WHITE);

        red.setAllowFriendlyFire(false);
        red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        red.setCanSeeFriendlyInvisibles(false);

        red.addPlayer(player);
        //onlinePlayers
        Score score = objective.getScore("Players:");
        score.setScore(Bukkit.getOnlinePlayers().size());
        //money
        Score money = objective.getScore(ChatColor.GREEN + "Team: ");
        money.setScore(2);

        player.setScoreboard(board);
    }

    public void updateScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            //Score score = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("players:");
            //score.setScore(Bukkit.getOnlinePlayers().size());
        }
    }
}

