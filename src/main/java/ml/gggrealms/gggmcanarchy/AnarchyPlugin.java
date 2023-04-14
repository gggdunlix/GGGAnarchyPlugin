package ml.gggrealms.gggmcanarchy;
/*
Changes in current version so far:

*/
import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.google.common.collect.Lists;
import ml.gggrealms.gggmcanarchy.Properties.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


public class AnarchyPlugin extends JavaPlugin implements Listener {

    public static AnarchyPlugin plugin;
    private Lang lang;

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
        this.getCommand("partychat").setExecutor(new PartyChat());
        this.getCommand("editcfg").setExecutor(new EditCfg());
        this.getCommand("bugreport").setExecutor(new BugreportCommand());
        this.getCommand("enter").setExecutor(new EnterCommand());
        this.getCommand("retire").setExecutor(new RetireCommand());
        this.getCommand("money").setExecutor(new MoneyCommand());
        this.getCommand("sell").setExecutor(new SellCommand());
        this.getCommand("buy").setExecutor(new BuyCommand());
        this.getCommand("join").setExecutor(new JoinCommand());
        this.getCommand("stash").setExecutor(new StashCommand());
        this.getCommand("properties").setExecutor(new PropertiesCommand());
        this.getCommand("safe").setExecutor(new SafeCommand());
        this.getCommand("safe").setTabCompleter(new SafeTabCompleter());
        this.getCommand("givemoney").setExecutor(new GiveMoneyCommand());
        this.getCommand("anarcrafthelp").setExecutor(new HelpCommand());
        this.getCommand("plist").setExecutor(new PListCommand());
        this.getCommand("autospawn").setExecutor(new AutospawnCommand());
        this.getCommand("autospawn").setTabCompleter(new AutospawnTabCompleter());
        this.getCommand("class").setExecutor(new ClassChoose());
        this.getCommand("class").setTabCompleter(new classOnTabCompleteClass());
        this.getCommand("party").setExecutor(new PartyCommand());
        this.getCommand("party").setTabCompleter(new PartyCommandTab());
        this.getCommand("propertyability").setExecutor(new PropertyAbilityCommand());
        this.getCommand("propertyability").setTabCompleter(new PropertyAbilityCommandTab());


        Bukkit.getServer().getConsoleSender().sendMessage("Plugin now activated, version 0.0.1");
        Bukkit.getServer().getConsoleSender().sendMessage("Plugin Preliminary stage, all players are in creative build mode.");
        Bukkit.getServer().getConsoleSender().sendMessage("----- GGG MC Anarchy Plugin -----");
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        //LET THEM BUILD!
        Player joinedP = event.getPlayer();

        //joinedP.sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
        //joinedP.sendMessage(Component.text("So, What's going on here? \nWell, This is gonna be the start of an anarchy server. But not just any random one, One where players cannot mine or place things, just play the game with mechanics and a gamemode we implement ourselves."));
        //joinedP.sendMessage(Component.text("Right now, we're gonna set you to creative and ask that you start building a city with buildings that would have a function, like stores, offices, houses, whatever you feel should fit. Eventually, the server will be playable and very fun.\nThanks, GGGDunlix & GGGCarl."));
        if (joinedP.isOp()) joinedP.sendMessage(lang.opJoinMessage);
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

        if (customConfig.get("players." + pUUID + ".autospawn") == null) {
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
            joinedP.sendMessage(lang.dataLoadedFromSave);
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
            joinedP.sendMessage(lang.noDataLoadedMessage);
            joinedP.removeScoreboardTag("classFarmer");
            joinedP.removeScoreboardTag("classDefault");
            joinedP.removeScoreboardTag("classRider");
            joinedP.removeScoreboardTag("classHorseman");
            joinedP.removeScoreboardTag("classBoater");
            joinedP.removeScoreboardTag("classThug");
            joinedP.removeScoreboardTag("classCrackhead");
            joinedP.removeScoreboardTag("classDev");
            joinedP.removeScoreboardTag("classFisherman");
            joinedP.removeScoreboardTag("classBankrobber");

            joinedP.removeScoreboardTag("factionFarmer");
            joinedP.removeScoreboardTag("factionLeather");
            joinedP.removeScoreboardTag("factionArmor");
            joinedP.performCommand("class");
            Boolean autospawnOn = false;
            int autospawnRate = customConfig.getInt("players." + pUUID + ".autospawnRate");

            if (customConfig.getBoolean("players." + pUUID + ".autospawn", false)) {
                joinedP.sendMessage(lang.autoSpawnWarning(customConfig, "players." + pUUID + ".autospawnRate"));
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
                if (cfg.getInt("players." + pUUID + ".horsemanCooldown") > 0) {
                    int currentCoolNum = cfg.getInt("players." + pUUID + ".horsemanCooldown");
                    cfg.set("players." + pUUID + ".horsemanCooldown", currentCoolNum - 1);
                }
                //FARMER Faction and Class Cooldown
                if (cfg.getInt("players." + pUUID + ".farmerCooldown") > 0) {
                    int currentCoolNum = cfg.getInt("players." + pUUID + ".farmerCooldown");
                    cfg.set("players." + pUUID + ".farmerCooldown", currentCoolNum - 1);
                }
                //Boat spawner Cooldown
                if (cfg.getInt("players." + pUUID + ".boatDelay") > 0) {
                    int currentCoolNum = cfg.getInt("players." + pUUID + ".boatDelay");
                    cfg.set("players." + pUUID + ".boatDelay", currentCoolNum - 1);
                }
                //Fisherman Cooldown
                if (cfg.getInt("players." + pUUID + ".fishermanCooldown") > 0) {
                    int currentCoolNum = cfg.getInt("players." + pUUID + ".fishermanCooldown");
                    cfg.set("players." + pUUID + ".fishermanCooldown", currentCoolNum - 1);
                }
                //Thug Cooldown
                if (cfg.getInt("players." + pUUID + ".thugCooldown") > 0) {
                    int currentCoolNum = cfg.getInt("players." + pUUID + ".thugCooldown");
                    cfg.set("players." + pUUID + ".thugCooldown", currentCoolNum - 1);
                }
                saveConfigFile();
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 20, 20);
        FastBoard board = new FastBoard(joinedP);
        board.updateTitle(lang.scoreboardTitle);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getFaction(joinedP).equals("farmer")) {
                    if (joinedP.getFoodLevel() <= 8) {
                        joinedP.setFoodLevel(8);
                    }
                }
                if (joinedP.getScoreboardTags().contains("isPlayerInHub")) {
                    joinedP.setInvisible(true);
                } else {
                    joinedP.setInvisible(false);
                }
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
                board.updateLine(3, "Rank: " + getRank(joinedP));
                int propCount = getPropCount(joinedP);
                if (propCount > 3) {
                    board.updateLine(4, "# Props: " + ChatColor.RED + propCount);
                } else board.updateLine(4, "# Props: " + propCount);

            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 1, 1);
    }
    public int getPropCount(Player player) {
        Set<String> tags = player.getScoreboardTags();
        int propertyCount = 0;
        Property[] allProps = lang.allProps;
        for (Property prop : allProps) {
            String namespace = prop.getInfo().getNamespace();
            if (tags.contains("propOwned." + namespace)) {
                propertyCount++;
            }
        }
        return propertyCount;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getPlayer();
        //remove score for any wanted level
        deadPlayer.removeScoreboardTag("classFarmer");
        deadPlayer.removeScoreboardTag("classDefault");
        deadPlayer.removeScoreboardTag("classRider");
        deadPlayer.removeScoreboardTag("classHorseman");
        deadPlayer.removeScoreboardTag("classBoater");
        deadPlayer.removeScoreboardTag("classThug");
        deadPlayer.removeScoreboardTag("classCrackhead");
        deadPlayer.removeScoreboardTag("classDev");
        deadPlayer.removeScoreboardTag("classFisherman");
        deadPlayer.removeScoreboardTag("classBankrobber");

        deadPlayer.removeScoreboardTag("factionFarmer");
        deadPlayer.removeScoreboardTag("factionAgent");
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
        spawnedPlayer.setGameMode(GameMode.ADVENTURE);
        UUID pUUID = spawnedPlayer.identity().uuid();
        FileConfiguration customConfig = getConfigFile();
        Boolean autospawnOn = false;
        int autospawnRate = customConfig.getInt("players." + pUUID + ".autospawnRate");

        if (customConfig.getBoolean("players." + pUUID + ".autospawn")) {
            spawnedPlayer.sendMessage(lang.autoRespawnWarning(autospawnRate));
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (customConfig.getBoolean("players." + pUUID + ".autospawn")) {
                        if (spawnedPlayer.getScoreboardTags().contains("isPlayerInHub")) {
                            spawnedPlayer.performCommand("class default");
                        }
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
        if (tags.contains("factionAgent")) {
            faction = "agent";
        }
        if (tags.contains("factionArmor")) {
            faction = "armorer";
        }
        return faction;
    }
    public String getRank(Player p) {
        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
        UUID pU = p.identity().uuid();
        int rank = cfg.getInt("players." + pU + ".rank");
        String toReturn = "";
        if (rank == 0) {
            toReturn = ChatColor.BLACK + Integer.toString(rank);
        } else if (rank < 5000){
            toReturn = ChatColor.DARK_GRAY + Integer.toString(rank);
        } else if (rank < 15000){
            toReturn = ChatColor.GRAY + Integer.toString(rank);
        } else if (rank < 35000){
            toReturn = ChatColor.WHITE + Integer.toString(rank);
        } else if (rank < 60000){
            toReturn = ChatColor.GREEN + Integer.toString(rank);
        } else if (rank < 100000){
            toReturn = ChatColor.AQUA + Integer.toString(rank);
        } else if (rank >= 100000) {
            toReturn = ChatColor.YELLOW + Integer.toString(rank);
        }
        return toReturn;
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
    public void onCloseInv(InventoryCloseEvent event) {
        Inventory i = event.getInventory();
        InventoryView inventoryView = event.getView();
        Player p = (Player) event.getPlayer();
        UUID pU = p.identity().uuid();
        if (inventoryView.getTitle().equals(lang.cheapAptStashTitle)) {
            p.sendMessage(lang.stashSaved);
            FileConfiguration cfg = getConfigFile();
            cfg.set("stashes." + pU + ".cheapAptStash", inventoryView.getTopInventory());
        }
        if (inventoryView.getTitle().equals(lang.USBankOfficeStashTitle)) {
            p.sendMessage(lang.stashSaved);
            FileConfiguration cfg = getConfigFile();
            cfg.set("stashes." + pU + ".USBankOfficeStash", inventoryView.getTopInventory());
        }
        if (inventoryView.getTitle().equals(lang.docksOfficeStash1Title)) {
            p.sendMessage(lang.stashSaved);
            FileConfiguration cfg = getConfigFile();
            cfg.set("stashes." + pU + ".docksStash1", inventoryView.getTopInventory());
        }
        if (inventoryView.getTitle().equals(lang.docksOfficeStash2Title)) {
            p.sendMessage(lang.stashSaved);
            FileConfiguration cfg = getConfigFile();
            cfg.set("stashes." + pU + ".docksStash2", inventoryView.getTopInventory());
        }
        if (inventoryView.getTitle().equals(lang.docksOfficeStash3Title)) {
            p.sendMessage(lang.stashSaved);
            FileConfiguration cfg = getConfigFile();
            cfg.set("stashes." + pU + ".docksStash3", inventoryView.getTopInventory());
        }
        if (inventoryView.getTitle().equals(lang.farmStashTitle)) {
            p.sendMessage(lang.stashSaved);
            FileConfiguration cfg = getConfigFile();
            cfg.set("stashes." + pU + ".farmStash", inventoryView.getTopInventory());
        }
        saveConfigFile();
    }
    @EventHandler
    public void onCraft(CraftItemEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (getFaction(p).equals("farmer")) {
            p.sendMessage("isFarmer");
            if (event.getRecipe().getResult().getType().toString().equals("PUMPKIN_SEEDS") || event.getRecipe().getResult().getType().toString().equals("MELON_SEEDS")) {
                ItemStack res = event.getRecipe().getResult();
                ItemMeta resultMeta = res.getItemMeta();
                resultMeta.setPlaceableKeys(Lists.newArrayList(Material.FARMLAND.getKey(), Material.CALCITE.getKey()));
                res.setItemMeta(resultMeta);
            }
        }
    }
    @EventHandler
    public void onInvItemClick(InventoryClickEvent event) {
        Player whoClicked = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        Boolean dontTake = false;
        // click Bounty item in stilt pc
        int finalBountySize = lang.bountyBase;
        int finalBountyPrice = finalBountySize * lang.bountyPriceFactor;
        String finalBountyPlayer = "";
        if (clickedItem.getItemMeta().lore().contains(lang.bountyAppLore)) {
            dontTake = true;
            event.getClickedInventory().close();
            whoClicked.closeInventory();
            Inventory playerChooseBounty = Bukkit.createInventory(null, 54, lang.bountyPlayerChoiceDisplayName);
            int playerCount = Bukkit.getOnlinePlayers().size();
            if (playerCount <= 54) {
                ArrayList<Player> players = (ArrayList<Player>) Bukkit.getOnlinePlayers();
                for (Player p : players) {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setOwningPlayer(p);
                    skullMeta.displayName(Component.text(p.name() + ""));
                    List<Component> skullLore = new ArrayList();
                    skullLore.add(lang.bountyPlayerSkullLore1);
                    skullLore.add(Component.text(p.name().toString()));
                    skull.setItemMeta(skullMeta);
                    playerChooseBounty.addItem(skull);
                }
            }
            whoClicked.openInventory(playerChooseBounty);
        }
        //When clicked on player in bounty menu
        if (clickedItem.lore().get(0).equals(lang.bountyPlayerSkullLore1)) {
            dontTake = true;
            String pName = PlainTextComponentSerializer.plainText().serialize(clickedItem.lore().get(1));
            Player setBountyOn = Bukkit.getPlayer(pName);
            FileConfiguration cfg = getConfigFile();
            UUID toSetUUID = setBountyOn.identity().uuid();
            int money = cfg.getInt("players." + whoClicked.identity().uuid() + ".money", 0);
            if (cfg.getInt("players." + toSetUUID + ".bounty", 0) > 0) {
                whoClicked.closeInventory();
                Inventory chooseBountyPrice = Bukkit.createInventory(null, 54, lang.bountyChooseAmtWindow);
                for (int i = 0; i <= 8; i++) {
                    int bounty = lang.bountyBase + (i*lang.bountyBase);
                    int cost = bounty*lang.bountyPriceFactor;
                    ItemStack price;
                    if (money >= cost) {
                        price = new ItemStack(Material.REDSTONE);
                    } else {
                        price = new ItemStack(Material.BARRIER);
                    }
                    ItemMeta pricemeta = price.getItemMeta();
                    pricemeta.displayName(Component.text(pName + " : ").append(Component.text("$" + bounty, TextColor.color(0xFF6604))));
                    List<Component> priceMetaLore = new ArrayList();
                    priceMetaLore.add(Component.text("Cost: ").append(Component.text("$" + cost, TextColor.color(0xB9FF55))));
                    if (money < cost) {
                        priceMetaLore.add(lang.bountyChooseAmtLoreNotEnough);
                    } else {
                        priceMetaLore.add(lang.bountyChooseAmtLore);
                    }
                    pricemeta.lore(priceMetaLore);
                    price.setItemMeta(pricemeta);
                    finalBountyPrice = cost;
                    finalBountySize = bounty;
                    finalBountyPlayer = pName;
                }

            } else {
                whoClicked.sendMessage(lang.alreadyBoundError(pName));
            }
        }
        if (clickedItem.lore().get(1).equals(lang.bountyChooseAmtLore)) {
            FileConfiguration cfg = getConfigFile();
            UUID pUUID = whoClicked.identity().uuid();
            int money = cfg.getInt("players." + pUUID + ".money");
            money -= finalBountyPrice;
            Player setBountyOn = Bukkit.getPlayer(finalBountyPlayer);
            cfg.set("players." + pUUID + ".money", money);
            cfg.set("players." + setBountyOn.identity().uuid() + ".bounty", finalBountySize);
            setBountyOn.sendMessage(lang.bountySetAlert(finalBountySize));
        }
        if (dontTake) {
            event.setCancelled(true);
        }
        saveConfigFile();
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity recip = event.getEntity();
        UUID damagerUUID = damager.getUniqueId();
        UUID recipUUID = damager.getUniqueId();
        FileConfiguration cfg = getConfigFile();
        int damagerParty = cfg.getInt("players." + damagerUUID + ".party", -1);
        int recipParty = cfg.getInt("players." + recipUUID + ".party", -1);
        if (recip.getScoreboardTags().contains("isPlayerInHub")) {
            event.setCancelled(true);
        }
        if (damagerParty == recipParty && damagerParty != -1) {
            event.setCancelled(true);
            damager.sendMessage(lang.blockedFriendlyFire);
        }
    }
    @EventHandler
    public void onCommandRun(org.bukkit.event.player.PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        if (command.equals("help")) {
            event.setMessage("serverhelp");
        }
        if (command.equals("list")) {
            event.setMessage("plist");
        }
    }
}
