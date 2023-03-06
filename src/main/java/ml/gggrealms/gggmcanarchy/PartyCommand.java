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
public class PartyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
        Player p = (Player) sender;
        UUID pU = p.identity().uuid();
        int currentParty = cfg.getInt("players." + pU + ".party");
        if (args.length == 0) {
            sender.sendMessage(Component.text("----- ---- Party System ---- -----", TextColor.color(91, 139, 255)));
            sender.sendMessage(Component.text("/party <party number> ", TextColor.color(91, 139, 255)).append(Component.text("- Use to join any party, regardless of whether it was created or not. Using a non-existent party automatically creates a new one.", TextColor.color(255,255,255))));
            sender.sendMessage(Component.text("/party leave ", TextColor.color(91, 139, 255)).append(Component.text("- Leaves your current party", TextColor.color(255,255,255))));
            sender.sendMessage(Component.text("/party list ", TextColor.color(91, 139, 255)).append(Component.text("- Lists the players in your party", TextColor.color(255,255,255))));
        } else if (StringUtils.isNumericSpace(args[0])) {
            int newParty = Integer.parseInt(args[0]);
            cfg.set("players." + pU + ".party", newParty);
            sender.sendMessage(Component.text("Joined party: ", TextColor.color(91, 139, 255)).append(Component.text(newParty + "", TextColor.color(255,255,255))));

        } else if (args[0].equals("leave")) {
            if (currentParty != -1) {
                cfg.set("players." + pU + ".party", 0);
                sender.sendMessage("Left party.");
            } else {
                sender.sendMessage(Component.text("You are not in a party.", TextColor.color(210, 11, 37)));
            }
        } else if (args[0].equals("list")) {
            if (currentParty != -1) {
                Collection<Player> serverPlayers = (Collection<Player>) Bukkit.getOnlinePlayers();
                for (Player player : serverPlayers) {
                    String name = player.getName();
                    UUID pUUID = player.identity().uuid();
                    Location pLoc = player.getLocation();
                    String locString = "(" + pLoc.blockX() + ", " + pLoc.blockY() + ", " + pLoc.blockZ() + ")";
                    sender.sendMessage(Component.text(name + ": ", TextColor.color(91, 139, 255)).append(Component.text("", TextColor.color(255, 255, 255))));
                    sender.sendMessage(Component.text("|  -> Location: ", TextColor.color(91, 139, 255)).append(Component.text(locString, TextColor.color(255, 255, 255))));
                    sender.sendMessage(Component.text("|  -> Faction: ", TextColor.color(91, 139, 255)).append(Component.text(AnarchyPlugin.plugin.getFaction(player), TextColor.color(255, 255, 255))));
                    sender.sendMessage(Component.text("|  -> Rank: ", TextColor.color(91, 139, 255)).append(Component.text(AnarchyPlugin.plugin.getRank(player), TextColor.color(255, 255, 255))));
                }
            } else {
                sender.sendMessage(Component.text("You are not in a party.", TextColor.color(210, 11, 37)));
            }
        }
        AnarchyPlugin.plugin.saveConfigFile();
        return true;
    }
}
