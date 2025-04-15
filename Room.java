import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private Map<String, Room> exits;
    private Monster monster;
    private boolean hasPotion;



    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.monster = null;
        this.hasPotion = false;
    }

    // --- Getters ---
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Room getExit(String direction) { return exits.get(direction.toLowerCase()); }
    public Map<String, Room> getExits() { return exits; }
    public Monster getMonster() { return monster; }
    public boolean hasPotion() { return hasPotion; }

    // --- Setters ---
    public void setExit(String direction, Room neighbor) { exits.put(direction.toLowerCase(), neighbor); }
    public void setMonster(Monster monster) { this.monster = monster; }
    public void setHasPotion(boolean hasPotion) { this.hasPotion = hasPotion; }
    public void removePotion() { this.hasPotion = false; }

    // --- Output Methods ---
    public String getLookInfo() {
        StringBuilder info = new StringBuilder();
        info.append("【房間】：").append(name).append("\n");
        info.append("【描述】：").append(description).append("\n");

        if (monster != null && monster.isAlive()) {
            info.append("【怪物】：").append(monster.toString()).append("\n");
        } else if (monster != null && !monster.isAlive()) {
             info.append("【怪物】：這裡躺著 ").append(monster.getName()).append(" 的殘骸。\n");
        } else {
             info.append("【怪物】：這裡沒有看到怪物。\n");
        }

        if (hasPotion) {
            info.append("【道具】：治療藥水\n");
        } else {
             info.append("【道具】：這裡沒有看到特別的道具。\n");
        }

        if (!exits.isEmpty()) {
            info.append("【出口】：");
            info.append(exits.keySet().stream().collect(Collectors.joining(", ")));
            info.append("\n");
        } else {
            info.append("【出口】：沒有明顯的出口。\n");
        }
        return info.toString();
    }

     public String getEntryInfo() {
        StringBuilder info = new StringBuilder();
        info.append("你目前在：").append(name).append("\n");

        if (monster != null && monster.isAlive()) {
            info.append("你看到：").append(monster.toString()).append("\n");
        } else if (monster != null && !monster.isAlive()) {
             info.append("你看到 ").append(monster.getName()).append(" 的殘骸躺在這裡。\n");
        } else {
             info.append("這裡一片寂靜。\n");
        }

         if (!exits.isEmpty()) {
             info.append("可用方向：");
             info.append(exits.keySet().stream().collect(Collectors.joining(", ")));
             info.append("\n");
         }

        if (hasPotion) {
            info.append("💧 有一瓶發光的治療藥水躺在地上。\n");
        }
        return info.toString();
    }
}