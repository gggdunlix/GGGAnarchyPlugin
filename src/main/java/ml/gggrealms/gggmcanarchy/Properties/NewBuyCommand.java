package ml.gggrealms.gggmcanarchy.Properties;

import com.google.common.collect.Lists;
import ml.gggrealms.gggmcanarchy.AnarchyPlugin;
import ml.gggrealms.gggmcanarchy.Lang;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class NewBuyCommand implements CommandExecutor {
    private Lang lang;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            boolean worked = false;
            Player p = (Player) sender;
            World w = p.getWorld();
            UUID pU = p.identity().uuid();
            Set<String> tags = p.getScoreboardTags();
            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
            int money = cfg.getInt("players." + pU + "money");
            int rank = cfg.getInt("players." + pU + "rank");
            Property[] props = lang.allProps;
            for (Property prop : props) {
                int cost = prop.getInfo().getCost();
                int upkeep = prop.getInfo().getUpkeep();
                String propName = prop.getInfo().getName();
                String propNamespace = prop.getInfo().getNamespace();
                String propTag = "propOwned." + propNamespace;
                if (!tags.contains(propTag)) {
                    if (cost > money) {
                        p.sendMessage(lang.notEnoughMoney(money, cost));
                    } else {
                        money -= cost;
                        rank += upkeep;
                        p.addScoreboardTag(propTag);
                        p.performCommand("e");
                    }
                } else {
                    p.sendMessage(lang.alreadyOwnProp(prop));
                    p.performCommand("e");
                }
            }

            if (lang.locationIsInCuboid(p.getLocation(), new Location(w, 1043,68,-670), new Location(w, 1038, 62,-671))) {
                //buy tools thing at farm
                if (AnarchyPlugin.plugin.getFaction(p).equals("farmer")) {
                    if (money >= 500) {
                        money -= 500;
                        PlayerInventory i = p.getInventory();
                        ItemStack stone_hoe = new ItemStack(Material.STONE_HOE);
                        ItemMeta hoe_meta = stone_hoe.getItemMeta();
                        hoe_meta.setPlaceableKeys(Lists.newArrayList(Material.GRASS_BLOCK.getKey(), Material.DIRT.getKey()));
                        hoe_meta.setDestroyableKeys(Lists.newArrayList(Material.WHEAT.getKey(), Material.BEETROOTS.getKey(), Material.CARROTS.getKey(), Material.POTATOES.getKey(), Material.MELON_STEM.getKey(), Material.MELON.getKey(), Material.PUMPKIN.getKey(), Material.PUMPKIN_STEM.getKey(), Material.SWEET_BERRY_BUSH.getKey()));
                        stone_hoe.setItemMeta(hoe_meta);
                        p.getInventory().addItem(stone_hoe);
                        ArrayList<ItemStack> seeds = new ArrayList<ItemStack>();
                        seeds.add(new ItemStack(Material.SWEET_BERRIES));
                        seeds.add(new ItemStack(Material.WHEAT_SEEDS));
                        seeds.add(new ItemStack(Material.CARROT));
                        seeds.add(new ItemStack(Material.POTATO));
                        seeds.add(new ItemStack(Material.BEETROOT_SEEDS));
                        seeds.add(new ItemStack(Material.MELON_SEEDS));
                        seeds.add(new ItemStack(Material.PUMPKIN_SEEDS));
                        for (ItemStack iS : seeds) {
                            ItemMeta m = iS.getItemMeta();
                            m.setPlaceableKeys(Lists.newArrayList(Material.FARMLAND.getKey()));
                            iS.setItemMeta(m);
                        }
                        int random_int2 = (int) Math.floor(Math.random() * ((seeds.size() - 1) - 0 + 1) + 0);
                        i.addItem(seeds.get(random_int2));
                    } else {
                        p.sendMessage(Component.text("Not enough money.", TextColor.color(210, 11, 37)));
                    }
                } else {
                    p.sendMessage(Component.text("You need to be a farmer to buy this.", TextColor.color(210, 11, 37)));
                }
            }
            else if (lang.locationIsInCuboid(p.getLocation(), new Location(w, 882,71,-878), new Location(w, 871,68,-879))) {
                //shop at walmart thing
                Inventory shop = Bukkit.createInventory(null, 54, "Supermarket");
                ItemStack bread = new ItemStack(Material.BREAD);
                ItemMeta breadMeta = bread.getItemMeta();
                List<Component> breadLore = new ArrayList();
                breadLore.add(Component.text("Bland and cheap"));
                breadLore.add(Component.text("Cost: ").append(Component.text("$30")));
                breadMeta.lore(breadLore);
            }
            if (!worked) {
                p.sendMessage(Component.text("Not close enough. Are you standing on the smooth stone?", TextColor.color(210, 11, 37)));
            }
            cfg.set("players." + pU + ".money", money);
            cfg.set("players." + pU + ".rank", rank);
            AnarchyPlugin.plugin.saveConfigFile();
        } else {
            sender.sendMessage(Component.text("Wrong usage. This command doesn't take any arguments, it's just /buy.", TextColor.color(210, 11, 37)));
        }
        return true;
    }


}
