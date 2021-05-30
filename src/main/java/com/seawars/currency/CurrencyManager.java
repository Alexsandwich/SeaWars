package me.drunkcactus;

import org.apache.logging.log4j.core.pattern.PlainTextRenderer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import javax.imageio.IIOException;
import java.io.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CurrencyManager {
    public static HashMap<UUID, Integer> currency = new HashMap<UUID, Integer>();
    public Main plugin;

    public CurrencyManager(Main plugin) {
        this.plugin = plugin;
    }

    public void saveCurrencyFile() throws FileNotFoundException, IOException {
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

            File file = new File("CurrencyData/Currency.dat");
            ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
            UUID uuid = p.getUniqueId();

            if (currency.get(uuid) != null) {
                currency.put(uuid, currency.get(uuid));

            }
            try {
                output.writeObject(currency);
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void loadCurrencyFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("CurrencyData/currency.dat");
        if (file != null) {
            ObjectInput input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
            Object readObject = input.readObject();
            input.close();
            if (!(readObject instanceof HashMap)) {
                throw new IOException("Data is not hashmap");

            }
            currency = (HashMap<UUID, Integer>) readObject;
            for (UUID key : currency.keySet()) {
                currency.put(key, currency.get(key));
            }


        }


    }

    public void addCurrencyToPlayer(OfflinePlayer p, int amount) {
        if (currency.get(p.getUniqueId()) != null) {
            currency.put(p.getUniqueId(), currency.get(p.getUniqueId()) + amount);
        } else {
            currency.put(p.getUniqueId(), amount);
        }


    }

    public void removeCurrencyFromPlayer(OfflinePlayer p, int amount) {
        if (currency.get(p.getUniqueId()) != null) {
            currency.put(p.getUniqueId(), currency.get(p.getUniqueId()) + amount);


        }
    }


    public void setPlayerCurrency(OfflinePlayer p, int amount) {
        currency.put(p.getUniqueId(), amount);

    }

    public int getPlayerCurrency(OfflinePlayer p){
        if (currency.get(p.getUniqueId()) !=null) {
            return currency.get(p.getUniqueId());

        }else{
            return 0;
        }


    }

}