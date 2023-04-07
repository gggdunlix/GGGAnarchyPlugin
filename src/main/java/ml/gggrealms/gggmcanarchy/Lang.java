package ml.gggrealms.gggmcanarchy;

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
    public double maxStashFactor = 2.5;

    World w = Bukkit.getWorld("world");

    public Property motel = new Property(5000, 0, "Motel", new String[]{"classCrackhead", "classEmployee"});
    public Property cheapApartment = new Property(20000, 5, "Cheap Apartment", new String[]{"classThug"});
    public Property farm = new Property(150000, 27, "Farm", new String[]{"classHorseman"});
    public Property opalHotel = new Property(200000, 54, "Opal Hotel", new String[]{"classDrugSlinger", "classGunsmith"});
    public Property usBankOffice = new Property(300000, 54, "US Bank Office", new String[]{"classBankRobber"});
    public Property rooseveltResidence = new Property(500000, 27, "Roosevelt Residence", new String[]{"classInvestor", "classBanker"}, 3);
    public Property docksOffice = new Property(650000, 27, "Opal Hotel", new String[]{"classDrugSlinger", "classGunsmith"}, 3);
    public Property bunker = new Property(1000000, 54, "Bunker", new String[]{"classHeister"}, 2);
    public Property casino = new Property(50000000, 54, "Casino", new String[]{"classPilot", "classAgent"}, 3);

    public Property[] allProps = new Property[]{motel,cheapApartment,farm,opalHotel,usBankOffice,rooseveltResidence,docksOffice,bunker,casino}

    public PropertyPos motelPos = new PropertyPos(motel, new Location(w, 00,00,00),new Location(w, 00,00,00),new Location(w, 00,00,00),new Location(w, 00,00,00),new Location(w, 00,00,00),new Location(w, 00,00,00));
    public PropertyPos cheapApartmentPos = new PropertyPos(cheapApartment,  new Location(w, 830, 76, -933), new Location(w, 829, 72, -934),new Location(w, 826, 73, -933),new Location(w, 827, 74, -933), new Location(w, 826, 72, -934),new Location(w, 830, 73, -933));

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



}
