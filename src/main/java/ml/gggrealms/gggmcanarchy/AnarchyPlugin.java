package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.HSVLike;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.css.RGBColor;

public class AnarchyPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getConsoleSender().sendMessage("Activating plugin");

        
        this.getCommand("savequit").setExecutor(new SaveQuit());
        
        Bukkit.getServer().getConsoleSender().sendMessage("----- GGG MC Anarchy Plugin -----");
        Bukkit.getServer().getConsoleSender().sendMessage("Plugin now activated, version 0.0.1");
        Bukkit.getServer().getConsoleSender().sendMessage("Plugin Preliminary stage, all players are in creative build mode.");
        Bukkit.getServer().getConsoleSender().sendMessage("----- GGG MC Anarchy Plugin -----");
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        //LET THEM BUILD!
        Player joinedP = event.getPlayer();
        joinedP.sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
        joinedP.sendMessage(Component.text("So, What's going on here? \nWell, This is gonna be the start of an anarchy server. But not just any random one, One where players cannot mine or place things, just play the game with mechanics and a gamemode we implement ourselves."));
        joinedP.sendMessage(Component.text("Right now, we're gonna set you to creative and ask that you start building a city with buildings that would have a function, like stores, offices, houses, whatever you feel should fit. Eventually, the server will be playable and very fun.\nThanks, GGGDunlix & GGGCarl."));
        joinedP.setGameMode(GameMode.CREATIVE);
        Bukkit.getServer().setSpawnRadius(0);


        // get if the player saved before quitting
        Boolean isDataSaved = false;

        if (isDataSaved) {
            joinedP.sendMessage(Component.text("Data loaded, welcome back!"))

        } else {
            //make them rechoose class
            //clear their inv:
            PlayerInventory pli1= joinedP.getInventory();
            pli1.clear();
            
            pli1.setHelmet(new ItemStack(Material.AIR));
            pli1.setChestplate(new ItemStack(Material.AIR));
            pli1.setLeggings(new ItemStack(Material.AIR));
            pli1.setBoots(new ItemStack(Material.AIR));
            joinedP.sendMessage(Component.text("No data saved, did you not use /savequit?", TextColor.color(HSVLike.fromRGB(255, 35, 10))));
        }

    }

}
