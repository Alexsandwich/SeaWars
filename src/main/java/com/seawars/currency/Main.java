package me.drunkcactus;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.IIOException;
import java.io.IOException;


public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        CurrencyManager currencyManager = new CurrencyManager(this);
        try {
            currencyManager.loadCurrencyFile();
        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
        registerManager();
        registerCommands();
        registerListeners();
    }


    @Override
    public void onDisable(){
        CurrencyManager currencyManager = new CurrencyManager(this);
        try {
            currencyManager.saveCurrencyFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerManager(){
        new CurrencyManager(this);

    }
    public void registerCommands(){
        new CurrencyCommand(this);

    }
    public void registerListeners() {

    }
}







