package com.seawars.database;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class MySQL {

    public static String host = "";
    public static String port = "3306";
    public static String database = "Main";
    public static String username = "******";
    public static String password = "*****";
    public static Connection con;

    static ConsoleCommandSender console = Bukkit.getConsoleSender();

    // connect
    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                console.sendMessage("\247c[\2476SeaWars\247c] \247bMySQL-Enabled!");
                //PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Players (Name VARCHAR(100),UUID VARCHAR(100),Kills INT(100),PRIMARY KEY (Name))");
                //ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // disconnect
    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                console.sendMessage("\247c[\2476SeaWars\247c]\247bMySQL-Disconnected!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // isConnected
    public static boolean isConnected() {
        return (con != null);
    }

    // getConnection
    public static Connection getConnection() {
        return con;
    }

    /***
     *
     * @param uuid stores the player uuid, connect their uuid to account
     * @param name player name
     * @param pkills amount of kills a player has
     * @throws SQLException Error message
     */

    public static void storePlayer(UUID uuid, String name, int pkills) throws SQLException {
        //PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT IGNORE INTO Players (Name,UUID,Kills) VALUES (?,?,?)");
        //ps.setString(1, name);
        //ps.setString(2, String.valueOf(uuid));
        //ps.setInt(3, pkills);
        //ps.executeUpdate();
    }
}