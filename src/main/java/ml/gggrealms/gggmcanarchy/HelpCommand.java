package ml.gggrealms.gggmcanarchy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;


public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Component.text("AnarCraft MC Server - HELP", TextColor.color(0xFF992C)));
            sender.sendMessage(Component.text("| -> /help", TextColor.color(0xFFEB25)).append(Component.text(" | Displays this command", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /props", TextColor.color(0xFFEB25)).append(Component.text(" | Displays help regarding all purchasable properties in the server.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /propabil", TextColor.color(0xFFEB25)).append(Component.text(" | Displays help regarding specific property abilities", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /party", TextColor.color(0xFFEB25)).append(Component.text(" | Displays help regarding the server's party functions", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("Other Commands:", TextColor.color(0xFF992C)));
            sender.sendMessage(Component.text("| -> /as", TextColor.color(0xFFEB25)).append(Component.text(" | Changes your autospawning configuartion", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /bug", TextColor.color(0xFFEB25)).append(Component.text(" | Report bugs to the owner", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /buy", TextColor.color(0xFFEB25)).append(Component.text(" | Allows you to purchase certain things while standing on smooth stone.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /class or /c", TextColor.color(0xFFEB25)).append(Component.text(" | Choose what class to spawn as.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /enter or /e", TextColor.color(0xFFEB25)).append(Component.text(" | Enters doors or areas/elevators when standing on smooth stone.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /join", TextColor.color(0xFFEB25)).append(Component.text(" | Join factions at certain areas.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /list", TextColor.color(0xFFEB25)).append(Component.text(" | Lists all connected players and their rank/faction.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /retire", TextColor.color(0xFFEB25)).append(Component.text(" | Leaves your current faction and allows you to respawn, while keeping your money/items.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /safe", TextColor.color(0xFFEB25)).append(Component.text(" | Allows you to access money safe at specific locations.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /savequit", TextColor.color(0xFFEB25)).append(Component.text(" | Saves your character at your current position after 20s, keeping money and loot.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /sell", TextColor.color(0xFFEB25)).append(Component.text(" | Sell certain items at specific places.", TextColor.color(0x5095FF))));
            sender.sendMessage(Component.text("| -> /stash", TextColor.color(0xFFEB25)).append(Component.text(" | Store items in a private container at certain places.", TextColor.color(0x5095FF))));

        }
        return true;
    }
}

