package ml.gggrealms.gggmcanarchy.Properties;

public class Property {
    private PropertyInfo info;
    private PropertyPos pos;
    private PropertySafe safe;
    private String name;
    private PropertyStash stash;
    public Property(PropertyInfo propInfo, PropertyPos position, PropertySafe propSafe, PropertyStash propStash, String namespace) {
        info = propInfo;
        pos = position;
        safe = propSafe;
        name = namespace;
        stash = propStash;
    }
    public Property(PropertyInfo propInfo, PropertyPos position, PropertySafe propSafe, PropertyStash propStash) {
        info = propInfo;
        pos = position;
        safe = propSafe;
        name = propInfo.getNamespace();
        stash = propStash;
    }
    public PropertyInfo getInfo(){
        return info;
    }
    public PropertyPos getPos(){
        return pos;
    }
    public PropertySafe getSafe(){
        return safe;
    }
    public String namespace(){
        return name;
    }
    public PropertyStash getStash() {
        return stash;
    }
}
