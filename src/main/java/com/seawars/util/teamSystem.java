package com.seawars.util;

import com.seawars.Seawars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;


public class teamSystem implements Listener {

    public static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static Scoreboard board = manager.getNewScoreboard();

    public static Team red = board.registerNewTeam("Red");
    public static Team blue = board.registerNewTeam("Blue");
    public static Team yellow = board.registerNewTeam("Yellow");
    public static Team green = board.registerNewTeam("Green");

    private Seawars plugin;


    public teamSystem(Seawars plugin) {
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
        Objective objective = board.registerNewObjective("Stats", "dummy");
        objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "--=Sea Wars=--");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);


        red.setPrefix(ChatColor.RED + "[RED] " + ChatColor.WHITE);
        blue.setPrefix(ChatColor.BLUE + "[BLUE] " + ChatColor.WHITE);
        yellow.setPrefix(ChatColor.YELLOW + "[YELLOW] " + ChatColor.WHITE);
        green.setPrefix(ChatColor.GREEN + "[GREEN] " + ChatColor.WHITE);

        red.setAllowFriendlyFire(false);
        red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        red.setCanSeeFriendlyInvisibles(false);

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

