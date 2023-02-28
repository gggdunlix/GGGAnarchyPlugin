package ml.gggrealms.gggmcanarchy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
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
                String cheapAptStash = cfg.getString("stashes." + pU + ".cheapAptStash");
                Inventory hopperStash = Bukkit.createInventory(null, InventoryType.HOPPER, "Cheap Apartment Stash");
                if (cheapAptStash == null) {
                    hopperStash.clear();
                    cfg.set("stashes." + pU + ".cheapAptStash", InventoryDecoder.inventoryToBase64(hopperStash));
                }
                try {
                    hopperStash = InventoryDecoder.fromBase64(cheapAptStash);
                    Inventory configInv = InventoryDecoder.fromBase64(cheapAptStash);
                    hopperStash.setContents(configInv.getContents());
                    p.openInventory(hopperStash);
                } catch (IOException e) {
                    p.sendMessage(Component.text("There was an error loading this stash: " + e, TextColor.color(210, 11, 37)));
                }
            } else {
                p.sendMessage(Component.text("You don't own this property.", TextColor.color(210, 11, 37)));
            }
        } else {
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