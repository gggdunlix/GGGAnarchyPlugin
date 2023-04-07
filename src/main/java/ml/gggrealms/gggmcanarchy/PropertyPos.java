package ml.gggrealms.gggmcanarchy;

import org.bukkit.Location;

public class PropertyPos extends Property {
    private Property property;
    private Location enter1;
    private Location enter2;
    private Location exit1;
    private Location exit2;
    private Location enterTPto;
    private Location exitTPto;

    public PropertyPos(Property prop, Location e1, Location e2, Location eTP, Location x1, Location x2, Location xTP) {
        super(prop.getCost(), prop.getStashSize(), prop.getName(), prop.getClasses());
        property = prop;
        enter1 = e1;
        enter2 = e2;
        exit1 = x1;
        exit2 = x2;
        enterTPto = eTP;
        exitTPto = xTP;
    }
    public Location getE1() {
        return enter1;
    }
    public Location getE2() {
        return enter2;
    }
    public Location getX1() {
        return exit1;
    }
    public Location getX2() {
        return exit2;
    }
    public Property getProperty() {
        return property;
    }
    public Location getEnterTP() {
        return enterTPto;
    }
    public Location getExitTP() {
        return exitTPto;
    }
}