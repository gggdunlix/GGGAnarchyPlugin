package ml.gggrealms.gggmcanarchy;

import org.bukkit.Location;

public class PropertyStash {
    private PropertyInfo propInfo;
    private Location stash1;
    private Location stash2;
    private int size;
    private int pages;
    private String name;
    private Lang lang;
    public PropertyStash(PropertyInfo propI, Location sa1, Location sa2, int stashSize, int pageCount) {
        propInfo = propI;
        stash1 = sa1;
        stash2 = sa2;
        size = stashSize;
        pages = pageCount;
        name = propInfo.getNamespace();
    }
    public PropertyStash(String namespace, Location sa1, Location sa2, int stashSize, int pageCount) {
        propInfo = null;
        stash1 = sa1;
        stash2 = sa2;
        size = stashSize;
        pages = pageCount;
        name = namespace;
    }
    public PropertyInfo getPropInfo() {
        return propInfo;
    }
    public Location getPos1() {
        return stash1;
    }
    public Location getPos2() {
        return stash2;
    }
    public int getSize() {
        return size;
    }
    public int getPages() {
        return pages;
    }
    public String getNamespace() {
        return name;
    }
}
