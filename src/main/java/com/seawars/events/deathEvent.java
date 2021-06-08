package com.seawars.events;

import com.seawars.Seawars;
import com.seawars.commands.setRegion;
import com.seawars.util.RainbowText;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class deathEvent implements Listener {

    private Seawars plugin;

    public deathEvent(Seawars plugin) {
        this.plugin = plugin;

    }


    //TODO Fix obtaining int from config
    int duration = plugin.getConfig().getInt("timer", 10);

    int deathCount = duration;

    //int deathCount = duration;

    //TODO Find better way to countdown for player death
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e){
        if (e.getEntity() instanceof Player){
            Player player = e.getEntity();
            player.spigot().respawn();
            player.setGameMode(GameMode.SPECTATOR);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (deathCount == 0) {
                            onJoin.teleport(player);
                            player.setGameMode(GameMode.SURVIVAL);
                        cancel();

                    } else {
                        deathCount--;
                        if (deathCount <= 10) {
                            RainbowText text3 = new RainbowText("Respawning in ");
                            RainbowText text4 = new RainbowText(deathCount + " seconds..");
                                player.sendTitle(text3.getText(), text4.getText(), 1, 20, 1);
                                text3.moveRainbow();
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);
                        }
                    }
                }
            }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Seawars"), 20L, 20L);
        }
    }

    public void reloadConfig() {
        plugin.saveDefaultConfig();
        plugin.config = plugin.getConfig();
        plugin.config.options().copyDefaults(true);
        plugin.saveConfig();
    }
}
