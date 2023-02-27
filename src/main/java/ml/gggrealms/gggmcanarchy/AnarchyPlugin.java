package ml.gggrealms.gggmcanarchy;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.google.common.collect.Lists;
import com.sun.tools.javac.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.HSVLike;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class AnarchyPlugin extends JavaPlugin implements Listener {

    public static AnarchyPlugin plugin;

    public void saveConfigFile() {
        this.saveConfig();
    }
    public FileConfiguration getConfigFile() {
        return this.getConfig();
    }
    @Override
    public void onEnable() {

        plugin = this;
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getConsoleSender().sendMessage("----- GGG MC Anarchy Plugin -----");
        Bukkit.getServer().getConsoleSender().sendMessage("Activating plugin");

        saveDefaultConfig();

        //City hall lightning
        new BukkitRunnable() {
            @Override
            public void run() {
                Entity light = Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), 823.476, 108.0, -1029.429), EntityType.LIGHTNING);
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 5*60*20, 5*60*20);

        this.getCommand("savequit").setExecutor(new SaveQuit());
        this.getCommand("bugreport").setExecutor(new BugreportCommand());
        this.getCommand("enter").setExecutor(new EnterCommand());
        this.getCommand("retire").setExecutor(new RetireCommand());
        this.getCommand("money").setExecutor(new MoneyCommand());
        this.getCommand("sell").setExecutor(new SellCommand());
        this.getCommand("buy").setExecutor(new BuyCommand());
        this.getCommand("join").setExecutor(new JoinCommand());
         this.getCommand("stash").setExecutor(new StashCommand());
        this.getCommand("safe").setExecutor(new SafeCommand());
        this.getCommand("safe").setTabCompleter(new SafeTabCompleter());
        this.getCommand("givemoney").setExecutor(new GiveMoneyCommand());
        this.getCommand("autospawn").setExecutor(new AutospawnCommand());
        this.getCommand("autospawn").setTabCompleter(new AutospawnTabCompleter());
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

        FileConfiguration customConfig = getConfigFile();

        String pUUID = joinedP.identity().uuid().toString();
        if (customConfig.getString("players." + pUUID) == null) {
            customConfig.set("players." + pUUID + ".prevName", joinedP.getName());
            customConfig.set("players." + pUUID + ".money", 0);
            customConfig.set("players." + pUUID + ".smallApt1Safe", 0);
            customConfig.set("players." + pUUID + ".medApt1Safe", 0);
            customConfig.set("players." + pUUID + ".highApt1Safe", 0);
            customConfig.set("players." + pUUID + ".bankSafe", 0);
            customConfig.set("players." + pUUID + ".riderCooldown", 0);

        }
        customConfig.set("players." + pUUID+ ".prevName", joinedP.getName());

        if (customConfig.get("players." + pUUID + "autospawn") == null) {
            customConfig.set("players." + pUUID + ".autospawn", true);
            customConfig.set("players." + pUUID + ".autospawnRate", 10);
        }


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
            joinedP.teleport(new Location(joinedP.getWorld(), 818, -13, -1031, -90, 0));
            customConfig.set("players." + pUUID + ".money", 0);
            PlayerInventory pli1= joinedP.getInventory();
            pli1.clear();
            joinedP.removeScoreboardTag("saved");
            joinedP.addScoreboardTag("isPlayerInHub");
            pli1.setHelmet(new ItemStack(Material.AIR));
            pli1.setChestplate(new ItemStack(Material.AIR));
            pli1.setLeggings(new ItemStack(Material.AIR));
            pli1.setBoots(new ItemStack(Material.AIR));
            joinedP.sendMessage(Component.text("No data saved, did you not use /savequit?", TextColor.color(HSVLike.fromRGB(255, 35, 10))));
            joinedP.removeScoreboardTag("classFarmer");
            joinedP.removeScoreboardTag("classDefault");
            joinedP.removeScoreboardTag("classRider");
            joinedP.removeScoreboardTag("classHorseman");
            joinedP.removeScoreboardTag("classDev");

            joinedP.removeScoreboardTag("factionFarmer");
            joinedP.removeScoreboardTag("factionLeather");
            joinedP.removeScoreboardTag("factionArmor");
            joinedP.performCommand("class");
            Boolean autospawnOn = false;
            int autospawnRate = customConfig.getInt("players." + pUUID + ".autospawnRate");

            if (customConfig.getBoolean("players." + pUUID + ".autospawn")) {
                joinedP.sendMessage("Automatically spawning as default in " + customConfig.getInt("players." + pUUID + ".autospawnRate"));
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (joinedP.getScoreboardTags().contains("isPlayerInHub")) {
                            joinedP.performCommand("class default");
                        }
                    }
                }.runTaskLater(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), customConfig.getInt("players." + pUUID + ".autospawnRate") * 20);
            }



        }


        new BukkitRunnable() {
            @Override
            public void run() {
                FileConfiguration cfg = getConfigFile();
                if (cfg.getInt("players." + pUUID + ".riderCooldown") > 0) {
                    int currentCoolNum = cfg.getInt("players." + pUUID + ".riderCooldown");
                    cfg.set("players." + pUUID + ".riderCooldown", currentCoolNum - 1);
                }
                //FARMER Faction and Class Cooldown
                if (cfg.getInt("players." + pUUID + ".farmerCooldown") > 0) {
                    int currentCoolNum = cfg.getInt("players." + pUUID + ".farmerCooldown");
                    cfg.set("players." + pUUID + ".farmerCooldown", currentCoolNum - 1);
                }
                saveConfigFile();
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 20, 20);
        FastBoard board = new FastBoard(joinedP);
        board.updateTitle("Info");
        new BukkitRunnable() {
            @Override
            public void run() {
                FileConfiguration cfg = getConfigFile();
                board.updateLine(0, "Faction: " + ChatColor.UNDERLINE + getFaction(joinedP));
                board.updateLine(1, "Money: " + ChatColor.GREEN + "$" + ChatColor.WHITE + cfg.getInt("players." + pUUID + ".money"));
                int ping = joinedP.getPing();
                if (ping < 100) {
                    board.updateLine(2, "Ping: " + ChatColor.GREEN + ping + ChatColor.WHITE + "ms");
                } else if (ping >= 100 && ping <= 200) {
                    board.updateLine(2, "Ping: " + ChatColor.WHITE + ping + ChatColor.WHITE + "ms");
                } else if (ping > 200 && ping < 400) {
                    board.updateLine(2, "Ping: " + ChatColor.YELLOW + ping + ChatColor.WHITE + "ms");
                } else if (ping >= 400 && ping < 600) {
                    board.updateLine(2, "Ping: " + ChatColor.GOLD + ping + ChatColor.WHITE + "ms");
                } else if (ping >= 600) {
                    board.updateLine(2, "Ping: " + ChatColor.DARK_RED + ping + ChatColor.WHITE + "ms");
                }

            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 1, 1);
    }
    public void startRiderCountdown(Player player) {
        FileConfiguration customConfig = getConfigFile();
        UUID pUUID = player.identity().uuid();
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

            }
        }, 20, 20);
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getPlayer();
        //remove score for any wanted level
        deadPlayer.removeScoreboardTag("classFarmer");
        deadPlayer.removeScoreboardTag("classDefault");
        deadPlayer.removeScoreboardTag("classRider");
        deadPlayer.removeScoreboardTag("classDev");

        deadPlayer.removeScoreboardTag("factionFarmer");
        deadPlayer.removeScoreboardTag("factionLeather");
        deadPlayer.removeScoreboardTag("factionArmor");
        editDataConfig("players."+ deadPlayer.identity().uuid() + ".money", 0);
        saveConfig();
    }
    
    @EventHandler
    public void onPlayerPostRespawn(PlayerPostRespawnEvent event) {
        Player spawnedPlayer = event.getPlayer();
        spawnedPlayer.teleport(new Location(spawnedPlayer.getWorld(), 818, -13, -1031, -90, 0));
        spawnedPlayer.addScoreboardTag("isPlayerInHub");

        UUID pUUID = spawnedPlayer.identity().uuid();
        FileConfiguration customConfig = getConfigFile();
        Boolean autospawnOn = false;
        int autospawnRate = customConfig.getInt("players." + pUUID + ".autospawnRate");

        if (customConfig.getBoolean("players." + pUUID + ".autospawn")) {
            spawnedPlayer.sendMessage("Automatically spawning as default in " + customConfig.getInt("players." + pUUID + ".autospawnRate"));
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (spawnedPlayer.getScoreboardTags().contains("isPlayerInHub")) {
                        spawnedPlayer.performCommand("class default");
                    }
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), customConfig.getInt("players." + pUUID + ".autospawnRate") * 20);
        }
    }
    public void editDataConfig(String datapath, Object value) {
        FileConfiguration config = getConfig();
        config.set(datapath, value);
        saveConfig();

    }
    public String getDataConfig(String datapath) {
        FileConfiguration config = getConfig();
        return config.getString(datapath);

    }
    public String getFaction(Player p) {
        Set<String> tags = p.getScoreboardTags();
        String faction = "none";
        if (tags.contains("factionFarmer")) {
            faction = "farmer";

        }
        if (tags.contains("factionLeather")) {
            faction = "leatherworker";
        }
        if (tags.contains("factionArmor")) {
            faction = "armorer";
        }
        return faction;
    }
    @EventHandler
    public void onBlockBreak(BlockDropItemEvent event) {
        Player p = event.getPlayer();
        if (getFaction(p).equals("farmer")) {
            ItemStack mainH = p.getInventory().getItemInMainHand();
            if (mainH.getType() == Material.WOODEN_HOE || mainH.getType() == Material.STONE_HOE) {
                List<Item> drops = event.getItems();
                for (Item stack : drops) {
                    ItemStack st = stack.getItemStack();
                    ItemMeta meta = st.getItemMeta();
                    meta.setPlaceableKeys(Lists.newArrayList(Material.FARMLAND.getKey()));
                    st.setItemMeta(meta);
                    stack.setItemStack(st);
                }
            }
        }
    }
    @EventHandler
    public void onCraft(CraftItemEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (getFaction(p).equals("farmer")) {
            if (event.getCurrentItem().getType().equals(Material.PUMPKIN) || event.getCurrentItem().getType().equals(Material.PUMPKIN)) {
                ItemStack res = event.getInventory().getResult();
                ItemMeta resultMeta = res.getItemMeta();
                resultMeta.setPlaceableKeys(Lists.newArrayList(Material.FARMLAND.getKey()));
                res.setItemMeta(resultMeta);
            }
        }
    }

}
