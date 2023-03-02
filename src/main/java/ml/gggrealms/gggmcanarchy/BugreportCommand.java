package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
            Player p = (Player) sender;
            Location pLoc = p.getLocation();
            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
            String whatWasSaid = new String();
            int x = pLoc.blockX();
            int y = pLoc.blockY();
            int z = pLoc.blockZ();
            String place = "at (" + x + ", " + y + ", " + z + ") : ";
            whatWasSaid += place;
            for (String i : args) {
                whatWasSaid += (" " + i);
            }
            cfg.set("bugreports.\"" + date.toString() + "\"", "\"" + sender.getName() + " said " + whatWasSaid + "\"");
            AnarchyPlugin.plugin.saveConfigFile();

            try {
                URL url = new URL("https://discord.com/api/webhooks/1079911197584928778/E5UfX5at1LTFitT9OBRUuzW83bIBqmjaeTeHorTDbiHQQEGkIdG7JNhWxhXgfvYXlrBM");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.addRequestProperty("User-Agent", "Mozilla");
                con.setDoOutput(true);
                String jsonInputString = "{" +
                        "username : \"" + sender.getName() + "\", " +
                        "content : \"" + whatWasSaid + "\"" +
                        "}";
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage(e.toString());
                }
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(e.toString());
            }

            sender.sendMessage("report sent.");
        }
        return true;
    }
}
