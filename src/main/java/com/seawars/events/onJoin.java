package com.seawars.events;

import com.seawars.Seawars;
import com.seawars.commands.setRegion;
import com.seawars.util.RainbowText;
import com.seawars.util.teamSystem;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class onJoin implements Listener {

    Collection<? extends Player> players = Bukkit.getOnlinePlayers();

    static Seawars plugin;

    static int count = 20;
    static int gameCount = 90;

    static String prefix = plugin.prefix;

    public onJoin(Seawars instance) {
        plugin = instance;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(prefix + ChatColor.RED + "Waiting for players!");

        blockPlace.redobbymap.put(String.valueOf(player), 1);
        blockPlace.greenobbymap.put(String.valueOf(player), 1);
        blockPlace.yellowobbymap.put(String.valueOf(player), 1);
        blockPlace.blueobbymap.put(String.valueOf(player), 1);

        blockPlace.redwoodmap.put(String.valueOf(player), 1);
        blockPlace.bluewoodmap.put(String.valueOf(player), 1);
        blockPlace.greenwoodmap.put(String.valueOf(player), 1);
        blockPlace.yellowwoodmap.put(String.valueOf(player), 1);


        if(players.toArray().length == 2) {
            countdown(player);
        }
    }

    public static BossBar bossBar;


    //TODO Fix Countdown not restarting!
    public static void countdown(Player player) {
            Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Game starting in " + count + " seconds..");
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (count == 0) {
                        // Start game method
                        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Teleporting players to arena");
                        //setRegion.startGame(player);
                        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {

                            String name = onlinePlayer.getName();
                            teleport(onlinePlayer);
                        }
                        gameStart(player);
                        cancel(); // Cancels the timer
                    } else {
                        RainbowText text = new RainbowText("Teleporting players in ");
                        RainbowText text2 = new RainbowText(count + " seconds..");

                        for(Player player : Bukkit.getOnlinePlayers()) {
                            player.sendTitle(text.getText(), text2.getText(), 1, 20, 1);
                            text.moveRainbow();
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);
                        }
                        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Teleporting players in " + count + " seconds..");
                        count--;

                    }
                }
            }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Seawars"), 20L, 20L);
        }

        public static void teleport(Player player) {
            if(teamSystem.red.hasPlayer(player)) {
                player.teleport(plugin.getConfig().getLocation("red"));
            }
            if(teamSystem.yellow.hasPlayer(player)) {
                player.teleport(plugin.getConfig().getLocation("yellow"));
            }
            if(teamSystem.green.hasPlayer(player)) {
                player.teleport(plugin.getConfig().getLocation("green"));
            }
            if(teamSystem.blue.hasPlayer(player)) {
                player.teleport(plugin.getConfig().getLocation("blue"));
            }
        }


    public static void gameStart(Player player) {
        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Water Rises in " + gameCount + " seconds..");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (gameCount == 0) {
                    // Start game method
                    Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Water has risen! Game on!");
                    blockMove(player);
                    setRegion.startGame(player);
                    cancel(); // Cancels the timer
                } else {
                    gameCount--;
                    if (gameCount <= 10) {
                        RainbowText text3 = new RainbowText("Water Rising in ");
                        RainbowText text4 = new RainbowText(gameCount + " seconds..");

                        for(Player player : Bukkit.getOnlinePlayers()) {
                            player.sendTitle(text3.getText(), text4.getText(), 1, 20, 1);
                            text3.moveRainbow();
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);
                        }
                        Bukkit.broadcastMessage(prefix + ChatColor.YELLOW + "Water Rising in " + gameCount + " seconds..");
                    }
                    if(gameCount == 1) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 2.0F, 1.0F);
                        }
                    }
                }
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Seawars"), 20L, 20L);
    }

    //TODO remove and add block to team specific arrays
    public static void blockMove(Player player) {
        Location location1 = (Location) plugin.getConfig().get("region1");
        Location location2 = (Location) plugin.getConfig().get("region2");

        int y = (location1.getBlockY() < location2.getBlockY() ? location2.getBlockY() : location1.getBlockY());

        for (Location location : blockPlace.redblockloc) {
            int x = location.getBlockX();
            int z = location.getBlockZ();
            new Location(player.getWorld(), x, y + 1, z).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
            if(location.getBlock().getType()==Material.AIR) {
                blockPlace.redblock.remove(location.getBlock().getType());
            }

        }

        for (Location location : blockPlace.blueblockloc) {
            int xb = location.getBlockX();
            int zb = location.getBlockZ();
            new Location(player.getWorld(), xb, y + 1, zb).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
            if(location.getBlock().getType()==Material.AIR) {
                blockPlace.blueblock.remove(location.getBlock().getType());
            }
        }

        for (Location location : blockPlace.greenblockloc) {
            int xg = location.getBlockX();
            int zg = location.getBlockZ();
            new Location(player.getWorld(), xg, y + 1, zg).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
        }
        for (Location location : blockPlace.yellowblockloc) {
            int xy = location.getBlockX();
            int zy = location.getBlockZ();
            new Location(player.getWorld(), xy, y + 1, zy).getBlock().setType(location.getBlock().getType());
            location.getBlock().setType(Material.AIR);
        }
    }
}
