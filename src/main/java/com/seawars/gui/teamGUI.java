package com.seawars.gui;

import com.seawars.Seawars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class teamGUI implements Listener {
    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4 * 9;

    private static Seawars plugin;

    public teamGUI(Seawars plugin) {
        this.plugin = plugin;
    }

    public static void initialize() {
        inventory_name = Utils.chat("&2&4 Teams Select");
        inv = Bukkit.createInventory(null, inv_rows);

    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        //  Create item here            (inv, item, ammount, slot, displayname,)
        Utils.createItemByte(inv, "wool", 14, 1, 1, "Red Team");
        Utils.createItemByte(inv, "wool", 4, 1, 2, "Yellow Team");
        Utils.createItemByte(inv, "wool", 5, 1, 3, "Green Team");
        Utils.createItemByte(inv, "wool", 11, 1, 4, "Blue Team");

        //utils.createitemByte

        toReturn.setContents(inv.getContents());
        return toReturn;

    }



    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Red Team"))) {
            TEAMS.red.addPlayer(p);
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Blue Team"))) {
            TEAMS.blue.addPlayer(p);
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Yellow Team"))) {
            TEAMS.yellow.addPlayer(p);

        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Green Team"))) {
            TEAMS.green.addPlayer(p);

        }



    }





}


