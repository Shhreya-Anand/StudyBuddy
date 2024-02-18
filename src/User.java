import java.util.HashMap;
import java.util.Map;
public class User {
    private String name;
    private String avatar; // Path to avatar image or identifier for an icon
    private int exp; // Experience points
    private int coins; // Coins
    private Map<String, Integer> inventory; // Inventory to store item counts

    public User(String name, String avatar, int exp) {
        this.name = name;
        this.avatar = avatar;
        this.exp = exp;
        this.coins=coins;
        this.inventory = new HashMap<>(); // Initialize inventory
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }



    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void addItemToInventory(String item, int count) {
        inventory.put(item, inventory.getOrDefault(item, 0) + count);
    }

    public boolean removeItemFromInventory(String item, int count) {
        if (!inventory.containsKey(item) || inventory.get(item) < count) {
            return false; // Item not present or insufficient quantity
        }
        int newCount = inventory.get(item) - count;
        if (newCount > 0) {
            inventory.put(item, newCount);
        } else {
            inventory.remove(item); // Remove item if count drops to 0
        }
        return true;
    }
}
