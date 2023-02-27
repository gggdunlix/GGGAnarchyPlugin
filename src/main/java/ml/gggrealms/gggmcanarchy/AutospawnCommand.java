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

public class AutospawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
        Player player = (Player) sender;
        UUID pUUID = player.identity().uuid();
        if (args.length == 0) {
            sender.sendMessage(Component.text("Wrong usage. Use: /as <true/false> or /as <int seconds>", TextColor.color(210, 11, 37)));
            return true;
        }
        if (args[0].equals("true")) {
            sender.sendMessage(Component.text("You will automatically spawn as ", TextColor.color(255,255,255)).append(Component.text("default", TextColor.color(13, 25, 200))).append(Component.text(" ")).append(Component.text(cfg.getInt("players." + pUUID + ".autospawnRate"), TextColor.color(13, 25, 200))).append(Component.text(" seconds after spawning", TextColor.color(255, 255, 255))));
            cfg.set("players." + pUUID + ".autospawn", true);
            AnarchyPlugin.plugin.saveConfigFile();
            return true;
        } else if (args[0].equals("false")) {
            cfg.set("players." + pUUID + ".autospawn", false);
            sender.sendMessage("You will not automatically spawn.");
            AnarchyPlugin.plugin.saveConfigFile();
            return true;
        } else if (Integer.parseInt(args[0]) > 0) {
            if (cfg.getBoolean("players." + pUUID + ".autospawn")) {
                cfg.set("players." + pUUID + ".autospawnRate", Integer.parseInt(args[0]));
                sender.sendMessage(Component.text("You will automatically spawn as ", TextColor.color(255,255,255)).append(Component.text("default", TextColor.color(13, 25, 200))).append(Component.text(" ")).append(Component.text(cfg.getInt("players." + pUUID + ".autospawnRate"), TextColor.color(13, 25, 200))).append(Component.text(" seconds after spawning", TextColor.color(255, 255, 255))));
            } else {
                sender.sendMessage("Autospawn isn't enabled.");
            }
            AnarchyPlugin.plugin.saveConfigFile();
            return true;
        }
        return false;
    }
}
