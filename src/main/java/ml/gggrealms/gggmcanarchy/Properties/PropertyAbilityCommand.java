package ml.gggrealms.gggmcanarchy.Properties;

import ml.gggrealms.gggmcanarchy.AnarchyPlugin;
import ml.gggrealms.gggmcanarchy.Lang;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PropertyAbilityCommand implements CommandExecutor {
    private Lang lang;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Set<String> tags = p.getScoreboardTags();
        World w = p.getWorld();
        UUID pU = p.identity().uuid();
        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
        if (args.length == 0) {
            sender.sendMessage(Component.text("Property Abilities: ", TextColor.color(42, 101, 255)));
            sender.sendMessage(Component.text("|  ->", TextColor.color(42, 101, 255)).append(Component.text("/pa boat ", TextColor.color(244, 126, 57)).append(Component.text("- Spawns a boat if near the Docks Office. Need to own Docks Office."))));
            sender.sendMessage(Component.text("|  ->", TextColor.color(42, 101, 255)).append(Component.text("/pa pc ", TextColor.color(244, 126, 57)).append(Component.text("- Access the US Bank Office PC. You need to own the property."))));

        }
        else if (args[0].equals("boat")) {
            if (tags.contains("doesPlayerOwnDocksOffice")) {
                if (locationIsInCuboid(p.getLocation(), new Location(w, 679,66,-800), new Location(w,672,61,-805))) {
                    int delay = cfg.getInt("players." + pU + ".boatDelay", 0);
                    if (delay > 0) {
                        p.sendMessage(Component.text("You must wait another ", TextColor.color(210, 11, 37)).append(Component.text(delay + " seconds", TextColor.color(255, 202, 54))));
                    } else {
                        Entity boat = w.spawnEntity(p.getLocation(), EntityType.BOAT);
                        boat.addPassenger(p);
                        delay = 600;
                        p.sendMessage(Component.text("You must wait another ", TextColor.color(255, 255, 255)).append(Component.text(delay + " seconds", TextColor.color(255, 202, 54))));
                        cfg.set("players." + pU + ".boatDelay", 600);
                    }
                } else {
                    p.sendMessage(lang.docksOfficeAbilDistanceError);
                }
            } else {
                p.sendMessage(lang.docksOfficeAbilOwnershipError);
            }
        }
        else if (args[0].equals("pc")) {
            if (tags.contains("doesPlayerOwnUSBankOffice")) {
                if (locationIsInCuboid(p.getLocation(), new Location(w, 914,91,-926), new Location(w,912,89,-928))) {
                    Inventory pcInv = Bukkit.createInventory(null, 54, lang.USBankOfficePCTitle);
                    ItemStack bountyItem = new ItemStack(Material.SKELETON_SKULL);
                    ItemMeta bountyItemMeta = bountyItem.getItemMeta();
                    bountyItemMeta.displayName(lang.bountyAppDisplayName);
                    List<Component> bountyItemLore = new ArrayList();
                    bountyItemLore.add(lang.bountyAppLore);
                    bountyItemMeta.lore(bountyItemLore);
                    bountyItem.setItemMeta(bountyItemMeta);
                    pcInv.setItem(0, bountyItem);
                } else {
                    p.sendMessage(lang.USBankOfficePCDistanceError);
                }
            } else {
                p.sendMessage(lang.USBankOfficeAbilOwnershipError);
            }
        }
        else {
            p.sendMessage(lang.propAbilUsageError);
        }
        AnarchyPlugin.plugin.saveConfigFile();
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
