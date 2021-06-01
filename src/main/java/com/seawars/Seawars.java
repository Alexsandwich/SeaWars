package com.seawars;

import com.seawars.commands.*;
import com.seawars.currency.CurrencyCommand;
import com.seawars.currency.CurrencyManager;
import com.seawars.events.blockPlace;
import com.seawars.events.onJoin;
import com.seawars.events.onLeave;
import com.seawars.files.DataManager;
import com.seawars.events.Listeners;
import com.seawars.gui.shopGUI;
import com.seawars.gui.teamGUI;
import com.seawars.util.teamSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public class Seawars extends JavaPlugin {

    public static Seawars instance;

    public FileConfiguration config;
    public DataManager data;

    public static String prefix = ChatColor.YELLOW + "[Sea Wars] ";


    public static Seawars getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Seawars has been enabled!");

        //Registering Commands
        this.getCommand("set").setExecutor(new setRegion(this));
        this.getCommand("help").setExecutor(new helpCommand());
        this.getCommand("balance").setExecutor(new getBalance(this));
        this.getCommand("test").setExecutor(new shopCommand(this));
        this.getCommand("team").setExecutor(new teamJoin(this));
        this.getCommand("restartCount").setExecutor(new restartCount());
        this.getCommand("currency").setExecutor(new CurrencyCommand(this));

        //Registering Listeners
        Bukkit.getPluginManager().registerEvents(new onJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new onLeave(), this);
        Bukkit.getPluginManager().registerEvents(new Listeners(this),this);
        Bukkit.getPluginManager().registerEvents(new teamSystem(this),this);
        Bukkit.getPluginManager().registerEvents(new blockPlace(this), this);

        //Setting up config
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.data = new DataManager(this);

        //Initializing GUI's
        shopGUI.initialize();
        teamGUI.initialize();

        instance = this;


        CurrencyManager currencyManager = new CurrencyManager(this);
        try {
            currencyManager.loadCurrencyFile();
        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
        registerManager();

        registerListeners();

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Seawars has been disabled!");
        this.saveConfig();

        CurrencyManager currencyManager = new CurrencyManager(this);
        try {
            currencyManager.saveCurrencyFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerManager(){
        new CurrencyManager(this);

    }
    public void registerListeners() {

    }
}
