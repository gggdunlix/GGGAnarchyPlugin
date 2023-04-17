package ml.gggrealms.gggmcanarchy.Properties;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ml.gggrealms.gggmcanarchy.Lang;
import java.util.Set;

public class PropertiesCommand implements CommandExecutor {
    private Lang lang;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Set<String> tags = p.getScoreboardTags();
        sender.sendMessage(lang.propListHeader);
        Property[] allProps = lang.allProps;
        for(Property prop : allProps) {
            lang.propListMessage(p, prop);
        }
        return true;
    }
}
