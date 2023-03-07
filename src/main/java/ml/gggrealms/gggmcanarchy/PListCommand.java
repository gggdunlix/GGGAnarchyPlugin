package ml.gggrealms.gggmcanarchy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.codehaus.plexus.util.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
public class PListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
        Player p = (Player) sender;
        UUID pU = p.identity().uuid();
        
        if (args.length == 0) {
            Collection<Player> serverPlayers = (Collection<Player>) Bukkit.getOnlinePlayers();
                for (Player player : serverPlayers) {
                    String name = player.getName();
                    UUID pUUID = player.identity().uuid();
                    Location pLoc = player.getLocation();
                    String locString = "(" + pLoc.blockX() + ", " + pLoc.blockY() + ", " + pLoc.blockZ() + ")";
                    sender.sendMessage(Component.text(name + ": ", TextColor.color(91, 139, 255)).append(Component.text("", TextColor.color(255, 255, 255))));
                    sender.sendMessage(Component.text("|  -> Faction: ", TextColor.color(91, 139, 255)).append(Component.text(AnarchyPlugin.plugin.getFaction(player), TextColor.color(255, 255, 255))));
                    sender.sendMessage(Component.text("|  -> Rank: ", TextColor.color(91, 139, 255)).append(Component.text(AnarchyPlugin.plugin.getRank(player), TextColor.color(255, 255, 255))));
                }
        } else {
            sender.sendMessage(Component.text("Wrong usage. Use /plist without any arguments.", TextColor.color(91, 139, 255)));
        }
        AnarchyPlugin.plugin.saveConfigFile();
        return true;
    }
}
