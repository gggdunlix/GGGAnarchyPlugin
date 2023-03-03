package ml.gggrealms.gggmcanarchy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class PropertiesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Set<String> tags = p.getScoreboardTags();
        sender.sendMessage(Component.text("List of un-owned server properties:", TextColor.color(80, 149, 255)));
        if (!tags.contains("doesPlayerOwnMotel")) {
            sender.sendMessage(Component.text("Motel:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $5,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $200", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $20,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (00, 00, -00)", TextColor.color(255, 255, 255)));
        }
        if (!tags.contains("doesPlayerOwnCheapApt")) {
            sender.sendMessage(Component.text("Cheap Apartment:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $20,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $1,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $60,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (830, 73, -933)", TextColor.color(255, 255, 255)));
        }
        if (!tags.contains("doesPlayerOwnFarm")) {
            sender.sendMessage(Component.text("Farm:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $150,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $5,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $500,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (1049, 63, -670)", TextColor.color(255, 255, 255)));
        }
        if (!tags.contains("doesPlayerOwnOpalHotel")) {
            sender.sendMessage(Component.text("Opal Hotel:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $200,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $15,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (923, 69, -929)", TextColor.color(255, 255, 255)));
        }
        if (!tags.contains("doesPlayerOwnUSBankOffice")) {
            sender.sendMessage(Component.text("US Bank Office:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $300,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $20,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (923, 69, -929)", TextColor.color(255, 255, 255)));
        }
        if (!tags.contains("doesPlayerOwnBunker")) {
            sender.sendMessage(Component.text("Bunker:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $50,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (923, 69, -929)", TextColor.color(255, 255, 255)));
        }
        if (!tags.contains("doesPlayerOwnCasino")) {
            sender.sendMessage(Component.text("Casino:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $50,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $75,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (923, 69, -929)", TextColor.color(255, 255, 255)));
        }
        sender.sendMessage(Component.text("List of owned server properties:", TextColor.color(58, 255, 88)));
        if (tags.contains("doesPlayerOwnMotel")) {
            sender.sendMessage(Component.text("Motel:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $5,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $200", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $20,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (00, 00, -00)", TextColor.color(255, 255, 255)));
        }
        if (tags.contains("doesPlayerOwnCheapApt")) {
            sender.sendMessage(Component.text("Cheap Apartment:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $20,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $1,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $60,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (830, 73, -933)", TextColor.color(255, 255, 255)));
        }
        if (tags.contains("doesPlayerOwnFarm")) {
            sender.sendMessage(Component.text("Farm:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $150,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $5,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $500,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (1049, 63, -670)", TextColor.color(255, 255, 255)));
        }
        if (tags.contains("doesPlayerOwnOpalHotel")) {
            sender.sendMessage(Component.text("Opal Hotel:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $200,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $15,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (923, 69, -929)", TextColor.color(255, 255, 255)));
        }
        if (tags.contains("doesPlayerOwnUSBankOffice")) {
            sender.sendMessage(Component.text("US Bank Office:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $300,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $20,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (923, 69, -929)", TextColor.color(255, 255, 255)));
        }
        if (tags.contains("doesPlayerOwnBunker")) {
            sender.sendMessage(Component.text("Bunker:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $50,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (923, 69, -929)", TextColor.color(255, 255, 255)));
        }
        if (tags.contains("doesPlayerOwnCasino")) {
            sender.sendMessage(Component.text("Casino:", TextColor.color(255, 153, 44)));
            sender.sendMessage(Component.text("|  -> Cost: $50,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Upkeep: $1,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Safe Max $: $75,000,000", TextColor.color(255, 255, 255)));
            sender.sendMessage(Component.text("|  -> Location: (923, 69, -929)", TextColor.color(255, 255, 255)));
        }

        return true;
    }
}
