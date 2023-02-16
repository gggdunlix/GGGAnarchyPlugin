package ml.gggrealms.gggmcanarchy;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SaveQuit implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
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
            sender.sendMessage("You can't save and quit while wanted. Either lose the wanted level, or quit and lose your inventory.");
        } else if (!canPlayerLeave && !playerWantedStatus) {
            sender.sendMessage("You can't leave the server right now.");
        } else if (canPlayerLeave) {
            sender.sendMessage("Stand Still for 20 seconds to disconnect!");
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (player.getLocation().equals(playerLoc)) {
                        player.addScoreboardTag("saved");
                        player.kick(Component.text("Data saved! See you later!"));
                        //save the DB as saved for this player!
                    } else {
                        sender.sendMessage("You moved! Try again!");
                    }
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 20*20);

        }
        
        


        return true;
    }


}
