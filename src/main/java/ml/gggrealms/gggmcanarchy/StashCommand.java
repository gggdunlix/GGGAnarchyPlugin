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
import org.jetbrains.annotations.NotNull;

public class StashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
      Player p = (Player) sender;
      Location pLoc = p.getLocation();
      World w = pLoc.getWorld();
      FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
      UUID pU = p.getIdentity().getUUID();
      Set<String> tags = p.getScoreboardTags();
      if (locationIsInCuboid(pLoc, new Location (w, 00, 00, -00), new Location(w, 00, 00, -00)) {
        if (tags.contains("doesPlayerOwnCheapApt")) {
          String cheapAptStash = cfg.getString("stashes." + pU + ".cheapAptStash");
          // ... 
          // needs to open an inventory of sorts, can stash items inside...
        } else {
          player.sendMessage(Component.text("You don't own this property.", TextColor.color(210, 11, 37)));
        }
      } else {
        player.sendMessage(Component.text("You aren't close enough to a stash. Are you sure you are standing on the smooth stone?", TextColor.color(210, 11, 37)));
      }
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
