package com.seawars.gui;

import com.seawars.Seawars;
import com.seawars.util.teamSystem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class teamGUI implements Listener {
    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4 * 9;

    private static Seawars plugin;

    static String prefix = plugin.prefix;

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
        Utils.createItemByte(inv, "wool", 14, 1, 11, "Red Team");
        Utils.createItemByte(inv, "wool", 4, 1, 13, "Yellow Team");
        Utils.createItemByte(inv, "wool", 5, 1, 15, "Green Team");
        Utils.createItemByte(inv, "wool", 11, 1, 17, "Blue Team");

        //utils.createitemByte

        toReturn.setContents(inv.getContents());
        return toReturn;

    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Red Team"))) {
            teamSystem.red.addPlayer(p);
            p.sendMessage(prefix + ChatColor.RED + "You've joined the red team!");
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You've joined the red team!"));
            p.closeInventory();
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Blue Team"))) {
            teamSystem.blue.addPlayer(p);
            p.sendMessage(prefix + ChatColor.BLUE + "You've joined the blue team!");
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.BLUE + "You've joined the blue team!"));
            p.closeInventory();
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Yellow Team"))) {
            teamSystem.yellow.addPlayer(p);
            p.sendMessage(prefix + ChatColor.YELLOW + "You've joined the yellow team!");
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.YELLOW + "You've joined the yellow team!"));
            p.closeInventory();

        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Green Team"))) {
            teamSystem.green.addPlayer(p);
            p.sendMessage(prefix + ChatColor.GREEN + "You've joined the green team!");
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "You've joined the green team!"));
            p.closeInventory();

        }



    }





}


