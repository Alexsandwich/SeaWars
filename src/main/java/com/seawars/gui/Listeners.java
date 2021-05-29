package com.seawars.gui;

import com.seawars.Seawars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Listeners implements Listener {

    private Seawars plugin;

    public Listeners(Seawars plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        String title = e.getView().getTitle();
        if (title.equals(GUI.inventory_name)){
            e.setCancelled(true);
            if (e.getCurrentItem()== null) {
                return;
            }
            if (title.equals(GUI.inventory_name)){
                GUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
    }

}
