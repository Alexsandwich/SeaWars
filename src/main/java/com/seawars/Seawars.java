package com.seawars;

import com.seawars.commands.*;
import com.seawars.events.onJoin;
import com.seawars.events.onLeave;
import com.seawars.files.DataManager;
import com.seawars.gui.GUI;
import com.seawars.events.Listeners;
import com.seawars.gui.TEAMS;
import com.seawars.gui.teamGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Seawars extends JavaPlugin {

    private static Seawars instance;

    public FileConfiguration config;
    public DataManager data;

    public static String prefix = ChatColor.YELLOW + "[Sea Wars] ";


    public static Seawars getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Seawars has been enabled!");
        this.getCommand("set").setExecutor(new setRegion(this));
        this.getCommand("help").setExecutor(new helpCommand());
        Bukkit.getPluginManager().registerEvents(new onJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new onLeave(), this);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.getCommand("test").setExecutor(new Commands(this));
        Bukkit.getPluginManager().registerEvents(new Listeners(this),this);
        this.getCommand("balance").setExecutor(new getBalance(this));
        Bukkit.getPluginManager().registerEvents(new TEAMS(this),this);

        this.getCommand("team").setExecutor(new teamJoin(this));
        this.data = new DataManager(this);

        GUI.initialize();
        teamGUI.initialize();


    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Seawars has been disabled!");
        this.saveConfig();
    }
}
