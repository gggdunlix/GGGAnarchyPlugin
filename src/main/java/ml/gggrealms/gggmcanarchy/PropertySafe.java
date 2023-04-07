package ml.gggrealms.gggmcanarchy;

import org.bukkit.Location;

public class PropertySafe extends Property {
    private Property property;
    private Location safe1;
    private Location safe2;
    private Location stash1;
    private Location stash2;
    public PropertySafe(Property prop, Location sa1, Location sa2, Location st1, Location st2) {
        super(prop.getCost(), prop.getStashSize(), prop.getName(), prop.getClasses(), prop.getUpkeep(), prop.getSafe(), prop.getStashPages());
        property = prop;
        safe1 = sa1;
        safe2 = sa2;
        stash1 = st1;
        stash2 = st2;
    }
}
