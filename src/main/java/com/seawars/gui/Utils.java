package me.drunkcactus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class Utils {

    public static String chat(String s){
        return ChatColor.translateAlternateColorCodes('&' , s);

    }
    public static ItemStack createItem(Inventory inv, String materialString, int amount, int invSlot, String displayName, String... lorestring) {

        ItemStack item;
        List<String> lore = new ArrayList<>();
        item = new ItemStack(Material.matchMaterial(materialString), amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : lorestring) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot - 1, item);
        return item;
    }

    public static ItemStack createItemByte(Inventory inv, String materialString, int byteId, int amount, int invSlot, String displayName, String... lorestring) {

        ItemStack item;
        List<String> lore = new ArrayList<>();
        item = new ItemStack(Material.matchMaterial(materialString), amount , (short) byteId);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : lorestring) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot - 1, item);
        return item;
    }



}