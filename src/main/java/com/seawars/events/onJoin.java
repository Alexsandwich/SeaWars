package com.seawars.events;

import com.seawars.commands.setRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.management.StringValueExp;
import java.util.ArrayList;

public class onJoin implements Listener {

    public static int players = 0;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Waiting for players to join");
        players = +1;
        player.sendMessage(String.valueOf(players));

        if(players == 4) {
            setRegion.startGame(player);

        }
    }
}
