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


public class shopGUI implements Listener {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4 * 9;


    private static Seawars plugin;

    static CurrencyManager manager = new CurrencyManager(plugin);

    public shopGUI(Seawars plugin) {
        this.plugin = plugin;
    }

    public static void initialize() {
        inventory_name = Utils.chat("Building Blocks and Weapons");
        inv = Bukkit.createInventory(null, inv_rows);

    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);
        Utils.createItem(inv, Material.OAK_PLANKS,  4, 1, "Oak Planks", "Basic Boat Material","Cost: 500$");
        Utils.createItem(inv, Material.OAK_LOG,  4 ,2, "Oak Logs", "Untreated Cheap Wood","Cost: 300$");
        Utils.createItem(inv, Material.WHITE_WOOL,  4 ,3, "Wool", "Basic Wool", "Cost: 100$");
        Utils.createItem(inv, Material.IRON_BLOCK, 2, 4 ,"Iron Blocks","Very Durable Material", "Cost: 500$");
        Utils.createItem(inv,Material.STONE_SWORD,1,19,"Stone Sword", "Cheap but effective", "Cost 100$");
        Utils.createItem(inv,Material.IRON_SWORD,1,20,"Iron Sword" , "Kill your enemies with your trusty sword", "Cost 200$");
        Utils.createItem(inv, Material.BLAZE_ROD, 1, 36, ChatColor.RED + "Next Page");
        toReturn.setContents(inv.getContents());
        return toReturn;
    }



    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Oak Planks"))) {
            if(manager.getPlayerCurrency(p) >= 500) {
                ItemStack oakplanks = new ItemStack(Material.OAK_PLANKS, 4);
                p.getInventory().addItem(oakplanks);
                p.sendMessage(Utils.chat((ChatColor.GOLD + "Purchase Successful")));
                manager.removeCurrencyFromPlayer(p, 500);
            } else {
                p.sendMessage("You do not have enough money");
            }
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Oak Logs"))) {
            if(manager.getPlayerCurrency(p) >= 300) {
                ItemStack oaklogs = new ItemStack(Material.OAK_LOG, 4);
                p.getInventory().addItem(oaklogs);
                p.sendMessage(Utils.chat((ChatColor.GOLD + "Purchase Successful")));
                manager.removeCurrencyFromPlayer(p, 300);
            } else {
                p.sendMessage("You do not have enough money");
            }
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Wool"))) {
            if(manager.getPlayerCurrency(p) >= 100) {
                ItemStack whitewool = new ItemStack(Material.WHITE_WOOL, 4);
                p.getInventory().addItem(whitewool);
                p.sendMessage(Utils.chat((ChatColor.GOLD + "Purchase Successful")));
                manager.removeCurrencyFromPlayer(p, 100);
            } else {
                p.sendMessage("You do not have enough money");
            }
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Iron Blocks"))) {
            if (manager.getPlayerCurrency(p) >= 500) {
                ItemStack ironblock = new ItemStack(Material.IRON_BLOCK, 2);
                p.getInventory().addItem(ironblock);
                p.sendMessage(Utils.chat((ChatColor.GOLD + "Purchase Successful")));
                manager.removeCurrencyFromPlayer(p, 500);
            } else {
                p.sendMessage("You do not have enough money");
            }
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Stone Sword"))) {
            if (manager.getPlayerCurrency(p) >= 100) {
                ItemStack stonesword = new ItemStack(Material.STONE_SWORD, 1);
                p.getInventory().addItem(stonesword);
                p.sendMessage(Utils.chat((ChatColor.GOLD + "Purchase Successful")));
                manager.removeCurrencyFromPlayer(p, 100);
            } else {
                p.sendMessage("You do not have enough money");
            }
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Iron Sword"))) {
            if (manager.getPlayerCurrency(p) >= 200) {
                ItemStack ironsword = new ItemStack(Material.IRON_SWORD, 1);
                p.getInventory().addItem(ironsword);
                p.sendMessage(Utils.chat((ChatColor.GOLD + "Purchase Successful")));
                manager.removeCurrencyFromPlayer(p, 200);
            } else {
                p.sendMessage("You do not have enough money");
            }
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(ChatColor.RED + "Next Page"))) {
            p.closeInventory();
            p.openInventory(cosmeticGUI.GUI(p));
        }
    }
}
