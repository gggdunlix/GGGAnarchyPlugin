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

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;
        UUID pUUID = p.identity().uuid();

        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();

        sender.sendMessage(Component.text("Money: ", TextColor.color(255, 255, 255)).append(Component.text("$", TextColor.color(103, 240, 117)).append(Component.text(cfg.getInt("players." + pUUID + ".money"), TextColor.color(103,240,117)))));
        return true;
    }
}
