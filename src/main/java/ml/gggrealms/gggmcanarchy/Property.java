package ml.gggrealms.gggmcanarchy;

public class Property {
    private int cost;
    private int upkeep;
    private String propertyName;
    private int maxSafe;
    private String[] unlockedClasses;
    private int stashSize;
    private Lang lang;
    private int stashPages = 1;
    public Property(int cost, int stashSize, String propertyName, String[] unlockedClasses, int upkeep, int maxSafe) {
        this.cost = cost;
        this.upkeep = upkeep;
        this.propertyName = propertyName;
        this.maxSafe = maxSafe;
        this.unlockedClasses = unlockedClasses;
        this.stashSize = stashSize;
    }
    public Property(int cost, int stashSize, String propertyName, String[] unlockedClasses, int upkeep, int maxSafe, int stashPages) {
        this.cost = cost;
        this.upkeep = upkeep;
        this.propertyName = propertyName;
        this.maxSafe = maxSafe;
        this.unlockedClasses = unlockedClasses;
        this.stashSize = stashSize;
        this.stashPages = stashPages;
    }
    public Property(int cost, int stashSize, String propertyName, String[] unlockedClasses) {
        this.cost = cost;
        upkeep = (int) (cost * lang.upkeepFactor);
        this.propertyName = propertyName;
        maxSafe = (int) (cost * lang.maxStashFactor);
        this.unlockedClasses = unlockedClasses;
        this.stashSize = stashSize;
    }
    public Property(int cost, int stashSize, String propertyName, String[] unlockedClasses, int stashPages) {
        this.cost = cost;
        upkeep = (int) (cost * lang.upkeepFactor);
        this.propertyName = propertyName;
        maxSafe = (int) (cost * lang.maxStashFactor);
        this.unlockedClasses = unlockedClasses;
        this.stashSize = stashSize;
        this.stashPages = stashPages;
    }
    public int getStashPages() {
        return stashPages;
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
    public int getSafe() {
        return maxSafe;
    }
    public String[] getClasses(){
        return unlockedClasses;
    }
    public int getStashSize() {
        return stashSize;
    }

}
