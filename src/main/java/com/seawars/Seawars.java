package com.seawars;

import com.seawars.commands.setRegion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Seawars extends JavaPlugin {



    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Seawars has been enabled!");
        this.getCommand("set").setExecutor(new setRegion());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Seawars has been disabled!");
    }
}
