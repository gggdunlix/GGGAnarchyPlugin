package ml.gggrealms.gggmcanarchy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Set;

public class ClassChoose implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        Boolean isPlayerInHub = false;
        Set<String> tags = player.getScoreboardTags();
        //Determine if the player is in the hub
        if (tags.contains("isPlayerInHub")) {
            isPlayerInHub = true;
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

        if (isPlayerInHub) {
            if (args.length == 0) {
                player.sendMessage(Component.text("Class List: ", TextColor.color(148, 144, 150)).append(Component.text("use /class <class-name> to spawn in.", TextColor.color(107, 175, 255))));
                if (doesPlayerOwnFarm) {
                    player.sendMessage(Component.text("horseman: ", TextColor.color(255,255,12)).append(Component.text("Spawns with a horse at a farm.", TextColor.color(255,255,255))));
                } else {
                    player.sendMessage(Component.text("horseman: ", TextColor.color(255,255,12)).append(Component.text("Spawns with a horse at a farm. - REQUIRES PROPERTY: Farm", TextColor.color(210, 11, 37))));
                }
                player.sendMessage(Component.text("farmer: ", TextColor.color(255,255,12)).append(Component.text("Spawns with a farming tool near a farm.", TextColor.color(255,255,255))));
                player.sendMessage(Component.text("rider: ", TextColor.color(255,255,12)).append(Component.text("Spawns with no items and a slow horse at a random location.", TextColor.color(255,255,255))));
                player.sendMessage(Component.text("default: ", TextColor.color(255,255,12)).append(Component.text("Spawns with no items at a random location.", TextColor.color(255,255,255))));
                player.sendMessage("^^^ Here are a list of classes that are available. Some require properties and/or come with other special perks. ^^^");
            } else if (args[0].equals("default")) {
                player.sendMessage(Component.text("You spawned as ").append(Component.text("default", TextColor.color(13, 25, 200))));
                ArrayList<Location> locations = new ArrayList<Location>();
                locations.add(new Location(player.getWorld(), 874, 63, -1024));
                locations.add(new Location(player.getWorld(), 781, 63, -1036));
                locations.add(new Location(player.getWorld(), 855, 66, -972));
                locations.add(new Location(player.getWorld(), 809, 64, -990));
                int random_int = (int)Math.floor(Math.random() * ((locations.size() - 1)  - 0 + 1) + 0);
                player.teleport(locations.get(random_int));
                player.removeScoreboardTag("isPlayerInHub");
                player.addScoreboardTag("classDefault");

            } else if (args[0].equals("rider")) {
                player.sendMessage(Component.text("You spawned as ").append(Component.text("rider", TextColor.color(13, 25, 200))));
                ArrayList<Location> locations = new ArrayList<Location>();
                locations.add(new Location(player.getWorld(), 866, 65, -1030));
                locations.add(new Location(player.getWorld(), 815, 63, -1057));
                locations.add(new Location(player.getWorld(), 768, 62, -1037));
                locations.add(new Location(player.getWorld(), 995, 62, -966));
                int random_int = (int)Math.floor(Math.random() * ((locations.size() - 1)  - 0 + 1) + 0);
                Location chosenLoc = locations.get(random_int);
                player.teleport(chosenLoc);
                player.removeScoreboardTag("isPlayerInHub");
                player.addScoreboardTag("riderClass");

                Donkey donkey = (Donkey) Bukkit.getWorld("world").spawnEntity(player.getLocation(), EntityType.DONKEY);
                donkey.setTamed(true);
                donkey.getInventory().setSaddle(new ItemStack(Material.SADDLE));

                donkey.damage(20);

                donkey.addPassenger(player);



            } else if (args[0].equals("farmer")) {
                player.sendMessage(Component.text("You spawned as ").append(Component.text("farmer", TextColor.color(13, 25, 200))));
                ArrayList<Location> locations = new ArrayList<Location>();
                locations.add(new Location(player.getWorld(), 000, 000, -000));
                locations.add(new Location(player.getWorld(), 000, 000, -000));
                locations.add(new Location(player.getWorld(), 000, 000, -000));
                locations.add(new Location(player.getWorld(), 000, 000, -000));
                int random_int = (int)Math.floor(Math.random() * ((locations.size() - 1)  - 0 + 1) + 0);
                Location chosenLoc = locations.get(random_int);
                player.teleport(chosenLoc);
                player.removeScoreboardTag("isPlayerInHub");
                
                player.getInventory().addItem(new ItemStack(Material.WOODEN_HOE));

                int random_int2 = (int)Math.floor(Math.random() * ((6 - 1)  - 0 + 1) + 0);
                if (random_int2 == 0) {
                    player.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS));
                } else if (random_int2 == 1) {
                    player.getInventory().addItem(new ItemStack(Material.BEETROOT_SEEDS));
                } else if (random_int2 == 2) {
                    player.getInventory().addItem(new ItemStack(Material.MELON_SEEDS));
                } else if (random_int2 == 3) {
                    player.getInventory().addItem(new ItemStack(Material.PUMPKIN_SEEDS));
                } else if (random_int2 == 4) {
                    player.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS));
                } else if (random_int2 == 5) {
                    player.getInventory().addItem(new ItemStack(Material.CARROT));
                } else if (random_int2 == 6) {
                    player.getInventory().addItem(new ItemStack(Material.POTATO));
                };
                player.addScoreboardTag("classFarmer");
                
            } else if (args[0].equals("horseman")) {
                if (doesPlayerOwnFarm) {
                    player.sendMessage(Component.text("You spawned as ").append(Component.text("horseman", TextColor.color(13, 25, 200))));
                    ArrayList<Location> locations = new ArrayList<Location>();
                    locations.add(new Location(player.getWorld(), 000, 000, -000));
                    locations.add(new Location(player.getWorld(), 000, 000, -000));
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

            } else return false;
        } else {
            sender.sendMessage("You need to be in the hub when running this command. You can't choose class while alive.");
        }

        
        


        return true;
    }



}
