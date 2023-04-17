package ml.gggrealms.gggmcanarchy.Properties;

import ml.gggrealms.gggmcanarchy.Lang;
import org.bukkit.Location;

public class PropertySafe {
    private PropertyInfo propInfo;
    private Location safe1;
    private Location safe2;
    private int max;
    private String name;
    private boolean[] safeParameters = {true,true,true,false};
    private String safeName;
    private Lang lang;
    public PropertySafe(PropertyInfo propI, Location sa1, Location sa2) {
        propInfo = propI;
        safe1 = sa1;
        safe2 = sa2;
        max = (int) (propInfo.getCost() * lang.maxSafeFactor);
        name = propInfo.getNamespace();
        safeName = propInfo.getName() + " Safe";
    }
    public PropertySafe(String namespace, Location sa1, Location sa2, int maximum, String safeName) {
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
    public String getNamespace() { return name; }
    public void setParameters(boolean depo, boolean with, boolean sell, boolean send) {
        safeParameters[0] = depo;
        safeParameters[1] = with;
        safeParameters[2] = sell;
        safeParameters[3] = send;
    }
    public String getName() {
        return safeName;
    }
    public boolean[] getSafeParameters() {
        return safeParameters;
    }

}
