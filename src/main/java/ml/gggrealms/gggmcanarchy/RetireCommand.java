package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class RetireCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Boolean playerWantedStatus = false;
        //Need to get player wanted status from the mongodb
        Boolean canPlayerLeave = true;
        Player player = (Player) sender;
        Location playerLoc = player.getLocation();
        //determine using playerWantedStatus whether they are allowed to leave

        if (playerWantedStatus == false) {
            canPlayerLeave = true;
        } else {
            canPlayerLeave = false;
        }
        if (!canPlayerLeave && playerWantedStatus) {
            sender.sendMessage("You can't retire while wanted.");
        } else if (!canPlayerLeave && !playerWantedStatus) {
            sender.sendMessage("You can't retire right now.");
        } else if (canPlayerLeave) {
            sender.sendMessage("Stand Still for 20 seconds to retire!");
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (player.getLocation().equals(playerLoc)) {
                        player.addScoreboardTag("isPlayerInHub");
                        player.sendMessage(Component.text("Retired!"));
                        player.teleport(new Location(player.getWorld(), 818, -13, -1031, -90, 0));

                        player.removeScoreboardTag("classFarmer");
                        player.removeScoreboardTag("classDefault");
                        player.removeScoreboardTag("classRider");
                        player.removeScoreboardTag("classHorseman");
                        player.removeScoreboardTag("classDev");

                        player.removeScoreboardTag("factionFarmer");
                        player.removeScoreboardTag("factionLeather");
                        player.removeScoreboardTag("factionArmor");
                        player.performCommand("class");
                    } else {
                        sender.sendMessage("You moved! Try again!");
                    }
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 20*20);

        }
        return true;
    }
}
