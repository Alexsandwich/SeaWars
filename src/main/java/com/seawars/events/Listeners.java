package com.seawars.events;

import com.seawars.Seawars;
import com.seawars.gui.cosmeticGUI;
import com.seawars.gui.shopGUI;
import com.seawars.gui.teamGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Listeners implements Listener {

    private Seawars plugin;

    public Listeners(Seawars plugin) {
        this.plugin = plugin;

    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        String title = e.getView().getTitle();
        if (title.equals(shopGUI.inventory_name)){
            e.setCancelled(true);
            if (e.getCurrentItem()== null) {
                return;
            }
            if (title.equals(shopGUI.inventory_name)){
                shopGUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
    }

    @EventHandler
    public void onClick2(InventoryClickEvent e){
        String title = e.getView().getTitle();
        if (title.equals(teamGUI.inventory_name)){
            e.setCancelled(true);
            if (e.getCurrentItem()== null) {
                return;
            }
            if (title.equals(teamGUI.inventory_name)){
                teamGUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
    }

    @EventHandler
    public void onClick3(InventoryClickEvent e){
        String title = e.getView().getTitle();
        if (title.equals(cosmeticGUI.inventory_name)){
            e.setCancelled(true);
            if (e.getCurrentItem()== null) {
                return;
            }
            if (title.equals(cosmeticGUI.inventory_name)){
                cosmeticGUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
    }

}

