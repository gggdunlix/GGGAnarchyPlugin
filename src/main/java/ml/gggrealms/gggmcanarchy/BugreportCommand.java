package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
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
            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
            cfg.set("bugreports.\"" + date.toString() + "\"", "\"" + sender.getName() + " said " + args + "\"");
            AnarchyPlugin.plugin.saveConfigFile();

            try {
                URL url = new URL("https://discord.com/api/webhooks/1079911197584928778/E5UfX5at1LTFitT9OBRUuzW83bIBqmjaeTeHorTDbiHQQEGkIdG7JNhWxhXgfvYXlrBM");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.addRequestProperty("Content-Type", "application/json");
                connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                OutputStream stream = connection.getOutputStream();
                stream.write(("\"" + sender.getName() + " said " + args + "\"").getBytes(StandardCharsets.UTF_8));
                stream.flush();
                stream.close();

                connection.getInputStream().close(); //I'm not sure why but it doesn't work without getting the InputStream
                connection.disconnect();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(e.toString());
            }

            sender.sendMessage("report sent.");
        }
        return true;
    }
}
