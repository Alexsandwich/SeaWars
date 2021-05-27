package com.seawars;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Seawars extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Seawars has been enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Seawars has been disabled!");
    }
}
