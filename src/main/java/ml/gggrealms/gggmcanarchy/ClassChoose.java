package ml.gggrealms.gggmcanarchy;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ClassChoose implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Boolean isPlayerInHub = false;
        Boolean doesPlayerOwnSmallApt = false;
        Player player = (Player) sender;

        if (isPlayerInHub) {
            if (args.length == 0) {
                player.sendMessage("\"rider\": Spawns with no items at a random location with a bad horse.");
                player.sendMessage("\"default\": Spawns with no items at a random location.");
                player.sendMessage("^^^ Here are a list of classes that are available. Some require properties and/or come with other special perks. ^^^");
            }
        }

        
        


        return false;
    }


}
