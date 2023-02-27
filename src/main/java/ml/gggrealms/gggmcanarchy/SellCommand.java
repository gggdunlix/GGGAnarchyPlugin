package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class SellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {

            Player p = (Player) sender;
            Set<String> tags = p.getScoreboardTags();
            World w = p.getWorld();
            UUID pU = p.identity().uuid();
            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
            int money = cfg.getInt("players." + pU + ".money");
            if (locationIsInCuboid(p.getLocation(), new Location(w, 1043, 68, -670), new Location(w, 1038, 62, -671))) {
                if (AnarchyPlugin.plugin.getFaction(p).equals("farmer")) {
                    PlayerInventory i = p.getInventory();
                    ItemStack[] contents = i.getContents();
                    for (ItemStack iS : contents) {
                        if (iS != null) {
                            int amt = iS.getAmount();
                            if (iS.getType().equals(Material.BEETROOT_SEEDS)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 5;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Beetroot seeds", TextColor.color(73, 210, 69)));
                                    i.remove(Material.BEETROOT_SEEDS);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Beetroot seeds to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.PUMPKIN_SEEDS)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 3;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Pumpkin seeds", TextColor.color(73, 210, 69)));
                                    i.remove(Material.PUMPKIN_SEEDS);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Pumpkin seeds to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.PUMPKIN)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 12;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Pumpkins", TextColor.color(73, 210, 69)));
                                    i.remove(Material.PUMPKIN);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Pumpkin seeds to sell.", TextColor.color(210, 11, 37)));
                                }
                            }else if (iS.getType().equals(Material.MELON_SEEDS)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 8;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Melon seeds", TextColor.color(73, 210, 69)));
                                    i.remove(Material.MELON_SEEDS);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Melon seeds to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.WHEAT_SEEDS)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 4;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Wheat seeds", TextColor.color(73, 210, 69)));
                                    i.remove(Material.WHEAT_SEEDS);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Wheat seeds to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.CARROT)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 6;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Carrots", TextColor.color(73, 210, 69)));
                                    i.remove(Material.CARROT);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Carrots to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.POTATO)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 6;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Potatos", TextColor.color(73, 210, 69)));
                                    i.remove(Material.POTATO);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Potatoes to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.BEETROOT)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 10;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Beetroots", TextColor.color(73, 210, 69)));
                                    i.remove(Material.BEETROOT);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Beetroots to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.MELON_SLICE)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 4;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Melon slices", TextColor.color(73, 210, 69)));
                                    i.remove(Material.MELON_SLICE);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Melon slices to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.WHEAT)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 10;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Wheat", TextColor.color(73, 210, 69)));
                                    i.remove(Material.WHEAT);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Wheat to sell.", TextColor.color(210, 11, 37)));
                                }
                            } else if (iS.getType().equals(Material.SWEET_BERRIES)) {
                                if (amt > 1) {
                                    int amtGiven = amt * 2;
                                    money += amtGiven;
                                    sender.sendMessage(Component.text("Given $" + amtGiven + " for " + amt + " Sweet Berries", TextColor.color(73, 210, 69)));
                                    i.remove(Material.SWEET_BERRIES);
                                } else {
                                    sender.sendMessage(Component.text("You need at least 2 Sweet Berries to sell.", TextColor.color(210, 11, 37)));
                                }
                            }
                        }
                    }
                    cfg.set("players." + pU + ".money", money);
                    AnarchyPlugin.plugin.saveConfigFile();
                } else {
                    sender.sendMessage(Component.text("You need to be a farmer to sell your crops. Use /join.", TextColor.color(210, 11, 37)));
                }
            } else {
                sender.sendMessage(Component.text("Too far, please stand on the smooth stone.", TextColor.color(210, 11, 37)));
            }
            AnarchyPlugin.plugin.saveConfigFile();
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
