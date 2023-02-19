package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class EnterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.getLocation().toVector().isInAABB(new Location(player.getWorld(), 770, 63, -950).toVector(), new Location(player.getWorld(), 769, 65, -949).toVector())) {
            //Enter First Pacific Bank Vault
            player.teleport(new Location(player.getWorld(), 770, 64, -946));
        } else if (player.getLocation().toVector().isInAABB(new Location(player.getWorld(), 769, 63, -946).toVector(), new Location(player.getWorld(), 770, 65, -947).toVector())) {
            //Exit First Pacific Bank Vault
            player.teleport(new Location(player.getWorld(), 769, 64, -950));
        } else {
            player.sendMessage(Component.text("Not close enough. Are you standing on the smooth stone?", TextColor.color(210, 11, 37)));
        }
        return true;
    }
}
