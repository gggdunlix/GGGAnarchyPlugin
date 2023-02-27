package ml.gggrealms.gggmcanarchy;
import org.bukkit.*;
import org.bukkit.inventory.*;
//Not at all complete, gonna be shittons of errors!
public static String[] playerInventoryToBase64(PlayerInventory playerInventory) throws IllegalStateException {
    	//get the main content part, this doesn't return the armor
    	String content = toBase64(playerInventory);
    	String armor = itemStackArrayToBase64(playerInventory.getArmorContents());
    	
    	return new String[] { content, armor };
    }

public static Inventory fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
    
            // Read the serialized inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }
            
            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
