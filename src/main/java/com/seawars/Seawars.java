package com.seawars;

import com.seawars.commands.helpCommand;
import com.seawars.commands.setRegion;
import com.seawars.events.onJoin;
import com.seawars.events.onLeave;
import com.seawars.commands.Commands;
import com.seawars.gui.GUI;
import com.seawars.events.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Seawars extends JavaPlugin {

    private static Seawars instance;

    public FileConfiguration config;

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

        GUI.initialize();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Seawars has been disabled!");
        this.saveConfig();
    }
}
