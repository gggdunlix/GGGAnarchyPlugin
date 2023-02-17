package ml.gggrealms.gggmcanarchy;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.HSVLike;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;
import org.w3c.dom.css.RGBColor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Set;

public class AnarchyPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getConsoleSender().sendMessage("----- GGG MC Anarchy Plugin -----");
        Bukkit.getServer().getConsoleSender().sendMessage("Activating plugin");

        //Need to write code to create (if not already) scoreboard objectives that can track the wanted level, money amount, etc.
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective money = scoreboard.registerNewObjective("money", Criteria.DUMMY, "$");

        this.getCommand("savequit").setExecutor(new SaveQuit());
        this.getCommand("class").setExecutor(new ClassChoose());
        this.getCommand("class").setTabCompleter(new classOnTabCompleteClass());



        Bukkit.getServer().getConsoleSender().sendMessage("Plugin now activated, version 0.0.1");
        Bukkit.getServer().getConsoleSender().sendMessage("Plugin Preliminary stage, all players are in creative build mode.");
        Bukkit.getServer().getConsoleSender().sendMessage("----- GGG MC Anarchy Plugin -----");
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        //LET THEM BUILD!
        Player joinedP = event.getPlayer();
        joinedP.sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
        joinedP.sendMessage(Component.text("So, What's going on here? \nWell, This is gonna be the start of an anarchy server. But not just any random one, One where players cannot mine or place things, just play the game with mechanics and a gamemode we implement ourselves."));
        joinedP.sendMessage(Component.text("Right now, we're gonna set you to creative and ask that you start building a city with buildings that would have a function, like stores, offices, houses, whatever you feel should fit. Eventually, the server will be playable and very fun.\nThanks, GGGDunlix & GGGCarl."));
        joinedP.setGameMode(GameMode.CREATIVE);
        Bukkit.getServer().setSpawnRadius(0);

        Boolean isDataSaved = false;
        // get if the player saved before quitting
        Set<String> tags = joinedP.getScoreboardTags();
        if (tags.contains("saved")) {
            isDataSaved = true;
        } else {
            isDataSaved = false;
        }

        if (isDataSaved) {
            joinedP.sendMessage(Component.text("Data loaded, welcome back!", TextColor.color(254, 214, 69)));
            joinedP.removeScoreboardTag("saved");

        } else {
            joinedP.performCommand("class");
            //make them rechoose class,
            joinedP.teleport(new Location(joinedP.getWorld(), 817, -13, -1030));
            // TP THem to the hub
            //clear their inv:
            PlayerInventory pli1= joinedP.getInventory();
            pli1.clear();
            joinedP.removeScoreboardTag("saved");
            joinedP.addScoreboardTag("isPlayerInHub");
            pli1.setHelmet(new ItemStack(Material.AIR));
            pli1.setChestplate(new ItemStack(Material.AIR));
            pli1.setLeggings(new ItemStack(Material.AIR));
            pli1.setBoots(new ItemStack(Material.AIR));
            joinedP.sendMessage(Component.text("No data saved, did you not use /savequit?", TextColor.color(HSVLike.fromRGB(255, 35, 10))));
        }

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective money = scoreboard.registerNewObjective("money", Criteria.DUMMY, "$");

        Score score = money.getScore(joinedP);
        score.setScore(score.getScore() + 0);
        money.setDisplaySlot(DisplaySlot.SIDEBAR);

    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getPlayer();
        //remove score for any wanted level
        

    }
    
    @EventHandler
    public void onPlayerPostRespawn(PlayerPostRespawnEvent event) {




        Player spawnedPlayer = event.getPlayer();
        spawnedPlayer.teleport(new Location(spawnedPlayer.getWorld(), 817, -13, -1030));
        spawnedPlayer.addScoreboardTag("isPlayerInHub");


    }

}
