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

public class GiveMoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {

            Player p = (Player) sender;
            UUID pUUID = p.identity().uuid();

            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
            cfg.set("players." + pUUID + ".money", cfg.getInt("players." + pUUID + ".money") + Integer.parseInt(args[0]));
            sender.sendMessage(Component.text("Money: ", TextColor.color(255, 255, 255)).append(Component.text("$", TextColor.color(103, 240, 117)).append(Component.text(cfg.getInt("players." + pUUID + ".money"), TextColor.color(103, 240, 117)))));
            AnarchyPlugin.plugin.saveConfigFile();
        } else {
            sender.sendMessage(Component.text("Wrong usage. Use: /givemoney <int dollars>", TextColor.color(210, 11, 37)));
        }
        return true;

    }
}
