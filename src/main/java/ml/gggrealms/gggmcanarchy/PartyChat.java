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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class PartyChat implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        String whatWasSaid = new String();
        for (String i : args) {
            whatWasSaid += (" " + i);
        }

        UUID pU = p.identity().uuid();
        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
        int pParty = cfg.getInt("players." + pU + ".party", -1);
        Collection<Player> allPlayers = (Collection<Player>) Bukkit.getOnlinePlayers();
        ArrayList<Player> partyPlayers = new ArrayList<Player>();
        for (Player player : allPlayers) {
            int party = cfg.getInt("players." + pU + ".party", -1);
            if (party == pParty && party != -1) {
                partyPlayers.add(player);
            }
        }
        for (Player player : partyPlayers) {
            player.sendMessage(Component.text("[" + p.getName() + " | " + "P" + pParty + "] - ", TextColor.color(0xFFA503)).append(Component.text(whatWasSaid, TextColor.color(255,255,255))));
        }


        return true;
    }
}
