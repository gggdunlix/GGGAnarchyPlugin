package ml.gggrealms.gggmcanarchy;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.UUID;
public class Skull {
  private UUID skullUUID;
  private Player player;
  private Location loc;
  private UUID pUUID;
  private int moneyAmount;
  private int level;
  public Skull() {}
  public Skull(Player owner, Location location, int money, int lvl) {
    skullUUID = UUID.randomUUID();
    player = owner;
    loc = location;
    pUUID = owner.identity().uuid();
    moneyAmount = money;
    level = lvl;
  }
  public void registerSkull() {
    FileConfiguration cfg = AnarchyPlugin.plugin.getConfigFile();
    cfg.set("skulls." + skullUUID + ".owner", player.getName());
    cfg.set("skulls." + skullUUID + ".location", loc);
    cfg.set("skulls." + skullUUID + ".amount", moneyAmount);
    cfg.set("skulls." + skullUUID + ".level", player.getName());
  }
}
