package ml.gggrealms.gggmcanarchy.Properties;

import ml.gggrealms.gggmcanarchy.AnarchyPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class EnterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        Set<String> tags = player.getScoreboardTags();

        Location pLoc = player.getLocation();
        World w = pLoc.getWorld();
        if (locationIsInCuboid(pLoc, new Location(w, 770,65,-946), new Location(w, 769,63,-947))) {
            // Exit First Pacific Bank Vault
            player.teleport(new Location(player.getWorld(), 770, 64, -949));
        } else if (locationIsInCuboid(pLoc, new Location(w, 770,65,-949), new Location(w, 769,63,-950))) {
            // Enter First Pacific Bank Vault
            player.teleport(new Location(player.getWorld(), 770, 64, -946));
        } else if (locationIsInCuboid(pLoc, new Location(w, 847,72,-892), new Location(w, 846,68,-893))) {
            // Enter MBA Bank right side
            player.teleport(new Location(player.getWorld(), 847, 69, -889));
        } else if (locationIsInCuboid(pLoc, new Location(w, 847,72,-889), new Location(w, 846,68,-890))) {
            // Exit MBA Bank right side
            player.teleport(new Location(player.getWorld(), 847, 69, -892));
        } else if (locationIsInCuboid(pLoc, new Location(w, 859,72,-892), new Location(w, 858,68,-893))) {
            // Enter MBA Bank left side
            player.teleport(new Location(player.getWorld(), 859, 69, -889));
        } else if (locationIsInCuboid(pLoc, new Location(w, 859,72,-889), new Location(w, 858,68,-890))) {
            // Exit MBA Bank left side
            player.teleport(new Location(player.getWorld(), 859, 69, -892));
        } else if (locationIsInCuboid(pLoc, new Location(w, 924,72,-929), new Location(w, 922,68,-930))) {
            // US Bank Lobby > mid
            player.teleport(new Location(player.getWorld(), 923, 89, -932));
        } else if (locationIsInCuboid(pLoc, new Location(w, 924,92,-932), new Location(w, 922,88,-933))) {
            // US Bank mid > lobby
            player.teleport(new Location(player.getWorld(), 924, 69, -929));
        } else if (locationIsInCuboid(pLoc, new Location(w, 921,200,-931), new Location(w, 920,198,-932))) {
            // US Bank roof > mid
            player.teleport(new Location(player.getWorld(), 923, 89, -926));
        } else if (locationIsInCuboid(pLoc, new Location(w, 923,92,-926), new Location(w, 922,88,-927))) {
            // US Bank mid > roof
            player.teleport(new Location(player.getWorld(), 921, 199, -931));
        } else if (locationIsInCuboid(pLoc, new Location(w, 924,92,-929), new Location(w, 922,88,-930))) {
            // US Bank Entry
            if (tags.contains("doesPlayerOwnUSBankOffice")) {
                player.teleport(new Location(player.getWorld(), 920, 89, -929));
            }
        } else if (locationIsInCuboid(pLoc, new Location(w, 920,90,-929), new Location(w, 919,88,-930))) {
            // US Bank exit
            player.teleport(new Location(player.getWorld(), 923, 89, -929));
        } else if (locationIsInCuboid(pLoc, new Location(w, 855,66,-886), new Location(w, 853,63,-888))) {
            // Break 1st bars MBA Bank vault
            player.sendMessage("Breaking bars...");
            new BukkitRunnable() {
                @Override
                public void run() {
                    w.getBlockAt(854,64,-887).setType(Material.AIR);
                    w.getBlockAt(854,65,-887).setType(Material.AIR);
                    player.sendMessage("Broke bars!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            w.getBlockAt(854,64,-887).setType(Material.IRON_BARS);
                            w.getBlockAt(854,65,-887).setType(Material.IRON_BARS);
                        }
                    }.runTaskLater(AnarchyPlugin.plugin, 20 * 20);
                }
            }.runTaskLater(AnarchyPlugin.plugin, 20 * 5);
        } else if (locationIsInCuboid(pLoc, new Location(w, 852,66,-889), new Location(w, 851,63,-891))) {
            // Break iron vault MBA Bank vault
            player.sendMessage("Breaking vault door...");
            new BukkitRunnable() {
                @Override
                public void run() {
                    w.getBlockAt(852,65,-890).setType(Material.AIR);
                    w.getBlockAt(852,64,-890).setType(Material.AIR);
                    player.sendMessage("Broke Door!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            w.getBlockAt(852,65,-890).setType(Material.IRON_BLOCK);
                            w.getBlockAt(852,64,-890).setType(Material.IRON_BLOCK);
                        }
                    }.runTaskLater(AnarchyPlugin.plugin, 20 * 30);
                }
            }.runTaskLater(AnarchyPlugin.plugin, 20 * 10);
        } else if (locationIsInCuboid(pLoc, new Location(w, 859,64,-895), new Location(w, 857,61,-897))) {
            // Break Right bars MBA Bank vault
            player.sendMessage("Breaking bars...");
            new BukkitRunnable() {
                @Override
                public void run() {
                    w.getBlockAt(858,62,-896).setType(Material.AIR);
                    w.getBlockAt(858,63,-896).setType(Material.AIR);
                    player.sendMessage("Broke bars!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            w.getBlockAt(858,62,-896).setType(Material.IRON_BARS);
                            w.getBlockAt(858,63,-896).setType(Material.IRON_BARS);
                        }
                    }.runTaskLater(AnarchyPlugin.plugin, 20 * 20);
                }
            }.runTaskLater(AnarchyPlugin.plugin, 20 * 5);
        } else if (locationIsInCuboid(pLoc, new Location(w, 854,65,-899), new Location(w, 851,61,-901))) {
            // Break Center bars MBA Bank vault
            player.sendMessage("Breaking bars...");
            new BukkitRunnable() {
                @Override
                public void run() {
                    w.getBlockAt(852,62,-900).setType(Material.AIR);
                    w.getBlockAt(852,63,-900).setType(Material.AIR);
                    player.sendMessage("Broke bars!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            w.getBlockAt(852,62,-900).setType(Material.IRON_BARS);
                            w.getBlockAt(852,63,-900).setType(Material.IRON_BARS);
                        }
                    }.runTaskLater(AnarchyPlugin.plugin, 20 * 20);
                }
            }.runTaskLater(AnarchyPlugin.plugin, 20 * 5);
        } else if (locationIsInCuboid(pLoc, new Location(w, 848,64,-895), new Location(w, 846,61,-897))) {
            // Break Left bars MBA Bank vault
            player.sendMessage("Breaking bars...");
            new BukkitRunnable() {
                @Override
                public void run() {
                    w.getBlockAt(847,62,-896).setType(Material.AIR);
                    w.getBlockAt(847,63,-896).setType(Material.AIR);
                    player.sendMessage("Broke bars!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            w.getBlockAt(847,63,-896).setType(Material.IRON_BARS);
                            w.getBlockAt(847,63,-896).setType(Material.IRON_BARS);
                        }
                    }.runTaskLater(AnarchyPlugin.plugin, 20 * 20);
                }
            }.runTaskLater(AnarchyPlugin.plugin, 20 * 5);
        } else if (locationIsInCuboid(pLoc, new Location(w, 00,00,-00), new Location(w, 798,69,-938))) {
            // Enter Small Apartment
            if (tags.contains("doesPlayerOwnSmallApt")) {
                player.teleport(new Location(player.getWorld(), 00, 00, -00));
            } else {
                player.sendMessage(Component.text("You don't own this property. Type /buy to buy it.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(pLoc, new Location(w, 00,00,-00), new Location(w, 800,69,-940))) {
            // Exit Small Apartment
            player.teleport(new Location(player.getWorld(), 00, 00, -00));
        } else if (locationIsInCuboid(pLoc, new Location(w, 830, 76, -933), new Location(w, 829, 72, -934))) {
            //Enter Cheap Apartment
            if (tags.contains("doesPlayerOwnCheapApt")) {
                player.teleport(new Location(w, 826, 73, -933));
            } else {
                player.sendMessage(Component.text("You don't own this property. Type /buy to buy it.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(pLoc, new Location(w, 827, 74, -933), new Location(w, 826, 72, -934))) {
            //Exit Cheap Apartment
            player.teleport(new Location(w, 830, 73, -933));

        } else if (locationIsInCuboid(pLoc, new Location(w, 1051,65,-669), new Location(w, 1048,62,-672))) {
            // Enter Farm
            if (tags.contains("doesPlayerOwnFarm")) {
                player.teleport(new Location(player.getWorld(), 1037, 71, -678));
            } else {
                player.sendMessage(Component.text("You don't own this property. Type /buy to buy it.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(pLoc, new Location(w, 1037,72,-678), new Location(w, 1036,70,-680))) {
            // Exit Farm
            player.teleport(new Location(player.getWorld(), 1048, 63, -670));
        } else if (locationIsInCuboid(pLoc, new Location(w, 902,102,-835), new Location(w, 900,99,-836))) {
            // Enter Stilt
            if (tags.contains("doesPlayerOwnStilt")) {
                player.teleport(new Location(player.getWorld(), 904, 99, -836));
            } else {
                player.sendMessage(Component.text("You don't own this property. Type /buy to buy it.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(pLoc, new Location(w, 684, 72, -830), new Location(w, 682, 69, -832))) {
            //enter Docks Office
            if (tags.contains("doesPlayerOwnDocksOffice")) {
                player.teleport(new Location(player.getWorld(), 683, 70, -827));
            } else {
                player.sendMessage(Component.text("You don't own this property. Type /buy to buy it.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(pLoc, new Location(w, 684, 72, -826), new Location(w, 682, 69, -828))) {
            player.teleport(new Location(player.getWorld(), 683, 70, -831));
        }
        else if (locationIsInCuboid(pLoc, new Location(w, 904,101,-835), new Location(w, 903,99,-836))) {
            // Exit Stilt
            player.teleport(new Location(player.getWorld(), 901, 99, -836));
        } else if (locationIsInCuboid(pLoc, new Location(w, 762,69,-599), new Location(w, 756,66,-601))) {
            // Bunker entry
            if (tags.contains("doesPlayerOwnBunker")) {
                player.teleport(new Location(player.getWorld(), 759, 58, -608));
            } else {
                player.sendMessage(Component.text("You don't own this property. Type /buy to buy it.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(pLoc, new Location(w, 760,60,-607), new Location(w, 758,57,-609))) {
            // Bunker Exit
            player.teleport(new Location(player.getWorld(), 759.5,67,-599.5));
        } else if (locationIsInCuboid(pLoc, new Location(w, 985, 71, -1232), new Location(w, 981, 68, -1234)) || locationIsInCuboid(pLoc, new Location(w, 985, 71, -1209), new Location(w, 981, 68, -1211))) {
            // casino elevator left/or/right > bottom
            player.teleport(new Location(player.getWorld(), 983,32,-1210));
        } else if (locationIsInCuboid(pLoc, new Location(w, 985, 34, -1209), new Location(w, 981, 31, -1211))) {
            // casino elevator bottom > left/or/right
            int random = (int) Math.floor(Math.random() *(1 - 0 + 1) + 0);
            if (random == 0) {
                player.teleport(new Location(player.getWorld(), 983,69,-1233));
            } else {
                player.teleport(new Location(player.getWorld(), 983,69,-1210));
            }
        } else {
            player.sendMessage(Component.text("Not close enough. Are you standing on the smooth stone?", TextColor.color(210, 11, 37)));
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
