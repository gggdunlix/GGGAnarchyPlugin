package ml.gggrealms.gggmcanarchy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;

public class Property {
    private PropertyInfo info;
    private PropertyPos pos;
    private PropertySafe safe;
    private String name;
    public Property(PropertyInfo propInfo, PropertyPos position, PropertySafe propSafe, String namespace) {
        info = propInfo;
        pos = position;
        safe = propSafe;
        name = namespace;
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
}
