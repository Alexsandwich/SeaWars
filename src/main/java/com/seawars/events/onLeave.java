package com.seawars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        onJoin.players = -1;
    }
}
