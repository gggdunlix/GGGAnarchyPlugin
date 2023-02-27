package ml.gggrealms.gggmcanarchy;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class JoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            Player p = (Player) sender;
            Set<String> tags = p.getScoreboardTags();
            World w = p.getWorld();
            UUID pU = p.identity().uuid();
            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
            if (locationIsInCuboid(p.getLocation(), new Location(w, 1043,68,-670), new Location(w, 1038, 62,-671))) {
                if (tags.contains("factionArmor") || tags.contains("factionLeather") || tags.contains("faction")) {
                    sender.sendMessage(Component.text("You must retire from faction '" + AnarchyPlugin.plugin.getFaction(p) +  "' by using /retire before you can join another.", TextColor.color(210, 11, 37)));
                } else if (tags.contains("factionFarmer")) {
                    sender.sendMessage(Component.text("You are already a farmer", TextColor.color(210, 11, 37)));
                } else if (cfg.getInt("players." + pU + ".farmerCooldown") <= 0){
                    p.addScoreboardTag("factionFarmer");
                    cfg.set("players." + pU + ".farmerCooldown", 600);
                    p.sendMessage("Welcome to the farmers faction. You can use a hoe to create farmland and plant seeds. After you harvest your crops and seeds, you can sell them here for money.");
                    ItemStack wooden_hoe = new ItemStack(Material.WOODEN_HOE);
                    ItemMeta hoe_meta = wooden_hoe.getItemMeta();
                    hoe_meta.setPlaceableKeys(Lists.newArrayList(Material.GRASS_BLOCK.getKey(), Material.DIRT.getKey()));
                    hoe_meta.setDestroyableKeys(Lists.newArrayList(Material.WHEAT.getKey(), Material.BEETROOTS.getKey(), Material.CARROTS.getKey(), Material.POTATOES.getKey(), Material.MELON_STEM.getKey(), Material.MELON.getKey(), Material.PUMPKIN.getKey(), Material.PUMPKIN_STEM.getKey(), Material.SWEET_BERRY_BUSH.getKey()));
                    wooden_hoe.setItemMeta(hoe_meta);
                    p.getInventory().addItem(wooden_hoe);
                    ArrayList<ItemStack> seeds = new ArrayList<ItemStack>();
                    seeds.add(new ItemStack(Material.SWEET_BERRIES));
                    seeds.add(new ItemStack(Material.WHEAT_SEEDS));
                    seeds.add(new ItemStack(Material.CARROT));
                    seeds.add(new ItemStack(Material.POTATO));
                    seeds.add(new ItemStack(Material.BEETROOT_SEEDS));
                    seeds.add(new ItemStack(Material.MELON_SEEDS));
                    seeds.add(new ItemStack(Material.PUMPKIN_SEEDS));
                    seeds.add(new ItemStack(Material.SWEET_BERRIES));
                    for (ItemStack i : seeds) {
                        ItemMeta m = i.getItemMeta();
                        m.setPlaceableKeys(Lists.newArrayList(Material.FARMLAND.getKey()));
                        i.setItemMeta(m);
                    }
                    int random_int2 = (int)Math.floor(Math.random() * ((seeds.size() - 1)  - 0 + 1) + 0);
                    p.getInventory().addItem(seeds.get(random_int2));
                } else {
                    sender.sendMessage(Component.text("You must wait ").append(Component.text(cfg.getInt("players." + pU + ".farmerCooldown") + " seconds", TextColor.color(255, 230, 87))).append(Component.text(" before joining this faction again.", TextColor.color(255, 255, 255))));
                }
            } else {
                sender.sendMessage(Component.text("Too far... Are you standing on the smooth stone?", TextColor.color(210, 11, 37)));
            }
            AnarchyPlugin.plugin.saveConfigFile();
        } else {
            sender.sendMessage(Component.text("Wrong usage. This command doesn't take any arguments, it's just /join.", TextColor.color(210, 11, 37)));
        }
        return true;
    }
    public boolean locationIsInCuboid(Location _playerLocation, Location min, Location max) {
        boolean trueOrNot = false;
        Location playerLocation = _playerLocation.getBlock().getLocation();
        if (playerLocation.getWorld() == min.getWorld() && playerLocation.getWorld() == max.getWorld()) {
            if (playerLocation.getX() >= min.getX() && playerLocation.getX() <= max.getX()) {
                if (playerLocation.getY() >= min.getY() && playerLocation.getY() <= max.getY()) {
                    if (playerLocation.getZ() >= min.getZ()
                            && playerLocation.getZ() <= max.getZ()) {
                        trueOrNot = true;
                    }
                }
            }
            if (playerLocation.getX() <= min.getX() && playerLocation.getX() >= max.getX()) {
                if (playerLocation.getY() <= min.getY() && playerLocation.getY() >= max.getY()) {
                    if (playerLocation.getZ() <= min.getZ()
                            && playerLocation.getZ() >= max.getZ()) {
                        trueOrNot = true;
                    }
                }
            }
        }
        return trueOrNot;
    }
}
