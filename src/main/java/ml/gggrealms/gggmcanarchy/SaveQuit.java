package ml.gggrealms.gggmcanarchy;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SaveQuit implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        Boolean playerWantedStatus = false;
        //Need to get player wanted status from the mongodb
        Boolean canPlayerLeave = true;
        Player player = (Player) sender;
        Location playerLoc = player.getLocation();
        //determine using playerWantedStatus whether or not they are allowed to leave
        if (playerWantedStatus == false) {
            canPlayerLeave = true;
        } else {
            canPlayerLeave = false;
        }
        if (canPlayerLeave = false && playerWantedStatus = true) {
            sender.sendMessage("You can't save and quit while wanted. Either lose the wanted level, or quit and lose your inventory.");
        } else if (canPlayerLeave = false && playerWantedStatus = false) {
            sender.sendMessage("You can't leave the server right now.");
        } else if (canPlayerLeave = true) {
            sender.sendMessage("Stand Still for 20 seconds to disconnect!")
            Thread.sleep(20);
            if (player.getLocation() == playerLoc) {
                player.kickPlayer("Data saved. See you later!")
                //save the DB as saved for this player!
            } else {
                sender.sendMessage("You moved! Try again!");
            }
        }
        
        


        return true;
    }


}
