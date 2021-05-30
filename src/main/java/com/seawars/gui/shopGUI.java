package com.seawars.gui;

import com.seawars.Seawars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class shopGUI implements Listener {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4 * 9;

    private static Seawars plugin;

    public shopGUI(Seawars plugin) {
        this.plugin = plugin;
    }

    public static void initialize() {
        inventory_name = Utils.chat("&2&4 GUI");
        inv = Bukkit.createInventory(null, inv_rows);

    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        //  Create item here            (inv, item, ammount, slot, displayname,)
        Utils.createItem(inv, "wood_spade", 1, 1, "Shovel", "BRUH");
        Utils.createItem(inv, "iron_sword", 1, 2, "Iron Sword", "KILL");
        Utils.createItemByte(inv, "wood", 0, 4, 3, "Oak Planks", "WOOD");

        //utils.createitemByte

        toReturn.setContents(inv.getContents());
        return toReturn;

    }



    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Shovel"))) {
            ItemStack shovel = new ItemStack(Material.WOODEN_SHOVEL);
            p.getInventory().addItem(shovel);
            p.sendMessage(Utils.chat(" &8[&6*&8] &6&1You bought some drip "));
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Iron Sword"))) {
            ItemStack sword = new ItemStack(Material.IRON_SWORD);
            p.getInventory().addItem(sword);

            p.sendMessage(Utils.chat(" &8[&6*&8] &6&1You bought SWORD "));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Oak Planks"))) {
                ItemStack OAKPLANKS = new ItemStack(Material.OAK_PLANKS,4);
                p.getInventory().addItem(OAKPLANKS);

                p.sendMessage(Utils.chat(" You bought WOOD "));

        }



    }





}
