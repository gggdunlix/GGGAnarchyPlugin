package ml.gggrealms.gggmcanarchy;
import me.deecaad.core.mechanics.defaultmechanics.Mechanic;
import me.deecaad.weaponmechanics.WeaponMechanics;
import me.deecaad.weaponmechanics.WeaponMechanicsAPI;
import me.deecaad.weaponmechanics.mechanics.WeaponGetMechanics;
import me.deecaad.weaponmechanics.weapon.WeaponHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;


public class ClassChoose implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        UUID pUUID = player.identity().uuid();
        Boolean isPlayerInHub = false;
        FileConfiguration config = AnarchyPlugin.plugin.getConfigFile();
        Set<String> tags = player.getScoreboardTags();
        //Determine if the player is in the hub
        if (tags.contains("isPlayerInHub")) {
            isPlayerInHub = true;
        }
        Boolean doesPlayerOwnCheapApt = false;
        if (tags.contains("doesPlayerOwnCheapApt")) {
            doesPlayerOwnCheapApt = true;
        }
        Boolean doesPlayerOwnSmallApt = false;
        if (tags.contains("doesPlayerOwnSmallApt")) {
            doesPlayerOwnSmallApt = true;
        }
        Boolean doesPlayerOwnMediumApt = false;
        if (tags.contains("doesPlayerOwnMediumApt")) {
            doesPlayerOwnMediumApt = true;
        }
        Boolean doesPlayerOwnHighApt = false;
        if (tags.contains("doesPlayerOwnHighApt")) {
            doesPlayerOwnHighApt = true;
        }
        Boolean doesPlayerOwnFarm = false;
        if (tags.contains("doesPlayerOwnFarm")) {
            doesPlayerOwnFarm = true;
        }
        Boolean doesPlayerOwnUSBankOffice = false;
        if (tags.contains("doesPlayerOwnUSBankOffice")) {
            doesPlayerOwnUSBankOffice = true;
        }
        Boolean doesPlayerOwnBunker = false;
        if (tags.contains("doesPlayerOwnBunker")) {
            doesPlayerOwnBunker = true;
        }
        Boolean doesPlayerOwnCasino = false;
        if (tags.contains("doesPlayerOwnCasino")) {
            doesPlayerOwnCasino = true;
        }
        Boolean doesPlayerOwnDocksOffice = false;
        if (tags.contains("doesPlayerOwnDocksOffice")) {
            doesPlayerOwnDocksOffice = true;
        }

        if (isPlayerInHub) {
            if (args.length == 0) {
                player.sendMessage(Component.text("Class List: ", TextColor.color(148, 144, 150)).append(Component.text("use /c <class-name> to spawn in.", TextColor.color(107, 175, 255))));
                if (!doesPlayerOwnFarm) {
                    player.sendMessage(Component.text("horseman: ", TextColor.color(255,255,12)).append(Component.text("Spawns with a horse at a farm. - REQUIRES PROPERTY: Farm", TextColor.color(210, 11, 37))));
                }
                if (!doesPlayerOwnUSBankOffice) {
                    player.sendMessage(Component.text("bankrobber: ", TextColor.color(255,255,12)).append(Component.text("Spawns prepared for a bank robbery. - REQUIRES PROPERTY: US Bank Office", TextColor.color(210, 11, 37))));

                }
                if (!doesPlayerOwnDocksOffice) {
                    player.sendMessage(Component.text("boater: ", TextColor.color(255,255,12)).append(Component.text("Spawns near docks, and can spawn a boat. - REQUIRES PROPERTY: Docks Office", TextColor.color(210, 11, 37))));

                }
                if (!doesPlayerOwnDocksOffice) {
                    player.sendMessage(Component.text("fisherman: ", TextColor.color(255,255,12)).append(Component.text("Spawns near docks, and can go fishing for money. - REQUIRES PROPERTY: Docks Office", TextColor.color(210, 11, 37))));

                }
                if (!doesPlayerOwnCheapApt) {
                    player.sendMessage(Component.text("thug: ", TextColor.color(255,255,12)).append(Component.text("Spawns as a common thug with a Glock. - REQUIRES PROPERTY: Cheap Apartment", TextColor.color(210, 11, 37))));

                }

                if (doesPlayerOwnDocksOffice) {
                    player.sendMessage(Component.text("boater: ", TextColor.color(255,255,12)).append(Component.text("Spawns near docks, and can spawn a boat.", TextColor.color(255,255,255))));
                }
                if (doesPlayerOwnDocksOffice) {
                    player.sendMessage(Component.text("fisherman: ", TextColor.color(255,255,12)).append(Component.text("Spawns near docks, and can go fishing for money. ($5,000 withdrawal on spawn)", TextColor.color(255,255,255))));
                }
                if (doesPlayerOwnFarm) {
                    player.sendMessage(Component.text("horseman: ", TextColor.color(255,255,12)).append(Component.text("Spawns with a horse at a farm.", TextColor.color(255,255,255))));
                }
                if (doesPlayerOwnUSBankOffice) {
                    player.sendMessage(Component.text("bankrobber: ", TextColor.color(255,255,12)).append(Component.text("Spawns prepared for a bank robbery.", TextColor.color(255,255,255))));
                }
                if (doesPlayerOwnCheapApt) {
                    player.sendMessage(Component.text("thug: ", TextColor.color(255,255,12)).append(Component.text("", TextColor.color(255,255,255))));
                }
                player.sendMessage(Component.text("farmer: ", TextColor.color(255,255,12)).append(Component.text("Spawns with a farming tool near a farm.", TextColor.color(255,255,255))));
                // player.sendMessage(Component.text("rider: ", TextColor.color(255,255,12)).append(Component.text("Spawns with no items and a slow horse at a random location.", TextColor.color(255,255,255))));
                player.sendMessage(Component.text("default: ", TextColor.color(255,255,12)).append(Component.text("Spawns with no items at a random location.", TextColor.color(255,255,255))));
                player.sendMessage("^^^ Here are a list of classes that are available. Some require properties and/or come with other special perks. ^^^");
            } else if (args[0].equals("default")) {
                player.sendMessage(Component.text("You spawned as ").append(Component.text("default", TextColor.color(13, 25, 200))));
                ArrayList<Location> locations = new ArrayList<Location>();
                locations.add(new Location(player.getWorld(), 874, 63, -1024));
                locations.add(new Location(player.getWorld(), 781, 63, -1036));
                locations.add(new Location(player.getWorld(), 855, 66, -972));
                locations.add(new Location(player.getWorld(), 809, 64, -990));
                locations.add(new Location(player.getWorld(), 960, 66, -930));
                locations.add(new Location(player.getWorld(), 920, 70, -900));
                locations.add(new Location(player.getWorld(), 780, 69, -915));
                locations.add(new Location(player.getWorld(), 696, 70, -833));
                locations.add(new Location(player.getWorld(), 652, 70, -924));

                int random_int = (int)Math.floor(Math.random() * ((locations.size() - 1)  - 0 + 1) + 0);
                player.teleport(locations.get(random_int));
                player.removeScoreboardTag("isPlayerInHub");
                player.addScoreboardTag("classDefault");

            } else if (args[0].equals("..........rider")) {
                if (config.getInt("players." + pUUID + ".riderCooldown") <= 0) {
                    player.sendMessage(Component.text("You spawned as ").append(Component.text("rider", TextColor.color(13, 25, 200))));
                    player.sendMessage(Component.text("You must wait ").append(Component.text("240 seconds", TextColor.color(255, 230, 87))).append(Component.text(" before spawning as this class again.", TextColor.color(255, 255, 255))));
                    config.set("players." + pUUID + ".riderCooldown", 240);
                    ArrayList<Location> locations = new ArrayList<Location>();
                    locations.add(new Location(player.getWorld(), 866, 65, -1030));
                    locations.add(new Location(player.getWorld(), 815, 63, -1057));
                    locations.add(new Location(player.getWorld(), 768, 62, -1037));
                    locations.add(new Location(player.getWorld(), 995, 62, -966));
                    int random_int = (int) Math.floor(Math.random() * ((locations.size() - 1) - 0 + 1) + 0);
                    Location chosenLoc = locations.get(random_int);
                    player.teleport(chosenLoc);
                    player.removeScoreboardTag("isPlayerInHub");
                    player.addScoreboardTag("classRider");

                    Donkey donkey = (Donkey) Bukkit.getWorld("world").spawnEntity(player.getLocation(), EntityType.DONKEY);
                    donkey.setTamed(true);
                    donkey.getInventory().setSaddle(new ItemStack(Material.SADDLE));

                    donkey.damage(20);
                    player.sendMessage(Component.text("This donkey will die in a few minutes!", TextColor.color(210, 11, 37)));
                    donkey.setHealth(donkey.getHealth()/3);
                    donkey.addPassenger(player);
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            donkey.setHealth(0);
                        }
                    }.runTaskLater(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 20*180);
                } else {
                    player.sendMessage(Component.text("You must wait ").append(Component.text(config.getInt("players." + pUUID + ".riderCooldown") + " seconds", TextColor.color(255, 230, 87))).append(Component.text(" before spawning as this class again.", TextColor.color(255, 255, 255))));
                }


            } else if (args[0].equals("farmer")) {
                if (config.getInt("players." + pUUID + ".farmerCooldown") <= 0) {
                    player.sendMessage(Component.text("You spawned as ").append(Component.text("farmer", TextColor.color(13, 25, 200))));
                    ArrayList<Location> locations = new ArrayList<Location>();
                    locations.add(new Location(player.getWorld(), 1041, 63, -670));
                    int random_int = (int) Math.floor(Math.random() * ((locations.size() - 1) - 0 + 1) + 0);
                    Location chosenLoc = locations.get(random_int);
                    player.teleport(chosenLoc);
                    player.performCommand("join");
                    player.removeScoreboardTag("isPlayerInHub");
                    config.set("players." + pUUID + ".farmerCooldown", 600);
                    player.addScoreboardTag("classFarmer");
                } else {
                    player.sendMessage(Component.text("You must wait ").append(Component.text(config.getInt("players." + pUUID + ".farmerCooldown") + " seconds", TextColor.color(255, 230, 87))).append(Component.text(" before spawning as this class again.", TextColor.color(255, 255, 255))));
                }

            } else if (args[0].equals("fisherman")) {
                if (doesPlayerOwnDocksOffice) {
                    if (config.getInt("players." + pUUID + ".fishermanCooldown") <= 0) {
                        if (config.getInt("players." + pUUID + ".docksOfficeBalance", 0) >= 5000) {
                            player.sendMessage(Component.text("You spawned as ").append(Component.text("fisherman", TextColor.color(13, 25, 200))));
                            ArrayList<Location> locations = new ArrayList<Location>();
                            locations.add(new Location(player.getWorld(), 716, 63, -862));
                            locations.add(new Location(player.getWorld(), 650, 69, -863));
                            locations.add(new Location(player.getWorld(), 729, 64, -796));
                            locations.add(new Location(player.getWorld(), 759, 63, -751));
                            int random_int = (int) Math.floor(Math.random() * ((locations.size() - 1) - 0 + 1) + 0);
                            Location chosenLoc = locations.get(random_int);
                            player.teleport(chosenLoc);
                            player.removeScoreboardTag("isPlayerInHub");
                            config.set("players." + pUUID + ".fishermanCooldown", 600);
                            player.addScoreboardTag("classFisherman");
                            player.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
                            config.set("players." + pUUID + ".docksOfficeBalance", config.getInt("players." + pUUID + ".docksOfficeBalance") - 5000); //remove $5000 withdrawal
                        } else {
                            player.sendMessage(Component.text("You need another $").append(Component.text(5000 - config.getInt("players." + pUUID + ".docksOfficeBalance"), TextColor.color(255, 66, 50))).append(Component.text(" in the 'Docks Office Safe' before spawning as this class again.", TextColor.color(255, 255, 255))));
                        }
                    } else {
                        player.sendMessage(Component.text("You must wait ").append(Component.text(config.getInt("players." + pUUID + ".fishermanCooldown") + " seconds", TextColor.color(255, 230, 87))).append(Component.text(" before spawning as this class again.", TextColor.color(255, 255, 255))));
                    }
                } else {
                    player.sendMessage(Component.text("You need to own 'Docks Office' to spawn as ", TextColor.color(210, 11, 37)).append(Component.text("fisherman", TextColor.color(13, 25, 200))));
                }
            } else if (args[0].equals("thug")) {
                if (config.getInt("players." + pUUID + ".thugCooldown") <= 0) {
                    player.sendMessage(Component.text("You spawned as ").append(Component.text("thug", TextColor.color(13, 25, 200))));
                    ArrayList<Location> locations = new ArrayList<Location>();
                    locations.add(new Location(player.getWorld(), 716, 63, -862)); //ADD SOME LOCATIONS
                    int random_int = (int) Math.floor(Math.random() * ((locations.size() - 1) - 0 + 1) + 0);
                    Location chosenLoc = locations.get(random_int);
                    player.teleport(chosenLoc);
                    player.removeScoreboardTag("isPlayerInHub");
                    config.set("players." + pUUID + ".thugCooldown", 360);
                    player.addScoreboardTag("classThug");
                    WeaponMechanicsAPI.giveWeapon("GLOCK", player);
                } else {
                    player.sendMessage(Component.text("You must wait ").append(Component.text(config.getInt("players." + pUUID + ".fishermanCooldown") + " seconds", TextColor.color(255, 230, 87))).append(Component.text(" before spawning as this class again.", TextColor.color(255, 255, 255))));
                }

            } else if (args[0].equals("horseman")) {
                if (doesPlayerOwnFarm) {
                    player.sendMessage(Component.text("You spawned as ").append(Component.text("horseman", TextColor.color(13, 25, 200))));
                    ArrayList<Location> locations = new ArrayList<Location>();
                    locations.add(new Location(player.getWorld(), 1039, 63, -693));
                    locations.add(new Location(player.getWorld(), 1049, 63, -679));
                    int random_int = (int) Math.floor(Math.random() * ((locations.size() - 1) - 0 + 1) + 0);
                    Location chosenLoc = locations.get(random_int);
                    player.teleport(chosenLoc);
                    player.removeScoreboardTag("isPlayerInHub");
                    Horse horse = (Horse) Bukkit.getWorld("world").spawnEntity(player.getLocation(), EntityType.HORSE);
                    horse.setTamed(true);
                    horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                    horse.addPassenger(player);

                    player.addScoreboardTag("classHorseman");
                } else {
                    player.sendMessage(Component.text("You need to own 'farm' to spawn as ", TextColor.color(210, 11, 37)).append(Component.text("horseman", TextColor.color(13, 25, 200))));
                }

            } else if (args[0].equals("boater")) {
                if (doesPlayerOwnDocksOffice) {
                    player.sendMessage(Component.text("You spawned as ").append(Component.text("boater", TextColor.color(13, 25, 200))));
                    ArrayList<Location> locations = new ArrayList<Location>();
                    locations.add(new Location(player.getWorld(), 716, 63, -862));
                    locations.add(new Location(player.getWorld(), 650, 69, -863));
                    locations.add(new Location(player.getWorld(), 729, 64, -796));
                    locations.add(new Location(player.getWorld(), 759, 63, -751));
                    int random_int = (int) Math.floor(Math.random() * ((locations.size() - 1) - 0 + 1) + 0);
                    Location chosenLoc = locations.get(random_int);
                    player.teleport(chosenLoc);
                    player.removeScoreboardTag("isPlayerInHub");

                    player.addScoreboardTag("classBoater");
                } else {
                    player.sendMessage(Component.text("You need to own 'Docks Office' to spawn as ", TextColor.color(210, 11, 37)).append(Component.text("boater", TextColor.color(13, 25, 200))));
                }

            } else if (args[0].equals("dev")) {
                if (player.isOp()) {
                    player.sendMessage(Component.text("You spawned as ").append(Component.text("Developer", TextColor.color(200, 125, 55))));
                    PlayerInventory i = player.getInventory();
                    player.teleport(new Location(player.getWorld(), 861,154,-861));
                    i.addItem(new ItemStack(Material.WOODEN_AXE));
                    ItemStack rockets = new ItemStack(Material.FIREWORK_ROCKET);
                    rockets.setAmount(64);
                    i.addItem(rockets);
                    i.setChestplate(new ItemStack(Material.ELYTRA));
                    player.setGameMode(GameMode.CREATIVE);
                    player.removeScoreboardTag("isPlayerInHub");
                    player.addScoreboardTag("classDev");
                } else {
                    player.sendMessage("no");
                }
            }else return false;
        } else {
            sender.sendMessage("You need to be in the hub when running this command. You can't choose class while alive.");
        }
        AnarchyPlugin.plugin.saveConfigFile();
        return true;
    }
}
