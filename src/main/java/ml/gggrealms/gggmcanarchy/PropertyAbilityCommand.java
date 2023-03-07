package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PropertyAbilityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Component.text("Property Abilities: ", TextColor.color(42, 101, 255)));
            sender.sendMessage(Component.text("|  ->", TextColor.color(42, 101, 255)).append(Component.text("/pa boat ", TextColor.color(244, 126, 57)).append(Component.text("- Spawns a boat if near the Docks Office. Need to own Docks Office."))));
            sender.sendMessage(Component.text("|  ->", TextColor.color(42, 101, 255)).append(Component.text("/pa boat ", TextColor.color(244, 126, 57)).append(Component.text("- Spawns a boat if near the Docks Office. Need to own Docks Office."))));

        }
        return true;
    }
}
