package ml.gggrealms.gggmcanarchy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class classOnTabCompleteClass implements TabCompleter {
    private static final String[] COMMANDS = { "default", "thug", "farmer", "horseman", "agent", "bankrobber", "crackhead", "heister", "pilot", "employee", "drugslinger", "gunsmith", "captain", "boater", "investor"};
    //create a static array of values

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        //create new array
        final List<String> completions = new ArrayList<>();
        //copy matches of first argument from list (ex: if first arg is 'm' will return just 'minecraft')
        StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), completions);
        //sort the list
        Collections.sort(completions);
        return completions;
    }
}