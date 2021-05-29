package com.seawars.gui;
import org.bukkit.plugin.java.JavaPlugin;



public class guiMain extends JavaPlugin {

    @Override
    public void onEnable() {

        new Listeners(this);
        new Commands(this);

        GUI.initialize();

    }



}
