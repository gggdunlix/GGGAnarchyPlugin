package ml.gggrealms.gggmcanarchy;

import ml.gggrealms.gggmcanarchy.Properties.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.HSVLike;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class Lang {
    public TextColor errorRed = TextColor.color(210, 11, 37);
    public String opJoinMessage = "Ops can spawn as dev class to spawn creative mode.";
    public Component dataLoadedFromSave = Component.text("Data loaded, welcome back!", TextColor.color(254, 214, 69));
    public Component noDataLoadedMessage = Component.text("No data saved, did you not use /savequit?", TextColor.color(HSVLike.fromRGB(255, 35, 10)));

    public Component autoSpawnWarning(FileConfiguration cfg, String path) {
        int asSeconds = cfg.getInt(path);
        return Component.text("Automatically spawning as default in ", TextColor.color(0x389495)).append(Component.text(asSeconds, TextColor.color(0xFFA503)).append(Component.text(" seconds.", TextColor.color(0x389495))));
    }
    public Component autoRespawnWarning(int rate) {
        return Component.text("Automatically spawning as default in ", TextColor.color(0x389495)).append(Component.text(rate, TextColor.color(0xFFA503)).append(Component.text("seconds. (/as to change)", TextColor.color(0x389495))));
    }
    public String scoreboardTitle = "AnarCraft";
    public String stashSaved = "Saved stash.";

    public Component blockedFriendlyFire = Component.text("Blocked friendly fire.", errorRed);

    public double upkeepFactor = .005;
    public double maxSafeFactor = 2.5;

    World w = Bukkit.getWorld("world");

    public Component alreadyOwnProp(Property prop) {
        return Component.text("You already own " + prop.getInfo().getName() + ". Entering anyway.", errorRed);
    }
    public PropertyInfo motelI = new PropertyInfo(5000, "Motel", new String[]{"classCrackhead", "classEmployee"});
    public PropertyInfo cheapApartmentI = new PropertyInfo(20000,  "Cheap Apartment", new String[]{"classThug"});
    public PropertyInfo farmI = new PropertyInfo(150000,  "Farm", new String[]{"classHorseman"});
    public PropertyInfo opalHotelI = new PropertyInfo(200000, "Opal Hotel", new String[]{"classDrugSlinger", "classGunsmith"});
    public PropertyInfo usBankOfficeI = new PropertyInfo(300000,  "US Bank Office", new String[]{"classBankRobber"}, "usBankOffice");
    public PropertyInfo rooseveltResidenceI = new PropertyInfo(500000,  "Roosevelt Residence", new String[]{"classInvestor", "classBanker"});
    public PropertyInfo docksOfficeI = new PropertyInfo(650000,  "Opal Hotel", new String[]{"classDrugSlinger", "classGunsmith"});
    public PropertyInfo bunkerI = new PropertyInfo(1000000,  "Bunker", new String[]{"classHeister"});
    public PropertyInfo casinoI = new PropertyInfo(50000000,  "Casino", new String[]{"classPilot", "classAgent"});

    public PropertyInfo[] allPropInfos = new PropertyInfo[]{motelI,cheapApartmentI,farmI,opalHotelI,usBankOfficeI,rooseveltResidenceI,docksOfficeI,bunkerI,casinoI};

    public PropertyPos motelPos = new PropertyPos(motelI, new Location(w, 00,00,00),new Location(w, 00,00,00),new Location(w, 00,00,00),new Location(w, 00,00,00),new Location(w, 00,00,00),new Location(w, 00,00,00));
    public PropertyPos cheapApartmentPos = new PropertyPos(cheapApartmentI,  new Location(w, 830, 76, -933), new Location(w, 829, 72, -934),new Location(w, 826, 73, -933),new Location(w, 827, 74, -933), new Location(w, 826, 72, -934),new Location(w, 830, 73, -933));
    public PropertyPos farmPos = new PropertyPos(farmI, new Location(w, 1051,65,-669), new Location(w, 1048,62,-672), new Location(w, 1037, 71, -678), new Location(w, 1037,72,-678), new Location(w, 1036,70,-680), new Location(w, 1048, 63, -670));
    public PropertyPos usBankOfficePos = new PropertyPos(usBankOfficeI,new Location(w, 924,92,-929), new Location(w, 922,88,-930),new Location(w, 920, 89, -929),new Location(w, 920,90,-929), new Location(w, 919,88,-930),new Location(w, 923, 89, -929));
    public PropertyPos docksOfficePos = new PropertyPos(docksOfficeI,new Location(w, 684, 72, -830), new Location(w, 682, 69, -832),new Location(w, 683, 70, -827),new Location(w, 684, 72, -826), new Location(w, 682, 69, -828),new Location(w, 683, 70, -831));
    public PropertyPos bunkerPos = new PropertyPos(bunkerI, new Location(w, 762,69,-599), new Location(w, 756,66,-601),new Location(w, 759, 58, -608), new Location(w, 760,60,-607), new Location(w, 758,57,-609),new Location(w, 759.5,67,-599.5));

    public PropertyPos[] allPropPoses = {motelPos,cheapApartmentPos,farmPos,usBankOfficePos,docksOfficePos,bunkerPos};

    public PropertySafe cheapApartmentSafe = new PropertySafe(cheapApartmentI, new Location(w,916, 92, -936), new Location(w, 914, 88, -938));
    public PropertySafe farmSafe = new PropertySafe(farmI, new Location(w,1038, 73, -694), new Location(w, 1036, 70, -697));
    public PropertySafe usBankOfficeSafe = new PropertySafe(usBankOfficeI, new Location(w,762, 16, -612), new Location(w, 756, 12, -615));
    public PropertySafe docksOfficeSafe = new PropertySafe(docksOfficeI, new Location(w,679, 71, -823), new Location(w, 678, 69, -826));
    public PropertySafe bunkerSafe = new PropertySafe(bunkerI, new Location(w,762, 16, -612), new Location(w, 756, 12, -615));
    
    public PropertySafe mbaBank1 = new PropertySafe("mbaBank", new Location(w, 855, 72, -893), new Location(w, 854, 68, -894), 300000);
    public PropertySafe mbaBank2 = new PropertySafe("mbaBank", new Location(w, 851, 72, -893), new Location(w, 850, 68, -894), 300000);

    public PropertySafe[] allPropSafes = {cheapApartmentSafe,farmSafe,usBankOfficeSafe,docksOfficeSafe,bunkerSafe};

    public PropertyStash cheapApartmentStash = new PropertyStash(cheapApartmentI, new Location(w, 824, 75, -927), new Location(w, 823, 72, -929),5,1);
    public PropertyStash farmStash = new PropertyStash(farmI, new Location(w, 1044, 73, -694), new Location(w, 1042, 70, -697), 27,1);
    public PropertyStash usBankOfficeStash = new PropertyStash(usBankOfficeI, new Location(w, 927, 92, -930), new Location(w, 926, 88, -934), 54,1);
    public PropertyStash docksOfficeStash = new PropertyStash(docksOfficeI, new Location(w, 687, 72, -823), new Location(w, 686, 69, -826), 27,3);

    public PropertyStash[] allPropStashes = {cheapApartmentStash,farmStash,usBankOfficeStash,docksOfficeStash};

    public Property cheapApartment = new Property(cheapApartmentI, cheapApartmentPos, cheapApartmentSafe, cheapApartmentStash);
    public Property farm = new Property(farmI,farmPos,farmSafe,farmStash);
    public Property usBankOffice = new Property(usBankOfficeI,usBankOfficePos,usBankOfficeSafe,usBankOfficeStash);
    public Property docksOffice = new Property(docksOfficeI,docksOfficePos,docksOfficeSafe,docksOfficeStash);

    public Property[] allProps = {cheapApartment,farm,usBankOffice,docksOffice};

    public ArrayList<Property> getOwnedProps(Player player) {
        Set<String> tags = player.getScoreboardTags();
        ArrayList<Property> ownedProps = new ArrayList<Property>();
        Property[] allProps = lang.allProps;
        for (Property prop : allProps) {
            String namespace = prop.getInfo().getNamespace();
            if (tags.contains("propOwned." + namespace)) {
                ownedProps.add(prop);
            }
        }
        return ownedProps;
    }
    
    public String cheapAptStashTitle = "Cheap Apartment Stash";
    public String USBankOfficeStashTitle = "US Bank Office Stash";
    public String docksOfficeStash1Title = "Docks Office Stash p1";
    public String docksOfficeStash2Title = "Docks Office Stash p2";
    public String docksOfficeStash3Title = "Docks Office Stash p3";
    public String farmStashTitle = "Farm Stash";

    public Component propAbilUsageError = Component.text("Invalid usage. Try using /pa to see available commands.", errorRed);

    public Component docksOfficeAbilOwnershipError = Component.text("You need to own 'Docks Office' to use this ability.", errorRed);
    public Component docksOfficeAbilDistanceError = Component.text("Go to the smooth stone at the back of the Docks Office to do this .", errorRed);

    public String USBankOfficePCTitle = "US Bank Office PC";
    public Component USBankOfficePCDistanceError = Component.text("Go to the smooth stone at the Computer in the US Bank Office to use this.", errorRed);
    public Component USBankOfficeAbilOwnershipError = Component.text("You need to own 'US Bank Office' to use this ability.", errorRed);
    
    public Component notEnoughMoney(int have, int need) {
        return Component.text("You don't have enough money. You need $" + (need-have) + " more.", errorRed);
    }

    public int bountyBase = 1000;
    public int bountyPriceFactor = 2;
    public Component bountyAppDisplayName = Component.text("Set Bounty", TextColor.color(0xFFA503));
    public Component bountyPlayerChoiceDisplayName = Component.text("Set Bounty", TextColor.color(0xFFA503));
    public Component bountyAppLore = Component.text("Sets a bounty on a specific player that will be awarded to their killer.");
    public Component bountyPlayerSkullLore1 = Component.text("Set a bounty on ");
    public String bountyChooseAmtWindow = "Choose Bounty Amount";
    public Component bountyChooseAmtLoreNotEnough = Component.text("Not enough $$$!");
    public Component bountyChooseAmtLore = Component.text("Click to set bounty.");
    public Component alreadyBoundError(String pName) {
        return Component.text("Player " + pName + " already has a bounty. Do /list to see player bounties.", errorRed);
    }
    public Component bountySetAlert(int size) {
        return Component.text("A bounty of $" + size + " has been set on you!", TextColor.color(0xFFA503));
    }

    public Component youAreNotInParty = Component.text("You are not in a party.", errorRed);
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
