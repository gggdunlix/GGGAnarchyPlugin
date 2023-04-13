package ml.gggrealms.gggmcanarchy.Properties;

import ml.gggrealms.gggmcanarchy.Lang;

import java.util.Locale;

public class PropertyInfo {
    private int cost;
    private int upkeep;
    private String propertyName;
    private String[] unlockedClasses;
    private String nameSp;
    private Lang lang;
    public PropertyInfo(int cost, String propertyName, String[] unlockedClasses, int upkeep) {
        this.cost = cost;
        this.upkeep = upkeep;
        this.propertyName = propertyName;
        this.unlockedClasses = unlockedClasses;
        String nameSpacesRemoved = propertyName.replace(" ", "");
        nameSpacesRemoved = nameSpacesRemoved.substring(0,1).toLowerCase() + nameSpacesRemoved.substring(1);
        nameSp = nameSpacesRemoved;
    }
    public PropertyInfo(int cost, String propertyName, String[] unlockedClasses) {
        this.cost = cost;
        upkeep = (int) (cost * lang.upkeepFactor);
        this.propertyName = propertyName;
        this.unlockedClasses = unlockedClasses;
        String nameSpacesRemoved = propertyName.replace(" ", "");
        nameSpacesRemoved = nameSpacesRemoved.substring(0,1).toLowerCase() + nameSpacesRemoved.substring(1);
        nameSp = nameSpacesRemoved;
    }
    public PropertyInfo(int cost, String propertyName, String[] unlockedClasses, String namespace) {
        this.cost = cost;
        upkeep = (int) (cost * lang.upkeepFactor);
        this.propertyName = propertyName;
        this.unlockedClasses = unlockedClasses;
        nameSp = namespace;
    }
    public String getNamespace() {
        return nameSp;
    }
    public int getCost() {
        return cost;
    }
    public int getUpkeep() {
        return upkeep;
    }
    public String getName() {
        return propertyName;
    }
    public String[] getClasses(){
        return unlockedClasses;
    }

}
