package ml.gggrealms.gggmcanarchy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class SafeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        Location playerLoc = player.getLocation();
        World w = player.getWorld();
        UUID pUUID = player.identity().uuid();
        Set<String> tags = player.getScoreboardTags();
        if (locationIsInCuboid(playerLoc, new Location(w, 855, 72, -893), new Location(w, 854, 68, -894)) || locationIsInCuboid(playerLoc, new Location(w, 851, 72, -893), new Location(w, 850, 68, -894))) {
            if (args.length == 0) {
                //Muegel Bank and Associates teller windows:
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                cfg.set("players." + pUUID + ".MBABankBalance", cfg.getInt("players." + pUUID + ".MBABankBalance") + 0);
                player.sendMessage("------Muegel Bank and Associates, a Premium banking experience.------");
                player.sendMessage(Component.text("Current Balance: ").append(Component.text("$", TextColor.color(84, 255, 88))).append(Component.text(cfg.getInt("players." + pUUID + ".MBABankBalance"))).append(Component.text(" / $200000")));
                player.sendMessage("Deposit money: /safe depo <int amount>");
                player.sendMessage("Withdraw money: /safe with <int amount>");
                player.sendMessage("Send money: /safe send <String playerName> <int amount>");
            } else if (args[0].equals("depo")) {
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                int money = cfg.getInt("players." + pUUID + ".money");
                int amtToDepo = money;
                if (args.length == 2) {
                    amtToDepo = Integer.parseInt(args[1]);
                }
                int MBABankBalance = cfg.getInt("players." + pUUID + ".MBABankBalance");
                if (amtToDepo >= 0) {
                    if (MBABankBalance < 200000) {
                        if (amtToDepo > money) {
                            amtToDepo = money;
                        }
                        if (amtToDepo + MBABankBalance > 200000) {
                            int maxAmtToDepo = MBABankBalance - amtToDepo;
                            money -= maxAmtToDepo;
                            MBABankBalance += maxAmtToDepo;
                        } else {
                            money -= amtToDepo;
                            MBABankBalance += amtToDepo;
                        }
                        player.performCommand("safe");
                    } else {
                        player.sendMessage(Component.text("Your balance is full at Muegel Bank & Associates ($200000).", TextColor.color(210, 11, 37)));
                    }
                } else {
                    player.sendMessage(Component.text("Deposit a positive amount of money.", TextColor.color(210, 11, 37)));
                }
                cfg.set("players." + pUUID + ".money", money);
                cfg.set("players." + pUUID + ".MBABankBalance", MBABankBalance);
                player.performCommand("safe");
                AnarchyPlugin.plugin.saveConfigFile();
            } else if (args[0].equals("with")) {
                FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                int money = cfg.getInt("players." + pUUID + ".money");
                int MBABankBalance = cfg.getInt("players." + pUUID + ".MBABankBalance");
                int amtToWith = MBABankBalance;
                if (args.length == 2) {
                    amtToWith = Integer.parseInt(args[1]);
                }
                if (amtToWith >= 0) {
                    if (amtToWith > MBABankBalance) {
                        money += MBABankBalance;
                        MBABankBalance = 0;
                    } else {
                        MBABankBalance -= amtToWith;
                        money += amtToWith;
                    }
                    cfg.set("players." + pUUID + ".money", money);
                    cfg.set("players." + pUUID + ".MBABankBalance", MBABankBalance);
                    AnarchyPlugin.plugin.saveConfigFile();
                    player.performCommand("safe");
                } else {
                    player.sendMessage(Component.text("Withdraw a positive amount of money.", TextColor.color(210, 11, 37)));
                }
            } else if (args[0].equals("send")) {
                if (args.length == 3) {
                    Player sendTo = Bukkit.getPlayer(args[1]);
                    if (sendTo != null) {
                        UUID sUUID = sendTo.identity().uuid();
                        FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                        int amountToSend = Integer.parseInt(args[2]);
                        int MBABankBalanceOfSender = cfg.getInt("players." + pUUID + ".MBABankBalance");
                        int MBABankBalanceOfRecip = cfg.getInt("players." + sUUID + ".MBABankBalance");
                        if (amountToSend >MBABankBalanceOfSender) {
                            amountToSend = MBABankBalanceOfSender;
                        }
                        if (MBABankBalanceOfRecip + amountToSend > 200000) {
                            amountToSend = 200000 - MBABankBalanceOfRecip;
                        }
                        MBABankBalanceOfSender -= amountToSend;
                        MBABankBalanceOfRecip += amountToSend;

                        cfg.set("players." + pUUID + ".MBABankBalance", MBABankBalanceOfSender);
                        cfg.set("players." + sUUID + ".MBABankBalance", MBABankBalanceOfRecip);

                        AnarchyPlugin.plugin.saveConfigFile();
                        player.sendMessage(Component.text("Sent " + amountToSend + " to player " + sendTo.getName()));

                    } else {
                        player.sendMessage(Component.text("Couldn't find player " + args[1] + ". Are they online?", TextColor.color(210, 11, 37)));
                    }
                } else {
                    player.sendMessage(Component.text("/safe send <player> <amount>", TextColor.color(210, 11, 37)));
                }
            }
        }
        else if (locationIsInCuboid(playerLoc, new Location(w,916, 92, -936), new Location(w, 914, 88, -938)))
        {
            if (tags.contains("doesPlayerOwnUSBankOffice")) {
                if (args.length == 0) {
                    //Cheap Apartment safe:
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    cfg.set("players." + pUUID + ".USBankOfficeBalance", cfg.getInt("players." + pUUID + ".USBankOfficeBalance") + 0);
                    player.sendMessage("------US Bank Office------");
                    player.sendMessage(Component.text("Current Balance: ").append(Component.text("$", TextColor.color(84, 255, 88))).append(Component.text(cfg.getInt("players." + pUUID + ".USBankOfficeBalance"))).append(Component.text(" / $60000")));
                    player.sendMessage("Deposit money: /safe depo <int amount>");
                    player.sendMessage("Withdraw money: /safe with <int amount>");
                    player.sendMessage(Component.text("Sell apartment: /safe sell", TextColor.color(255, 205, 44)));
                } else if (args[0].equals("depo")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pUUID + ".money");
                    int amtToDepo = money;
                    if (args.length == 2) {
                        amtToDepo = Integer.parseInt(args[1]);
                    }
                    int USBankOfficeBalance = cfg.getInt("players." + pUUID + ".USBankOfficeBalance");
                    if (amtToDepo >= 0) {
                        if (USBankOfficeBalance < 1000000) {
                            if (amtToDepo > money) {
                                amtToDepo = money;
                            }
                            if (amtToDepo + USBankOfficeBalance > 1000000) {
                                int maxAmtToDepo = USBankOfficeBalance - amtToDepo;
                                money -= maxAmtToDepo;
                                USBankOfficeBalance += maxAmtToDepo;
                            } else {
                                money -= amtToDepo;
                                USBankOfficeBalance += amtToDepo;
                            }
                            player.performCommand("safe");
                        } else {
                            player.sendMessage(Component.text("Your balance is full at the US Bank Office Safe ($1000000).", TextColor.color(210, 11, 37)));
                        }
                    } else {
                        player.sendMessage(Component.text("Deposit a positive amount of money.", TextColor.color(210, 11, 37)));
                    }
                    cfg.set("players." + pUUID + ".money", money);
                    cfg.set("players." + pUUID + ".CheapAptBalance", USBankOfficeBalance);
                    player.performCommand("safe");
                    AnarchyPlugin.plugin.saveConfigFile();
                } else if (args[0].equals("with")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pUUID + ".money");
                    int USBankOfficeBalance = cfg.getInt("players." + pUUID + ".USBankOfficeBalance");
                    int amtToWith = USBankOfficeBalance;
                    if (args.length == 2) {
                        amtToWith = Integer.parseInt(args[1]);
                    }
                    if (amtToWith >= 0) {
                        if (amtToWith > USBankOfficeBalance) {
                            money += USBankOfficeBalance;
                            USBankOfficeBalance = 0;
                        } else {
                            USBankOfficeBalance -= amtToWith;
                            money += amtToWith;
                        }
                        cfg.set("players." + pUUID + ".money", money);
                        cfg.set("players." + pUUID + ".CheapAptBalance", USBankOfficeBalance);
                        AnarchyPlugin.plugin.saveConfigFile();
                        player.performCommand("safe");
                    } else {
                        player.sendMessage(Component.text("Withdraw a positive amount of money.", TextColor.color(210, 11, 37)));
                    }
                } else if (args[0].equals("sell")) {
                    if (args.length == 1) {
                        player.sendMessage(Component.text("Are you sure you want to sell this apartment? You won't get the items in the stash back. You will keep the the amount the apartment cost and what was in the safe. If you are sure, please type /safe sell yes", TextColor.color(210, 11, 37)));
                    } else if (args.length == 2) {
                        if (args[1].equals("yes")) {
                            player.sendMessage(Component.text("Sold apartment.", TextColor.color(210, 11, 37)));
                            player.removeScoreboardTag("doesPlayerOwnUSBankOffice");
                            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                            int money = cfg.getInt("players." + pUUID + ".money");
                            int safeMoney = cfg.getInt("players." + pUUID + ".USBankOfficeBalance");
                            cfg.set("players." + pUUID + ".USBankOfficeBalance", 0);
                            money += 300000;
                            money += safeMoney;
                            cfg.set("players." + pUUID + ".money", money);
                            AnarchyPlugin.plugin.saveConfigFile();
                        } else {
                            player.sendMessage(Component.text("Wrong usage, try /safe maybe?", TextColor.color(210, 11, 37)));
                        }
                    } else {
                        player.sendMessage(Component.text("Wrong usage, try /safe maybe?", TextColor.color(210, 11, 37)));
                    }
                }
            } else {
                player.sendMessage(Component.text("You don't own this apartment.", TextColor.color(210, 11, 37)));
            }
        }else if (locationIsInCuboid(playerLoc, new Location(w,827, 75, -926), new Location(w, 826, 72, -929)))
        {
            if (tags.contains("doesPlayerOwnCheapApt")) {
                if (args.length == 0) {
                    //Cheap Apartment safe:
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    cfg.set("players." + pUUID + ".CheapAptBalance", cfg.getInt("players." + pUUID + ".CheapAptBalance") + 0);
                    player.sendMessage("------Cheap Apartment------");
                    player.sendMessage(Component.text("Current Balance: ").append(Component.text("$", TextColor.color(84, 255, 88))).append(Component.text(cfg.getInt("players." + pUUID + ".CheapAptBalance"))).append(Component.text(" / $60000")));
                    player.sendMessage("Deposit money: /safe depo <int amount>");
                    player.sendMessage("Withdraw money: /safe with <int amount>");
                    player.sendMessage(Component.text("Sell apartment: /safe sell", TextColor.color(255, 205, 44)));
                } else if (args[0].equals("depo")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pUUID + ".money");
                    int amtToDepo = money;
                    if (args.length == 2) {
                        amtToDepo = Integer.parseInt(args[1]);
                    }
                    int CheapAptBalance = cfg.getInt("players." + pUUID + ".CheapAptBalance");
                    if (amtToDepo >= 0) {
                        if (CheapAptBalance < 60000) {
                            if (amtToDepo > money) {
                                amtToDepo = money;
                            }
                            if (amtToDepo + CheapAptBalance > 60000) {
                                int maxAmtToDepo = CheapAptBalance - amtToDepo;
                                money -= maxAmtToDepo;
                                CheapAptBalance += maxAmtToDepo;
                            } else {
                                money -= amtToDepo;
                                CheapAptBalance += amtToDepo;
                            }
                            player.performCommand("safe");
                        } else {
                            player.sendMessage(Component.text("Your balance is full at the Cheap Apartment Safe ($60000).", TextColor.color(210, 11, 37)));
                        }
                    } else {
                        player.sendMessage(Component.text("Deposit a positive amount of money.", TextColor.color(210, 11, 37)));
                    }
                    cfg.set("players." + pUUID + ".money", money);
                    cfg.set("players." + pUUID + ".CheapAptBalance", CheapAptBalance);
                    player.performCommand("safe");
                    AnarchyPlugin.plugin.saveConfigFile();
                } else if (args[0].equals("with")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pUUID + ".money");
                    int CheapAptBalance = cfg.getInt("players." + pUUID + ".CheapAptBalance");
                    int amtToWith = CheapAptBalance;
                    if (args.length == 2) {
                        amtToWith = Integer.parseInt(args[1]);
                    }
                    if (amtToWith >= 0) {
                        if (amtToWith > CheapAptBalance) {
                            money += CheapAptBalance;
                            CheapAptBalance = 0;
                        } else {
                            CheapAptBalance -= amtToWith;
                            money += amtToWith;
                        }
                        cfg.set("players." + pUUID + ".money", money);
                        cfg.set("players." + pUUID + ".CheapAptBalance", CheapAptBalance);
                        AnarchyPlugin.plugin.saveConfigFile();
                        player.performCommand("safe");
                    } else {
                        player.sendMessage(Component.text("Withdraw a positive amount of money.", TextColor.color(210, 11, 37)));
                    }
                } else if (args[0].equals("sell")) {
                    if (args.length == 1) {
                        player.sendMessage(Component.text("Are you sure you want to sell this apartment? You won't get the items in the stash back. You will keep the the amount the apartment cost and what was in the safe. If you are sure, please type /safe sell yes", TextColor.color(210, 11, 37)));
                    } else if (args.length == 2) {
                        if (args[1].equals("yes")) {
                            player.sendMessage(Component.text("Sold apartment.", TextColor.color(210, 11, 37)));
                            player.removeScoreboardTag("doesPlayerOwnCheapApt");
                            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                            int money = cfg.getInt("players." + pUUID + ".money");
                            int safeMoney = cfg.getInt("players." + pUUID + ".CheapAptBalance");
                            cfg.set("players." + pUUID + ".CheapAptBalance", 0);
                            money += 20000;
                            money += safeMoney;
                            cfg.set("players." + pUUID + ".money", money);
                            AnarchyPlugin.plugin.saveConfigFile();
                        } else {
                            player.sendMessage(Component.text("Wrong usage, try /safe maybe?", TextColor.color(210, 11, 37)));
                        }
                    } else {
                        player.sendMessage(Component.text("Wrong usage, try /safe maybe?", TextColor.color(210, 11, 37)));
                    }
                }
            } else {
                player.sendMessage(Component.text("You don't own this apartment.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(playerLoc, new Location(w,1038, 73, -694), new Location(w, 1036, 70, -697)))
        {
            if (tags.contains("doesPlayerOwnFarm")) {
                if (args.length == 0) {
                    //Cheap Apartment safe:
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    cfg.set("players." + pUUID + ".FarmBalance", cfg.getInt("players." + pUUID + ".FarmBalance") + 0);
                    player.sendMessage("------Farm------");
                    player.sendMessage(Component.text("Current Balance: ").append(Component.text("$", TextColor.color(84, 255, 88))).append(Component.text(cfg.getInt("players." + pUUID + ".FarmBalance"))).append(Component.text(" / $500000")));
                    player.sendMessage("Deposit money: /safe depo <int amount>");
                    player.sendMessage("Withdraw money: /safe with <int amount>");
                    player.sendMessage(Component.text("Sell farm: /safe sell", TextColor.color(255, 205, 44)));
                } else if (args[0].equals("depo")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pUUID + ".money");
                    int amtToDepo = money;
                    if (args.length == 2) {
                        amtToDepo = Integer.parseInt(args[1]);
                    }
                    int FarmBalance = cfg.getInt("players." + pUUID + ".FarmBalance");
                    if (amtToDepo >= 0) {
                        if (FarmBalance < 60000) {
                            if (amtToDepo > money) {
                                amtToDepo = money;
                            }
                            if (amtToDepo + FarmBalance > 60000) {
                                int maxAmtToDepo = FarmBalance - amtToDepo;
                                money -= maxAmtToDepo;
                                FarmBalance += maxAmtToDepo;
                            } else {
                                money -= amtToDepo;
                                FarmBalance += amtToDepo;
                            }
                            player.performCommand("safe");
                        } else {
                            player.sendMessage(Component.text("Your balance is full at the Farm ($500000).", TextColor.color(210, 11, 37)));
                        }
                    } else {
                        player.sendMessage(Component.text("Deposit a positive amount of money.", TextColor.color(210, 11, 37)));
                    }
                    cfg.set("players." + pUUID + ".money", money);
                    cfg.set("players." + pUUID + ".FarmBalance", FarmBalance);
                    player.performCommand("safe");
                    AnarchyPlugin.plugin.saveConfigFile();
                } else if (args[0].equals("with")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pUUID + ".money");
                    int FarmBalance = cfg.getInt("players." + pUUID + ".FarmBalance");
                    int amtToWith = FarmBalance;
                    if (args.length == 2) {
                        amtToWith = Integer.parseInt(args[1]);
                    }
                    if (amtToWith >= 0) {
                        if (amtToWith > FarmBalance) {
                            money += FarmBalance;
                            FarmBalance = 0;
                        } else {
                            FarmBalance -= amtToWith;
                            money += amtToWith;
                        }
                        cfg.set("players." + pUUID + ".money", money);
                        cfg.set("players." + pUUID + ".FarmBalance", FarmBalance);
                        AnarchyPlugin.plugin.saveConfigFile();
                        player.performCommand("safe");
                    } else {
                        player.sendMessage(Component.text("Withdraw a positive amount of money.", TextColor.color(210, 11, 37)));
                    }
                } else if (args[0].equals("sell")) {
                    if (args.length == 1) {
                        player.sendMessage(Component.text("Are you sure you want to sell this farm? You won't get the items in the stash back. You will keep the the amount the farm cost and what was in the safe. If you are sure, please type /safe sell yes", TextColor.color(210, 11, 37)));
                    } else if (args.length == 2) {
                        if (args[1].equals("yes")) {
                            player.sendMessage(Component.text("Sold farm.", TextColor.color(210, 11, 37)));
                            player.removeScoreboardTag("doesPlayerOwnFarm");
                            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                            int money = cfg.getInt("players." + pUUID + ".money");
                            int safeMoney = cfg.getInt("players." + pUUID + ".farmBalance");
                            cfg.set("players." + pUUID + ".farmBalance", 0);
                            money += 150000;
                            money += safeMoney;
                            cfg.set("players." + pUUID + ".money", money);
                            AnarchyPlugin.plugin.saveConfigFile();
                        } else {
                            player.sendMessage(Component.text("Wrong usage, try /safe maybe?", TextColor.color(210, 11, 37)));
                        }
                    } else {
                        player.sendMessage(Component.text("Wrong usage, try /safe maybe?", TextColor.color(210, 11, 37)));
                    }
                }
            } else {
                player.sendMessage(Component.text("You don't own this farm.", TextColor.color(210, 11, 37)));
            }
        } else if (locationIsInCuboid(playerLoc, new Location(w,762, 16, -612), new Location(w, 756, 12, -615)))
        {
            if (tags.contains("doesPlayerOwnBunker")) {
                if (args.length == 0) {
                    //US Bank safe:
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    cfg.set("players." + pUUID + ".bunkerBalance", cfg.getInt("players." + pUUID + ".bunkerBalance") + 0);
                    player.sendMessage("------Bunker------");
                    player.sendMessage(Component.text("Current Balance: ").append(Component.text("$", TextColor.color(84, 255, 88))).append(Component.text(cfg.getInt("players." + pUUID + ".bunkerBalance"))).append(Component.text(" / $1500000")));
                    player.sendMessage("Deposit money: /safe depo <int amount>");
                    player.sendMessage("Withdraw money: /safe with <int amount>");
                    player.sendMessage(Component.text("Sell office: /safe sell", TextColor.color(255, 205, 44)));
                } else if (args[0].equals("depo")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pUUID + ".money");
                    int amtToDepo = money;
                    if (args.length == 2) {
                        amtToDepo = Integer.parseInt(args[1]);
                    }
                    int bunkerBalance = cfg.getInt("players." + pUUID + ".bunkerBalance");
                    if (amtToDepo >= 0) {
                        if (bunkerBalance < 1500000) {
                            if (amtToDepo > money) {
                                amtToDepo = money;
                            }
                            if (amtToDepo + bunkerBalance > 1500000) {
                                int maxAmtToDepo = bunkerBalance - amtToDepo;
                                money -= maxAmtToDepo;
                                bunkerBalance += maxAmtToDepo;
                            } else {
                                money -= amtToDepo;
                                bunkerBalance += amtToDepo;
                            }
                            player.performCommand("safe");
                        } else {
                            player.sendMessage(Component.text("Your balance is full at the bunker ($1500000).", TextColor.color(210, 11, 37)));
                        }
                    } else {
                        player.sendMessage(Component.text("Deposit a positive amount of money.", TextColor.color(210, 11, 37)));
                    }
                    cfg.set("players." + pUUID + ".money", money);
                    cfg.set("players." + pUUID + ".bunkerBalance", bunkerBalance);
                    player.performCommand("safe");
                    AnarchyPlugin.plugin.saveConfigFile();
                } else if (args[0].equals("with")) {
                    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                    int money = cfg.getInt("players." + pUUID + ".money");
                    int bunkerBalance = cfg.getInt("players." + pUUID + ".bunkerBalance");
                    int amtToWith = bunkerBalance;
                    if (args.length == 2) {
                        amtToWith = Integer.parseInt(args[1]);
                    }
                    if (amtToWith >= 0) {
                        if (amtToWith > bunkerBalance) {
                            money += bunkerBalance;
                            bunkerBalance = 0;
                        } else {
                            bunkerBalance -= amtToWith;
                            money += amtToWith;
                        }
                        cfg.set("players." + pUUID + ".money", money);
                        cfg.set("players." + pUUID + ".bunkerBalance", bunkerBalance);
                        AnarchyPlugin.plugin.saveConfigFile();
                        player.performCommand("safe");
                    } else {
                        player.sendMessage(Component.text("Withdraw a positive amount of money.", TextColor.color(210, 11, 37)));
                    }
                } else if (args[0].equals("sell")) {
                    if (args.length == 1) {
                        player.sendMessage(Component.text("Are you sure you want to sell this bunker? You won't get the items in the stash back. You will keep the the amount the bunker cost and what was in the safe. If you are sure, please type /safe sell yes", TextColor.color(210, 11, 37)));
                    } else if (args.length == 2) {
                        if (args[1].equals("yes")) {
                            player.sendMessage(Component.text("Sold bunker.", TextColor.color(210, 11, 37)));
                            player.removeScoreboardTag("doesPlayerOwnBunker");
                            FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
                            int money = cfg.getInt("players." + pUUID + ".money");
                            int safeMoney = cfg.getInt("players." + pUUID + ".bunkerBalance");
                            cfg.set("players." + pUUID + ".bunkerBalance", 0);
                            money += 1000000;
                            money += safeMoney;
                            cfg.set("players." + pUUID + ".money", money);
                            AnarchyPlugin.plugin.saveConfigFile();
                        } else {
                            player.sendMessage(Component.text("Wrong usage, try /safe maybe?", TextColor.color(210, 11, 37)));
                        }
                    } else {
                        player.sendMessage(Component.text("Wrong usage, try /safe maybe?", TextColor.color(210, 11, 37)));
                    }
                }
            } else {
                player.sendMessage(Component.text("You don't own this bunker.", TextColor.color(210, 11, 37)));
            }
        } else {
            player.sendMessage(Component.text("Not close enough to a bank/safe. Are you standing on the smooth stone?", TextColor.color(210, 11, 37)));
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
