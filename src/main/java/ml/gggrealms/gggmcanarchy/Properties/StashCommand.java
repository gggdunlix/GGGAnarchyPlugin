package ml.gggrealms.gggmcanarchy.Properties;
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
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;
public class StashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Location pLoc = p.getLocation();
        World w = pLoc.getWorld();
        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
        UUID pU = p.identity().uuid();
        Set<String> tags = p.getScoreboardTags();
        if (locationIsInCuboid(pLoc, new Location(w, 824, 75, -927), new Location(w, 823, 72, -929))) {
            if (tags.contains("doesPlayerOwnCheapApt")) {
                Inventory hopperStash = Bukkit.createInventory(null, InventoryType.HOPPER, "Cheap Apartment Stash");
                hopperStash = cfg.getObject("stashes." + pU + ".cheapAptStash", hopperStash.getClass());

                if (hopperStash == null) {
                    hopperStash = Bukkit.createInventory(null, InventoryType.HOPPER, "Cheap Apartment Stash");
                }
                hopperStash.addItem(new ItemStack(Material.AIR));
                InventoryView view = p.openInventory(hopperStash);
            } else {
                p.sendMessage(Component.text("You don't own this property.", TextColor.color(210, 11, 37)));
            }
        }
        else if (locationIsInCuboid(pLoc, new Location(w, 927, 92, -930), new Location(w, 926, 88, -934))) {
            if (tags.contains("doesPlayerOwnUSBankOffice")) {
                Inventory chest2Stash = Bukkit.createInventory(null, 54, "US Bank Office Stash");

                chest2Stash = cfg.getObject("stashes." + pU + ".USBankOfficeStash", chest2Stash.getClass());

                if (chest2Stash == null) {
                    chest2Stash = Bukkit.createInventory(null, 54, "US Bank Office Stash");
                }
                chest2Stash.addItem(new ItemStack(Material.AIR));
                InventoryView view = p.openInventory(chest2Stash);
            } else {
                p.sendMessage(Component.text("You don't own this property.", TextColor.color(210, 11, 37)));
            }
        }
        else if (locationIsInCuboid(pLoc, new Location(w, 1044, 73, -694), new Location(w, 1042, 70, -697))) {
            if (tags.contains("doesPlayerOwnFarm")) {
                String farmStash = cfg.getString("stashes." + pU + ".farmStash");
                Inventory chestStash = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Farm Stash");
                chestStash = cfg.getObject("stashes." + pU + ".farmStash", chestStash.getClass());

                if (chestStash == null) {
                    chestStash = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Farm Stash");
                }
                chestStash.addItem(new ItemStack(Material.AIR));
                InventoryView view = p.openInventory(chestStash);
            } else {
                p.sendMessage(Component.text("You don't own this property.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(pLoc, new Location(w, 687, 72, -823), new Location(w, 686, 69, -826))) {
            if (tags.contains("doesPlayerOwnDocksOffice")) {
                if (args.length == 1) {
                    if (Integer.parseInt(args[0]) == 1) {
                        Inventory chestStash = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Docks Office Stash p1");
                        chestStash = cfg.getObject("stashes." + pU + ".docksStash1", chestStash.getClass());

                        if (chestStash == null) {
                            chestStash = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Docks Office Stash p1");
                        }
                        chestStash.addItem(new ItemStack(Material.AIR));
                        InventoryView view = p.openInventory(chestStash);
                    } else if (Integer.parseInt(args[0]) == 2) {
                        Inventory chestStash = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Docks Office Stash p2");
                        chestStash = cfg.getObject("stashes." + pU + ".docksStash2", chestStash.getClass());

                        if (chestStash == null) {
                            chestStash = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Docks Office Stash p2");
                        }
                        chestStash.addItem(new ItemStack(Material.AIR));
                        InventoryView view = p.openInventory(chestStash);
                    } else if (Integer.parseInt(args[0]) == 3) {
                        Inventory chestStash = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Docks Office Stash p3");
                        chestStash = cfg.getObject("stashes." + pU + ".docksStash3", chestStash.getClass());

                        if (chestStash == null) {
                            chestStash = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Docks Office Stash p3");
                        }
                        chestStash.addItem(new ItemStack(Material.AIR));
                        InventoryView view = p.openInventory(chestStash);
                    } else {
                        p.sendMessage(Component.text("What page? There's only 1, 2, and 3.", TextColor.color(210, 11, 37)));
                    }
                } else {
                    p.sendMessage(Component.text("This stash has 3 pages. Please type /stash <pageNumber> to access a specific page.", TextColor.color(210, 11, 37)));
                }
            } else {
                p.sendMessage(Component.text("You don't own this property.", TextColor.color(210, 11, 37)));
            }
        } else  {
            p.sendMessage(Component.text("You aren't close enough to a stash. Are you sure you are standing on the smooth stone?", TextColor.color(210, 11, 37)));
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