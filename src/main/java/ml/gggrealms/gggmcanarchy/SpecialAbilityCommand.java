package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SpecialAbilityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        String faction = AnarchyPlugin.plugin.getFaction(p);
        if (args.lenght == 0) {
          if (faction.equals("none") {
            p.sendMessage("You aren't in a faction, and don't have a special ability") 
          }
        } else {
          p.sendMessage("Wrong syntax. Just do /abil without any arguments.")
        }
    }
}
