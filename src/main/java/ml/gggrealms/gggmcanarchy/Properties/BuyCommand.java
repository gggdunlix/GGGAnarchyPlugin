package ml.gggrealms.gggmcanarchy.Properties;

import com.google.common.collect.Lists;
import ml.gggrealms.gggmcanarchy.AnarchyPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.common.initializedfields.qual.EnsuresInitializedFields;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BuyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            Player p = (Player) sender;
            World w = p.getWorld();
            UUID pU = p.identity().uuid();
            Set<String> tags = p.getScoreboardTags();
            if (locationIsInCuboid(p.getLocation(), new Location(w, 1043,68,-670), new Location(w, 1038, 62,-671))) {
                //buy tools thing at farm
                if (AnarchyPlugin.plugin.getFaction(p).equals("farmer")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pU + ".money");
                    if (money >= 500) {
                        money -= 500;
                        cfg.set("players." + pU + ".money", money);
                        AnarchyPlugin.plugin.saveConfigFile();
                        PlayerInventory i = p.getInventory();
                        ItemStack stone_hoe = new ItemStack(Material.STONE_HOE);
                        ItemMeta hoe_meta = stone_hoe.getItemMeta();
                        hoe_meta.setPlaceableKeys(Lists.newArrayList(Material.GRASS_BLOCK.getKey(), Material.DIRT.getKey()));
                        hoe_meta.setDestroyableKeys(Lists.newArrayList(Material.WHEAT.getKey(), Material.BEETROOTS.getKey(), Material.CARROTS.getKey(), Material.POTATOES.getKey(), Material.MELON_STEM.getKey(), Material.MELON.getKey(), Material.PUMPKIN.getKey(), Material.PUMPKIN_STEM.getKey(), Material.SWEET_BERRY_BUSH.getKey()));
                        stone_hoe.setItemMeta(hoe_meta);
                        p.getInventory().addItem(stone_hoe);
                        ArrayList<ItemStack> seeds = new ArrayList<ItemStack>();
                        seeds.add(new ItemStack(Material.SWEET_BERRIES));
                        seeds.add(new ItemStack(Material.WHEAT_SEEDS));
                        seeds.add(new ItemStack(Material.CARROT));
                        seeds.add(new ItemStack(Material.POTATO));
                        seeds.add(new ItemStack(Material.BEETROOT_SEEDS));
                        seeds.add(new ItemStack(Material.MELON_SEEDS));
                        seeds.add(new ItemStack(Material.PUMPKIN_SEEDS));
                        for (ItemStack iS : seeds) {
                            ItemMeta m = iS.getItemMeta();
                            m.setPlaceableKeys(Lists.newArrayList(Material.FARMLAND.getKey()));
                            iS.setItemMeta(m);
                        }
                        int random_int2 = (int) Math.floor(Math.random() * ((seeds.size() - 1) - 0 + 1) + 0);
                        i.addItem(seeds.get(random_int2));
                    } else {
                        p.sendMessage(Component.text("Not enough money.", TextColor.color(210, 11, 37)));
                    }
                } else {
                    p.sendMessage(Component.text("You need to be a farmer to buy this.", TextColor.color(210, 11, 37)));
                }
            }
            else if (locationIsInCuboid(p.getLocation(), new Location(w, 830, 76, -933), new Location(w, 829, 72, -934))) {
                //buy cheap apartment
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                int money = cfg.getInt("players." + pU + ".money");
                if (!tags.contains("doesPlayerOwnCheapApt")) {
                    if (money >= 20000) {
                        money -= 20000;
                        cfg.set("players." + pU + ".money", money);
                        p.addScoreboardTag("doesPlayerOwnCheapApt");
                        cfg.set("players." + pU + ".rank", cfg.getInt("players." + pU + ".rank") + 1000);
                        p.performCommand("e");
                    } else {
                        p.sendMessage(Component.text("You do not have enough money. You need " + (20000 - money) + " more.", TextColor.color(210, 11, 37)));
                    }
                } else {
                    p.sendMessage(Component.text("You already own this apartment. Entering anyway...", TextColor.color(210, 11, 37)));
                    p.performCommand("e");
                }
                AnarchyPlugin.plugin.saveConfigFile();
            }
            else if (locationIsInCuboid(p.getLocation(), new Location(w, 684, 72, -830), new Location(w, 682, 69, -832))) {
                //buy Docks office
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                int money = cfg.getInt("players." + pU + ".money");
                if (!tags.contains("doesPlayerOwnDocksOffice")) {
                    if (money >= 650000) {
                        money -= 650000;
                        cfg.set("players." + pU + ".money", money);
                        p.addScoreboardTag("doesPlayerOwnDocksOffice");
                        cfg.set("players." + pU + ".rank", cfg.getInt("players." + pU + ".rank") + 35000);
                        p.performCommand("e");
                    } else {
                        p.sendMessage(Component.text("You do not have enough money. You need " + (650000 - money) + " more.", TextColor.color(210, 11, 37)));
                    }
                } else {
                    p.sendMessage(Component.text("You already own this office. Entering anyway...", TextColor.color(210, 11, 37)));
                    p.performCommand("e");
                }
                AnarchyPlugin.plugin.saveConfigFile();
            }
            else if (locationIsInCuboid(p.getLocation(), new Location(w, 924,92,-929), new Location(w, 922,88,-930))) {
                //US Bank Office apartment
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                int money = cfg.getInt("players." + pU + ".money");
                if (!tags.contains("doesPlayerOwnUSBankOffice")) {
                    if (money >= 300000) {
                        money -= 300000;
                        cfg.set("players." + pU + ".money", money);
                        cfg.set("players." + pU + ".rank", cfg.getInt("players." + pU + ".rank") + 20000);
                        p.addScoreboardTag("doesPlayerOwnUSBankOffice");
                        p.performCommand("e");
                    } else {
                        p.sendMessage(Component.text("You do not have enough money. You need $" + (300000 - money) + " more.", TextColor.color(210, 11, 37)));
                    }
                } else {
                    p.sendMessage(Component.text("You already own this office. Entering anyway...", TextColor.color(210, 11, 37)));
                    p.performCommand("e");
                }
                AnarchyPlugin.plugin.saveConfigFile();
            }
            else if (locationIsInCuboid(p.getLocation(), new Location(w, 1051,65,-669), new Location(w, 1048,62,-672))) {
                //Farm
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                int money = cfg.getInt("players." + pU + ".money");
                if (!tags.contains("doesPlayerOwnFarm")) {
                    if (money >= 150000) {
                        money -= 150000;
                        cfg.set("players." + pU + ".money", money);
                        cfg.set("players." + pU + ".rank", cfg.getInt("players." + pU + ".rank") + 5000);
                        p.addScoreboardTag("doesPlayerOwnFarm");
                        p.performCommand("e");
                    } else {
                        p.sendMessage(Component.text("You do not have enough money. You need $" + (150000 - money) + " more.", TextColor.color(210, 11, 37)));
                    }
                } else {
                    p.sendMessage(Component.text("You already own this farm. Entering anyway...", TextColor.color(210, 11, 37)));
                    p.performCommand("e");
                }
                AnarchyPlugin.plugin.saveConfigFile();
            }
            else if (locationIsInCuboid(p.getLocation(), new Location(w, 762,69,-599), new Location(w, 756,66,-601))) {
                //Bunker
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                int money = cfg.getInt("players." + pU + ".money");
                if (!tags.contains("doesPlayerOwnBunker")) {
                    if (money >= 1000000) {
                        money -= 1000000;
                        cfg.set("players." + pU + ".money", money);
                        cfg.set("players." + pU + ".rank", cfg.getInt("players." + pU + ".rank") + 50000);
                        p.addScoreboardTag("doesPlayerOwnBunker");
                        p.performCommand("e");
                    } else {
                        p.sendMessage(Component.text("You do not have enough money. You need $" + (1000000 - money) + " more.", TextColor.color(210, 11, 37)));
                    }
                } else {
                    p.sendMessage(Component.text("You already own this bunker. Entering anyway...", TextColor.color(210, 11, 37)));
                    p.performCommand("e");
                }
                AnarchyPlugin.plugin.saveConfigFile();
            }
            else if (locationIsInCuboid(p.getLocation(), new Location(w, 882,71,-878), new Location(w, 871,68,-879))) {
                //shop at walmart thing
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                int money = cfg.getInt("players." + pU + ".money");
                Inventory shop = Bukkit.createInventory(null, 54, "Supermarket");
                ItemStack bread = new ItemStack(Material.BREAD);
                ItemMeta breadMeta = bread.getItemMeta();
                List<Component> breadLore = new ArrayList();
                breadLore.add(Component.text("Bland and cheap"));
                breadLore.add(Component.text("Cost: ").append(Component.text("$30")));
                breadMeta.lore(breadLore);


                AnarchyPlugin.plugin.saveConfigFile();
            }
            else {
                p.sendMessage(Component.text("Not close enough. Are you standing on the smooth stone?", TextColor.color(210, 11, 37)));
            }
        } else {
            sender.sendMessage(Component.text("Wrong usage. This command doesn't take any arguments, it's just /buy.", TextColor.color(210, 11, 37)));
        }
        return true;
    }
    public boolean locationIsInCuboid(Location _playerLocation, Location min, Location max) {
        boolean trueOrNot = false;
        Location playerLocation = _playerLocation.getBlock().getLocation();
        if (playerLocation.getWorld() == min.getWorld() && playerLocation.getWorld() == max.getWorld()) {
            if (playerLocation.getX() >= min.getX() && playerLocation.getX() <= max.getX()) {
                if (playerLocation.getY() >= min.getY() && playerLocation.getY() <= max.getY()) {
                    if (playerLocation.getZ() >= min.getZ()
                            && playerLocation.getZ() <= max.getZ()) {
                        trueOrNot = true;
                    }
                }
            }
            if (playerLocation.getX() <= min.getX() && playerLocation.getX() >= max.getX()) {
                if (playerLocation.getY() <= min.getY() && playerLocation.getY() >= max.getY()) {
                    if (playerLocation.getZ() <= min.getZ()
                            && playerLocation.getZ() >= max.getZ()) {
                        trueOrNot = true;
                    }
                }
            }
        }
        return trueOrNot;
    }

}
