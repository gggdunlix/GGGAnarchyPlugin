package ml.gggrealms.gggmcanarchy;

import org.bukkit.Location;

public class PropertySafe {
    private PropertyInfo propInfo;
    private Location safe1;
    private Location safe2;
    private int max;
    private String name;
    private Lang lang;
    public PropertySafe(PropertyInfo propI, Location sa1, Location sa2) {
        propInfo = propI;
        safe1 = sa1;
        safe2 = sa2;
        max = (int) (propInfo.getCost() * lang.maxSafeFactor);
        name = propInfo.getNamespace();
    }
    public PropertySafe(String namespace, Location sa1, Location sa2, int maximum) {
        safe1 = sa1;
        safe2 = sa2;
        max = maximum;
        name = namespace;
    }
    public PropertyInfo getPropInfo() {
        return propInfo;
    }
    public Location getSafe1() {
        return safe1;
    }
    public Location getSafe2() {
        return safe2;
    }
    public int getMax() {
        return max;
    }

}
