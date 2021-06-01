package com.seawars.gui;

import com.seawars.Seawars;
import com.seawars.currency.CurrencyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class cosmeticGUI implements Listener {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4 * 9;



    private static Seawars plugin;

    static String prefix = plugin.prefix;

    static CurrencyManager manager = new CurrencyManager(plugin);

    public cosmeticGUI(Seawars plugin) {
        this.plugin = plugin;
    }

    public static void initialize() {
        inventory_name = Utils.chat("&2&4 Cosmetics");
        inv = Bukkit.createInventory(null, inv_rows);

    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        //  Create item here            (inv, item, ammount, slot, displayname,)
        Utils.createItem(inv, "leather_helmet", 1, 1, ChatColor.GREEN + "" + ChatColor.BOLD + "Turtle Hat", "Cost: $5000");
        Utils.createItem(inv, "blaze_Rod", 1, 36, ChatColor.RED + "Next Page");


        //utils.createitemByte

        toReturn.setContents(inv.getContents());
        return toReturn;

    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(ChatColor.GREEN + "" + ChatColor.BOLD + "Turtle Hat"))) {
            if (manager.getPlayerCurrency(p) >= 5000) {
                ItemStack hat = new ItemStack(Material.TURTLE_HELMET);
                p.getInventory().setHelmet(hat);
                p.sendMessage(prefix + ChatColor.GREEN + "You bought a turtle hat!");
                manager.removeCurrencyFromPlayer(p, 5000);
            } else {
                p.sendMessage(ChatColor.RED + "You do not have enough money!");
            }

        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(ChatColor.RED + "Next Page"))) {
            p.closeInventory();

        }


    }
}






