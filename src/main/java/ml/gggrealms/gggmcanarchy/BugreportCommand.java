package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.time.Clock;
import java.time.LocalDate;

public class BugreportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Component.text("Wrong usage. Use: /bug <message>", TextColor.color(210, 11, 37)));
        } else {
            java.util.Date date = new java.util.Date();
            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
            cfg.set("bugreports." + date.toString().replace(":", "."), args);
            AnarchyPlugin.plugin.saveConfigFile();
        }
        return true;
    }
}
